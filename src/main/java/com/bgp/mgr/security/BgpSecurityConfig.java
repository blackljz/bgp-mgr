package com.bgp.mgr.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BgpSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()//定义当需要用户登录时候，转到的登录页面。
                .and()
                .authorizeRequests()//定义哪些URL需要被保护、哪些不需要被保护
                .anyRequest()//任何请求,登录后可以访问
                .authenticated()
                .and()
                .csrf().disable()//关闭csrf防护
                .headers().frameOptions().sameOrigin();//允许frame嵌套
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}