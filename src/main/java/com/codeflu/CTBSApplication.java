package com.codeflu;

import com.codeflu.Dao.BookingDAO;
import com.codeflu.Dao.EventDAO;
import com.codeflu.Resources.BookingResource;
import com.codeflu.Resources.EventResource;
import com.codeflu.Resources.HelloResource;
import com.codeflu.Services.BookingService;
import com.codeflu.Services.EventService;
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
        final HelloResource resource = new HelloResource();

        final EventDAO eventDAO = new EventDAO();
        final EventService eventService = new EventService(eventDAO);
        final EventResource eventResource = new EventResource(eventService);

        final BookingDAO bookingDAO = new BookingDAO();
        final BookingService bookingService = new BookingService(bookingDAO, eventService);
        final BookingResource bookingResource = new BookingResource(bookingService,eventService);

        environment.jersey().register(resource);
        environment.jersey().register(eventResource);
        environment.jersey().register(bookingResource);
    }
}
