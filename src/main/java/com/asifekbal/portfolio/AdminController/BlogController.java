package com.asifekbal.portfolio.AdminController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asifekbal.portfolio.model.Blog;
import com.asifekbal.portfolio.repository.BlogRepository;


@Controller
public class BlogController {
	private final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/images/blog/";

	@Autowired
	BlogRepository blogRepo;

	@GetMapping("/admin/blog")
	public String getExpenses(Model model) {

        List<Blog> blog = blogRepo.findAll();
		model.addAttribute("blog",blog);
		
		return "blog/blog";
	}

	

	@GetMapping("/admin/blog/add")
	public String addExpense(Model model) {
		model.addAttribute("blog", new Blog());
		return "blog/add";
	}

	@GetMapping("/admin/blog/showUpdateForm/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Blog blog = blogRepo.findById(id).get();
		model.addAttribute("blog", blog);
		return "blog/add";
	}

	@GetMapping("/admin/blog/deleteBlog/{id}")
	public String deleteExpense(@PathVariable("id") long id) {
        Blog blog = blogRepo.findById(id).get();

		blogRepo.delete(blog);
		return "redirect:/admin/blog";
	}

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
		try {
			Path path = Paths.get(UPLOAD_DIR + fileName);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// return success response
		attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

		blog.setImagename(fileName);
		blogRepo.save(blog);
		return "redirect:/admin/blog";
	}

}
