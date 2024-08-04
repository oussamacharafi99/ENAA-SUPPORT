package com.ENAA_SUPPORT.Config;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Set;

@Service
public class JwtAuth {

    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(String username, Set<String> roles) {
        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 24 hours expiration
                .signWith(SECRET_KEY)
                .compact();
    }
}
