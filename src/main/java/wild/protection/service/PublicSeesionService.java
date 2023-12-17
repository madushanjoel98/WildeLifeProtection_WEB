package wild.protection.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import wild.protection.controllers.admins.LoginController;
import wild.protection.models.PublicLogin;
import wild.protection.repository.PublicLoginRepository;
import wild.protection.utils.PublicExpections;

@Service
public class PublicSeesionService {
	  Logger logger = LoggerFactory.getLogger(PublicSeesionService.class);
	public final static String loginPara = "publog";
	@Autowired
	PublicLoginRepository loginRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void login(HttpSession httpSession, String loginemail, String password) throws PublicExpections {
		Optional<PublicLogin> logse = loginRepository.findByEmail(loginemail);
		if (!logse.isPresent()) {
			throw new PublicExpections("User not found");
		}
		if (!passwordEncoder.matches(password, logse.get().getPassword())) {
			logger.error(passwordEncoder.encode(password));
			logger.error(passwordEncoder.encode(logse.get().getPassword()));
		    throw new PublicExpections("Invalid password");
		}

		httpSession.setAttribute(loginPara, logse.get());
	}

	public void register(PublicLogin login) throws PublicExpections {
		try {
		Optional<PublicLogin> logse = loginRepository.findByEmail(login.getEmail());

		if (logse.isPresent()) {
			throw new PublicExpections("User already Exits");
		}
			if(login.getPassword().length()>16 || login.getPassword().length()<8) {
				String yourlength="Your password length "+login.getPassword().length();
				throw new Exception("Minmum length of the password is:16 and Maximum is 16."+yourlength);
			}
		logger.info(login.getPassword());
		String passenocode=passwordEncoder.encode(login.getPassword());
		login.setPassword(passenocode);

		//login.setPassword(passwordEncoder.encode(login.getPassword()));
		loginRepository.save(login);
		}catch (Exception e) {
			throw new PublicExpections(e.getMessage());
		}
	}

	public PublicLogin logedpublic(HttpSession httpSession) {
		return (PublicLogin) httpSession.getAttribute(loginPara);
	}
	public void logout(HttpSession httpSession) throws PublicExpections{
		try {
			httpSession.removeAttribute(loginPara);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new PublicExpections(e.getMessage());
		}
		
	}
}
