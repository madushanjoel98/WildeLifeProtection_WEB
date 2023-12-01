package wild.protection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class WildeLifeProtectionWebApplication {
//New comment
	public static void main(String[] args) {
		SpringApplication.run(WildeLifeProtectionWebApplication.class, args);
	}

}
