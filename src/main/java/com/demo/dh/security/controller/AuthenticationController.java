package com.demo.dh.security.controller;

import com.demo.dh.security.dto.AuthenticationRequest;
import com.demo.dh.security.dto.AuthenticationResponse;
import com.demo.dh.security.service.login.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> loginAuth(@RequestBody @Validated AuthenticationRequest request) {

        AuthenticationResponse jwtDto = authenticationService.login(request);

        return ResponseEntity.ok(jwtDto);
    }

    @GetMapping("/public-access")
    public ResponseEntity<String> publicAccess() {

        return ResponseEntity.ok("This ENDPOINT is public");
    }
}
