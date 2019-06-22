package com.adrianjlane.appcontroller;

import javax.servlet.http.HttpServletRequest;

public class RequestContextFactory {
    private static RequestContextFactory instance = null;

    private RequestContextFactory() {

    }

    public static RequestContextFactory getInstance() {
        if (instance == null) {
            instance = new RequestContextFactory();
        }
        return instance;
    }

    public RequestContext getRequestContext(HttpServletRequest request) {
        // Return a default context
        return new RequestContext(request);
    }
}
