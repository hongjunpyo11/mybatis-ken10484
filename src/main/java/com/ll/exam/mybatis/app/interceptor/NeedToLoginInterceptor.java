package com.ll.exam.mybatis.app.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Slf4j
public class NeedToLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("NeedToLoginInterceptor::preHandle 실행됨");

        HttpSession session = request.getSession();

        Long loginedMemberId = (Long) session.getAttribute("loginedMemberId");

        boolean isLogined = loginedMemberId != null; // 임시

        if (isLogined == false) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().append("로그인 후 이용바랍니다.");

            return false;

        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}

