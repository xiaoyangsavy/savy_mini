package com.savy.controller;


import com.savy.model.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping(value = "/recognition/get")
    @ResponseBody
    public Test testGet() {
        System.out.println("call /test/get");
        Test test = new Test();
        test.setValue("recognition");

        return test;
    }


    @RequestMapping(value = "/recognition/submit")
    @ResponseBody
    public Test testSubmit() {
        System.out.println("call /test/submit");
        Test test = new Test();
        test.setValue("success");
        return test;
    }

}
