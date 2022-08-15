package com.example.nf.newfine_backend.Homework.controller;

import com.example.nf.newfine_backend.Homework.domain.CheckedItem;
import com.example.nf.newfine_backend.Homework.dto.SHomeworkDto;
import com.example.nf.newfine_backend.Homework.service.SHomeworkService;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.exception.PhoneNumberNotFoundException;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SHomeworkController {

    private final SHomeworkService sHomeworkService;
    private final StudentRepository studentRepository;


    //댓글 작성
    /*
    @PostMapping("/sh/{thId}")

    public SHomeworkDto uploadSHomework(@PathVariable("thId") Long thId, @RequestBody SHomeworkDto sHomeworkDto) {
        Listener listener = listenerRepository.findById(1L).get();
        return sHomeworkService.createSHomework(thId, sHomeworkDto, listener);
    }
    */

    //댓글 목록 조회
    @GetMapping("/sh/list/{thId}")
    public List<SHomeworkDto> getSHomeworks(@PathVariable("thId") Long thId){
        return sHomeworkService.getSHomeworks(thId);
    }


//    @PutMapping("/sh/point")
//    public Long update(@PathVariable final Long Id, @RequestBody SHomeworkDto sHomeworkDto) {
//        return sHomeworkService.updateSHomework(Id, sHomeworkDto);
//    }

    @PutMapping("/sh/merge")
    @ResponseBody
    public String test2(@RequestBody CheckedItem[] checklist) {
        return "성공";
    }

    @PutMapping("/sh/point")
    @ResponseBody
    public String checkSHomework(@RequestBody Map<String, Object>[] checklist){
        for (Map<String, Object> ck : checklist) {
            System.out.println(ck.get("shId") + " : " + ck.get("grade"));
            Long Id = Long.parseLong(String.valueOf(ck.get("shId")));
            String grade = String.valueOf(ck.get("grade"));
//            Long Id = Long.valueOf(c.get("shId").toString());
            System.out.println("shId: " + Id + " : grade: " + grade);
            sHomeworkService.updateSHomework(Id, grade);
        }
        return "성공";
    }

    @PutMapping("/sh/point2")
    @ResponseBody
    public String checkSHomework2(@RequestBody Map<String, String>[] checklist){
        for (Map<String, String> ck : checklist) {
            System.out.println(ck.get("shId") + " : " + ck.get("grade"));
            Long Id = Long.parseLong(String.valueOf(ck.get("shId")));
            String grade = String.valueOf(ck.get("grade"));
//            Long Id = Long.valueOf(c.get("shId").toString());
            System.out.println("shId: " + Id + " : grade: " + grade);
            sHomeworkService.updateSHomework(Id, grade);
        }
        return "성공";
    }

    @PutMapping("/sh/test")
    @ResponseBody
    public String test(@RequestBody List<Map<String, Object>> checklist) {
        for (Map<String, Object> c : checklist) {
            System.out.println(c.get("shId") + " : " + c.get("grade"));
            Long Id = Long.parseLong(String.valueOf(c.get("shId")));
            String grade = String.valueOf(c.get("grade"));
//            Long Id = Long.valueOf(c.get("shId").toString());
            System.out.println("shId: " + Id + " : grade: " + grade);
            sHomeworkService.updateSHomework(Id, grade);
        }
        return "성공";
    }

    @PutMapping("/sh/map1")
    @ResponseBody
    public String maptest1(@RequestBody Map<String, Object> checklist) {
//        for (Map<String, Object> c : checklist) {
//            System.out.println(c.get("shId") + " : " + c.get("grade"));
//            Long Id = Long.parseLong(String.valueOf(c.get("shId")));
//            String grade = String.valueOf(c.get("grade"));
//            Long Id = Long.valueOf(c.get("shId").toString());
//            System.out.println("shId: " + Id + " : grade: " + grade);
//            sHomeworkService.updateSHomework(Id, grade);
//        }
        return "성공";
    }

    @PutMapping("/sh/map2")
    @ResponseBody
    public String maptest2(@RequestBody Map<String, String> checklist) {
//        for (Map<String, Object> c : checklist) {
//            System.out.println(c.get("shId") + " : " + c.get("grade"));
//            Long Id = Long.parseLong(String.valueOf(c.get("shId")));
//            String grade = String.valueOf(c.get("grade"));
//            Long Id = Long.valueOf(c.get("shId").toString());
//            System.out.println("shId: " + Id + " : grade: " + grade);
//            sHomeworkService.updateSHomework(Id, grade);
//        }
        return "성공";
    }


    @PutMapping("/sh/ppoint")
    @ResponseBody
    public String insertMemberInfo(@RequestBody List<String> checklist){
//
//        Map<String, Object> result = new HashMap<>();
//        try {
//            /*JSONArray jsonArray = JSONArray.fromObject(paramData);*/
//            List<Map<String,Object>> datalist = new ArrayList<Map<String,Object>>();
//            datalist = JSONArray.fromObject(paramData);
//
//            for (Map<String, Object> data : datalist) {
//                System.out.println(data.get("shId") + " : " + data.get("grade"));
//            }
//            result.put("result", true);
//        } catch (Exception e) {
//            result.put("result", false);
//        }
        return "제발";

    }

    //    public Map<String,Object> updateBrandPage(HttpServletRequest request, HttpServletResponse response,
//                                              HttpSession session, @RequestBody Map<String, Object> data) {
//        System.out.println(data);
//        Map<String,Object> map = new HashMap<String,Object>();
//        return map;



//    @PostMapping("/sh/point")
//    public void checkSHomework(@RequestBody List<CheckedItem> checkedItems){
//        checkedItems.forEach(c -> {
//            System.console().printf("ShId : " + c.getShId());
//            sHomeworkService.updateSHomework(c.getShId(), String.valueOf(c.getGrade()));
//            System.console().printf("Grade : " + c.getGrade());
//        });
//    }


    // shomework student 별로 조회하되, 미제출인 과제만
    @GetMapping("/shlist/unchecked")
    public List<SHomeworkDto> getSHomeworksByStudent1(){
        Student student=studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        return sHomeworkService.getSHomeworksByStudent1(student);
    }

    @GetMapping("/shlist/checked")
    public List<SHomeworkDto> getSHomeworksByStudent2(){
        Student student=studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        return sHomeworkService.getSHomeworksByStudent2(student);
    }

    //댓글 삭제
   /*
    @DeleteMapping("/sh/{thId}/{shId}")
    public String deleteSHomework(@PathVariable("thId") Long thId, @PathVariable("shId") Long shId) {
        // 추후 JWT 로그인 기능을 추가하고나서, 세션에 로그인된 유저와 댓글 작성자를 비교해서, 맞으면 삭제 진행하고
        // 틀리다면 예외처리를 해주면 된다.

        return sHomeworkService.deleteSHomework(shId);
    }
    */
}
