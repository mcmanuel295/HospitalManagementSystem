package com.mcmanuel.HospitalManagementSystem;

import com.mcmanuel.HospitalManagementSystem.service.impl.OwnerServiceImpl;
import com.mcmanuel.HospitalManagementSystem.service.intf.AdminService;
import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class HospitalManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalManagementSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner addOwner(AdminService adminService,OwnerServiceImpl ownerService){
		return args -> {
			try {
				adminService.addAdminByEmail(ownerService.addOwner().getEmail());
				log.info("admin added");
			}
			catch (EntityExistsException ex){
				log.error("Entity already exist{}",ex.getMessage());
			}
			catch (Exception ex){
				log.error("error occurred: {}",ex.getMessage());
			}
		};
	}
}
