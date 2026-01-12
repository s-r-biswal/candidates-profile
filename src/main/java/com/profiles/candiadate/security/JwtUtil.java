package com.profiles.candiadate.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET = "secret-key";

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getAuthorities().iterator().next().getAuthority())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 36000000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
    }

    public boolean validToken(String token) {
        try{
            extractClaims(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
