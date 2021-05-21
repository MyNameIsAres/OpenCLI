package org.ares.openterminal.foundation.impl.conversation;

import org.apache.velocity.VelocityContext;
import org.ares.openterminal.Buildable;
import org.ares.openterminal.util.TemplateBuilder;
import org.ares.openterminal.util.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.Writer;

@Command(name = "make:conversation", aliases = {"conv"})
public class CreateSimpleConversation implements Runnable, Buildable {

    @Parameters()
    private String name;

    final static String PROPERTY_KEY = "command_location";

    final static String TEMPLATE = "\\conversation\\SimpleConversationTemplate.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", name);

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
