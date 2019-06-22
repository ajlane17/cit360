package com.adrianjlane.appcontroller;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static sun.java2d.cmm.ColorTransform.Out;

public class ResponseContext {

    private String result;

    public ResponseContext(String result) {
        this.result = result;
    }

    public void setResponse(HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>AppController Example</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Example Response</h1>");
            out.println("<p>The response from the command was " + result + "</p>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
