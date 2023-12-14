package wild.protection.controllers.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import antlr.collections.List;
import wild.protection.configs.SecurityUserDetailsService;
import wild.protection.models.Admin;
import wild.protection.models.PublicComplain;
import wild.protection.repository.PublicComplainRepository;
import wild.protection.utils.Commoncontexts;
import wild.protection.utils.JSONObj_Serial;
import wild.protection.utils.UserContextUsage;

@Controller
@RequestMapping(value = "/admin")
public class DashboardController {
	@Autowired
	private SecurityUserDetailsService userDetailsManager;

	@Autowired
	UserContextUsage usercontext;

	@Autowired
	PublicComplainRepository complainRepository;

	

	@GetMapping("/dashboard")
	public String register(Model model) {
		model.addAttribute(Commoncontexts.PAGE_MODEL, "/admin/dashboard/dashboardmain.html");
		model.addAttribute("admintypeid", usercontext.getLoginUSER().getAdminTypes().getAdmintyID());
		return "admin.html";
	}

	@GetMapping(value = "/getmyconcomplain",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCurrentCompanyCompalins() {
		ResponseEntity<?> output = null;
		try {
			java.util.List<PublicComplain> pubs = complainRepository
					.findByCountry(usercontext.getLoginUSER().getCountryid().getId());
			output = new ResponseEntity(JSONObj_Serial.toJSONArray("data", pubs).toString(), HttpStatus.OK);
		} catch (Exception e) {
			output = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return output;
	}

}
