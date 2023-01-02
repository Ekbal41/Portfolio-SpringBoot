package com.asifekbal.portfolio.AdminController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asifekbal.portfolio.model.Blog;
import com.asifekbal.portfolio.repository.BlogRepository;

public class UploadController {

    @Autowired
    BlogRepository blogRepo;

    @PostMapping("admin/blog/add/save")
    public String saveExpense(@ModelAttribute Blog blog, @RequestParam("file") MultipartFile file,
            RedirectAttributes attributes) {
        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/admin/blog/add";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // save the file on the local file system
        String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/";
        Path copyLocation = Paths.get(uploadDir + File.separator + fileName);
        if (!Files.exists(copyLocation)) {
            try {
                Files.createDirectories(copyLocation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        blog.setImagename(fileName);
        blogRepo.save(blog);
        return "redirect:/admin/panel/blog";
    }

};