package org.ares.openterminal.spring;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.ares.openterminal.util.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

@Command(name = "make:model")
public class SpringCreateModelCommand implements Runnable {

    @Parameters()
    private String name;

    @Option(names = "-args")
    String option;

    VelocityEngine velocityEngine = new VelocityEngine();
    VelocityContext velocityContext = new VelocityContext();

    private void handleArguments(String options) {
        if (options.isEmpty()) {
            return;
        }

        if (options.length() == 1) {

            loadArgument(options);
        }

        if (options.length() > 1) {
            String[] optionsArray = options.split("");


            for (String item : optionsArray) {
                loadArgument(item);
            }
        }

    }

    private void loadArgument(String item) {
        if (item.equals("a")) {
            System.out.println("Logic to create all options..");
        }

        if (item.equals("c")) {
            System.out.println("Logic to create controller..");

            SpringCreateControllerCommand springCreateControllerCommand = new SpringCreateControllerCommand();
            springCreateControllerCommand.setName(name);
            springCreateControllerCommand.build();


        }

        if (item.equals("r")) {
            System.out.println("Logic to create repository..");

        }

        if (item.equals("s")) {
            System.out.println("Logic to create service..");

        }
    }

    @Override
    public void run() {

        handleArguments(option);

        final String CLASSNAME = name;

        Map<String, Object> data = new YamlHandler().fetchInformation();
        String projectPath = data.get("project-path").toString();

        String packageName = projectPath + data.get("model_location");
        packageName = packageName.replace("/", ".");

        velocityEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, System.getProperty("user.home") + "\\OpenTerminal\\spring-templates\\");
        velocityEngine.init();

        velocityContext.put("PACKAGE_NAME", packageName);
        velocityContext.put("NAME", name);

        if (data.get("command_location") == null) {

            System.out.println("Couldn't load property named \"model_location\" or any of its corresponding values!");
            return;
        }

        String targetLocation = "src/main/java/" + projectPath + data.get("model_location") + "/" + CLASSNAME;


        Writer writer = null;

        try {
            writer = new FileWriter(targetLocation + ".java");
        } catch (IOException ioException) {

            System.out.println(ioException.getMessage());

            System.out.println("An error occurred!!!");
        }

        Template template = velocityEngine.getTemplate("model.vm");

        velocityEngine.mergeTemplate(template.getName(), "UTF-8", velocityContext, writer);

        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
