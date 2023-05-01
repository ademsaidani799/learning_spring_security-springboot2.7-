package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("login")
    public String getLogin(){
        return "login"; //the retunred must be the same as the name of the file in the resources but wihtout the extension
    }

    @GetMapping("courses")
    public String getCourses(){
        return "courses"; //the retunred must be the same as the name of the file in the resources but wihtout the extension
    }
}
