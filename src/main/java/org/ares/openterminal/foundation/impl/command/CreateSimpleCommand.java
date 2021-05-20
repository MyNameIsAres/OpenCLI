package org.ares.openterminal.foundation.impl.command;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.ares.openterminal.Buildable;
import org.ares.openterminal.util.StringUtil;
import org.ares.openterminal.util.TemplateBuilder;
import org.ares.openterminal.util.VelocityBuilder;
import org.ares.openterminal.util.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import java.io.*;

@Command(name = "make:command")
public class CreateSimpleCommand implements Runnable {

    @Parameters()
    private String name;

    final static String PROPERTY_KEY = "command_location";

    final static String TEMPLATE = "\\command\\SimpleCommandTemplate.vm";

    final YamlHandler yamlHandler = new YamlHandler();

    final String packageName = yamlHandler.getPackageName(PROPERTY_KEY);

    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", packageName);
        context.put("CLASS_NAME", name);
        context.put("NAME", StringUtil.getCommandName(name));

        return context;
    }

    @Override
    public void run() {

        TemplateBuilder templateBuilder = new TemplateBuilder();
        Writer writer = templateBuilder.createFileWriter(PROPERTY_KEY, name);
        templateBuilder.createTemplate(writer, TEMPLATE, buildContext());
        templateBuilder.flushFileWriter(writer);

    }
}
