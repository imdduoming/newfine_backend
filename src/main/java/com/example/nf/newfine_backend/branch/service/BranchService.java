package com.example.nf.newfine_backend.branch.service;

import com.example.nf.newfine_backend.branch.domain.Branch;
import com.example.nf.newfine_backend.branch.repository.BranchRepository;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.Listener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;

//    public List<BranchName> getAllCourses() {
//        return BranchName;
//    }
//}

    public List<Branch> getBranchList(){
        return branchRepository.findAll();
    }
}
