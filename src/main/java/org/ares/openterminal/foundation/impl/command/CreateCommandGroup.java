package org.ares.openterminal.foundation.impl.command;

import org.apache.velocity.VelocityContext;
import org.ares.openterminal.Buildable;
import org.ares.openterminal.util.PackageHandler;
import org.ares.openterminal.util.StringUtil;
import org.ares.openterminal.util.TemplateBuilder;
import org.ares.openterminal.util.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import java.io.Writer;

@Command(name = "make:commandgroup", aliases = "make:cmdgroup")
public class CreateCommandGroup implements Runnable, Buildable {

    @Parameters
    private String name;

    @Parameters(defaultValue = "")
    private final String subPackageName = "";


    final static String PROPERTY_KEY = "command_location";

    final static String TEMPLATE = "\\command\\SimpleCommandGroupTemplate.vm";

    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", "");
        context.put("CLASS_NAME", StringUtil.addCommandGroupLabel(name));

        return context;
    }

    // TODO To be refactored later
    @Override
    public void run() {
        final String packageName =  PackageHandler.createPackage(name, subPackageName, PROPERTY_KEY);
        TemplateBuilder templateBuilder = new TemplateBuilder();
        VelocityContext context = buildContext();
        context.put("PACKAGE_NAME", new YamlHandler().getCommandGroupPackageName(PROPERTY_KEY, packageName));

        Writer writer = templateBuilder.createFileWriterCommandGroup(PROPERTY_KEY, packageName, name);
        templateBuilder.createTemplate(writer, TEMPLATE, context);
        templateBuilder.flushFileWriter(writer);
    }

}
