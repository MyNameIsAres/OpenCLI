package org.ares.openterminal.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileBuilder {

    public Writer createFileWriter(YamlHandler yamlHandler, String projectPath, String propertyKey, String name) {
        try {
            return new FileWriter(yamlHandler.getTargetLocation(projectPath, yamlHandler.getKeyValue(propertyKey), name) + ".java");
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
}
