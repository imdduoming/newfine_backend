package com.example.nf.newfine_backend.member.service;

import com.example.nf.newfine_backend.config.CustomUser;
import com.example.nf.newfine_backend.member.dto.SignUpDto;
import com.example.nf.newfine_backend.member.exception.CustomException;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.transaction.Transactional;

import static com.example.nf.newfine_backend.member.exception.ErrorCode.DUPLICATE_MEMBER;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class}) // When using JUnit5
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
@ActiveProfiles("test")
class AuthServiceTest{
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Before
    public void setUp(RestDocumentationContextProvider restDocumentationContextProvider) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentationContextProvider))
                .apply(springSecurity())
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @AfterEach
    public void delete(){
        studentRepository.deleteAll();
    }
    @Autowired
    private AuthService authService;
    @Autowired
    private StudentRepository studentRepository;
    @Test
    @Transactional
    @WithAnonymousUser
    public void 학생_회원가입() throws Exception {
        String username = "이제노";
        String password = "1234";
        String phone= "01030303030";
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setName(username);
        signUpDto.setPassword(password);
        signUpDto.setPhoneNumber(phone);

        authService.signup(signUpDto);
        Student student =studentRepository.findByPhoneNumber(phone).get();
        assertEquals(student.getName(),username);


    }

    @Test
    @Transactional
    @WithAnonymousUser
    public void 학생_회원가입_중복() throws Exception {
        String username = "이제노";
        String password = "dlwpsh0423!";
        String phone= "01030303030";
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setName(username);
        signUpDto.setPassword(password);
        signUpDto.setPhoneNumber(phone);
        authService.signup(signUpDto);


        String username2 = "나재민";
        String password2 = "dlwpsh0423!";
        String phone2= "01030303030";
        SignUpDto signUpDto2 = new SignUpDto();
        signUpDto2.setName(username2);
        signUpDto2.setPassword(password2);
        signUpDto2.setPhoneNumber(phone2);

        CustomException returnStatusMessage = assertThrows(CustomException.class,
                ()-> authService.signup(signUpDto2));
        System.out.println("오류메시지");
        System.out.println(returnStatusMessage.getErrorCode());
        assertEquals(DUPLICATE_MEMBER,returnStatusMessage.getErrorCode());



    }

    @Test
    @Transactional
    @WithAnonymousUser
    public void 로그인성공() throws Exception {
        String username = "이제노";
        String password = "dlwpsh0423!";
        String phone = "01030303030";
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setName(username);
        signUpDto.setPassword(password);
        signUpDto.setPhoneNumber(phone);

        authService.signup(signUpDto);


        // 성공 TEST
        mockMvc.perform(formLogin().user(signUpDto.getPhoneNumber()).password(password))
                .andDo(print())
                .andExpect(authenticated());

    }

    @Test
    @Transactional
    @WithAnonymousUser
    public void 로그인실패_비밀번호오류() throws Exception {
        String username = "이제노";
        String password = "dlwpsh0423!";
        String phone = "01030303030";
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setName(username);
        signUpDto.setPassword(password);
        signUpDto.setPhoneNumber(phone);
        authService.signup(signUpDto);



        // 성공 TEST
        mockMvc.perform(formLogin().user(signUpDto.getPhoneNumber()).password("01020202020"))
                .andDo(print())
                .andExpect(unauthenticated());

    }

    @Test
    @Transactional
    @CustomUser("01030303030")
    public void 로그아웃() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.post("/logout").with(csrf()))
                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/")) // (2)
                .andExpect(unauthenticated()); // (3)// 1

    }


}