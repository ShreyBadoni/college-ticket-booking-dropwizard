package com.codeflu;

import com.codeflu.Resources.HelloResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;


public class CTBSApplication extends Application<CTBSConfiguration> {

    public static void main(String[] args) throws Exception {
        new CTBSApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<CTBSConfiguration> bootstrap) {
        // nothing to initialize for now
    }

    @Override
    public void run(CTBSConfiguration configuration, Environment environment) {
        // Register resources here
        final HelloResource resource = new HelloResource();
        environment.jersey().register(resource);
    }
}
