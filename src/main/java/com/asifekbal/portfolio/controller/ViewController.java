package com.asifekbal.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.asifekbal.portfolio.model.Blog;
import com.asifekbal.portfolio.repository.BlogRepository;



import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ViewController {

    @Autowired
    BlogRepository blogRepo;

    @GetMapping("/")
    public String index (Model model) {
        model.addAttribute("blogs", blogRepo.findAll().subList(0, 3));
        return "home";
    }


    @GetMapping("/blog")
    public String blog (Model model) {
       
        model.addAttribute("blogs", blogRepo.findAll());
        return "blog/single";
    }

    @GetMapping("blog/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Blog blog = blogRepo.findById(id).get();
        List<Blog> blogs = blogRepo.findAll();
		model.addAttribute("blogs",blogs);

		model.addAttribute("blog", blog);
		return "blog/single";
	}

    
   

}    
