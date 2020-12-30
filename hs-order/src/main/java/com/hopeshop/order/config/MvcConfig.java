package com.hopeshop.order.config;

import com.hopeshop.cart.config.JwtProperties;
import com.hopeshop.cart.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author silence
 * @create 2020-01-27
 */
@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private JwtProperties jwtProperties;

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor(jwtProperties);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/swagger-ui.html").excludePathPatterns("/webjars/**").excludePathPatterns("/swagger-resources/**");
    }
}
