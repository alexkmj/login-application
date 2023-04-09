/**
 * This package contains the controllers for the application. The controllers
 * are responsible for handling requests and returning responses to the client.
 */
package com.login.application.loginapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;

/**
 * This class is a controller that handles requests for the welcome page,
 * restricted page, signup page, and home page. It is responsible for returning
 * the corresponding view for each page.
 * 
 * The {@link @Controller} annotation is used to specify that this class is a
 * controller. The {@link @RequiredArgsConstructor} annotation is used to
 * automatically generate a constructor for the class that takes in all of the
 * final fields and initializes them.
 * 
 * The {@link @GetMapping} annotation is used to specify that this method
 * handles GET requests for the "/restricted" path.
 * 
 * @author Alex Koh
 */
@Controller
@RequiredArgsConstructor
public class BasicController {

    /**
     * Returns the view for the home page.
     * 
     * @return the view for the home page
     */
    @GetMapping("/")
    public String home() {
        return "index";
    }

    /**
     * Returns the view for the signup page.
     * 
     * @return the view for the signup page
     */
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    /**
     * Returns the view for the welcome page.
     * 
     * @return the view for the welcome page
     */
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * Returns the view for the restricted page.
     * 
     * @return the view for the restricted page
     */
    @GetMapping("/restricted")
    public String restricted() {
        return "restricted";
    }
}
