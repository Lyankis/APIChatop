package com.openclassrooms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.openclassrooms.repository.UserRepository;
import com.openclassrooms.service.UserService;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig{
	
	@Autowired
	UserService userService;
	private final UserRepository userRepository = null;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http
		.addFilter(new JwtAuthentificationFilter())
			.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("/register").permitAll()
					.requestMatchers("/auth/signup").permitAll()
					
					.requestMatchers(							
							// -- Swagger UI v2
				            "/v2/api-docs",
				            "/swagger-resources",
				            "/swagger-resources/**",
				            "/configuration/ui",
				            "/configuration/security",
				            "/swagger-ui.html",
				            "/webjars/**",
				            // -- Swagger UI v3 (OpenAPI)
				            "/v3/api-docs/**",
				            "/swagger-ui/**"
			                )
			        .permitAll()
					
					.anyRequest().authenticated()
					
					
			)
//			SI PAS CA 403 lors du register
			.csrf(AbstractHttpConfigurer::disable)
			
			
			.formLogin(formLogin -> formLogin.permitAll()
			)
			.rememberMe(org.springframework.security.config.Customizer.withDefaults());
		
		
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return username -> userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} 
}