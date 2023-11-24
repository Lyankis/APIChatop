package com.openclassrooms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.openclassrooms.service.JwtService;
import com.openclassrooms.service.UserService;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig{
	
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http
		.addFilterAfter(new JwtAuthentificationFilter(jwtService, userService), BasicAuthenticationFilter.class)
			.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("images/**").permitAll()
					.requestMatchers("api/auth/register").permitAll()
					.requestMatchers("api/auth/login").permitAll()
					
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
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} 
}