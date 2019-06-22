package com.adrianjlane.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "ParamsExample")
public class ParamsExample extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } finally {
            reader.close();
        }
        System.out.println(sb.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Enumeration<String> parameterNames = request.getParameterNames();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("<title>Params Example</title></head>");
            out.println("<body>");
            out.println("<h1>Params Example</h1>");
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                out.println("<p>" + paramName + " values:");
                String[] paramValues = request.getParameterValues(paramName);
                for (int i = 0; i < paramValues.length; i++) {
                    String paramValue = paramValues[i];
                    out.println("    " + paramValue);
                }
            }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }

    }
}
