package com.demo.dh.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Bean
//    public CommandLineRunner createPasswordCommand() {
//		return args -> {
//            System.out.println(this.passwordEncoder.encode("customer2343"));
//            System.out.println(this.passwordEncoder.encode("admin2343"));
//        };
//    }
}
