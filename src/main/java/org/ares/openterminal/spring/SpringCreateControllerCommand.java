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

@Command(name = "make:controller")
public class SpringCreateControllerCommand implements Runnable {


    @Parameters()
    String name;

    @Option(names = "type")
    String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    VelocityEngine velocityEngine = new VelocityEngine();
    VelocityContext velocityContext = new VelocityContext();
    Map<String, Object> data = new YamlHandler().fetchInformation();

    public void build() {
        final String CLASSNAME = name;


        String projectPath = data.get("project-path").toString();

        String packageName = projectPath + data.get("controller_location");
        packageName = packageName.replace("/", ".");

        velocityContext.put("PACKAGE_NAME", packageName);
        velocityContext.put("NAME", name);

        if(type.equals("rest")) {
            velocityContext.put("TYPE", "RestController");
        } else {
            velocityContext.put("TYPE", "Controller");
        }

        if (data.get("controller_location") == null) {

            System.out.println("Couldn't load property named \"controller_location\" or any of its corresponding values!");
            return;
        }

        String targetLocation = "src/main/java/" + projectPath + data.get("controller_location") + "/" + CLASSNAME;

        velocityEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, System.getProperty("user.home") + "\\OpenTerminal\\spring-templates");
        velocityEngine.init();

        Writer writer = null;

        try {
            writer = new FileWriter(targetLocation + ".java");
        } catch (IOException ioException) {

            System.out.println(ioException.getMessage());

            System.out.println("An error occurred!!!");
        }

        Template template = velocityEngine.getTemplate("controller.vm");

        velocityEngine.mergeTemplate(template.getName(), "UTF-8", velocityContext, writer);

        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run() {
        this.build();
    }
}
