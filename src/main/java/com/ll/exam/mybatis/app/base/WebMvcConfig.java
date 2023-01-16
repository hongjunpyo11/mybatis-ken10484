package com.ll.exam.mybatis.app.base;

import com.ll.exam.mybatis.app.interceptor.BeforeActionInterceptor;
import com.ll.exam.mybatis.app.interceptor.NeedToAdminInterceptor;
import com.ll.exam.mybatis.app.interceptor.NeedToLoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final BeforeActionInterceptor beforeActionInterceptor;
    private final NeedToLoginInterceptor needToLoginInterceptor;
    private final NeedToAdminInterceptor needToAdminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir;

        ir = registry.addInterceptor(beforeActionInterceptor);
        ir.addPathPatterns("/**");
        ir.excludePathPatterns("/favicon.ico");
        ir.excludePathPatterns("/resource/**");
        ir.excludePathPatterns("/gen/**");
        ir.excludePathPatterns("/error");

        ir = registry.addInterceptor(needToLoginInterceptor);
        ir.addPathPatterns("/article/write");
        ir.addPathPatterns("/member/me");

        ir = registry.addInterceptor(needToAdminInterceptor);
        ir.addPathPatterns("/adm/**");
    }
}

/**
 * article/write 에서는 먼저 등록된 beforeActionInterceptor 먼저 작동 후 needToLoginInterceptor 작동
 */