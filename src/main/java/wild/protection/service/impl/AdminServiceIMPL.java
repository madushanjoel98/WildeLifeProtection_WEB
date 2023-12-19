package wild.protection.service.impl;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import wild.protection.dto.request.ChangePassword;
import wild.protection.models.Admin;
import wild.protection.models.PublicLogin;
import wild.protection.repository.UserRepository;
import wild.protection.service.AdminService;
import wild.protection.utils.AdminExpections;
import wild.protection.utils.PublicExpections;

@Service
@Transactional
public class AdminServiceIMPL implements AdminService {
@Autowired
UserRepository userRepository;

@Autowired
private PasswordEncoder passwordEncoder;
	@Override
	public void changePassword(ChangePassword changepass, Admin loggedAdmin) throws AdminExpections {
		try {
			Admin user = userRepository.findById(loggedAdmin.getUserId())
	                .orElseThrow(() -> new EntityNotFoundException("User not found"));

	        if (changepass.getNewPassword().length() > 16 || changepass.getNewPassword().length() < 8) {
	            String passwordLengthMessage = "Your password length " + changepass.getNewPassword().length();
	            throw new PublicExpections("Minimum length of the password is 8, and the maximum is 16. " + passwordLengthMessage);
	        }

	        if (!passwordEncoder.matches(changepass.getOldpassword(), user.getPassword())) {
	            throw new PublicExpections("Password doesn't match with the old password");
	        }

	        user.setPassword(passwordEncoder.encode(changepass.getNewPassword()));
		} catch (Exception e) {
			throw new AdminExpections(e.getMessage());
		}
		
	}

}
