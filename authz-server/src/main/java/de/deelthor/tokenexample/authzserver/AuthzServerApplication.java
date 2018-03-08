package de.deelthor.tokenexample.authzserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthzServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthzServerApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(@Value("${security.password.strength:10}") int strength) {
		return new BCryptPasswordEncoder(strength);
	}
}
