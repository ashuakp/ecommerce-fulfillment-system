package com.ashis.order;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class OrderDispatchServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("OrderDispatchServlet: Loading heavy resources...");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        try{
            Thread.sleep(50);
        } catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write("{\"status\": \"success\", \"message\": \"Order API is running on raw Servlet\"}");
    }
}
