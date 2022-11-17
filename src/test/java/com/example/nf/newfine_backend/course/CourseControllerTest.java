//package com.example.nf.newfine_backend.course;
//
//import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
//import com.example.nf.newfine_backend.config.CustomUser;
//import com.example.nf.newfine_backend.member.student.domain.Student;
//import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.RestDocumentationContextProvider;
//import org.springframework.restdocs.RestDocumentationExtension;
//import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.util.StopWatch;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.filter.CharacterEncodingFilter;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import org.hamcrest.core.IsEqual;
//
//@ExtendWith({RestDocumentationExtension.class, SpringExtension.class}) // When using JUnit5
//@AutoConfigureMockMvc
//@AutoConfigureRestDocs
//@SpringBootTest
//@ActiveProfiles("test")
//class CourseControllerTest {
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private CourseRepository courseRepository;
//    @Autowired
//    private StudentRepository studentRepository;
//    @Autowired
//    private ListenerRepository listenerRepository;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Before
//    public void setUp(RestDocumentationContextProvider restDocumentationContextProvider) {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .apply(documentationConfiguration(restDocumentationContextProvider))
//                .apply(springSecurity())
//                .addFilters(new CharacterEncodingFilter("UTF-8", true))
//                .build();
//
//    }
//
//    @AfterEach
//    public void delete(){
//        studentRepository.deleteAll();
//        courseRepository.deleteAll();
//    }
//    @Autowired
//    private CourseService courseService;
//
//    @Test
//    void 모든강의() {
//        Course course = new Course("내신 미적분","고등학교","수학","미적분","15:00","20:00");
//        courseRepository.save(course);
//        List<Course> courseList = courseService.getAllCourses();
//        System.out.println(courseList);
//        Course course1 = courseList.get(0);
//        assertEquals( course1.getCName(),"내신 미적분");
//
//    }
//
//    @Test
//    @WithMockUser(roles ="ADMIN")
//    void 원하는강의_성공() throws Exception {
//        String cname = "$.[?(@.cname == '%s')]";
//
//        Course course = new Course("내신 미적분","고등학교","수학","미적분","15:00","20:00");
//        courseRepository.save(course);
//
//        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
//
//        info.add("type", "미적분");
//
//        mockMvc.perform(RestDocumentationRequestBuilders.get("/courses") // 1
//                        .params(info) //
//                        .contentType(MediaType.APPLICATION_JSON)) //
//                .andExpect(status().isOk())
//                .andExpect(jsonPath( cname, "내신 미적분").exists())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())//어떤 응답과 요청을 받았는지 확인가능.
//                .andDo(MockMvcRestDocumentationWrapper.document("유형별 강의"// 5,
//                ));
//
//
//
//    }
//
//    @Test
//    @CustomUser("01030303030")
//    void 강의수강생() throws Exception {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        String cname = "$.[?(@.cname == '%s')]";
//        String student_name = "$..student[?(@.phoneNumber == '%s')]";
//
//        Course course = new Course("내신 미적분","고등학교","수학","미적분","15:00","20:00");
//        courseRepository.save(course);
//        Listener listener = new Listener();
//        listener.setCourse(course);
//        Student student = studentRepository.findByPhoneNumber("01030303030").get();
//        listener.setStudent(student);
//        listenerRepository.save(listener);
//        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
//
//        info.add("id", Long.toString(course.getId()));
//        mockMvc.perform(RestDocumentationRequestBuilders.get("/listeners") // 1
//                        .params(info) //
//                        .contentType(MediaType.APPLICATION_JSON)) //
//                .andExpect(status().isOk())
//                .andExpect(jsonPath( student_name, "01030303030").exists())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())//어떤 응답과 요청을 받았는지 확인가능.
//                .andDo(MockMvcRestDocumentationWrapper.document("강의수강생"// 5,
//                ));
//        stopWatch.stop();
//        System.out.println("소요시간:"+stopWatch.getTotalTimeMillis()+"ms");
//        System.out.println(stopWatch.prettyPrint());
//
//    }
//
//    @Test
//    @CustomUser("01030303030")
//    void 학생강의() throws Exception {
//        String course_name = "$..course[?(@.cname == '%s')]";
//        String student_name = "$..student[?(@.phoneNumber == '%s')]";
//        Course course = new Course("내신 미적분","고등학교","수학","미적분","15:00","20:00");
//        courseRepository.save(course);
//        Listener listener = new Listener();
//        listener.setCourse(course);
//        Student student = studentRepository.findByPhoneNumber("01030303030").get();
//        listener.setStudent(student);
//        listenerRepository.save(listener);
////        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
////
////        info.add("id", Long.toString(course.getId()));
//        mockMvc.perform(RestDocumentationRequestBuilders.get("/student/courses") // 1
////                        .params(info) //
//                        .contentType(MediaType.APPLICATION_JSON)) //
//                .andExpect(status().isOk())
//                .andExpect(jsonPath( course_name, "내신 미적분").exists())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())//어떤 응답과 요청을 받았는지 확인가능.
//                .andDo(MockMvcRestDocumentationWrapper.document("강의수강생"// 5,
//                ));
//
//    }
// }