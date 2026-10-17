package com.ashis.order;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class PerformanceLoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig){
        System.out.println("PerformanceLoggingFilter Initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        long startTime = System.currentTimeMillis();
        HttpServletRequest req = (HttpServletRequest) request;

        System.out.println("Incoming request: " + req.getMethod() + " " + req.getRequestURI());
        chain.doFilter(request, response);
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Total execution time: " + duration + " ms");
    }

    @Override
    public void destroy() {
        System.out.println("Performance LoggingFilter destroyed");
    }

}
