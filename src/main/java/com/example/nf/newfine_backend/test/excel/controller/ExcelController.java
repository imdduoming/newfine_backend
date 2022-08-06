package com.example.nf.newfine_backend.test.excel.controller;

import com.example.nf.newfine_backend.test.domain.Test;
import com.example.nf.newfine_backend.test.excel.service.ExcelService;
import com.example.nf.newfine_backend.test.repository.TestRepository;
import com.example.nf.newfine_backend.test.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ExcelController {

    private final ExcelService excelService;
    private final TestService testService;
    private final TestRepository testRepository;

    @ResponseBody
    @RequestMapping(value = "/excelUpload.do", method = RequestMethod.POST)
    public Map<String, String> excelUploadAjax(MultipartHttpServletRequest request) throws Exception{
        Map<String, String> result = new HashMap<String, String>();
        MultipartFile excelFile = request.getFile("excelFile");

        try {
            if(excelFile != null || !excelFile.isEmpty()) {
                result.put("code", "1");
                result.put("msg", "업로드 성공");

                File destFile = new File("C:\\upload\\"+excelFile.getOriginalFilename()); // 파일위치 지정
                excelFile.transferTo(Paths.get(destFile.getAbsolutePath().substring(1))); // 엑셀파일 생성
//                System.out.println(destFile.getAbsolutePath().substring(1));
//                System.out.println(Paths.get(destFile.getAbsolutePath().substring(1)));
                excelService.excelUpload(destFile); // service단 호출
                destFile.delete(); // 업로드된 엑셀파일 삭제
            }else {
                result.put("code", "0");
                result.put("msg", "업로드 실패");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/courseTestResultsFileUpload.do", method = RequestMethod.POST)
    public Map<String, String> courseTestResultsFileUploadAjax(MultipartHttpServletRequest request) throws Exception{

        Map<String, String> result = new HashMap<String, String>();

        System.out.println("\n\n\n\n\n\n\n\n 멀티파일 리퀘스트\n\n\n\n\n\n\n\n\n\n"+request);
        System.out.println(request.getFile("courseTestResultsFile"));
//        System.out.println(request.getParameter("test_name"));
//        System.out.println(request.getParameter("test_date"));

//        String testName=request.getParameter("test_name");
//        LocalDate testDate= LocalDate.parse(request.getParameter("test_date"));
//        Long courseId= Long.valueOf(request.getParameter("course_id"));
//        Test test= testService.createTest(courseId, testDate, testName);

        System.out.println(request.getParameter("test_code"));
        Long testCode= Long.valueOf(request.getParameter("test_code"));

        Test test= testRepository.findById(testCode).orElseThrow(RuntimeException::new);

        MultipartFile courseTestResultsFile = request.getFile("courseTestResultsFile");

        try {
            if(courseTestResultsFile != null || !courseTestResultsFile.isEmpty()) {
                result.put("code", "1");
                result.put("msg", "업로드 성공");

                File destFile = new File("C:\\upload\\"+courseTestResultsFile.getOriginalFilename()); // 파일위치 지정
                courseTestResultsFile.transferTo(Paths.get(destFile.getAbsolutePath().substring(1))); // 엑셀파일 생성
                System.out.println(destFile.getAbsolutePath());
                System.out.println(destFile.getAbsolutePath().substring(1));
                System.out.println(Paths.get(destFile.getAbsolutePath().substring(1)));
//                excelService.courseTestResultsFileUpload(test, destFile); // service단 호출
                excelService.courseTestResultsFileUpload(test, destFile); // service단 호출
                destFile.delete(); // 업로드된 엑셀파일 삭제
            }else {
                result.put("code", "0");
                result.put("msg", "업로드 실패");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/studentTestResultsFileUpload.do", method = RequestMethod.POST)
    public Map<String, String> studentTestResultsFileUploadAjax(MultipartHttpServletRequest request) throws Exception{

        Map<String, String> result = new HashMap<String, String>();

//        System.out.println("\n\n\n\n\n\n\n\n 멀티파일 리퀘스트\n\n\n\n\n\n\n\n\n\n"+request);
//        System.out.println(request.getFile("studentTestResultsFile"));
//        System.out.println(request.getParameter("test_name"));
//        System.out.println(request.getParameter("test_date"));

//        String testName=request.getParameter("test_name");
//        LocalDate testDate= LocalDate.parse(request.getParameter("test_date"));
//        Long courseId= Long.valueOf(request.getParameter("course_id"));
//        Test test= testService.createTest(courseId, testDate, testName);

        System.out.println(request.getParameter("test_code"));
        Long testCode= Long.valueOf(request.getParameter("test_code"));

        Test test= testRepository.findById(testCode).orElseThrow(RuntimeException::new);

        MultipartFile studentTestResultsFile = request.getFile("studentTestResultsFile");

        try {
            if(studentTestResultsFile != null || !studentTestResultsFile.isEmpty()) {
                result.put("code", "1");
                result.put("msg", "업로드 성공");

                File destFile = new File("C:\\upload\\"+studentTestResultsFile.getOriginalFilename()); // 파일위치 지정
                studentTestResultsFile.transferTo(Paths.get(destFile.getAbsolutePath().substring(1))); // 엑셀파일 생성
                System.out.println(destFile.getAbsolutePath());
                System.out.println(destFile.getAbsolutePath().substring(1));
                System.out.println(Paths.get(destFile.getAbsolutePath().substring(1)));
                excelService.studentTestResultsFileUpload(test, destFile); // service단 호출
                destFile.delete(); // 업로드된 엑셀파일 삭제
            }else {
                result.put("code", "0");
                result.put("msg", "업로드 실패");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
