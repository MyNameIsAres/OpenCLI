package org.ares.openterminal.foundation.impl;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.ares.openterminal.util.VelocityBuilder;
import org.ares.openterminal.util.YamlHandler;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

@Command(name = "create:enchantment")
public class CreateSimpleEnchantment implements Runnable{

    @Parameters()
    private String name;

    final static String PROPERTY_KEY = "enchant_location";

    VelocityContext velocityContext = new VelocityContext();

    public void build() {
        VelocityEngine engine = new VelocityBuilder().createVelocityEngineFoundation();

        Map<String, Object> data = new YamlHandler().fetchInformation();
        String projectPath = data.get("project-path").toString();

        String packageName = projectPath + data.get(PROPERTY_KEY);
        packageName = packageName.replace("/", ".");

        velocityContext.put("PACKAGE_NAME", packageName);
        velocityContext.put("NAME", name);

        if (data.get(PROPERTY_KEY) == null || data.get(PROPERTY_KEY).toString().isEmpty()) {

            System.out.println("Couldn't load property named " + PROPERTY_KEY + "or any of its corresponding values!");
            return;
        }

        String targetLocation = "src/main/java/" + projectPath + data.get(PROPERTY_KEY) + "/" + name;


        Writer writer = null;

        try {
            writer = new FileWriter(targetLocation + ".java");
        } catch (IOException ioException) {

            System.out.println(ioException.getMessage());

            System.out.println("An error occurred!!!");
        }

        Template template = engine.getTemplate("SimpleEnchantment.vm");

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
