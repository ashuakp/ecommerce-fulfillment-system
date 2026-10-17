package com.ashis.order;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SecurityFilter implements Filter {

    private SecurityService securityService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TIGHT COUPLING: We have to manually instantiate this without a DI container.
        this.securityService = new SecurityService();
        System.out.println("SecurityFilter Initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //Simulating extracting a user from a header (e.g., from a JWT later)
        String currentUser = req.getHeader("X-User");

        if(currentUser == null || !securityService.hasAdminRole(currentUser)){
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            resp.getWriter().write("{\"error\": \"Forbidden: Admin role required\"}");
            System.out.println("SecurityFilter : Blocked Unauthorized Access.");
            return; // Short-circuit the request pipeline
        }

        // User is authorized, continue down the chain
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
