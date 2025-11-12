package com.mcmanuel.HospitalManagementSystem.configuration;

import com.mcmanuel.HospitalManagementSystem.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@RequiredArgsConstructor
@Configuration
public class JwtFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException, UsernameNotFoundException {
        String header = request.getHeader("Authorization");
        String token;
        String username;
        System.out.println("In the outer or outside of the method");

        if (header != null && header.startsWith("Bearer ")) {
            System.out.println("In the inner or inside of the method");
            token = header.substring(7);
            username= jwtService.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() ==null) {
                UserDetails userDetail = userDetailsService.loadUserByUsername(username);

               if(userDetail!=null && jwtService.validateToken(userDetail,token)){
                   UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetail,null,userDetail.getAuthorities());
                   authToken.setDetails(request);
                   SecurityContextHolder.getContext().setAuthentication(authToken);
                    System.out.println("In the last");

               }
            }
            filterChain.doFilter(request,response);
        }
        filterChain.doFilter(request,response);
    }
}
