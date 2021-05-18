package org.ares.openterminal.foundation.impl.command;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.ares.openterminal.Buildable;
import org.ares.openterminal.util.StringUtil;
import org.ares.openterminal.util.VelocityBuilder;
import org.ares.openterminal.util.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import java.io.*;

@Command(name = "make:command")
public class CreateSimpleCommand implements Runnable, Buildable {

    @Parameters()
    private String name;

    final static String PROPERTY_KEY = "command_location";

    final static String TEMPLATE = "\\command\\SimpleCommandTemplate.vm";

    final YamlHandler yamlHandler = new YamlHandler();

    final String projectPath = yamlHandler.getProjectPath();
    final String packageName = yamlHandler.getPackageName(PROPERTY_KEY);

    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        new StringUtil();
        String commandName = StringUtil.getCommandName(name);

        context.put("PACKAGE_NAME", packageName);
        context.put("CLASS_NAME", name);
        context.put("NAME", commandName);

        return context;
    }

    // TODO: Extract to class??
    public Writer createFileWriter() {
        try {
            return new FileWriter(yamlHandler.getTargetLocation(projectPath, yamlHandler.getKeyValue(PROPERTY_KEY), name) + ".java");
        } catch (IOException ioException) {

            System.out.println(ioException.getMessage());
        }

        return null;
    }

    public void flushFileWriter(Writer writer) {
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTemplate(Writer writer) {
        VelocityEngine engine = new VelocityBuilder().createVelocityEngineFoundation();
        Template template = engine.getTemplate(TEMPLATE);

        engine.mergeTemplate(template.getName(), "UTF-8", buildContext(), writer);
    }

    public void writeToTemplate() {
        final Writer writer = createFileWriter();

        createTemplate(writer);
        flushFileWriter(writer);
    }

    public void buildTemplate() {
        buildContext();
        writeToTemplate();
    }


    @Override
    public void run() {
        buildTemplate();
    }
}
