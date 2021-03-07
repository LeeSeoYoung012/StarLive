package com.example.sycompany.StarLive.config;


import com.example.sycompany.StarLive.config.Oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true) //@Secured 활성화, preAuthorize어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated() //인증만 되면 들어갈 수 있는 주소!!
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") //manager도 되고 admin도 된다.
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll() //위의 api아니면 다 접근권한이 있다.
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login") // lesson4: /login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해줍니다.
                .defaultSuccessUrl("/")
                .and()
                .oauth2Login()
                .loginPage("/loginF orm")
                .userInfoEndpoint()
                .userService(principalOauth2UserService);
    }
}//구글로그인 후에 후처리가 필요함. 1. 코드 받기(인증이 된다) , 2.엑세스토큰(권함) 3.사용자프로필 정보를 가져오고
// 4-1. 그 정보를 토대로 회원가입 자동으로 진행시킴,  4-2, (이메일, 전화번호, 이름, 아이디) 쇼핑몰 -> (집주소), 백화점몰->(vip등급,일반등급) 이필요함
// 구글이 주는 '정보만' 필요한게 아닐 수도 있다.
// Tip: 코드x (엑세스토큰 + 사용자 프로필정보 O)
