package com.turkcell.spring_cqrs.core.security.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.turkcell.spring_cqrs.core.security.jwt.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Her istekte devreye gir, varsa JWT'i doğrula,

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // request -> istek
        // response -> response'ın o ana kadarki oluşan halini
        // filterChain -> zincirin kendisi
        String jwtHeader = request.getHeader("Authorization");

        if (jwtHeader != null) {
            String token = jwtHeader.substring(7);
            
            // JWT'i doğrula, kullanıcıyı bul ve sisteme tanıt
            try {
                if(jwtService.isTokenValid(token)) {
                    // Kullanıcıyı sisteme tanıt
                }
            } catch(Exception e) {

            }
        }

        filterChain.doFilter(request, response); // chaini ilerlet
    }

}
