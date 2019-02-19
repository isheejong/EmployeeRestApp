package com.developer.service;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;


public class APIApplication extends ResourceConfig {
	
	public APIApplication() {

        register(EmployeeController.class);

        register(JacksonJsonProvider.class);

        register(LoggingFilter.class);
    }

}
