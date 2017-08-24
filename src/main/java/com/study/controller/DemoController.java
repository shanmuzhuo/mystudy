package com.study.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoController {


    @RequestMapping("/weight")
    public String getWeight(HttpServletRequest request, Model model) {
    	System.out.println("fasdf");
		model.addAttribute("num", "123456");
        return "demo";
    }
}
