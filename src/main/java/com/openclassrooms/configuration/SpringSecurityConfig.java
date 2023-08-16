package com.openclassrooms.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
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

}