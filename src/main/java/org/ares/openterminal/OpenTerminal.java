package org.ares.openterminal;

import org.ares.openterminal.foundation.impl.CreateSimpleEnchantment;
import org.ares.openterminal.foundation.impl.command.CreateSimpleCommand;
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
//                .addSubcommand(new SpringCreateModelCommand())
                .execute(args);



    }


    @Override
    public void run() {

        System.out.println("Running command..");

    }
}
