package com.cos.securityex01.config;

import com.cos.securityex01.config.auth.PrincipalDetailsService;
import com.cos.securityex01.config.ouath.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// securedEnabled => secure 어노테이션 활성화
// prePostEnabled => preAuthorize 어노테이션 활성
@Configuration // IoC 빈(bean)을 등록
@EnableWebSecurity // 필터 체인 관리 시작 어노테이션
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // 특정 주소 접근시 권한 및 인증을 위한 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // http://localhost:8080/logout

        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated() //인증만 되면 들어갈수 있는 주소
                //.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                //.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN')  or hasRole('ROLE_MANAGER')")
                .anyRequest().permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/loginProc")  //lgin주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해줍니다.
                    .defaultSuccessUrl("/")
                    .failureUrl("/loginFailure")
                .and()
                    .oauth2Login()  //social login
                    .loginPage("/login")// social 로그인이 완료된 뒤의 후처리 필요
                    .userInfoEndpoint()
                    .userService(principalOauth2UserService);  //google 로그인이 완료된 뒤에 후처리 필요함. TIP. 코드 X(엑세스토큰 + 사용자프로필정보O)
    }

    // 1. 코드받기(안증) 2.액세스토큰(권한)
    // 3.사용자프로필 정보 가져오고 4. 그 정보를 퇟로 회원가입을 자동으로 시키기도 함
    // 4-2 (이메일,전화버호, 이름, 아이디) 쇼핑몰 > (집주소), 백화점몰 ->

}
