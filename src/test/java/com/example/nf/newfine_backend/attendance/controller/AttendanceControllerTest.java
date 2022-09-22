//package com.example.nf.newfine_backend.attendance.controller;
//
//import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
//import com.example.nf.newfine_backend.attendance.domain.Attendance;
//import com.example.nf.newfine_backend.attendance.repository.AttendanceRepository;
//import com.example.nf.newfine_backend.attendance.service.AttendanceService;
//import com.example.nf.newfine_backend.config.CustomUser;
//import com.example.nf.newfine_backend.course.Course;
//import com.example.nf.newfine_backend.course.CourseRepository;
//import com.example.nf.newfine_backend.course.Listener;
//import com.example.nf.newfine_backend.course.ListenerRepository;
//import com.example.nf.newfine_backend.member.service.CustomUserDetailsService;
//import com.example.nf.newfine_backend.member.student.domain.Student;
//import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
//import com.example.nf.newfine_backend.study.StudyRepository;
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
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.util.StopWatch;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.filter.CharacterEncodingFilter;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//@ExtendWith({RestDocumentationExtension.class, SpringExtension.class}) // When using JUnit5
//@AutoConfigureMockMvc
//@AutoConfigureRestDocs
//@SpringBootTest
//@ActiveProfiles("test")
//class AttendanceControllerTest {
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
//    @Autowired
//    private StudentRepository studentRepository;
//    @Autowired
//    private CourseRepository courseRepository;
//    @Autowired
//    private AttendanceRepository attendanceRepository;
//    @Autowired
//    private ListenerRepository listenerRepository;
//    @Autowired
//    private AttendanceService attendanceService;
//
//    private Long id;
//    @Before
//    public void setUp(RestDocumentationContextProvider restDocumentationContextProvider) {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .apply(documentationConfiguration(restDocumentationContextProvider))
//                .addFilters(new CharacterEncodingFilter("UTF-8", true))
//                .build();
//
//
//
//    }
//
//    @AfterEach
//    public void delete(){
//        studentRepository.deleteAll();
//    }
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void 출석생성() throws Exception {
//        Course course = new Course("내신 미적분","고등학교","수학","미적분","15:00","20:00");
//        courseRepository.save(course);
//        id= course.getId();
//
//        Map<String,String> input = new HashMap<>();
//        // body에 json 형식으로 회원의 데이터를 넣기 위해서 Map을 이용한다.
//        LocalDateTime now = LocalDateTime.now();
//        input.put("course_id",Long.toString(id));
//        input.put("startTime", "2020-09-23T23:00:24");
//        input.put("endTime", "2020-09-23T23:59:24");
//        String content = objectMapper.writeValueAsString(input);
//        mockMvc.perform(RestDocumentationRequestBuilders.post("/make/attendance") // 1
//                        .content(content) //
//                        .contentType(MediaType.APPLICATION_JSON)) //
//                .andExpect(status().isOk()) // 4
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())//어떤 응답과 요청을 받았는지 확인가능.
//                .andDo(MockMvcRestDocumentationWrapper.document("관리자 출석 생성"// 5,
//                        ,requestFields( // 6
//                                fieldWithPath("course_id").description("강의id"),
//                                fieldWithPath("startTime").description("출석시작시간"), // 7,
//                                fieldWithPath("endTime").description("출석마감시간") // 7
//                        )
//                ));
//
//
//    }
//
//    @Test
//    @CustomUser("01030303030")
//    void 출석_정상출석() throws Exception {
//        Course course = new Course("내신 미적분","고등학교","수학","미적분","15:00","20:00");
//        courseRepository.save(course);
//        id= course.getId();
//
//
//        Listener listener = new Listener();
//        listener.setCourse(course);
//        Student student = studentRepository.findByPhoneNumber("01030303030").get();
//        listener.setStudent(student);
//        listenerRepository.save(listener);
//
//
//        LocalDateTime start = LocalDateTime.now();
//        LocalDateTime end = start.plusHours(1);
//
//        Attendance attendance = attendanceService.makeAttendance(id,start,end);
//
//        Long attendance_id = attendance.getAttendanceId();
//        Map<String,String> input = new HashMap<>();
//        // body에 json 형식으로 회원의 데이터를 넣기 위해서 Map을 이용한다.
//        LocalDateTime now = LocalDateTime.now();
//        input.put("attendance_id",Long.toString(attendance_id));
//        String content = objectMapper.writeValueAsString(input);
//        mockMvc.perform(RestDocumentationRequestBuilders.post("/add/attendance") // 1
//                        .content(content) //
//                        .contentType(MediaType.APPLICATION_JSON)) //
//                .andExpect(status().isOk()) // 4
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())//어떤 응답과 요청을 받았는지 확인가능.
//                .andDo(MockMvcRestDocumentationWrapper.document("관리자 출석 생성"// 5,
//                        ,requestFields( // 6
//                                fieldWithPath("attendance_id").description("출석id")
//                        )
//                ));
//
//
//    }
//
//    @Test
//    @CustomUser("01030303030")
//    void 출석_지각() throws Exception {
//        Course course = new Course("내신 미적분","고등학교","수학","미적분","15:00","20:00");
//        courseRepository.save(course);
//        id= course.getId();
//
//
//        Listener listener = new Listener();
//        listener.setCourse(course);
//        Student student = studentRepository.findByPhoneNumber("01030303030").get();
//        listener.setStudent(student);
//        listenerRepository.save(listener);
//
//
//        LocalDateTime start = LocalDateTime.now();
//        LocalDateTime end = start.plusSeconds(1);
//
//        Attendance attendance = attendanceService.makeAttendance(id,start,end);
//
//        Long attendance_id = attendance.getAttendanceId();
//        Map<String,String> input = new HashMap<>();
//        // body에 json 형식으로 회원의 데이터를 넣기 위해서 Map을 이용한다.
//        LocalDateTime now = LocalDateTime.now();
//        input.put("attendance_id",Long.toString(attendance_id));
//        String content = objectMapper.writeValueAsString(input);
//        mockMvc.perform(RestDocumentationRequestBuilders.post("/add/attendance") // 1
//                        .content(content) //
//                        .contentType(MediaType.APPLICATION_JSON)) //
//                .andExpect(status().isOk()) // 4
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())//어떤 응답과 요청을 받았는지 확인가능.
//                .andDo(MockMvcRestDocumentationWrapper.document("관리자 출석 생성" ));
//
//
//    }
//
//    @Test
//    @CustomUser("01030303030")
//    void 강의_출석보기() throws Exception {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        Course course = new Course("내신 미적분","고등학교","수학","미적분","15:00","20:00");
//        courseRepository.save(course);
//        id= course.getId();
//
//
//        Listener listener = new Listener();
//        listener.setCourse(course);
//        Student student = studentRepository.findByPhoneNumber("01030303030").get();
//        listener.setStudent(student);
//        listenerRepository.save(listener);
//
//
//        LocalDateTime start1 = LocalDateTime.now();
//        LocalDateTime end1 = start1.plusSeconds(5);
//
//        LocalDateTime start2 = LocalDateTime.now();
//        LocalDateTime end2 = start2.plusSeconds(10);
//        Attendance attendance1 = attendanceService.makeAttendance(id,start1,end1);
//        Attendance attendance2 = attendanceService.makeAttendance(id,start2,end2);
//
//        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
//
//        info.add("id", Long.toString(course.getId()));
//        mockMvc.perform(RestDocumentationRequestBuilders.get("/attendances/my") // 1
//                        .params(info) //
//                        .contentType(MediaType.APPLICATION_JSON)) //
//                .andExpect(status().isOk()) // 4
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())//어떤 응답과 요청을 받았는지 확인가능.
//                .andDo(MockMvcRestDocumentationWrapper.document("강의 출석 보기"// 5,
//                ));
//        stopWatch.stop();
//        System.out.println("소요시간:"+stopWatch.getTotalTimeMillis()+"ms");
//        System.out.println(stopWatch.prettyPrint());
//
//
//    }
//
//}