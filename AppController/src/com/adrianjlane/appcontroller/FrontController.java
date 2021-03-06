package com.adrianjlane.appcontroller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FrontController")
public class FrontController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationControllerFactory ACFactory = ApplicationControllerFactory.getInstance();
        ApplicationController applicationController = ACFactory.getApplicationController(request);
        applicationController.init();
        System.out.println("ApplicationController successfully initialized");

        RequestContextFactory requestContextFactory = RequestContextFactory.getInstance();
        RequestContext requestContext = requestContextFactory.getRequestContext(request);
        System.out.println("RequestContext successfully created");

        ResponseContext responseContext;
        responseContext = applicationController.handleRequest(requestContext);
        responseContext.setResponse(response);

        applicationController.handleResponse(requestContext, responseContext);
        applicationController.destroy();
    }
}
