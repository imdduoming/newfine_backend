//package com.example.nf.newfine_backend.config;
//
//import com.example.nf.newfine_backend.member.dto.SignUpDto;
//import com.example.nf.newfine_backend.member.service.AuthService;
//import com.example.nf.newfine_backend.member.service.CustomUserDetailsService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.test.context.support.WithSecurityContextFactory;
//
//public class WithCustomUserSecurityContextFactory implements WithSecurityContextFactory <CustomUser>
//{
//    @Autowired
//    CustomUserDetailsService customUserDetailsService;
//    @Autowired
//    AuthService authService;
//
//    @Override
//    public SecurityContext createSecurityContext(CustomUser customUser) {
//        String phone = customUser.value();
//        SignUpDto student = new SignUpDto();
//        student.setName("이제노");
//        student.setPhoneNumber(phone);
//        student.setPassword("123");
//        // @UserDetails 가 원래 하던 일
//        // Authetication 만들고 Securitycontext 에 넣어주기
//        authService.signup(student);
//        UserDetails principal = customUserDetailsService.loadUserByUsername(phone);
//        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(),principal.getAuthorities());
//        // 빈 시큐리티 컨텍스트 만들고 거기에 AutheticationToken 넣기
//        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//        securityContext.setAuthentication(authentication);
//
//        return securityContext;
//
//
//    }
//
//}
