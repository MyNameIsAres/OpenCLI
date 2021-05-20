package org.ares.openterminal.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class TemplateBuilder {

    YamlHandler yamlHandler = new YamlHandler();


    public Writer createFileWriter( String propertyKey, String name) {
        try {
            return new FileWriter(yamlHandler.getTargetLocation(yamlHandler.getProjectPath(), yamlHandler.getKeyValue(propertyKey), name) + ".java");
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

    public void createTemplate(Writer writer, String template, VelocityContext context) {
        VelocityEngine engine = new VelocityBuilder().createVelocityEngineFoundation();
        Template templateName = engine.getTemplate(template);

        engine.mergeTemplate(templateName.getName(), "UTF-8", context, writer);
    }

    public void buildTemplate() {

    }

}
