package org.ares.openterminal.util.spring;

public class SpringModelUtil {

    private void handleArguments(String options) {
        if (options.isEmpty()) {
            return;
        }

        if (options.length() == 1) {
            loadArgument(options);
        }

        if (options.length() > 1) {
            String[] optionsArray = options.split("");

            for (String item : optionsArray) {
                loadArgument(item);
            }
        }

    }

    // TODO: Convert to a switch
    private void loadArgument(String item) {
        if (item.equals("a")) {
            System.out.println("Logic to create all options..");
        }

        if (item.equals("c")) {
            System.out.println("Logic to create controller..");
//
//            CreateControllerCommand springCreateControllerCommand = new CreateControllerCommand();
//            springCreateControllerCommand.setName(name);
//            springCreateControllerCommand.build();


        }

        if (item.equals("r")) {
            System.out.println("Logic to create repository..");

        }

        if (item.equals("s")) {
            System.out.println("Logic to create service..");

        }
    }

}
