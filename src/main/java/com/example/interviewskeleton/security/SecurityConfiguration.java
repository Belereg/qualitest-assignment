package com.example.interviewskeleton.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new HeaderBasedAuthFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/greet/**").permitAll()
                                .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(this::isGreetRequest)
                );

        return http.build();
    }

    private static class HeaderBasedAuthFilter implements Filter {
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String header = httpRequest.getHeader("X-Auth-Token");

            if ("such-secure-much-wow".equals(header)) {
                chain.doFilter(request, response);
            } else {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }
    }

    private boolean isGreetRequest(HttpServletRequest request) {
        return request.getMethod().equals(HttpMethod.GET.name()) &&
                request.getRequestURI().startsWith("/greet/");
    }
}
