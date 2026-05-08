package com.example.demo.Security;

import com.example.demo.enums.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.KeyRep;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String key = "mysecretkey123456789012345678901";
    private static final SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

    public String generateToken(Long userId, UserRole role) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("role", role.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(secretKey)
                .compact();
    }

    public String generateRefreshToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7)) // 7 gün
                .signWith(secretKey)
                .compact();
    }

    public Long extractUserId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        ;
        String subject = claims.getSubject();
        if (subject == null) {
            System.out.println("Subject not found in the token.");
            return null;
        }
        return Long.valueOf(subject);
    }

    public UserRole extractRole(String token) {
        Claims claim = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        String role = claim.get("role", String.class);
        return UserRole.valueOf(role);
    }

}

