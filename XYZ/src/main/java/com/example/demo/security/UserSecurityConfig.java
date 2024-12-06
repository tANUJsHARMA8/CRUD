package com.example.demo.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.HttpMediaTypeException;

@Configuration
public class UserSecurityConfig {
	
	@Bean
	public UserDetailsService userDetailService() {
		
	UserDetails admin = User.withUsername("tanuj").password(passwordEncoder().encode("tanuj")).roles("ADMIN").build();
	UserDetails user = User.withUsername("unknown").password(passwordEncoder().encode("1234")).roles("USER").build();
		
	return new InMemoryUserDetailsManager(admin, user);
	}
	
	
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	
	http.csrf(csrf->csrf.disable())
	.authorizeHttpRequests((e)->{
				/*
				 * e.requestMatchers(HttpMethod.POST, "/base/**").hasRole("ADMIN");
				 * e.requestMatchers(HttpMethod.GET, "/base/**").hasAnyRole("ADMIN","USER");
				 * e.requestMatchers(HttpMethod.PUT, "/base/**").hasAnyRole("ADMIN","USER");
				 * e.requestMatchers(HttpMethod.DELETE, "/base/**").hasRole("USER");
				 */
		e.anyRequest().authenticated();
		}).httpBasic(Customizer.withDefaults());
	
	return http.build();
	
}

@Bean
public PasswordEncoder passwordEncoder() {
	
	return new BCryptPasswordEncoder();
}
	

}
