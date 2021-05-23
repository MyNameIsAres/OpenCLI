package org.ares.openterminal;

import org.ares.openterminal.foundation.impl.CreateSimpleEnchantment;
import org.ares.openterminal.foundation.impl.command.CreateCommandGroup;
import org.ares.openterminal.foundation.impl.command.CreateSimpleCommand;
import org.ares.openterminal.foundation.impl.command.CreateSubCommand;
import org.ares.openterminal.foundation.impl.conversation.CreateSimpleConversation;
import org.ares.openterminal.foundation.impl.conversation.CreateSimplePrompt;
import org.ares.openterminal.spring.SpringCreateModelCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.*;

@Command()
class OpenTerminal implements Runnable {

//    @CommandLine.Option(names = "spigot")
//    private String spigotTemplate;


    public static void main(String[] args) throws IOException {



// =================================================
//        Scanner scanner = new Scanner(System.in);
//
//
//
//
        new CommandLine(new OpenTerminal())
//                .addSubcommand(new CreateSimpleEnchantment())
                .addSubcommand(new CreateSimpleCommand())
                .addSubcommand(new SpringCreateModelCommand())
                .addSubcommand(new CreateSimplePrompt())
                .addSubcommand(new CreateSimpleConversation())
                .addSubcommand(new CreateCommandGroup())
                .addSubcommand(new CreateSubCommand())
                .execute(args);



    }


    @Override
    public void run() {

        System.out.println("Running command..");

    }
}
