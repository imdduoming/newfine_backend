package com.example.nf.newfine_backend.study;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.MockMvc;
@ExtendWith(RestDocumentationExtension.class) // When using JUnit5
@AutoConfigureMockMvc
@SpringBootTest
@WebAppConfiguration
class StudyControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .build();
    }

//    @Test
//    @DisplayName("관리자 자습 생성")
//    @WithMockUser(roles = "ADMIN")
//    public void makeStudy() throws Exception {
//        Map<String,LocalDateTime> input = new HashMap<>();
//        // body에 json 형식으로 회원의 데이터를 넣기 위해서 Map을 이용한다.
//        LocalDateTime now = LocalDateTime.now();
//        input.put("startTime", now);
//        mockMvc.perform(post("/make/study") // 1
//                        .content(objectMapper.writeValueAsString(input)) //
//                        .contentType(MediaType.APPLICATION_JSON)) // 3
//                        .andExpect(status().isCreated()) // 4
//                        .andDo(document("make_study"// 5
//                        ,requestFields( // 6
//                                fieldWithPath("start_time").description("자습시작시간") // 7
//                        )
//                ));
//    }

//    @Autowired
//    StudentStudy studentStudy
//    @Test
//    @DisplayName("자습 입실")
//    void enterStudy() {
//
//    }
//
//    @Test
//    void endStudy() {
//    }
//
//    @Test
//    void getMyStudy() {
//    }
//
//    @Test
//    void getTotal() {
//    }
}