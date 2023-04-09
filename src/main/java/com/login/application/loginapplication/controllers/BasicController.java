package com.login.application.loginapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BasicController {

    /**
     * The welcome page.
     * 
     * @return the welcome page
     */
	@GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * The login page.
     * 
     * @return the login page
     */
    @GetMapping("/restricted")
    public String restricted() {
        return "restricted";
    }

    /**
     * The signup page.
     * 
     * @return the signup page
     */
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    /**
     * The signup page.
     * 
     * @return the signup page
     */
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
