package pl.damianrowinski.springappadswithregistration.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout-confirm")
    public String confirmLogout() {
        return "/logout-confirm";
    }

}
