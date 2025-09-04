package com.mcmanuel.HospitalManagementSystem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/home/get")
    public String homePage(){
        return "welcome" ;
    }
}
