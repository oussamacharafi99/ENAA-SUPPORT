package com.ENAA_SUPPORT.Config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationToken = request.getHeader("Authorization");
        if (authorizationToken != null && authorizationToken.startsWith("Bearer ")) {
            try {
                String jwt = authorizationToken.substring(7);
                Claims claims = Jwts.parser()
                        .setSigningKey(JwtAuth.SECRET_KEY)
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();

                String username = claims.getSubject();
                List<String> roles = claims.get("roles", List.class);
                Collection<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);

            } catch (Exception e) {
                response.setHeader("error message", e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
