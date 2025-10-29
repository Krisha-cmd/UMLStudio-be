package com.UMLStudio.backend.security;

import com.UMLStudio.backend.model.User;
import com.UMLStudio.backend.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter implements Filter {

    private final JwtServicePort jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtServicePort jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;

        String header = httpReq.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                if (jwtService.validateToken(token)) {
                    String username = jwtService.getUsernameFromToken(token);
                    if (username != null) {
                        User user = userRepository.findByUsername(username).orElse(null);
                        if (user != null) {
                            // set an attribute so controllers/filters can know the authenticated user
                            httpReq.setAttribute("authenticatedUser", user);
                        }
                    }
                }
            } catch (Exception ex) {
                // ignore and continue without an authenticated user
            }
        }

        chain.doFilter(request, response);
    }
}
