//package com.example.nf.newfine_backend.member.student.service;
//
//import com.example.nf.newfine_backend.config.CustomUser;
//import com.example.nf.newfine_backend.member.dto.SignUpDto;
//import com.example.nf.newfine_backend.member.exception.CustomException;
//import com.example.nf.newfine_backend.member.service.AuthService;
//import com.example.nf.newfine_backend.member.student.domain.Student;
//import com.example.nf.newfine_backend.member.student.dto.NicknameRequestDto;
//import com.example.nf.newfine_backend.member.student.dto.StudentRequestDto;
//import com.example.nf.newfine_backend.member.student.exception.DuplicatedNicknameException;
//import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
//import org.junit.jupiter.api.AfterEach;
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
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.filter.CharacterEncodingFilter;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static com.example.nf.newfine_backend.member.exception.ErrorCode.DUPLICATE_MEMBER;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ExtendWith({RestDocumentationExtension.class, SpringExtension.class}) // When using JUnit5
//@AutoConfigureMockMvc
//@AutoConfigureRestDocs
//@SpringBootTest
//@ActiveProfiles("test")
//class StudentServiceTest {
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private StudentRepository studentRepository;
//    @Before
//    public void setUp(RestDocumentationContextProvider restDocumentationContextProvider) {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .apply(documentationConfiguration(restDocumentationContextProvider))
//                .apply(springSecurity())
//                .addFilters(new CharacterEncodingFilter("UTF-8", true))
//                .build();
//    }
//
//    @AfterEach
//    public void delete(){
//        studentRepository.deleteAll();
//    }
//    @Autowired
//    private StudentService studentService;
//    @Autowired
//    private AuthService authService;
//
//    @Test
//    @CustomUser("01030303030")
//    void 닉네임설정_성공() {
//
//        NicknameRequestDto nicknameRequestDto = new NicknameRequestDto();
//        nicknameRequestDto.setNickname("뚜오밍");
//
//        studentService.setNickname(nicknameRequestDto);
//        Student student = studentRepository.findByPhoneNumber("01030303030").get();
//        assertEquals(student.getNickname(),"뚜오밍");
//
//    }
//    @Test
//    @CustomUser("01030303030")
//    void 닉네임설정_중복오류() {
//
//        String username = "유바비";
//        String password = "dlwpsh0423!";
//        String phone= "01012121212";
//        SignUpDto signUpDto = new SignUpDto();
//        signUpDto.setName(username);
//        signUpDto.setPassword(password);
//        signUpDto.setPhoneNumber(phone);
//        authService.signup(signUpDto);
//        Student student = studentRepository.findByPhoneNumber("01012121212").get();
//        student.setNickname("뚜오밍");
//        studentRepository.save(student);
//
//        NicknameRequestDto nicknameRequestDto = new NicknameRequestDto();
//        nicknameRequestDto.setNickname("뚜오밍");
//        DuplicatedNicknameException returnStatusMessage = assertThrows(DuplicatedNicknameException.class,
//                ()-> studentService.setNickname(nicknameRequestDto));
//        System.out.println("오류메시지");
//        System.out.println(returnStatusMessage);
//        assertEquals(DUPLICATE_MEMBER,returnStatusMessage,"이미 사용 중인 닉네임입니다.");
//
//    }
//
//
//    @Test
//    @CustomUser("01030303030")
//    void 닉네임업데이트_성공() {
//        // 유바비 , 닉네임 : 뚜오밍
//        String username = "유바비";
//        String password = "dlwpsh0423!";
//        String phone= "01012121212";
//        SignUpDto signUpDto = new SignUpDto();
//        signUpDto.setName(username);
//        signUpDto.setPassword(password);
//        signUpDto.setPhoneNumber(phone);
//        authService.signup(signUpDto);
//        Student student = studentRepository.findByPhoneNumber("01012121212").get();
//        student.setNickname("뚜오밍");
//        studentRepository.save(student);
//
//        // 01030303030 , 기존 닉네임 바비짱 -> 유미짱으로 변경
//        NicknameRequestDto nicknameRequestDto = new NicknameRequestDto();
//        nicknameRequestDto.setNickname("바비짱");
//        studentService.setNickname(nicknameRequestDto);
//
//        NicknameRequestDto update= new NicknameRequestDto();
//        update.setNickname("유미짱");
//        studentService.updateNickname(update);
//
//        Student student1 = studentRepository.findByPhoneNumber("01030303030").get();
//        assertEquals("유미짱",student1.getNickname());
//    }
//
//    @Test
//    @CustomUser("01030303030")
//    void 닉네임업데이트_중복오류() {
//        // 유바비 , 닉네임 : 뚜오밍
//        String username = "유바비";
//        String password = "dlwpsh0423!";
//        String phone= "01012121212";
//        SignUpDto signUpDto = new SignUpDto();
//        signUpDto.setName(username);
//        signUpDto.setPassword(password);
//        signUpDto.setPhoneNumber(phone);
//        authService.signup(signUpDto);
//        Student student = studentRepository.findByPhoneNumber("01012121212").get();
//        student.setNickname("뚜오밍");
//        studentRepository.save(student);
//
//        // 01030303030 , 기존 닉네임 바비짱 -> 유미짱으로 변경
//        NicknameRequestDto nicknameRequestDto = new NicknameRequestDto();
//        nicknameRequestDto.setNickname("바비짱");
//        studentService.setNickname(nicknameRequestDto);
//
//        NicknameRequestDto update= new NicknameRequestDto();
//        update.setNickname("유미짱");
//        studentService.updateNickname(update);
//
//        Student student1 = studentRepository.findByPhoneNumber("01030303030").get();
//        assertEquals("유미짱",student1.getNickname());
//    }
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private ObjectMapper objectMapper;
//
////    @Test
////    @CustomUser("01030303030")
////    void deleteStudent() throws Exception {
////
////        Map<String,String> idset = new HashMap<>();
////        // body에 json 형식으로 회원의 데이터를 넣기 위해서 Map을     이용한다.
////
////        idset.put("password", "dltndud0626!");
////        String content = objectMapper.writeValueAsString(idset);
////        mockMvc.perform(RestDocumentationRequestBuilders.delete("/delete")
////                        .content(content) //
////                        .contentType(MediaType.APPLICATION_JSON)) //
////                        .with(csrf()) // 이 부분
////                .andExpect(unauthenticated());
////        ;
////
////
////    }
//
//    @Test
//    void updatePassword() {
//    }
//}