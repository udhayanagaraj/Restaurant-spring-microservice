package com.example.security.controller;


import com.example.security.dto.AuthRequest;
import com.example.security.entity.UserCredential;
import com.example.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addUser(@RequestBody UserCredential user){
        try {

            return authService.addUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest){
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
            if(authentication.isAuthenticated()){
                return authService.generateToken(authRequest.getUsername());
            }else {
                throw new RuntimeException("Invalid access");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        try {
            authService.validateToken(token);
            return "Token is valid";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
