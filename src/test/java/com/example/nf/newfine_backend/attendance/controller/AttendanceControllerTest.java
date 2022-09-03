package com.example.nf.newfine_backend.attendance.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.CourseRepository;
import com.example.nf.newfine_backend.member.service.CustomUserDetailsService;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.study.StudyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class}) // When using JUnit5
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
@ActiveProfiles("test")
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
    @Autowired
    private CourseRepository courseRepository;

    private Long id;
    @Before
    public void setUp(RestDocumentationContextProvider restDocumentationContextProvider) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentationContextProvider))
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();



    }

//    @AfterEach
//    public void delete(){
//        studentRepository.deleteAll();
//    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void 출석생성() throws Exception {
        Course course = new Course("내신 미적분","고등학교","수학","미적분","15:00","20:00");
        courseRepository.save(course);
        id= course.getId();

        Map<String,String> input = new HashMap<>();
        // body에 json 형식으로 회원의 데이터를 넣기 위해서 Map을 이용한다.
        LocalDateTime now = LocalDateTime.now();
        input.put("course_id",Long.toString(id));
        input.put("startTime", "2020-09-23T23:00:24");
        input.put("endTime", "2020-09-23T23:59:24");
        String content = objectMapper.writeValueAsString(input);
        mockMvc.perform(RestDocumentationRequestBuilders.post("/make/attendance") // 1
                        .content(content) //
                        .contentType(MediaType.APPLICATION_JSON)) //
                .andExpect(status().isOk()) // 4
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())//어떤 응답과 요청을 받았는지 확인가능.
                .andDo(MockMvcRestDocumentationWrapper.document("관리자 출석 생성"// 5,
                        ,requestFields( // 6
                                fieldWithPath("course_id").description("강의id"),
                                fieldWithPath("startTime").description("출석시작시간"), // 7,
                                fieldWithPath("endTime").description("출석마감시간") // 7
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