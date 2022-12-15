package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    
    // 홈
    // http://127.0.0.1:8085/QOOT1/admin/home.do
    @GetMapping(value = "/home.do" )
    public String homeGET(
        Model model
    ){  
        
        
        return "admin/home";
    }
}
