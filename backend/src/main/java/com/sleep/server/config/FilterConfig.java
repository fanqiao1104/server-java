package com.sleep.server.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean CorsFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        //注入过滤器
        registrationBean.setFilter(new CorsFilter());
        //过滤器名称
        registrationBean.setName("CorsFilter");
        //拦截规则
        registrationBean.addUrlPatterns("/*");
        //过滤器顺序
        registrationBean.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);

        return registrationBean;
    }

}
