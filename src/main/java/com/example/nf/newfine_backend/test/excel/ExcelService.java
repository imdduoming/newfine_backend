package com.example.nf.newfine_backend.test.excel;

import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.test.excel.domain.Excel;
import com.example.nf.newfine_backend.test.excel.util.ExcelRead;
import com.example.nf.newfine_backend.test.excel.util.ExcelReadOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExcelService{

    private final ExcelRepository excelRepository;

    @Transactional
    public void excelUpload(File destFile) throws Exception {
        ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(destFile.getAbsolutePath()); //파일경로 추가
        excelReadOption.setOutputColumns("A", "B", "C", "D", "E", "F", "G"); //추출할 컬럼명 추가
        excelReadOption.setStartRow(2); //시작행(헤더부분 제외)

        List<ExcelDto> listExcel = new ArrayList<ExcelDto>();

        List<Map<String, String>> excelContent  = ExcelRead.read(excelReadOption);

        for(Map<String, String> article: excelContent){
            System.out.println(article.get("A"));
            System.out.println(article.get("B"));
            System.out.println(article.get("C"));
            System.out.println(article.get("D"));
            System.out.println(article.get("E"));
            System.out.println(article.get("F"));
            System.out.println(article.get("G"));

            ExcelDto excelInfo = new ExcelDto();

            // 각 셀의 데이터를 VO에 set한다.
            excelInfo.setA(article.get("A"));
            excelInfo.setB(article.get("B"));
            excelInfo.setC(article.get("C"));
            excelInfo.setD(article.get("D"));
            excelInfo.setE(article.get("E"));
            excelInfo.setF(article.get("F"));
            excelInfo.setG(article.get("G"));

            listExcel.add(excelInfo);
        }

        // 리스트에 담은 VO를 DB에 저장
        for (ExcelDto oneExcel : listExcel){
//            excelService.excelUpload(oneExcel);
            Excel ex = oneExcel.toExcel();
            excelRepository.save(ex);
        }
    }
}
