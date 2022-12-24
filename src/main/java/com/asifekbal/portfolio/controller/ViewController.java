package com.asifekbal.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.asifekbal.portfolio.repository.BlogRepository;

@Controller
public class ViewController {

    @Autowired
    BlogRepository blogRepo;

    @GetMapping("/")
    public String index (Model model) {
        model.addAttribute("blogs", blogRepo.findAll().subList(0, 3));
        return "home";
    }
}    
