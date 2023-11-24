package com.openclassrooms.service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.model.User;
import com.openclassrooms.repository.UserRepository;

import dto.Response;
import dto.JwtAuthenticationResponse;
import request.SignUpRequest;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
    private JwtService jwtService;
	
	public Optional<User> findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}	
	
	public static boolean isValidEmail(String email) {
	    // Utiliser une expression régulière pour vérifier la conformité de l'adresse e-mail
	    String regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]+$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(email);

	    // Retourner le résultat de la correspondance
	    return matcher.matches();
	}
	
	public ResponseEntity<Response> register(User user) {
		
	    Long time = Date.from(Instant.now()).getTime();

	    User newUser = new User();
	    newUser.setEmail(user.getEmail());
	    newUser.setName(user.getName());

	    newUser.setPassword(passwordEncoder.encode(user.getPassword()));

	    newUser.setCreated_at(new Date(time));
	    newUser.setUpdated_at(new Date(time));

	    // Vérifier si l'email est déjà utilisé
	    Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
	    if (existingUser.isPresent()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(new Response(400, "L'email est déjà utilisé"));
	    }

	    // Vérifier si l'email est conforme
	    if (!isValidEmail(user.getEmail())) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(new Response(400, "L'email n'est pas conforme"));
	    }

	    // Enregistrer le nouvel utilisateur
	    userRepository.save(newUser);

	 // Retourner une réponse 200 OK avec message
	    return ResponseEntity.status(HttpStatus.OK)
	            .body(new Response(200, "Utilisateur enregistré avec succès !"));
	}
	
	public ResponseEntity<JwtAuthenticationResponse> login(SignUpRequest request) throws Exception {
	    User user = new User();
	    user.setEmail(request.getEmail());
	    user.setPassword(passwordEncoder.encode(request.getPassword()));

	    Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
	    if (!userOptional.isPresent()) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(new JwtAuthenticationResponse(null, "Rejeté", "Email ou mot de passe invalide !"));
	    }

	    String jwt = jwtService.generateToken(userOptional.get());
	    return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}
	
}
