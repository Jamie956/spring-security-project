package com.jamie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller()
public class IndexController {

    @GetMapping("list")
    @ResponseBody
    public String list() {
        return "list";
    }

    @GetMapping("hi")
    @ResponseBody
    public String hi() {
        return "hi";
    }

}
