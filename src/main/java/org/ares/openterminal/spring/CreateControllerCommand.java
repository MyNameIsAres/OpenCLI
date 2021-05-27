package org.ares.openterminal.spring;

import org.apache.velocity.VelocityContext;
import org.ares.openterminal.Buildable;
import org.ares.openterminal.util.StringUtil;
import org.ares.openterminal.util.TemplateBuilder;
import org.ares.openterminal.util.YamlHandler;
import org.ares.openterminal.util.spring.SpringUtility;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import java.io.Writer;

@Command(name = "make:controller")
public class CreateControllerCommand implements Runnable, Buildable {


    @Parameters()
    String name;

    @Parameters(defaultValue = "RestController")
    String controllerType = "";

    @Parameters(defaultValue = "true")
    String createCrudMethods = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    final static String PROPERTY_KEY = "controller_location";

    final static String template = "\\controller\\";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    @Override
    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();
        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", name);
        context.put("CONTROLLER_TYPE",  controllerType);

        return context;
    }


    @Override
    public void run() {
        SpringUtility utility = new SpringUtility();
        utility.modelExists(name);


//        TemplateBuilder templateBuilder = new TemplateBuilder();
//        Writer writer = templateBuilder.createFileWriter(PROPERTY_KEY, name);
//        templateBuilder.createTemplateSpring(writer, TEMPLATE, buildContext());
//        templateBuilder.flushFileWriter(writer);
    }


}
