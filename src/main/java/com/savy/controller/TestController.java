package com.savy.controller;

import com.savy.model.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    @RequestMapping(value = "/test/get")
    @ResponseBody
    public ModelAndView testFreemarker() {
        Test test = new Test();
        test.setValue("test");
        ModelAndView mv = new ModelAndView();
        mv.addObject("test", test);
        mv.setViewName("test");
        return mv;
    }

}
