package com.example.nf.newfine_backend.member.config;

import com.example.nf.newfine_backend.member.jwt.JwtAccessDeniedHandler;
import com.example.nf.newfine_backend.member.jwt.JwtAuthenticationEntryPoint;
import com.example.nf.newfine_backend.member.jwt.JwtSecurityConfig;
import com.example.nf.newfine_backend.member.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {  // Spring Security 설정
    private final TokenProvider tokenProvider;
    private final RedisTemplate redisTemplate;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // h2 database 테스트가 원활하도록 관련 API 들은 전부 무시
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring()
//                .antMatchers("/h2-console/**", "/favicon.ico");
////                .antMatchers("/h2-console/**", "/favicon.ico","/js/**","/resources/**", "/index.html");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CSRF 설정 Disable
        http.csrf().disable()

                // exception handling 할 때 Custom 클래스를 추가
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)  // 유저 정보 없이 접근
                .accessDeniedHandler(jwtAccessDeniedHandler)    // 권한 없이 접근

                // h2-console 을 위한 설정을 추가
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 시큐리티는 기본적으로 세션을 사용
                // 여기서는 세션을 사용하지 않기 때문에 세션 설정을 Stateless 로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 로그인, 회원가입 API 는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정
                .and()
                .authorizeRequests()
                .antMatchers("/", "/**").permitAll()
                .antMatchers("/all/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/make/attendance").permitAll()
                .antMatchers("/get/**").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/h2-console/**", "/favicon.ico").permitAll()
                .anyRequest().authenticated()   // 나머지 API 는 전부 인증 필요

                // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
                .and()
                .apply(new JwtSecurityConfig(tokenProvider, redisTemplate));
    }
}
