package ru.otus.project.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomLogoutFilter implements LogoutHandler {

    private final SecurityService securityService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        securityService.revokeToken();
        response.setHeader("referer", "http://localhost:8086/");
        try {
            response.sendRedirect("http://localhost:8010/api/oauth/logout");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
