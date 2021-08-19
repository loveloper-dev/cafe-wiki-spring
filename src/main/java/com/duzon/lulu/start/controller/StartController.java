package com.duzon.lulu.start.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartController {

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/")
    public String index2() {
        return "index";
    }

}
