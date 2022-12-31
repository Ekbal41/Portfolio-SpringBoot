package com.asifekbal.portfolio.AdminController;

import java.io.File;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asifekbal.portfolio.model.Project;
import com.asifekbal.portfolio.repository.ProjectRepository;


@Controller
@CrossOrigin(origins = "*")
public class ProjectController {
	//private final String UPLOAD_DIR ="./src/main/resources/static/images/";
	//Path UPLOAD_DIR = Paths.get(new FileSystemResource("src/main/resources/static/images/upload").getFile().getPath() );

	@Autowired
	ProjectRepository projectRepo;

	@GetMapping("/admin/project")
	public String getExpenses(Model model) {

        List<Project> project = projectRepo.findAll();
		model.addAttribute("project",project);
		try{
			String path = new File(".").getCanonicalPath() + "/src/main/resources/static/images/upload";
			model.addAttribute("path", path);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return "project/project";
	}

	

	@GetMapping("/admin/project/add")
	public String addExpense(Model model) {
		model.addAttribute("project", new Project());
		return "project/add";
	}

	@GetMapping("/admin/project/showUpdateForm/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Project project = projectRepo.findById(id).get();
		model.addAttribute("project", project);
		return "project/add";
	}

	@GetMapping("/admin/project/deleteProject/{id}")
	public String deleteExpense(@PathVariable("id") long id) {
        Project project = projectRepo.findById(id).get();

		projectRepo.delete(project);
		return "redirect:/admin/project";
	}

	@PostMapping("admin/project/add/save")
	public String saveExpense(@ModelAttribute Project project, @RequestParam("file") MultipartFile file,
			RedirectAttributes attributes) {
		// check if file is empty
		if (file.isEmpty()) {
			attributes.addFlashAttribute("message", "Please select a file to upload.");
			return "redirect:/admin/project/add";
		}

		// normalize the file path
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		// save the file on the local file system
		try {
			String UPLOAD_DIR = new File(".").getCanonicalPath() + "/src/main/resources/static/images/upload";
			Path path = Paths.get(UPLOAD_DIR + fileName);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// return success response
		attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

		project.setImagename(fileName);
		projectRepo.save(project);
		return "redirect:/admin/panel/project";
	}

}
