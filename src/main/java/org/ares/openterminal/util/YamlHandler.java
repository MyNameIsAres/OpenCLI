package org.ares.openterminal.util;

import org.ares.openterminal.exceptions.KeyValueNotFoundException;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class YamlHandler {

    final static String PREFIX = "src/main/java/";

    public Map<String, Object> fetchInformation() {

        InputStream inputStream;
        Map<String, Object> data;
        try {
            inputStream = new FileInputStream(
                    "op-config.yml"
            );

            Yaml yaml = new Yaml();
            data = yaml.load(inputStream);

            return data;

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        
        return null;
    }

    public String getProjectPath() {
        verifyKey("project-path");

        return fetchInformation().get("project-path").toString();
    }

    public String getKeyValue(String key) {

        return fetchInformation().get(key).toString();
    };

    public void verifyKey(String key)  {
        if (this.getKeyValue(key) == null || this.getKeyValue(key).isEmpty()) {

        }

    }

    public String getTargetLocation(final String projectPath, final String key, final String name) {
        return  PREFIX + projectPath + key + "/" + name;
    }

    public String getPackageName(String key) {
        String packageName = this.getProjectPath() + this.getKeyValue(key).replace("/", ".");
        packageName = packageName.replace("/", ".");

        return packageName;
    }
}
