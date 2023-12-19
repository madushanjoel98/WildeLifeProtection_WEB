package wild.protection.controllers.admins;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import wild.protection.configs.SecurityConfigs;
import wild.protection.configs.SecurityUserDetailsService;
import wild.protection.dto.request.ChangePassword;
import wild.protection.models.Admin;
import wild.protection.repository.AdminTypeRespos;
import wild.protection.repository.CountriesRepository;
import wild.protection.repository.UserRepository;
import wild.protection.service.AdminService;
import wild.protection.utils.Commoncontexts;
import wild.protection.utils.EncryptionText;
import wild.protection.utils.UserContextUsage;

@Controller
@RequestMapping(value = "/admin")
public class LoginController {
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private SecurityUserDetailsService userDetailsManager;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userre;

	@Autowired
	AdminTypeRespos adminTypeRespos;
	@Autowired
	CountriesRepository countriesRepository;
	@Autowired
	UserContextUsage usercontext;
@Autowired
AdminService adminService;
	EncryptionText ence = EncryptionText.getInstance();

	@GetMapping(value = "/login")
	public String adminLogin(Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("loginu", new Admin());
		
		return "/admin/view/login/adminlogin.html";
	}

	@PostMapping("/login")
	public String processLogin(@ModelAttribute Admin adimin, RedirectAttributes redirectAttributes) {
		try {
			if (authenticateUser(adimin.getUsername(), adimin.getPassword())) {
				setAuthenticationDetails(adimin.getUsername());

				return "redirect:/dashboard?";
			} else {
				String error = "Invalid username and or password!";
				logger.error(error);
				redirectAttributes.addFlashAttribute("error", error);
				return "redirect:/admin/login";
			}
		} catch (BadCredentialsException | LockedException e) {
			logger.error(e.getMessage());
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/admin/login";
		}
	}

	@PostMapping(value = "/registers")
	public String addUser(@ModelAttribute Admin admin) {
		Admin user = new Admin();

		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		admin.setAccountNonLocked(true);
		userre.save(admin);
		return "redirect:/admin/login?"; // Redirect to login page after registration
	}

	@GetMapping("/register")
	public String register(Model model) {
//			model.addAttribute("admintypeid", usercontext.getLoginUSER().getAdminTypes().getAdmintyID());
		model.addAttribute(Commoncontexts.PAGE_MODEL, "/admin/register.html");
		model.addAttribute("rolelist", adminTypeRespos.findAll());
		model.addAttribute("countrylist", countriesRepository.findAll());
		model.addAttribute("reg", new Admin());
		return "admin.html"; // Update with the correct view name
	}

	private boolean authenticateUser(String username, String password) {
		// Replace this with your actual authentication logic
		// For example, using Spring Security's UserDetailsService,
		// AuthenticationProvider, etc.
		// Here, we are checking against a simple UserRepository (for illustration
		// purposes)
		Optional<Admin> userOptional = userre.findByUsername(username);

		return userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword());
	}

	private void setAuthenticationDetails(String username) {
		// Create authentication token and set it to the SecurityContext
		Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
				Collections.emptyList());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Set authentication details manually (not recommended in a real-world
		// application)
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		WebAuthenticationDetails details = new WebAuthenticationDetails(request);
		((UsernamePasswordAuthenticationToken) authentication).setDetails(details);
	}
	
	@PostMapping(value = "/changepassword")
	private String changepassword(@ModelAttribute ChangePassword changePassword,HttpSession session,RedirectAttributes attributes) {
	
		
		String output = null;
		
		try {
	
			adminService.changePassword(changePassword, usercontext.getLoginUSER());
			attributes.addFlashAttribute(Commoncontexts.SUCCESS, "Password Updated");
			
		} catch (Exception e) {
			attributes.addFlashAttribute("error", e.getMessage());
			
		}
		return "redirect:/admin/userprofile";
	}
}
