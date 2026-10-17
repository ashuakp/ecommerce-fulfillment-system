package com.ashis.order;

public class SecurityService {
    public boolean hasAdminRole(String user){
        //Simulating a DB/Cache lookup
        System.out.println("SecurityService: Checking roles for user -> " + user);
        return "admin_user".equalsIgnoreCase(user);
    }
}
