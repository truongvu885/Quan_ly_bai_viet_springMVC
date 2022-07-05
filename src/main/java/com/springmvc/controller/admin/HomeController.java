package com.springmvc.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "HomeControllerOfAdmin")
public class HomeController {

    @GetMapping(value = "/admin/home")
    public ModelAndView homePage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

}
