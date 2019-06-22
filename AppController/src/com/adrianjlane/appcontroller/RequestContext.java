package com.adrianjlane.appcontroller;

import javax.servlet.http.HttpServletRequest;

public class RequestContext {

    private HttpServletRequest request;

    public RequestContext(HttpServletRequest request) {
        this.request = request;
    }

    public String getCommandName() {
        return request.getMethod();
    }
}
