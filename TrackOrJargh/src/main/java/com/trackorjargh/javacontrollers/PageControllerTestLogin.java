package com.trackorjargh.javacontrollers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageControllerTestLogin {
    @RequestMapping("/tests-login")
    public String index() {
        return "tests-login/index";
    }

    @RequestMapping("/tests-login/login")
    public String login() {
    	return "tests-login/login";
    }
    
    @RequestMapping("/tests-login/loginerror")
    public String loginerror() {
    	return "tests-login/loginerror";
    }

    @RequestMapping("/tests-login/home")
    public String home(Model model, HttpServletRequest request) {
    	
    	model.addAttribute("admin", request.isUserInRole("ADMIN"));
    	
    	return "tests-login/home";
    }
    
    @RequestMapping("/tests-login/admin")
    public String admin() {
    	return "tests-login/admin";
    }
}
