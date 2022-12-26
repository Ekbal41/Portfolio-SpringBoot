package com.asifekbal.portfolio.AuthController;

import java.util.HashSet;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.asifekbal.portfolio.model.Role;
import com.asifekbal.portfolio.model.User;
import com.asifekbal.portfolio.model.UserSTO;
import com.asifekbal.portfolio.repository.RolesRepository;
import com.asifekbal.portfolio.repository.UserRepository;





@Controller
@CrossOrigin(origins = "*")

public class LoginController {
    @Autowired private UserRepository userRepo;
    @Autowired private RolesRepository roleRepo;
    
    @GetMapping("/admin_login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String addNewEmployee(Model model) {
        UserSTO userSTO = new UserSTO();
        model.addAttribute("userSTO", userSTO);
        return "register";
    }
    @Bean
    public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
   }

    @PostMapping("/register/save_user")
    public String saveEmployee(UserSTO userSTO) {
        User user = new User();

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepo.findByName("ROLE_ADMIN"));
            user.setRoles(roles);

        String password = encoder().encode(userSTO.getPassword());
        
        user.setUsername(userSTO.getUsername());
        user.setPassword(password);
        user.setEmail(userSTO.getEmail());
        userRepo.save(user);
        
        return "redirect:/register";
    }
}