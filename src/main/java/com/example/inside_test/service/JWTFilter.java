package com.example.inside_test.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
public class JWTFilter extends OncePerRequestFilter {
    private static final String TOKEN_PREFIX = "Bearer ";

    private final IJWTService IJWTService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String username = null;
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            username = IJWTService.extractUsername(authorizationHeader);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            String commaSeparatedListOfAuthorities = IJWTService.extractAuthorities(authorizationHeader);
            List<? extends GrantedAuthority> authorities =
                    AuthorityUtils.commaSeparatedStringToAuthorityList(commaSeparatedListOfAuthorities);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
