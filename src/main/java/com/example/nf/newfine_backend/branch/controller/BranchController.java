package com.example.nf.newfine_backend.branch.controller;

import com.example.nf.newfine_backend.branch.domain.Branch;
import com.example.nf.newfine_backend.branch.dto.BranchResponseDto;
import com.example.nf.newfine_backend.branch.service.BranchService;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.member.student.dto.StudentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/branch")
public class BranchController {

    private final BranchService branchService;

    @GetMapping("/getBranchList")
    public List<Branch> getBranchList(){
        return branchService.getBranchList();
    }
//    @GetMapping("/getBranchList")
//    public ResponseEntity<BranchResponseDto> getBranchList() {
//        return ResponseEntity.ok(branchService.getBranchList());
//    }
}
