package com.openclassrooms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.openclassrooms.service.UserService;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Autowired
	UserService userService;
	
//	@Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests((authz) -> authz
//                .requestMatchers(antMatcher("/user/**")).hasRole("USER")
//                .requestMatchers(antMatcher(HttpMethod.POST, "/user/**")).hasRole("ADMIN")
//                .requestMatchers(regexMatcher(".*\\?x=y")).hasRole("SPECIAL") // matches /any/path?x=y
//                .anyRequest().authenticated()
//            );
//        return http.build();
//    }
                                                                                                                                                                                                                                                            
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((authz) -> authz.requestMatchers("/auth/register").permitAll()
////                .requestMatchers("/**").permitAll()
//				.requestMatchers("/swagger-ui/**", "/v3/api-docs/**")
//				.permitAll()
//				.requestMatchers("/app/**/*.{js,html}")
//				.permitAll()
//
////                .requestMatchers("/api/admin/**").hasRole("ADMIN")
////                .requestMatchers("/api/user/**").hasRole("USER")
//
//				.requestMatchers().authenticated().and().httpBasic());
//		return http.build();
//	}

//	FONTIONNE
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http
//			.authorizeHttpRequests(authorizeRequests  -> authorizeRequests
//				.anyRequest().permitAll())
//				.csrf(AbstractHttpConfigurer::disable);
//
//		return http.build();
//	}
//	JUSQU'ICI

//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}

	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("/register").permitAll()
					.anyRequest().authenticated()
			)
//			SI PAS CA 403 lors du register
			.csrf(AbstractHttpConfigurer::disable)
			
			
			.formLogin(formLogin -> formLogin.permitAll()
			)
			.rememberMe(org.springframework.security.config.Customizer.withDefaults());
		
		
		return http.build();
	}
	
	
//	@Configuration
//	public class CrossOriginConfig {
//
//	    @Bean
//	    public WebMvcConfigurer corsConfigurer() {
//	        return new WebMvcConfigurer() {
//	            @Override
//	            public void addCorsMappings(CorsRegistry registry) {
//	                registry
//	                        .addMapping("/**")
//	                        .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS");
//	            }
//	        };
//	    }
//
//	}
	
	
//	A METTRE
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
//	    UserDetails user = User.withUsername("user")
//	      .password(encoder().encode("userPass"))
//	      .roles("USER")
//	      .build();
//	    return new InMemoryUserDetailsManager(user);
//	}
//	 
//	@Bean
//	public PasswordEncoder encoder() {
//	    return new BCryptPasswordEncoder();
//	}
//	JUSQU'ICI
	
}