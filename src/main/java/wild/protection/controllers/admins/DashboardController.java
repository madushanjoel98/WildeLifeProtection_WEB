package wild.protection.controllers.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import wild.protection.configs.SecurityUserDetailsService;
import wild.protection.models.Admin;
import wild.protection.utils.Commoncontexts;

@Controller
@RequestMapping(value = "/admin")
public class DashboardController {
@Autowired
	private SecurityUserDetailsService userDetailsManager;
 @GetMapping("/dashboard")
    public String register(Model model) {
    	model.addAttribute(Commoncontexts.PAGE_MODEL, "/admin/dashboard/dashboardmain.html");
        return "admin.html";
    }

}
