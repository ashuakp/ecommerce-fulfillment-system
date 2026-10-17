package com.ashis.order;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

import java.io.File;

public class App {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector(); //Initialize default Connector

        // Create a dummy context
        Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());

        // 1. Register the Servlet
        Tomcat.addServlet(ctx, "OrderDispatchServlet", new OrderDispatchServlet());
        ctx.addServletMappingDecoded("/api/orders", "OrderDispatchServlet");

        // 2. Register the Filter
        FilterDef filterDef = new FilterDef();
        filterDef.setFilterName("PerformanceFilter");
        filterDef.setFilterClass(PerformanceLoggingFilter.class.getName());
        ctx.addFilterDef(filterDef);

        // 2b. Register the Security Filter
        /*FilterDef securityFilterDef = new FilterDef();
        securityFilterDef.setFilterName("SecurityFilter");
        securityFilterDef.setFilterClass(SecurityFilter.class.getName());
        ctx.addFilterDef(securityFilterDef);

        FilterMap securityFilterMap = new FilterMap();
        securityFilterMap.setFilterName("SecurityFilter");
        securityFilterMap.addURLPattern("/api/orders/*"); // Protect order endpoints
        ctx.addFilterMap(securityFilterMap);*/

        // 3. Map the Filters to URL
        FilterMap filterMap = new FilterMap();
        filterMap.setFilterName("PerformanceFilter");
        filterMap.addURLPattern("/*");
        ctx.addFilterMap(filterMap);

        System.out.println("Starting embeded Tomcat on port 8080...");
        tomcat.start();
        tomcat.getServer().await();

    }
}
