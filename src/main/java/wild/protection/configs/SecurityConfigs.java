package wild.protection.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfigs extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http
         .csrf().disable()
         .authorizeRequests()
             .antMatchers("/admin/register**").permitAll()
             .antMatchers("/img/**","/public/**","/css/**", "/js/**", "/assets/**", "/plugins/**","/").permitAll()
             .anyRequest().authenticated().and().formLogin().loginPage("/admin/login").permitAll()
             .and()
         .logout()
             .logoutUrl("/admin/logout")
             .logoutSuccessUrl("/admin/login?logout")
             .clearAuthentication(true)
             .permitAll();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
