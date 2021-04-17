package com.sleep.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsUtils;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("zhangsan").password("123456").roles("GUEST");
//        auth.inMemoryAuthentication().withUser("zhihao.miao").password("123456").roles("USER");
//        auth.inMemoryAuthentication().withUser("lisi").password("12345678").roles("USER", "ADMIN");
    }

    protected void configure(HttpSecurity http) throws Exception {

        //post请求默认的都开启了csrf的模式，所有post请求都必须带有token之类的验证信息才可以进入登陆页面，这边是禁用csrf模式
        //http.csrf().disable();

        //表示所有的get请求都不需要权限认证
        //http.authorizeRequests().antMatchers(HttpMethod.GET).access("permitAll");

        //对/hello 进行匹配，不管HTTP METHOD是什么
        //http.authorizeRequests().antMatchers("/v1/hello").hasRole("USER");

        //匹配/hello，且http method是POST，需要权限认证
        //http.authorizeRequests().antMatchers(HttpMethod.POST, "/v1/world").hasRole("USER");

        //匹配 /hello，且http method是GET，不需要权限认证
        //http.authorizeRequests().antMatchers(HttpMethod.GET, "/v1/hello").access("permitAll");

        //匹配/admin，并且http method不管是什么，需要admin权限
//        http.authorizeRequests().antMatchers("/v1/admin").hasRole("ADMIN");
//
//
//        http.authorizeRequests().antMatchers("/**/*.html").access("permitAll");
//        http.authorizeRequests().antMatchers("/**/*.css").access("permitAll");
//        http.authorizeRequests().antMatchers("/**/*.js").access("permitAll");
//        http.authorizeRequests().antMatchers("/**/*.png").access("permitAll");

//        http.authorizeRequests().anyRequest().authenticated();
//
//        http.formLogin();
//        http.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

       // http
                // 由于使用的是JWT，我们这里不需要csrf
              //  .csrf().disable()
                // 基于token，所以不需要session
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
               // .authorizeRequests()
                // 所有 / 的所有请求 都放行
                //.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();   //对preflight放行
               // .antMatchers("/*").permitAll()
//                .antMatchers("/u").denyAll()
//                .antMatchers("/article/**").permitAll()
//                .antMatchers("/video/**").permitAll()
//                .antMatchers("/api/**").permitAll()
//                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**","/swagger-ui.html", "/webjars/**")
//                .permitAll()
//                .antMatchers("/manage/**").hasRole("ADMIN") // 需要相应的角色才能访问
                // 除上面外的所有请求全部需要鉴权认证
                //.anyRequest().authenticated();

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/*").permitAll();//allow CORS option calls
//                .antMatchers("/resources/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .and()
//                .httpBasic();

    }
}