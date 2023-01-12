package com.ll.exam.mybatis.base;

import com.ll.exam.mybatis.interceptor.BeforeActionInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final BeforeActionInterceptor beforeActionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir;

        ir = registry.addInterceptor(beforeActionInterceptor);
        ir.addPathPatterns("/**");
        ir.excludePathPatterns("/favicon.ico");
        ir.excludePathPatterns("/resource/**");
        ir.excludePathPatterns("/gen/**");
        ir.excludePathPatterns("/error");
    }
}