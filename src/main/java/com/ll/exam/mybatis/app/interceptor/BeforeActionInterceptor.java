package com.ll.exam.mybatis.app.interceptor;

import com.ll.exam.mybatis.app.base.rq.Rq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
@RequiredArgsConstructor
public class BeforeActionInterceptor implements HandlerInterceptor {
    private final Rq rq;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("BeforeActionInterceptor::preHandle 실행됨");

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}


