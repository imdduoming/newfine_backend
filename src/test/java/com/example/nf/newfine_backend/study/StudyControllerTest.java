package com.example.nf.newfine_backend.study;//package com.example.nf.newfine_backend.study;
//
//import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
//import com.example.nf.newfine_backend.CustomUser;
//import com.example.nf.newfine_backend.member.service.CustomUserDetailsService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.With;
//import org.junit.Before;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.RestDocumentationContextProvider;
//import org.springframework.restdocs.RestDocumentationExtension;
//import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
//import com.example.nf.newfine_backend.CustomUser;
//import com.example.nf.newfine_backend.member.service.CustomUserDetailsService;
//import com.example.nf.newfine_backend.study.Study;
//import com.example.nf.newfine_backend.study.StudyRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.RestDocumentationContextProvider;
//import org.springframework.restdocs.RestDocumentationExtension;
//import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
//import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
//import org.springframework.restdocs.snippet.Snippet;
//import org.springframework.security.test.context.support.TestExecutionEvent;
//import org.springframework.security.test.context.support.WithAnonymousUser;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.security.test.context.support.WithUserDetails;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.resourceDetails;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
//import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
//import static org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.filter.CharacterEncodingFilter;
//
//import javax.sql.DataSource;
//
//@ExtendWith({RestDocumentationExtension.class, SpringExtension.class}) // When using JUnit5
//@AutoConfigureMockMvc
//@AutoConfigureRestDocs
//@SpringBootTest
//@ActiveProfiles("test")
//public class StudyControllerTest {
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//    @Autowired
//    CustomUserDetailsService customUserDetailsService;
//    @Autowired
//    private StudyRepository studyRepository;
//
//
//    @Before
//    public void setUp(RestDocumentationContextProvider restDocumentationContextProvider) {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .apply(documentationConfiguration(restDocumentationContextProvider))
//                .addFilters(new CharacterEncodingFilter("UTF-8", true))
//                .build();
//    }
//
//    @Test
//    @DisplayName("관리자 자습 생성")
//    @WithMockUser(roles = "ADMIN")
//    public void makeStudy() throws Exception {
//        Map<String,String> input = new HashMap<>();
//        // body에 json 형식으로 회원의 데이터를 넣기 위해서 Map을 이용한다.
//        LocalDateTime now = LocalDateTime.now();
//        input.put("startTime", "2020-09-23T18:34:24");
//        String content = objectMapper.writeValueAsString(input);
//        mockMvc.perform(RestDocumentationRequestBuilders.post("/make/study") // 1
//                        .content(content) //
//                        .contentType(MediaType.APPLICATION_JSON)) //
//                        .andExpect(status().isOk()) // 4
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                        .andDo(print())//어떤 응답과 요청을 받았는지 확인가능.
//                        .andDo(MockMvcRestDocumentationWrapper.document("관리자 자습 생성"// 5,
//                                ,requestFields( // 6
//                                fieldWithPath("startTime").description("자습시작시간") // 7
//                        )
//                ));
//    }
//
//    @Test
//    @DisplayName("자습 입실")
//    @CustomUser(value = "01030303030")
//    void enterStudy() throws Exception {
//        LocalDateTime now = LocalDateTime.now();
//        Study study = new Study(now);
//        studyRepository.save(study);
//        Map<String,String> idset = new HashMap<>();
//        // body에 json 형식으로 회원의 데이터를 넣기 위해서 Map을 이용한다.
//        idset.put("studyId", Long.toString(study.getStudy_id()));
//        String content = objectMapper.writeValueAsString(idset);
//        mockMvc.perform(RestDocumentationRequestBuilders.post("/study/start") // 1
//                        .content(content) //
//                        .contentType(MediaType.APPLICATION_JSON)) //
//                .andExpect(status().isOk()) // 4
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())//어떤 응답과 요청을 받았는지 확인가능.
//                .andDo(MockMvcRestDocumentationWrapper.document("자습 입실"// 5,
//                        ,requestFields( // 6
//                                fieldWithPath("studyId").description("자습 id") // 7
//                        )
//                ));
//    }
//
//    @Test
//    @DisplayName("자습 퇴실")
//    @CustomUser(value = "01030303030")
//    void endStudy() throws Exception {
//        LocalDateTime now = LocalDateTime.now();
//        Study study = new Study(now);
//        studyRepository.save(study);
//        Map<String,String> idset = new HashMap<>();
//        // body에 json 형식으로 회원의 데이터를 넣기 위해서 Map을 이용한다.
//        idset.put("studyId", Long.toString(study.getStudy_id()));
//        String content = objectMapper.writeValueAsString(idset);
//        mockMvc.perform(RestDocumentationRequestBuilders.put("/study/end") // 1
//                        .content(content) //
//                        .contentType(MediaType.APPLICATION_JSON)) //
//                .andExpect(status().isOk()) // 4
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())//어떤 응답과 요청을 받았는지 확인가능.
//                .andDo(MockMvcRestDocumentationWrapper.document("자습 퇴실"// 5,
//                        ,requestFields( // 6
//                                fieldWithPath("studyId").description("자습 id") // 7
//                        )
//                ));
//    }
//
//    @Test
//    void getMyStudy() {
//    }
//
//    @Test
//    void getTotal() {
//    }
//}