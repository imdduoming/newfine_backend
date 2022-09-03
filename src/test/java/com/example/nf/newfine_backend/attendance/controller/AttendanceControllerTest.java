package com.example.nf.newfine_backend.attendance.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.example.nf.newfine_backend.member.service.CustomUserDetailsService;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.study.StudyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AttendanceControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    private StudyRepository studyRepository;
    @Autowired
    private StudentRepository studentRepository;


    @Before
    public void setUp(RestDocumentationContextProvider restDocumentationContextProvider) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentationContextProvider))
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @AfterEach
    public void delete(){
        studentRepository.deleteAll();
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void 출석생성() throws Exception {
        Map<String,String> input = new HashMap<>();
        // body에 json 형식으로 회원의 데이터를 넣기 위해서 Map을 이용한다.
        LocalDateTime now = LocalDateTime.now();
        input.put("startTime", "2020-09-23T18:34:24");
        String content = objectMapper.writeValueAsString(input);
        mockMvc.perform(RestDocumentationRequestBuilders.post("/make/study") // 1
                        .content(content) //
                        .contentType(MediaType.APPLICATION_JSON)) //
                .andExpect(status().isOk()) // 4
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())//어떤 응답과 요청을 받았는지 확인가능.
                .andDo(MockMvcRestDocumentationWrapper.document("관리자 자습 생성"// 5,
                        ,requestFields( // 6
                                fieldWithPath("startTime").description("자습시작시간") // 7
                        )
                ));


    }

    @Test
    void addAttendance() {
    }

    @Test
    void getAllAttendances() {
    }

    @Test
    void getMyAttendance() {
    }
}