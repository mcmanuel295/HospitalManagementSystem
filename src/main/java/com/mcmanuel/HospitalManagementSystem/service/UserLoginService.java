package com.mcmanuel.HospitalManagementSystem.service;

import com.mcmanuel.HospitalManagementSystem.pojo.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final ApplicationContext context;
    private final JwtService jwtService;

    public String login(LoginRequest loginRequest) {
        try {
            AuthenticationManager manager = context.getBean(AuthenticationManager.class);
            Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            if(authentication.isAuthenticated()) {
                String jwt = jwtService.generateToken(loginRequest.getEmail());
                System.out.println(loginRequest.getEmail() + " jwt: " + "successful");
                return jwt;
            } else {
                return "error";
            }

        }
        catch (BadCredentialsException e) {
            System.out.println("Bad credentials for user: " + loginRequest.getEmail());
            return "error: bad credentials";
        }
        catch (Exception e) {
            System.out.println("Login error for user: " + loginRequest.getEmail() + " - " + e.getMessage());
            return "error: authentication failed";
        }
    }
}
