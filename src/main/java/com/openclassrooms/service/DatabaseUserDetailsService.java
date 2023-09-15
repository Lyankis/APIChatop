//package com.openclassrooms.service;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.openclassrooms.repository.UserRepository;
//
//public class DatabaseUserDetailsService implements UserDetailsService{
//	
//	  private final UserRepository userRepository;
//	  private final UserDetailsMapper userDetailsMapper;
//
//	  // constructor ...
//
//	  @Override
//	  public UserDetails loadUserByUsername(String email) 
//	                         throws UsernameNotFoundException {
//	    UserCredentials userCredentials =
//	                    userRepository.findByEmail(email);
//	    return userDetailsMapper.toUserDetails(userCredentials);
//	  }
//}
