package org.ares.openterminal.foundation.impl.tool;

import org.apache.velocity.VelocityContext;
import org.ares.openterminal.Buildable;

import java.io.Writer;

public class CreateTool implements Runnable, Buildable {

    @Override
    public VelocityContext buildContext() {
        return null;
    }

    @Override
    public Writer createFileWriter() {
        return null;
    }

    @Override
    public void flushFileWriter(Writer writer) {

    }

    @Override
    public void writeToTemplate() {

    }

    @Override
    public void buildTemplate() {

    }

    @Override
    public void run() {

    }
}
