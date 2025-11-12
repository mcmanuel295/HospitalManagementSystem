package com.mcmanuel.HospitalManagementSystem.service;

import com.mcmanuel.HospitalManagementSystem.pojo.LoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
                log.info("jwt: successful: {}", loginRequest.getEmail());
                return jwt;
            }
            else {
                return "error";
            }

        }
        catch (BadCredentialsException e) {
            log.error("Bad credentials for user:{} ",loginRequest.getEmail());
            return "error: bad credentials";
        }
        catch (Exception e) {
            log.error("Login error for user: {} - {}", loginRequest.getEmail(),e.getMessage());
            return "error: authentication failed";
        }
    }
}
