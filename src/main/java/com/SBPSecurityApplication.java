package com;

import com.dto.UsersPojo;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SBPSecurityApplication implements CommandLineRunner {

    @Autowired
    UsersService empService;

    public static void main(String[] args) {
        SpringApplication.run(SBPSecurityApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        empService.saveEmp(new UsersPojo(0,"sai","sai123","ROLE_ADMIN"));
        empService.saveEmp(new UsersPojo(0,"pranith","pranith123","ROLE_USER"));
    }
}
