package com.springmvc.controller.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller(value = "HomeControllerOfWeb")
public class HomeController {

    @RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("web/home");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ModelAndView logoutPage(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return new ModelAndView("redirect:/trang-chu");
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView accessDenied(){
        return new ModelAndView("redirect:/login?accessDenied");
    }
}
