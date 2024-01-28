package com.demo.dh.security.controller;

import com.demo.dh.security.dto.AuthenticationRequest;
import com.demo.dh.security.dto.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Validated AuthenticationRequest request) {

        return null;
    }

    @GetMapping("/public-access")
    public ResponseEntity<String> publicAccess() {

        return ResponseEntity.ok("This ENDPOINT is public");
    }
}
