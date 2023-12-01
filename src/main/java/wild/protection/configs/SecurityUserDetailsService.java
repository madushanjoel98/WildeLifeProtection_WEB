package wild.protection.configs;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import wild.protection.models.Admin;
import wild.protection.repository.UserRepository;


@Service
public class SecurityUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Admin> userOptional = userRepository.findByUsername(username);
		if (userOptional.isEmpty()) {
			throw new UsernameNotFoundException("User not present");
		}
		Admin user = userOptional.get();
		return user;
	}

	public void createUser(UserDetails user) {
		// You can't save UserDetails directly, so you might need to convert it to your
		// User class
		// and save it in the UserRepository.
		Admin newUser = new Admin();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(user.getPassword());
		newUser.setAccountNonLocked(true);
		
	}
}
