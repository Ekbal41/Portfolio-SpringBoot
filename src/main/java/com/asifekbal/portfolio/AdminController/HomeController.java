package com.asifekbal.portfolio.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.asifekbal.portfolio.repository.BlogRepository;
import com.asifekbal.portfolio.model.Blog;
@Controller
public class HomeController {

    @Autowired
    BlogRepository blogRepo;
   

    @GetMapping("/admin")
    public String Home(){
        return "adminpage";
    }

    @GetMapping("/admin/panel")
    public String Panel(Model model){
        List<Blog> blogList = blogRepo.findAll();
        Integer blog_count = blogList.size();
        model.addAttribute("blog_count", blog_count);
        
        return "panel";
    }

    @GetMapping("/admin/panel/blog")
    public String Blog(Model model){
        List<Blog> blogList = blogRepo.findAll();
        model.addAttribute("blogList", blogList);
        return "pages/blogpanel";

       
    }


}
