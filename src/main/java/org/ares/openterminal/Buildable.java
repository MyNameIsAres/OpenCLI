package org.ares.openterminal;

import org.apache.velocity.VelocityContext;

import java.io.Writer;

public interface Buildable {

    VelocityContext buildContext();

    Writer createFileWriter();

    void flushFileWriter(Writer writer);

    void createTemplate(Writer writer);

    void writeToTemplate();

    void buildTemplate();

}
