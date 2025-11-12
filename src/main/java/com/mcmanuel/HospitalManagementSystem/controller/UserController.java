package com.mcmanuel.HospitalManagementSystem.controller;

import com.mcmanuel.HospitalManagementSystem.pojo.LoginRequest;
import com.mcmanuel.HospitalManagementSystem.service.JwtService;
import com.mcmanuel.HospitalManagementSystem.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserLoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Validated LoginRequest loginRequest){
        String login = loginService.login(loginRequest);

        if(login.equals("error")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(login, HttpStatus.OK);
    }
}
