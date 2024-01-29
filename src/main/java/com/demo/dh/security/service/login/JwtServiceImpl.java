package com.demo.dh.security.service.login;

import com.demo.dh.security.model.entity.User;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import static org.springframework.cache.interceptor.SimpleKeyGenerator.generateKey;

@Service
public class JwtServiceImpl {

    @Value("${security.jwt.expiration-minutes}")
    private long EXPIRATION_MINUTES;

    @Value("${security.jwt.secret}")
    private String SECRET_KEY;

    public String generateToken(User user, Map<String, Object> extraClaims) {

        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_MINUTES * 60 * 1000));

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(this.generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key generateKey() {
        System.out.println("MAIN KEY: " + SECRET_KEY);
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
