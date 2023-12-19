package wild.protection.controllers.admins;

import javax.servlet.http.HttpSession;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AcceptAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import antlr.collections.List;
import wild.protection.configs.SecurityUserDetailsService;
import wild.protection.dto.request.AcceptCompalinDTO;
import wild.protection.dto.request.ByIDRequest;
import wild.protection.dto.request.ChangePassword;
import wild.protection.dto.request.RejectCompalinDTO;
import wild.protection.models.AcceptedComplains;
import wild.protection.models.Admin;
import wild.protection.models.PublicComplain;
import wild.protection.repository.PublicComplainRepository;
import wild.protection.service.ComplaintActionService;
import wild.protection.utils.Commoncontexts;
import wild.protection.utils.JSONObj_Serial;
import wild.protection.utils.UserContextUsage;

@Controller
@RequestMapping(value = "/admin")
public class AdminDashboardController {
	final Logger logger = LoggerFactory.getLogger(AdminDashboardController.class);
	@Autowired
	private SecurityUserDetailsService userDetailsManager;

	@Autowired
	UserContextUsage usercontext;

	@Autowired
	PublicComplainRepository complainRepository;

	@Autowired
	ComplaintActionService complaintActionService;

	@GetMapping("/dashboard")
	public String register(Model model, RedirectAttributes redirectAttributes) {
		
		model.addAttribute(Commoncontexts.PAGE_MODEL, "/admin/dashboard/dashboardmain.html");
		model.addAttribute("user", usercontext.getLoginUSER());
		model.addAttribute("acccom", new AcceptCompalinDTO());
		model.addAttribute("rrcomp", new RejectCompalinDTO());
		model.addAttribute("pendingcou", complainRepository.findPenddings().size());
		model.addAttribute("getPbyc",complainRepository.findByPendingsbyCountry(usercontext.getLoginUSER().getCountryid().getId()).size());
		model.addAttribute("allcomplains", complainRepository.count());
		model.addAttribute("yourcomplains", complainRepository.findByCountry(usercontext.getLoginUSER().getCountryid().getId()).size());
		model.addAttribute("admintypeid", usercontext.getLoginUSER().getAdminTypes().getAdmintyID());
		
		return "admin.html";
	}

	@GetMapping(value = "/getmyconcomplain", produces = MediaType.APPLICATION_JSON_VALUE)
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

	@GetMapping(value = "/getwwComplains", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getwwComplains() {
		ResponseEntity<?> output = null;
		try {

			if (usercontext.getLoginUSER().getAdminTypes().getAdmintyID() != 1) {
				throw new Exception("Admin as no Access to this");
			}
			java.util.List<PublicComplain> pubs = complainRepository.findAll();
			output = new ResponseEntity(JSONObj_Serial.toJSONArray("data", pubs).toString(), HttpStatus.OK);
		} catch (Exception e) {
			output = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return output;
	}

	@PostMapping(value = "/acceptComplain")
	public String acceptComplain(@ModelAttribute AcceptCompalinDTO acceptAction,
			RedirectAttributes redirectAttributes) {
		try {

			complaintActionService.acceptChange(acceptAction.getAcceptcom(), usercontext.getLoginUSER(),
					acceptAction.getcID());
			redirectAttributes.addFlashAttribute(Commoncontexts.SUCCESS, "Complain Status updated");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute(Commoncontexts.ERROR, e.getMessage());
		}
		return "redirect:/admin/dashboard";

	}

	@PostMapping(value = "/rejectComplain")
	public String rejectComplain(@ModelAttribute RejectCompalinDTO reject, RedirectAttributes redirectAttributes) {
		try {
			complaintActionService.rejectChange(reject.getRejectResons(), usercontext.getLoginUSER(), reject.getcID());
			redirectAttributes.addFlashAttribute(Commoncontexts.SUCCESS, "Complain Status updated");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute(Commoncontexts.ERROR, e.getMessage());
		}
		return "redirect:/admin/dashboard";

	}

	@PostMapping(value = "/getbyComplainID")
	private ResponseEntity<?> findbyComlainID(@RequestBody ByIDRequest request, HttpSession session) {
		ResponseEntity<?> output = null;

		try {
			PublicComplain complain = complainRepository.findById(request.getId()).get();

			output = new ResponseEntity<>(complain, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			output = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return output;
	}
	
	@PostMapping(value = "/getStatus")
	private ResponseEntity<?> getStatus(@RequestBody ByIDRequest request) {
		ResponseEntity<?> output = null;
		
		try {
	
			output = new ResponseEntity<>(complaintActionService.getStatus(request.getId()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			output = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return output;
	}
	
	@PostMapping(value = "/retoretheComplain")
	private ResponseEntity<?> retoretheComplain(@RequestBody ByIDRequest request) {
		ResponseEntity<?> output = null;
		
		try {
			complaintActionService.retoreComplain(request.getId());
			output = new ResponseEntity<>("Complain Retored", HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			output = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return output;
	}
	
	@GetMapping(value="/userprofile")
	public String userprofile(Model model) {
		//templates/admin/userprofile/userprofile.html
		model.addAttribute("user", usercontext.getLoginUSER());
		model.addAttribute("changepass", new ChangePassword());
		model.addAttribute("admintypeid", usercontext.getLoginUSER().getAdminTypes().getAdmintyID());
		model.addAttribute(Commoncontexts.PAGE_MODEL, "/admin/userprofile/userprofile.html");
		return "admin.html";
	}
	
}
