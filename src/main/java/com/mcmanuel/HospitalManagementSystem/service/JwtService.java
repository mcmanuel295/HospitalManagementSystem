package com.mcmanuel.HospitalManagementSystem.service;

import com.mcmanuel.HospitalManagementSystem.entity.User;
import com.mcmanuel.HospitalManagementSystem.pojo.LoginRequest;
import com.mcmanuel.HospitalManagementSystem.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private String key="";
    private final UserRepository userRepo;
    private final ApplicationContext context;


    @PostConstruct
    private void postConstruct() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKeyString = keyGen.generateKey();
        key = Base64.getEncoder().encodeToString(secretKeyString.getEncoded());
    }


    private SecretKey getKey() {
        byte[] bytes = Base64.getDecoder().decode(key);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+(1000*60*60*3)))
                .and()
                .signWith(getKey())
                .compact();
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
        try {
            AuthenticationManager manager = context.getBean(AuthenticationManager.class);
            Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            if(authentication.isAuthenticated()) {
                String jwt = generateToken(loginRequest.getEmail());
                System.out.println(loginRequest.getEmail() + " jwt: " + jwt);
                return "successful";
            } else {
                return "error";
            }

        }
        catch (BadCredentialsException e) {
            System.out.println("Bad credentials for user: " + loginRequest.getEmail());
            return "error: bad credentials";
        }
        catch (Exception e) {
            System.out.println("Login error for user: " + loginRequest.getEmail() + " - " + e.getMessage());
            return "error: authentication failed";
        }
    }
}
