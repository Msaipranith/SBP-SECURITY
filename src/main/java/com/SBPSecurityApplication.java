package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.controller.ProController;

@SpringBootApplication
public class SBPSecurityApplication {

    @Autowired
    ProController controller;

    public static void main(String[] args) {
        SpringApplication.run(SBPSecurityApplication.class, args);
    }


}
