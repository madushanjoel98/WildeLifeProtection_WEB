package wild.protection.configs;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import wild.protection.models.Admin;
import wild.protection.repository.UserRepository;



@Component
public class AuthProvider implements AuthenticationProvider {
	private static final int ATTEMPTS_LIMIT = 3;

	@Autowired
	private SecurityUserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		Optional<Admin> userOptional = userRepository.findByUsername(username);
System.out.println(":::::"+username);
		if (!userOptional.isPresent()) {
			throw new BadCredentialsException("User not found");
		}

		Admin user = userOptional.get();

		if (!user.isAccountNonLocked()) {
			throw new LockedException("Account is locked");
		}

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("Invalid password");
		}

		// Authentication successful
		return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
	}

	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
