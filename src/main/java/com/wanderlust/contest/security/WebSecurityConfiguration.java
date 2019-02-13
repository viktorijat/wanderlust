//package com.wanderlust.contest.security;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable()
//                .antMatcher("/**")
//                .authorizeRequests()
//                .antMatchers("/", "/index.html")
//                .permitAll()
//                .anyRequest().authenticated();
//    }
//}
