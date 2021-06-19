package com.iti.jets.reportingsystem.utils.jwt;

import com.iti.jets.reportingsystem.services.impls.CustomUserDetailsService;
import com.iti.jets.reportingsystem.utils.helpers.RepoHelper;
import com.iti.jets.reportingsystem.utils.helpers.ThreadLocalToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final RepoHelper repoHelper;

    @Autowired
    public JwtFilter(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService, RepoHelper repoHelper){
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
        this.repoHelper = repoHelper;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String token = null;
        String email = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            email = jwtUtil.extractUsername(token);
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

            if (jwtUtil.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

//        RepoHelper.token = token;
//        ThreadLocal<String> threadLocalTokenValue = new ThreadLocal<>();
//        threadLocalTokenValue.set(token);
//        repoHelper.setThreadLocalValue(threadLocalTokenValue);

        ThreadLocalToken.create(token);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
