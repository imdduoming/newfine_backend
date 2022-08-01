package com.example.nf.newfine_backend.member.student.controller;

import com.example.nf.newfine_backend.member.student.domain.Point;
import com.example.nf.newfine_backend.member.student.dto.PointResponseDto;
import com.example.nf.newfine_backend.member.dto.response.ListResult;
import com.example.nf.newfine_backend.member.dto.response.Result;
import com.example.nf.newfine_backend.member.student.exception.PhoneNumberNotFoundException;
import com.example.nf.newfine_backend.member.student.service.PointService;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.service.ResponseService;
import com.example.nf.newfine_backend.member.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointController {

    private final PointService pointService;
    private final StudentRepository studentRepository;
    private final ResponseService responseService;

    @GetMapping("/point")
    public Result getByID(){
        Student student=studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        List<Point> result= pointService.getAllPointList(student.getId());
        ListResult<PointResponseDto> list=responseService.getListResult(result.stream().map(PointResponseDto::convertToPointDto).collect(Collectors.toList()));
        int answer=0;
        for(int i=0; i<result.size(); i++) {
            answer += result.get(i).getScore();
            list.getData().get(i).setScoreSum(answer);
        }
        return list;
    }

    @GetMapping("/pointDesc")
    public Result getByIDDesc(){
        Student student=studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        List<Point> result= pointService.getAllPointListDesc(student.getId());
        ListResult<PointResponseDto> list=responseService.getListResult(result.stream().map(PointResponseDto::convertToPointDto).collect(Collectors.toList()));
        int answer=0;
        for(int i=result.size()-1; i>=0; i--) {
            answer += result.get(i).getScore();
            list.getData().get(i).setScoreSum(answer);
        }
        return list;
    }
}
