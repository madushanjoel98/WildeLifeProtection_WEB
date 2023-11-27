package wild.protection.controllers.admins;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @GetMapping(value = "/login")
    public String adminLogin(Model model) {
        return "/admin/view/login/adminlogin.html";
    }
}
