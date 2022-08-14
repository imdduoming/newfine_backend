package com.example.nf.newfine_backend.admin.controller;

import com.example.nf.newfine_backend.admin.dto.DeleteRequestByAdminDto;
import com.example.nf.newfine_backend.admin.dto.SignUpByAdminDto;
import com.example.nf.newfine_backend.admin.service.AdminService;
import com.example.nf.newfine_backend.member.dto.SignInDto;
import com.example.nf.newfine_backend.member.dto.SignUpDto;
import com.example.nf.newfine_backend.member.dto.TokenDto;
import com.example.nf.newfine_backend.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final AuthService authService;

    // ************ 관리자 페이지에서 학생 회원가입
    @ResponseBody
    @RequestMapping(value = "/studentSignUpForm.do", method = RequestMethod.POST)
    public Map<String, String> signupByAdmin(HttpServletRequest request, SignUpByAdminDto signUpByAdminDto) {

        Map<String, String> result = new HashMap<String, String>();

//        signUpByAdminDto.setBranch("signUp_branch");
        signUpByAdminDto.setName(request.getParameter("signUp_name"));
        signUpByAdminDto.setPhoneNumber(request.getParameter("signUp_phoneNumber"));
        signUpByAdminDto.setPassword(request.getParameter("signUp_password"));
        signUpByAdminDto.setNickname(request.getParameter("signUp_nickname"));

        try {
            if(Objects.equals(adminService.signupByAdmin(signUpByAdminDto), "회원 가입 완료")) {
                result.put("code", "1");
                result.put("msg", "회원가입되었습니다!");
            }else {
                result.put("code", "0");
                result.put("msg", "회원가입 실패!");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
//        return ResponseEntity.ok(adminService.signupByAdmin(signUpDto));    // 상태 코드 + data(body) (ResponseEntity 에 헤더 정보, 상태 코드 담을 수 있음)
    }

    // ****************** 관리자 페이지에서 학생 탈퇴
    @ResponseBody
    @RequestMapping(value = "studentDeleteForm.do", method = RequestMethod.POST)
    public Map<String, String> deleteStudentByAdmin(HttpServletRequest request, DeleteRequestByAdminDto deleteRequestByAdminDto) {

        Map<String, String> result = new HashMap<String, String>();

        deleteRequestByAdminDto.setPhoneNumber(request.getParameter("delete_phoneNumber"));
        deleteRequestByAdminDto.setPassword(request.getParameter("delete_password"));

        // *************************** 특수분자 영어 숫자 8자 이상인지 확인하기~

        try {
            if(Objects.equals(adminService.deleteStudentByAdmin(deleteRequestByAdminDto), "탈퇴 완료")) {
                result.put("code", "1");
                result.put("msg", "탈퇴 완료");
            }else {
                result.put("code", "0");
                result.put("msg", "탈퇴 실패");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
        //        return ResponseEntity.ok(adminService.deleteStudentByAdmin(deleteRequestDto));
    }

    @PostMapping("/checkLogin")
    public String checkLogin() {

        return "로그인ing";
    }

}
