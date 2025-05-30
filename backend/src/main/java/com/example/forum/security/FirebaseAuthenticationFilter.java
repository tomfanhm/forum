package com.example.forum.security;

import com.example.forum.exception.AuthException;
import com.example.forum.exception.EmailNotVerifiedException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class FirebaseAuthenticationFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService customUserDetailsService;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    private final List<String> excludedPaths = Arrays.asList(
            "/api/public/**",
            "/api/auth/**"
    );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return excludedPaths.stream()
                .anyMatch(pattern -> pathMatcher.match(pattern, path));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new AuthException("Authorization header missing or not Bearer.");
        }

        FirebaseToken decoded;
        try {
            decoded = FirebaseAuth.getInstance().verifyIdToken(header.substring(7));
        } catch (FirebaseAuthException e) {
            throw new AuthException("Invalid Firebase token.");
        }

        if (!decoded.isEmailVerified()) {
            throw new EmailNotVerifiedException("Email not verified");
        }

        UserDetails user;
        try {
            user = customUserDetailsService.loadUserByUsername(decoded.getUid());
        } catch (UsernameNotFoundException e) {
            throw new AuthException("User not found in system.");
        }

        if (!user.isEnabled()) {
            throw new AuthException("User account is inactive.");
        }

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        chain.doFilter(request, response);
    }
}