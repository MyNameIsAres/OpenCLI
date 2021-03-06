package org.ares.openterminal.spring;

import org.apache.velocity.VelocityContext;
import org.ares.openterminal.Buildable;
import org.ares.openterminal.util.TemplateBuilder;
import org.ares.openterminal.util.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import java.io.Writer;

@Command(name = "make:model")
public class SpringCreateModelCommand implements Runnable, Buildable {

    @Parameters()
    private String name;

    @Parameters(defaultValue = "")
    private String options;

    final static String PROPERTY_KEY = "model_location";

    final static String TEMPLATE = "\\Model.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);


    @Override
    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", name);
        context.put("NAME", name);

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
