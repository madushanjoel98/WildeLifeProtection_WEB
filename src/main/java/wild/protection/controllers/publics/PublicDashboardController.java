package wild.protection.controllers.publics;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import wild.protection.models.PublicComplain;
import wild.protection.models.PublicLogin;
import wild.protection.repository.CountriesRepository;
import wild.protection.repository.PublicComplainRepository;
import wild.protection.service.PublicSeesionService;
import wild.protection.utils.Commoncontexts;

@Controller
@RequestMapping("/public")
public class PublicDashboardController {
	@Autowired
	PublicSeesionService publicSeesionService;
	@Autowired
	PublicComplainRepository complainRepository;
	@Autowired
	CountriesRepository countriesRepository;

	@GetMapping("/dashbord")
	public String dashborad(Model model, HttpSession session, RedirectAttributes attributes) {
		if (publicSeesionService.logedpublic(session) == null) {
			attributes.addFlashAttribute("error", "Please Sign UP");
			return "redirect:/public/login";
		}
		model.addAttribute("country", countriesRepository.findAll());
		model.addAttribute("complainr", new PublicComplain());
		model.addAttribute("user", publicSeesionService.logedpublic(session));
		model.addAttribute(Commoncontexts.PAGE_MODEL, "/public/dashboard/dashboardmain.html");
		return "velonicpage.html";
	}

	@PostMapping("/addComplaint")
	public String addComplain(RedirectAttributes attributes, HttpSession session,
			@ModelAttribute @Valid PublicComplain complain,BindingResult bindingResult) {
		if (publicSeesionService.logedpublic(session) == null) {
			attributes.addFlashAttribute("error", "Please Sign UP");
			return "redirect:/public/login";
		}
		 if (bindingResult.hasErrors()) {
				attributes.addFlashAttribute("error", "Error: "+bindingResult.getFieldError().getDefaultMessage());
				return "redirect:/public/dashbord";
		    } 
		try {
			PublicLogin loged=publicSeesionService.logedpublic(session);
			complain.setPublicid(loged);
			complainRepository.save(complain);
			attributes.addFlashAttribute("success", "complain added");
		} catch (Exception e) {
			attributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/public/dashbord";
		}

		return "redirect:/public/dashbord";

	}
}
