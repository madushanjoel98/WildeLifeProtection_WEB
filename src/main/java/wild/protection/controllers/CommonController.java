package wild.protection.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import wild.protection.utils.UserContextUsage;

@Controller
public class CommonController {
	@Autowired
	UserContextUsage contextUsage;
@GetMapping("/")
public String redirectd() {
	try {
	if(contextUsage.getLoginUSER()!=null) {
		return "redirect:/admin/dashboard";
	}
	}catch (Exception e) {
		return "redirect:/public/home";
	}
	return "redirect:/public/home";
}

}
