package com.bgp.mgr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class HomeController {

    /**
     * 首页
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
