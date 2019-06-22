package com.adrianjlane.appcontroller;

public interface ApplicationController {

    void init();

    ResponseContext handleRequest(RequestContext requestContext);

    void handleResponse(RequestContext requestContext, ResponseContext responseContext);

    void destroy();
}
