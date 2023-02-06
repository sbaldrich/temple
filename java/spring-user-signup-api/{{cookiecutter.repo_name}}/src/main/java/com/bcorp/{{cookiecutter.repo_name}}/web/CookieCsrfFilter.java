package com.bcorp.signup.web;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CookieCsrfFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(CookieCsrfFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CsrfToken token = ((CsrfToken) request.getAttribute(CsrfToken.class.getName()));
        response.setHeader(token.getHeaderName(), token.getToken());
        filterChain.doFilter(request, response);
    }
}
