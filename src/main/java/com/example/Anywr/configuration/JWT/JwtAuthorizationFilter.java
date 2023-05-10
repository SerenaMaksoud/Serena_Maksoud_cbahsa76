package com.example.Anywr.configuration.JWT;

import com.example.Anywr.configuration.properties.ConfigPropertyReader;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    @Autowired
    private final ConfigPropertyReader jwtConfig;

    @Autowired
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, ConfigPropertyReader jwtConfig) {
        super(authenticationManager);
        this.jwtConfig = jwtConfig;
    }

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.jwtConfig = null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader(jwtConfig.getAuthorizationHeader());
        if (StringUtils.isEmpty(header) || !header.startsWith(jwtConfig.getTokenPrefix())) {
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(jwtConfig.getAuthorizationHeader()).replace(jwtConfig.getTokenPrefix(), "");
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSecretKey().getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            if (StringUtils.isEmpty(username)) {
                return null;
            }

            return new UsernamePasswordAuthenticationToken(username, null, null);
        } catch (SignatureException ex) {
            return null;
        }
    }
}