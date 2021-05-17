package org.ares.openterminal.util;

import java.util.Arrays;

public class StringUtil {

    public String getCommandName(String name) {
        if (!name.contains("Command")) {
            return name;
        }

        return Arrays.toString(name.split("Command"))
                .replace("[", "")
                .replace("]", "");

    }
}
