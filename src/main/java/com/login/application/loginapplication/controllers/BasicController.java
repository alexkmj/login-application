package com.login.application.loginapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.login.application.loginapplication.repositories.UserRepository;
import com.login.application.loginapplication.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BasicController {

    /**
     * The welcome page.
     * 
     * @return the welcome page
     */
	@RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * The login page.
     * 
     * @return the login page
     */
    @RequestMapping("/restricted")
    public String restricted() {
        return "restricted";
    }

    /**
     * The signup page.
     * 
     * @return the signup page
     */
    @RequestMapping("/signup")
    public String signup() {
        return "signup";
    }

    /**
     * The signup page.
     * 
     * @return the signup page
     */
    @RequestMapping("/")
    public String home() {
        return "index";
    }
}
