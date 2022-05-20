package com.tq.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController//相当于controller+resposebody
@Controller
public class HelloController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello springboot!!";
    }

    @RequestMapping("/success")
    public String success() {
        return "success";
    }


}
