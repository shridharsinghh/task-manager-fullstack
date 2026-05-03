package com.taskmanager.taskmanager.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // ✅ IMPORTANT FIX
        String path = request.getRequestURI();

        // 🔥 Allow auth endpoints without token
        if (path.startsWith("/api/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 🔐 Get Authorization header
        String authHeader = request.getHeader("Authorization");

        // ❌ No token → block
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        // 🔑 Extract token
        String token = authHeader.substring(7);

        // ❌ Invalid token → block
        if (!jwtUtil.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        // ✅ Valid → continue
        filterChain.doFilter(request, response);
    }
}