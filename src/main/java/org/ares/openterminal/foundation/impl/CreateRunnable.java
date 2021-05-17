package org.ares.openterminal.foundation.impl;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.ares.openterminal.util.VelocityBuilder;
import org.ares.openterminal.util.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

@Command(name = "make:runnable")
public class CreateRunnable implements Runnable{

    @Parameters()
    String name;

    final static String PROPERTY_KEY = "runnable_location";

    final static String TEMPLATE_NAME = "RunnableTemplate.vm";

    public void build() {

       VelocityEngine engine = new VelocityBuilder().createVelocityEngineFoundation();

        VelocityContext velocityContext = new VelocityContext();
        Map<String, Object> data = new YamlHandler().fetchInformation();
        String projectPath = data.get("project-path").toString();

        // TODO: Transform this into a String method?
        String packageName = projectPath + data.get(PROPERTY_KEY);
        packageName = packageName.replace("/", ".");

        // TODO: Make this a standard method that takes in a context?
        velocityContext.put("PACKAGE_NAME", packageName);
        velocityContext.put("NAME", name);


        if (data.get(PROPERTY_KEY) == null || data.get(PROPERTY_KEY).toString().isEmpty()) {
            System.out.println("Couldn't load property named " + PROPERTY_KEY + "or any of its corresponding values!");
            return;
        }

        // TODO: Maybe make a method that returns the string we use?
        String targetLocation = "src/main/java/" + projectPath + data.get(PROPERTY_KEY) + "/" + name;


        Writer writer = null;

        try {
            writer = new FileWriter(targetLocation + ".java");
        } catch (IOException ioException) {

            System.out.println(ioException.getMessage());

            System.out.println("An error occurred!!!");
        }

        Template template = engine.getTemplate(TEMPLATE_NAME);

        engine.mergeTemplate(template.getName(), "UTF-8", velocityContext, writer);

        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        build();
    }
}
