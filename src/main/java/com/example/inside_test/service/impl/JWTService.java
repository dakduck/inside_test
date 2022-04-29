package com.example.inside_test.service.impl;

import com.example.inside_test.service.IJWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
public class JWTService implements IJWTService {

    private static final String TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.token.secret}")
    private String SECRET_KEY;

    @Value("${jwt.token.expired}")
    private long sessionTime;

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String separatedListOfAuthorities = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        claims.put("authorities", separatedListOfAuthorities);
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date expire = new Date(System.currentTimeMillis() + sessionTime);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expire)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    @Override
    public String extractUsername(String authorizationHeader) {
        return extractAllClaims(authorizationHeader).getSubject();
    }

    @Override
    public String extractAuthorities(String authorizationHeader) {
        Function<Claims, String> claimsListFunction = claims -> ((String) claims.get("authorities"));
        final Claims claims = extractAllClaims(authorizationHeader);
        return claimsListFunction.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();
    }
}
