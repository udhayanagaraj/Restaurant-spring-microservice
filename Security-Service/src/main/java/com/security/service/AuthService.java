package com.example.security.service;


import com.example.security.entity.UserCredential;
import com.example.security.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserCredentialRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService service;


    public String addUser(UserCredential credential){
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);
        return "user added";
    }

    public String generateToken(String username){
        return service.generateToke(username);
    }

    public void validateToken(String token){
        service.validateToken(token);
    }

}
