package org.ares.openterminal.foundation.impl.orion.boss;

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
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@Command(name = "make:npc")
public class CreateNPC implements Runnable, Buildable {

    @Parameters()
    private String name;

    final static String PROPERTY_KEY = "npc_location";

    final static String TEMPLATE = "\\Orion\\Boss\\NpcTemplate.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    @Override
    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", name);
        context.put("NAME", StringUtil.getRankName(name));

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
