package com.example.demo.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

public class jwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public jwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig, SecretKey secretKey) {

        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try{
            UsernameAndPasswordAuthenticatonRequest authenticatonRequest=
                    new ObjectMapper()
                    .readValue(request.getInputStream(),UsernameAndPasswordAuthenticatonRequest.class);//this will map the useraname and password coming from request to that specified class
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticatonRequest.getUsername(),
                    authenticatonRequest.getPassword()
            );
            Authentication authenticate = authenticationManager.authenticate(authentication);
            return authenticate;

        }catch (IOException e){
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {


        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities",authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(String.valueOf(LocalDateTime.now().plusWeeks(2))))
                .signWith(secretKey)
                .compact();

                response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix()+token);


    }

}
