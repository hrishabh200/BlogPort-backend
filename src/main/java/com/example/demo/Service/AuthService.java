package com.example.demo.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.memory.UserAttributeEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Entity.EmailNotification;
import com.example.demo.Entity.User;
import com.example.demo.Entity.VerificationToken;
import com.example.demo.Exceptions.BlogPortException;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Repository.VerificationTokenRepo;
import com.example.demo.dto.UserVerification;

@Service
//Auth service is used for creating user object,saving it to database and sending out activation email etc.
public class AuthService {
//	@Autowired
//	User user; 
//	@Autowired
//	PasswordEncoder passwordEncoder;
	@Autowired
	UserRepo userRepo; 
	@Autowired
	VerificationTokenRepo verificationTokenRepo;
//	@Autowired
//	VerificationToken verificationToken;
	@Autowired
	MailService mailService;
	@Autowired 
	PasswordEncoder passwordEncoder;
	@Transactional
	//Using autowire we use field injection-Spring assigns the dependencies directly to the fields. It is different than Constructor Injection or Setter based Dependency Injection. 
	//The interesting thing to understand is, Spring injects the dependencies, even if the field is private. 
	// Spring uses Java Reflections to do so
	public void signup(UserVerification userVerify)
	{
		User user = new User();
		user.setUsername(userVerify.getUserName());
	    user.setPassword(passwordEncoder.encode(userVerify.getPassword()));;
		user.setEmail(userVerify.getEmail());
		user.setCreated(Instant.now());
		user.setEnabled(false);
		//we encode BcryptHashing algorithm to encrypt password.
		//spring security enables it using BCryptPasswordEncoder method
		userRepo.save(user);
		//saving the user in repository
		  String token = generateVerificationToken(user);
		 mailService.sendMail(new EmailNotification("Please Activate your Account",
	                user.getEmail(), "Thank you for signing up to BlogPort, " +
	                "please click on the below url to activate your account : " +
	                "http://localhost:8080/api/auth/accountVerification/" + token));
	    }
	
	  private String generateVerificationToken(User user) {
		  VerificationToken verificationToken = new VerificationToken();
	        String token = UUID.randomUUID().toString(); //generates random 128 bit value.
	        verificationToken.setToken(token);
	        verificationToken.setUser(user);
	        verificationTokenRepo.save(verificationToken); //we save the token in database as user can verify later after couple of days.
        return token;
	  }
	        //after this we send activation emails to user.
	        // we use template engine called thymeLeaf to send html templetes.	    }
        public void verifyAccount(String token)
        {
        	Optional<VerificationToken>  verificationToken  =verificationTokenRepo.findByToken(token);
        	verificationToken.orElseThrow(()-> new BlogPortException("Invalid Token"));
        	 fetchUserAndEnable(verificationToken.orElseThrow(() -> new BlogPortException("Invalid Token")));
        }
        private void fetchUserAndEnable(VerificationToken verificationToken) {
            String username = verificationToken.getUser().getUsername();
            User user = userRepo.findByUsername(username).orElseThrow(() -> new BlogPortException("User not found with name - " + username));
            user.setEnabled(true);
            userRepo.save(user);
        }
        
}
