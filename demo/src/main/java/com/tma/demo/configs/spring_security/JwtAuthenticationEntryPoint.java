package com.tma.demo.configs.spring_security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * This class will extend Spring's AuthenticationEntryPoint class and override its method commence.
 * It rejects every unauthenticated request and send error code 401
 */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable, AuthenticationSuccessHandler, AuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setHeader("log-detail-trace", "Token is expired, please try again.");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is expired, please try again!");
//        response.sendRedirect("/login");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request , HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
//        response.sendRedirect("/home");
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        UrlPathHelper pathHelper = new UrlPathHelper();
//        response.sendRedirect(pathHelper.getContextPath(request)+"/login?error");
    }
}
