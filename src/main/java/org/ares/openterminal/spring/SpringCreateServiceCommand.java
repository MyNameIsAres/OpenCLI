package org.ares.openterminal.spring;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "make:service")
public class SpringCreateServiceCommand implements Runnable {

    @Parameters()
    private String name;


    @Override
    public void run() {
        // TODO: Check list of what todo

        /*
            Do we have a Model with the given name?



         */
    }
}
