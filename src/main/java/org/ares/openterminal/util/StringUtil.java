package org.ares.openterminal.util;

import java.util.Arrays;

public class StringUtil {

    public static String getCommandName(String name) {
        if (!name.contains("Command")) {
            return name;
        }

        return Arrays.toString(name.split("Command"))
                .replace("[", "")
                .replace("]", "");

    }

    public static String getBossName(String name) {
        if (!name.contains("Boss")) {
            return name;
        }

        return Arrays.toString(name.split("Boss"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String getNPCName(String name) {
        if (!name.contains("NPC")) {
            return name;
        }

        return Arrays.toString(name.split("NPC"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String getSkillName(String name) {
        if (!name.contains("Skill")) {
            return name;
        }

        return Arrays.toString(name.split("Skill"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String getRankName(String name) {
        if (!name.contains("Rank")) {
            return name;
        }

        return Arrays.toString(name.split("Rank"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String getQuestName(String name) {
        if (!name.contains("Quest")) {
            return name;
        }

        return Arrays.toString(name.split("Quest"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String getClassName(String name) {
        if (!name.contains("Class")) {
            return name;
        }

        return Arrays.toString(name.split("Class"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String getBlockToolName(String name) {
        if (!name.contains("BlockTool")) {
            return name;
        }

        return Arrays.toString(name.split("BlockTool"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String getToolName(String name) {
        if (!name.contains("Tool")) {
            return name;
        }

        return Arrays.toString(name.split("Tool"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String getRocketName(String name) {
        if (!name.contains("Rocket")) {
            return name;
        }

        return Arrays.toString(name.split("Rocket"))
                .replace("[", "")
                .replace("]", "");
    }


}
