package ua.logos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BaseController {

//    @RequestMapping(value = "/", method = RequestMethod.GET)  - old realisation
    @GetMapping("/")
    public String showHome(){
        return "home";
    }

    @GetMapping("/profile")
    public String showProfile(Model model){
        model.addAttribute("username", "John Doe");
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String showEditProfile(){
        return "editprofile";
    }

}
