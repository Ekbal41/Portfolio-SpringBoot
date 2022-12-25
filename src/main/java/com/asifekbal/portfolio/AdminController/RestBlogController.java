package com.asifekbal.portfolio.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asifekbal.portfolio.model.Blog;
import com.asifekbal.portfolio.repository.BlogRepository;


@RestController
@CrossOrigin(origins = "*")
public class RestBlogController {
    @Autowired
	BlogRepository blogRepo;

    @GetMapping("/admin/getAllblog")
    public List<Blog> getAllBlog() {
       List<Blog> blogList = blogRepo.findAll();
         return blogList;
    }
    
}