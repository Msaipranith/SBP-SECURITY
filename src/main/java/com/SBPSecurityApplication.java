package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.controller.ProController;
import com.dto.UsersPojo;

@SpringBootApplication
public class SBPSecurityApplication implements CommandLineRunner {

	@Autowired
	ProController controller;

	public static void main(String[] args) {
		SpringApplication.run(SBPSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

//			String url="http://localhost:2222/employee/findAllEmp";
//			
//			RestTemplate restTemplate=new RestTemplate();
//			ResponseEntity<String> op = restTemplate.getForEntity(url, String.class);
//		System.out.println(op);

		controller.saveEmp(new UsersPojo(1, "pranith", "Pranith@0418"));
		controller.saveEmp(new UsersPojo(2, "sai", "Sai@0418"));
		controller.saveEmp(new UsersPojo(3, "saipranith", "SaiPranith@0418"));
		
	// from  branch b1

	}

}
