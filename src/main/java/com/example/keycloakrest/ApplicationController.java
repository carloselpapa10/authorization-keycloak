package com.example.keycloakrest;

import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {

    /* DEFAULT RESOURCES
    * ROLE: user
    * */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String handleAny() {
        return createResponse("DEFAULT RESOURCES");
    }

    @RequestMapping(value = "/api/resourcea", method = RequestMethod.GET)
    public String handleResourceA() {
        return createResponse("DEFAULT RESOURCES");
    }

    @RequestMapping(value = "/api/resourceb", method = RequestMethod.GET)
    public String handleResourceB() {
        return createResponse("DEFAULT RESOURCES");
    }

    /* PREMIUM RESOURCES
     * ROLE: user-premium
     * */
    @RequestMapping(value = "/api/premium", method = RequestMethod.GET)
    public String handlePremiumResource() {
        return createResponse("Scope:View Resource: PREMIUM RESOURCES");
    }

    @RequestMapping(value = "/api/premium", method = RequestMethod.DELETE)
    public String handleDeletePremiumResource() {
        return createResponse("Scope:Delete Resource: PREMIUM RESOURCES");
    }

    /* ADMIN RESOURCES
     * USER: cavendanoa
     * */
    @RequestMapping(value = "/api/admin", method = RequestMethod.GET)
    public String handleAdminResource() {
        return createResponse("ADMIN RESOURCES");
    }

    private String createResponse(String resource) {
        return String.format("\nAccess Granted : %s", resource);
    }
}