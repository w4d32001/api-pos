package com.nocode.main.infrastructure.shared.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256).toString();
    private static final Key SIGNING_KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public String generateToken(String id, String userName) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("user_name", userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SIGNING_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractId(String token) {
        return (String) extractClaims(token).get("id");
    }

    public boolean isTokenValid(String token, String id) {
        return extractId(token).equals(id) && !isTokenExpired(token);
    }
}
