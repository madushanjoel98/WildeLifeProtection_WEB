package wild.protection.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

@GetMapping("/")
public String redirectd() {
	return "redirect:/public/home";
}

}
