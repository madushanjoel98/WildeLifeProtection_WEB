package wild.protection.controllers.publics;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import wild.protection.utils.Commoncontexts;

@Controller
@RequestMapping("/public")
public class PublicHomeController {

	@GetMapping("/")
	public String loadmainPage(Model model) {
		model.addAttribute(Commoncontexts.PAGE_MODEL, "/public/publichome.html");
		return "page";
	}
	
	
}
