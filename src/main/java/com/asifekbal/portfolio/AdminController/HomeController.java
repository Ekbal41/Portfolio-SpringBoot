package com.asifekbal.portfolio.AdminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
   

    @GetMapping("/admin")
    public String Home(){
        return "adminpage";
    }


}
