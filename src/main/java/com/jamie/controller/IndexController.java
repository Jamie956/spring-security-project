package com.jamie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
