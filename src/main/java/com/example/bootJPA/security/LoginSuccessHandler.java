package com.example.bootJPA.security;

import com.example.bootJPA.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.io.IOException;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy
            = new DefaultRedirectStrategy();

    private RequestCache requestCache =
            new HttpSessionRequestCache();

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 1. lastlogin 기록 : authentication.getName() ==> username
        boolean isOk = userService.updateLastLogin(authentication.getName());

        HttpSession ses = request.getSession();;
        if(!isOk || ses == null){
            return;
        }else {
            ses.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        redirectStrategy.sendRedirect(request, response,
                savedRequest != null ? savedRequest.getRedirectUrl() : "/board/list");

    }

}
