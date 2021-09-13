package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.AuthService;
import com.example.demo.dto.UserVerification;

@RestController //Rest apis are stateless
@RequestMapping("/api/auth")
public class AuthController 
{

@Autowired
AuthService authService;
@PostMapping("/signup")
public ResponseEntity<String> signup(@RequestBody UserVerification userVerify) {
        authService.signup(userVerify);
        return new ResponseEntity<>("User Registration Successful",
                HttpStatus.OK);
   
}
@GetMapping("accountVerification/{token}")
public ResponseEntity<String> verifyAccount(@PathVariable String token)
{
	authService.verifyAccount(token);
	   return new ResponseEntity<>("Account Activated Successfully", HttpStatus.OK);
}


}
