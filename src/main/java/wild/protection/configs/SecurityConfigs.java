package wild.protection.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfigs extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/public/**","/assets/**","/css/**", "/js/**","/plugins/**").permitAll()  // Allow public access to "/" and "/public/**"
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/admin/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

}
