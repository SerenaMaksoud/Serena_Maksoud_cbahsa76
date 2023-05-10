package com.example.Anywr.configuration.JWT;
import com.example.Anywr.datahandlers.services.Impl.UserDetailsServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        String header = request.getHeader("Authorization");
//        if (StringUtils.isEmpty(header) || !header.startsWith("Bearer ")) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        String token = header.replace("Bearer ", "");
//        Claims claims = jwtUtils.getClaimsFromToken(token);
//
//        if (claims != null && claims.getSubject() != null) {
//            Authentication authentication = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, null);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        chain.doFilter(request, response);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = jwtUtils.resolveToken(request);
            if (jwt != null && jwtUtils.validateToken(jwt)) {
                String username = jwtUtils.getUsername(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Failed to authenticate user: " + e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
