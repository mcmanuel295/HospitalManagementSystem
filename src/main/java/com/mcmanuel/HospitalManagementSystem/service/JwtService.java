package com.mcmanuel.HospitalManagementSystem.service;

import com.mcmanuel.HospitalManagementSystem.pojo.LoginRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final String key;
    private AuthenticationManager authenticationManager;

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
        return extractDate(token).after(new Date()) &&
                (userDetails.getUsername().equals(extractUsername(token)));
    }

    private Date extractDate(String jwt){
        return extractClaim(jwt,Claims::getExpiration);
    }


    public String login(LoginRequest loginRequest) {
        Authentication auth =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        if (auth.isAuthenticated()){
            return "successful";
        }
        else return "failed";
    }
}
