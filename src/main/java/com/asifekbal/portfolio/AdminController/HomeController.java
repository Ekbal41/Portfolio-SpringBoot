package com.asifekbal.portfolio.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.asifekbal.portfolio.repository.BlogRepository;
import com.asifekbal.portfolio.repository.ProjectRepository;
import com.asifekbal.portfolio.model.Blog;
import com.asifekbal.portfolio.model.Project;
@Controller
@CrossOrigin(origins = "*")
public class HomeController {

    @Autowired
    BlogRepository blogRepo;
    @Autowired
    ProjectRepository projectRepo;
   

    @GetMapping("/admin")
    public String Home(){
        return "adminpage";
    }

    @GetMapping("/admin/panel")
    public String Panel(Model model){
        List<Blog> blogList = blogRepo.findAll();
        Integer blog_count = blogList.size();
        List<Project> projectList = projectRepo.findAll();
        Integer project_count = projectList.size();
        model.addAttribute("blog_count", blog_count);
        model.addAttribute("project_count", project_count);
        return "panel";
    }

    @GetMapping("/admin/panel/blog")
    public String Blog(Model model){
        List<Blog> blogList = blogRepo.findAll();
        model.addAttribute("blogList", blogList);
        return "pages/blogpanel";

       
    }

    @GetMapping("/admin/panel/project")
    public String Project(Model model){
        List<Project> projectList = projectRepo.findAll();
        model.addAttribute("projectList", projectList);
        return "pages/projectpanel";

       
    }


}
