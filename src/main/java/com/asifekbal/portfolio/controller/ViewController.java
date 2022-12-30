package com.asifekbal.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asifekbal.portfolio.model.Blog;
import com.asifekbal.portfolio.model.Email;
import com.asifekbal.portfolio.model.Project;
import com.asifekbal.portfolio.repository.BlogRepository;
import com.asifekbal.portfolio.repository.ProjectRepository;

@Controller
@CrossOrigin(origins = "*")
public class ViewController {
    

    @Autowired
    BlogRepository blogRepo;
    @Autowired
    ProjectRepository projectRepo;
    @Autowired private JavaMailSender javaMailSender;


    @GetMapping("/")
    public String index(Model model, @ModelAttribute("message") String message) {
        if (blogRepo.findAll().size() > 3) {
            model.addAttribute("blogs", blogRepo.findAll().subList(0, 3));
        } else {
            model.addAttribute("blogs", blogRepo.findAll());
        }
        if (projectRepo.findAll().size() > 6) {
            model.addAttribute("projects", projectRepo.findAll().subList(0, 6));
        } else {
            model.addAttribute("projects", projectRepo.findAll());
        }
        model.addAttribute("message", message);

        return "home";
    }

    @GetMapping("/blogpage")
    public String blog(Model model) {

        model.addAttribute("blogs", blogRepo.findAll());
        return "blog/blogpage";
    }

    @GetMapping("blog/{id}")
    public String showblog(@PathVariable("id") long id, Model model) {
        Blog blog = blogRepo.findById(id).get();
        List<Blog> blogs = blogRepo.findAll();
        model.addAttribute("blogs", blogs);

        model.addAttribute("blog", blog);
        return "blog/single";
    }

    @GetMapping("/projectpage")
    public String project(Model model) {

        model.addAttribute("projects", projectRepo.findAll());
        return "project/projectpage";
    }

    @GetMapping("project/{id}")
    public String showproject(@PathVariable("id") long id, Model model) {
        Project project = projectRepo.findById(id).get();
        List<Project> projects = projectRepo.findAll();
        model.addAttribute("projects", projects);

        model.addAttribute("project", project);
        return "project/single";
    }

    @PostMapping("/sendmail")
    public String Mailer(Email details, RedirectAttributes redirectAttributes){
        try{
            SimpleMailMessage mailMessage
                = new SimpleMailMessage();
            String sendto = "mdasifekbalshagor@gmail.com";
            String sender = details.getSender();
            String body = details.getBody();
            String subject = "Message from <" + sender + "> : " + "(Spring-Portfolio)";
 
            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(sendto);
            mailMessage.setText(body);
            mailMessage.setSubject(subject);
 
            // Sending the mail
            javaMailSender.send(mailMessage);
            redirectAttributes.addFlashAttribute("message", "s");
        }
        catch(Exception e){
            redirectAttributes.addFlashAttribute("message", "e");
        }
            return "redirect:/";
    }

   

}
