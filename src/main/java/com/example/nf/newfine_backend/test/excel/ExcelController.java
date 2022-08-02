package com.example.nf.newfine_backend.test.excel;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ExcelController {

    private final ExcelService excelService;

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
                excelFile.transferTo(destFile); // 엑셀파일 생성
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

}
