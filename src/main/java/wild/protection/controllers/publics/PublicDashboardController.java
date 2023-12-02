package wild.protection.controllers.publics;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import wild.protection.models.PublicLogin;
import wild.protection.service.PublicSeesionService;
import wild.protection.utils.Commoncontexts;

@Controller
@RequestMapping("/public")
public class PublicDashboardController {
	@Autowired
	PublicSeesionService publicSeesionService;

	@GetMapping("/dashbord")
	public String dashborad(Model model, HttpSession session, RedirectAttributes attributes) {
		if (publicSeesionService.logedpublic(session) == null) {
			attributes.addFlashAttribute("error", "Please Sign UP");
			return "redirect:/public/login";
		}
		model.addAttribute("user", publicSeesionService.logedpublic(session));
		model.addAttribute(Commoncontexts.PAGE_MODEL, "/public/dashboard/dashboardmain.html");
		return "velonicpage.html";
	}

}
