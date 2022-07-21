package com.springmvc.controller.admin;

import com.springmvc.dto.NewsDTO;
import com.springmvc.service.ICategoryService;
import com.springmvc.service.INewService;
import com.springmvc.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller(value = "NewsControllerofAdmin")
public class NewsController {

    @Autowired
    private INewService newsService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private MessageUtil messageUtil;

    @GetMapping(value = "/admin/new/list")
    public ModelAndView newsList(@RequestParam("page") int page, @RequestParam("limit")int limit,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        NewsDTO model = new NewsDTO();
        model.setPage(page);
        model.setLimit(limit);
        Pageable pageable = new PageRequest(page - 1 ,limit);
        model.setTotalItem(newsService.getTotalItem());
        model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
        model.setListResult(newsService.findAll(pageable));
        if (request.getParameter("message")!=null){
            Map<String,String> message =  messageUtil.getMessage(request.getParameter("message"));
            modelAndView.addObject("message",message.get("message"));
            modelAndView.addObject("alert",message.get("alert"));
        }
        modelAndView.setViewName("admin/new/list");
        modelAndView.addObject("model",model);
        return modelAndView;
    }

    @GetMapping(value = "/admin/new/edit")
    public ModelAndView editNews(@RequestParam(value = "id",required = false) Long id, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        NewsDTO model = new NewsDTO();
        if(id != null)
        {
            model = newsService.findById(id);
        }
        if (request.getParameter("message")!=null){
          Map<String,String> message =  messageUtil.getMessage(request.getParameter("message"));
            modelAndView.addObject("message",message.get("message"));
            modelAndView.addObject("alert",message.get("alert"));
        }

        modelAndView.addObject("model",model);
        modelAndView.addObject("categories",categoryService.findAll());
        modelAndView.setViewName("admin/new/edit");
        return modelAndView;
    }

    @PostMapping(value = "/admin/new/img")
    public ModelAndView upLoadImg(@RequestParam("img") MultipartFile img){
        ModelAndView modelAndView = new ModelAndView();
        try {
            newsService.upLoadImg(img);
            NewsDTO newsDTO = new NewsDTO();
            modelAndView.addObject("model",newsDTO);
            modelAndView.setViewName("/admin/new/edit/?message=insert_success&alert");
        } catch (IOException e) {
            modelAndView.setViewName("/admin/new/list?message=error_system&alert=danger");
        }
        return modelAndView;
    }
}
