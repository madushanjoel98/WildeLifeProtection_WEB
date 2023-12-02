package wild.protection.utils;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import wild.protection.models.Admin;
import wild.protection.repository.UserRepository;


@Service
public class UserContextUsage {
	Logger logger = LoggerFactory.getLogger(UserContextUsage.class);
	
	@Autowired
	UserRepository userRepository;
	public  Admin getLoginUSER() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Optional<Admin> u = userRepository.findByUsername(auth.getName());
		return u.get();
	}
	public void functionUsageLogger(String message) {
    logger.info(getLoginUSER().getUsername()+":"+message);
		
	}
//	public void isHasAccess(int module) throws NoAccessExpection, FAExpectation {
//		 	user_Service.throwsAccess(getLoginUSER(), module);
//	}
	
}
