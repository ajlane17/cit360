package com.adrianjlane.appcontroller;

import javax.servlet.http.HttpServletRequest;

public class ApplicationControllerFactory {

    private static ApplicationControllerFactory instance = null;

    private ApplicationControllerFactory() {

    }

    public static ApplicationControllerFactory getInstance() {
        if (instance == null) {
            instance = new ApplicationControllerFactory();
        }
        return instance;
    }

    public ApplicationController getApplicationController(HttpServletRequest request) {
        // returning WebApplicationController by default
        return new WebApplicationController();
    }
}
