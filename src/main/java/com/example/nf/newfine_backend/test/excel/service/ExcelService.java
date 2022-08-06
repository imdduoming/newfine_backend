package com.example.nf.newfine_backend.test.excel.service;

import com.example.nf.newfine_backend.test.domain.CourseTestResults;
import com.example.nf.newfine_backend.test.domain.StudentTestResults;
import com.example.nf.newfine_backend.test.domain.Test;
import com.example.nf.newfine_backend.test.dto.CourseTestResultsDto;
import com.example.nf.newfine_backend.test.dto.StudentTestResultsDto;
import com.example.nf.newfine_backend.test.excel.dto.ExcelDto;
import com.example.nf.newfine_backend.test.excel.repository.ExcelRepository;
import com.example.nf.newfine_backend.test.excel.domain.Excel;
import com.example.nf.newfine_backend.test.excel.util.ExcelRead;
import com.example.nf.newfine_backend.test.excel.util.ExcelReadOption;
import com.example.nf.newfine_backend.test.repository.CourseTestResultsRepository;
import com.example.nf.newfine_backend.test.repository.StudentTestResultsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExcelService{

    private final ExcelRepository excelRepository;
    private final CourseTestResultsRepository courseTestResultsRepository;
    private final StudentTestResultsRepository studentTestResultsRepository;

    @Transactional
    public void excelUpload(File destFile) throws Exception {
        ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(destFile.getAbsolutePath().substring(1)); //파일경로 추가
//        System.out.println(excelReadOption.getFilePath());
//        System.out.println(excelReadOption.getFilePath().substring(1));
//        System.out.println(excelReadOption.getFilePath().substring(2));
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

    @Transactional
    public void courseTestResultsFileUpload(Test test, File destFile) throws Exception {
        ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(destFile.getAbsolutePath().substring(1)); //파일경로 추가
//        System.out.println(excelReadOption.getFilePath());
//        System.out.println(excelReadOption.getFilePath().substring(1));
//        System.out.println(excelReadOption.getFilePath().substring(2));
        excelReadOption.setOutputColumns("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"); //추출할 컬럼명 추가
        excelReadOption.setStartRow(2); //시작행(헤더부분 제외)

        List<CourseTestResultsDto> listCourseTestResults = new ArrayList<CourseTestResultsDto>();

        List<Map<String, String>> CourseTestResultsFileContent  = ExcelRead.read(excelReadOption);

        for(Map<String, String> article: CourseTestResultsFileContent){
            System.out.println(article.get("A"));
            System.out.println(article.get("B"));
            System.out.println(article.get("C"));
            System.out.println(article.get("D"));
            System.out.println(article.get("E"));
            System.out.println(article.get("F"));
            System.out.println(article.get("G"));

            CourseTestResultsDto CourseTestResultsInfo = new CourseTestResultsDto();

            // 각 셀의 데이터를 VO에 set한다.
//            .subject(subject)
//                    .questionNum(questionNum)
//                    .correctAns(correctAns)
//                    .points(points)
//                    .type(type)
//                    .correctAnsRate(correctAnsRate)
//                    .choose1(choose1)
//                    .choose2(choose2)
//                    .choose3(choose3)
//                    .choose4(choose4)
//                    .choose5(choose5)
//                    .proportion1(proportion1)
//                    .proportion2(proportion2)
//                    .proportion3(proportion3)
//                    .proportion4(proportion4)
//                    .proportion5(proportion5)
            CourseTestResultsInfo.setSubject(article.get("A"));
            CourseTestResultsInfo.setQuestionNum(article.get("B"));
            CourseTestResultsInfo.setCorrectAns(article.get("C"));
            CourseTestResultsInfo.setPoints(article.get("D"));
            CourseTestResultsInfo.setType(article.get("E"));
            CourseTestResultsInfo.setCorrectAnsRate(Double.parseDouble(article.get("F")));
            CourseTestResultsInfo.setChoose1(Integer.parseInt(article.get("G")));
            CourseTestResultsInfo.setChoose2(Integer.parseInt(article.get("H")));
            CourseTestResultsInfo.setChoose3(Integer.parseInt(article.get("I")));
            CourseTestResultsInfo.setChoose4(Integer.parseInt(article.get("J")));
            CourseTestResultsInfo.setChoose5(Integer.parseInt(article.get("K")));
            CourseTestResultsInfo.setProportion1(Double.parseDouble(article.get("L")));
            CourseTestResultsInfo.setProportion2(Double.parseDouble(article.get("M")));
            CourseTestResultsInfo.setProportion3(Double.parseDouble(article.get("N")));
            CourseTestResultsInfo.setProportion4(Double.parseDouble(article.get("O")));
            CourseTestResultsInfo.setProportion5(Double.parseDouble(article.get("P")));

            listCourseTestResults.add(CourseTestResultsInfo);
        }

        // 리스트에 담은 VO를 DB에 저장
        for (CourseTestResultsDto oneCourseTestResults : listCourseTestResults){
//            excelService.excelUpload(oneExcel);
            CourseTestResults ex = oneCourseTestResults.toCourseTestResults();
            ex.setTest(test);
            courseTestResultsRepository.save(ex);
        }
    }

    @Transactional
    public void studentTestResultsFileUpload(Test test, File destFile) throws Exception {
        ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(destFile.getAbsolutePath().substring(1)); //파일경로 추가
//        System.out.println(excelReadOption.getFilePath());
//        System.out.println(excelReadOption.getFilePath().substring(1));
//        System.out.println(excelReadOption.getFilePath().substring(2));
        excelReadOption.setOutputColumns("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"); //추출할 컬럼명 추가
        excelReadOption.setStartRow(2); //시작행(헤더부분 제외)

        List<StudentTestResultsDto> listStudentTestResults = new ArrayList<StudentTestResultsDto>();

        List<Map<String, String>> StudentTestResultsFileContent  = ExcelRead.read(excelReadOption);

        for(Map<String, String> article: StudentTestResultsFileContent){
            System.out.println(article.get("A"));
            System.out.println(article.get("B"));
            System.out.println(article.get("C"));
            System.out.println(article.get("D"));
            System.out.println(article.get("E"));
            System.out.println(article.get("F"));
            System.out.println(article.get("G"));

            StudentTestResultsDto studentTestResultsInfo = new StudentTestResultsDto();

            // 각 셀의 데이터를 VO에 set한다.
//            .subject(subject)
//                    .questionNum(questionNum)
//                    .correctAns(correctAns)
//                    .points(points)
//                    .type(type)
//                    .correctAnsRate(correctAnsRate)
//                    .choose1(choose1)
//                    .choose2(choose2)
//                    .choose3(choose3)
//                    .choose4(choose4)
//                    .choose5(choose5)
//                    .proportion1(proportion1)
//                    .proportion2(proportion2)
//                    .proportion3(proportion3)
//                    .proportion4(proportion4)
//                    .proportion5(proportion5)
            studentTestResultsInfo.setName(article.get("A"));
            studentTestResultsInfo.setTestCode(article.get("B"));
            studentTestResultsInfo.setTotalScore(Integer.parseInt(article.get("C")));
            studentTestResultsInfo.setScore1(Integer.parseInt(article.get("D")));
            studentTestResultsInfo.setScore2(Integer.parseInt(article.get("E")));
            studentTestResultsInfo.setQ1((article.get("F")));
            studentTestResultsInfo.setQ2((article.get("G")));
            studentTestResultsInfo.setQ3((article.get("H")));
            studentTestResultsInfo.setQ4((article.get("I")));
            studentTestResultsInfo.setQ5((article.get("J")));
            studentTestResultsInfo.setQ6((article.get("K")));
            studentTestResultsInfo.setQ7((article.get("L")));
            studentTestResultsInfo.setQ8((article.get("M")));
            studentTestResultsInfo.setQ9((article.get("N")));
            studentTestResultsInfo.setQ10((article.get("O")));
            studentTestResultsInfo.setQ11((article.get("P")));
            studentTestResultsInfo.setQ12((article.get("Q")));
            studentTestResultsInfo.setQ13((article.get("R")));
            studentTestResultsInfo.setQ14((article.get("S")));
            studentTestResultsInfo.setQ15((article.get("T")));
            studentTestResultsInfo.setQ16((article.get("U")));
            studentTestResultsInfo.setQ17((article.get("V")));
            studentTestResultsInfo.setQ18((article.get("W")));
            studentTestResultsInfo.setQ19((article.get("X")));
            studentTestResultsInfo.setQ20((article.get("Y")));
            studentTestResultsInfo.setQ21((article.get("Z")));
            studentTestResultsInfo.setQ22((article.get("AA")));
            studentTestResultsInfo.setQ23((article.get("AB")));
            studentTestResultsInfo.setQ24((article.get("AC")));
            studentTestResultsInfo.setQ25((article.get("AD")));
            studentTestResultsInfo.setQ26((article.get("AE")));
            studentTestResultsInfo.setQ27((article.get("AF")));
            studentTestResultsInfo.setQ28((article.get("AG")));
            studentTestResultsInfo.setQ29((article.get("AH")));
            studentTestResultsInfo.setQ30((article.get("AI")));
            studentTestResultsInfo.setQ31((article.get("AJ")));
            studentTestResultsInfo.setQ32((article.get("AK")));
            studentTestResultsInfo.setQ33((article.get("AL")));
            studentTestResultsInfo.setQ34((article.get("AM")));
            studentTestResultsInfo.setQ35((article.get("AN")));
            studentTestResultsInfo.setQ36((article.get("AO")));
            studentTestResultsInfo.setQ37((article.get("AP")));
            studentTestResultsInfo.setQ38((article.get("AQ")));
            studentTestResultsInfo.setQ39((article.get("AR")));
            studentTestResultsInfo.setQ40((article.get("AS")));

            listStudentTestResults.add(studentTestResultsInfo);
        }

        // 리스트에 담은 VO를 DB에 저장
        for (StudentTestResultsDto oneStudentTestResults : listStudentTestResults){
//            excelService.excelUpload(oneExcel);
            StudentTestResults ex = oneStudentTestResults.toStudentTestResults();
            ex.setTest(test);
           studentTestResultsRepository.save(ex);
        }
    }
}
