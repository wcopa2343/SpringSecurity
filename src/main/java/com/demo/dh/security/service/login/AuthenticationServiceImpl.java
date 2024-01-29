package com.demo.dh.security.service.login;

import com.demo.dh.security.dto.AuthenticationRequest;
import com.demo.dh.security.dto.AuthenticationResponse;
import com.demo.dh.security.model.entity.User;
import com.demo.dh.security.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;


    private JwtServiceImpl jwtService;


    public AuthenticationServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtServiceImpl jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()
        );

        authenticationManager.authenticate(token);

        User user = this.userRepository.findByUsername(request.getUsername()).get();
        String jwt = jwtService.generateToken(user, this.generateExtraClaims(user));

        return new AuthenticationResponse(jwt);
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole());
        extraClaims.put("permissions", user.getAuthorities());

        return extraClaims;
    }
}
