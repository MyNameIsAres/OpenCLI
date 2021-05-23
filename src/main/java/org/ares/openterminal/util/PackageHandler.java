package org.ares.openterminal.util;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PackageHandler {

    // TODO To be refactored later
    public static String createPackage(String name, String subPackageName, String propertyKey)  {
        YamlHandler yamlHandler = new YamlHandler();
        try {
            if (subPackageName.isEmpty()) {
                String packageName = StringUtil.getCommandGroupName(name).toLowerCase();

                System.out.println("First one runs?");

                Files.createDirectory(Paths.get(yamlHandler.getCommandGroupLocation(yamlHandler.getProjectPath(), yamlHandler.getKeyValue(propertyKey))
                        + packageName));

                return packageName;
            } else {
                Files.createDirectory(Paths.get(yamlHandler.getCommandGroupLocation(yamlHandler.getProjectPath(), yamlHandler.getKeyValue(propertyKey))
                        + subPackageName));

                System.out.println("First one runs?");


                return subPackageName;
            }

        } catch (IOException ioException) {
            System.out.println("An IOException occurred");
            ioException.printStackTrace();
        }
        return "command";
    }

    public static String createPackageSubCommand(String name, String subPackageName, String propertyKey)  {
        YamlHandler yamlHandler = new YamlHandler();
        try {
            if (subPackageName.isEmpty()) {
                String packageName = StringUtil.getCommandName(name).toLowerCase();

                System.out.println("First one runs?");

                Files.createDirectory(Paths.get(yamlHandler.getCommandGroupLocation(yamlHandler.getProjectPath(), yamlHandler.getKeyValue(propertyKey))
                        + packageName));

                return packageName;
            } else {
                try {
                    Files.createDirectory(Paths.get(yamlHandler.getCommandGroupLocation(yamlHandler.getProjectPath(), yamlHandler.getKeyValue(propertyKey))
                            + subPackageName));

                    return subPackageName;
                } catch (FileAlreadyExistsException exception) {
                    return subPackageName;
                }

            }

        } catch (IOException ioException) {
            System.out.println("An IOException occurred");
            ioException.printStackTrace();
        }
        return "command";
    }
}
