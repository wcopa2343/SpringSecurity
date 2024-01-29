package com.demo.dh.security.config.security.filter;

import com.demo.dh.security.model.entity.User;
import com.demo.dh.security.repository.UserRepository;
import com.demo.dh.security.service.login.JwtServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private JwtServiceImpl jwtService;

    private UserRepository userRepository;

    public JwtAuthenticationFilter(JwtServiceImpl jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. get the header

        String authHeader = request.getHeader("Authorization");

        if(authHeader ==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);

            return;

        }

        // 2. get the jwt from header

        String jwt = authHeader.split(" ")[1];

        // 3. Get subject/username

        String username = this.jwtService.extractUsername(jwt);

        // 4. Setter a object Authentication from SecurityContext

        User user = this.userRepository.findByUsername(username).get();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 5. Execute the next filter
        filterChain.doFilter(request, response);


    }
}
