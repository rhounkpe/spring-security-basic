package be.bt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	// Registre d'utilisateurs/roles, pour l'authentification
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth

	 	.inMemoryAuthentication().withUser("leila").password(encoder().encode("password")).roles("USER").and()
	 			.withUser("didier").password(encoder().encode("password")).roles("USER", "ADMIN");

	}
	
	// Règle d'accès aux ressources (ACM) Politique de sécurité
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http
			.authorizeRequests()
				.antMatchers("/public").permitAll()
				.antMatchers("/secret").hasRole("ADMIN")
				.and()
				.httpBasic();
	}
	
	@Bean
	BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
