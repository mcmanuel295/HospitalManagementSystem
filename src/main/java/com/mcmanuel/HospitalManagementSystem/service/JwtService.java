package com.mcmanuel.HospitalManagementSystem.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecretKeyAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.function.Function;

@Service
public class JwtService {
    private final String key;

    public JwtService() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("Hmac256");
        SecretKey secretKeyString = keyGen.generateKey();
        key = Base64.getEncoder().encodeToString(secretKeyString.getEncoded()) ;

    }

    private SecretKey getKey() {
        byte[] bytes = Base64.getDecoder().decode(key);
        return Keys.hmacShaKeyFor(bytes);
    }


    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }


    private <T>T extractClaim(String token, Function<Claims,T> resolver){
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String jwt){
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public boolean validateToken(UserDetails userDetails,String token) {
        return false;
    }
}
