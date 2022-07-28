package com.example.nf.newfine_backend.student.controller;

import com.example.nf.newfine_backend.student.dto.response.Result;
import com.example.nf.newfine_backend.student.service.RankingService;
import com.example.nf.newfine_backend.student.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ranking")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;
    private final ResponseService responseService;

    @GetMapping("/allRank")
    public Result getRankingList(){
        return responseService.getListResult(rankingService.getRankingList());
    }

    @GetMapping("/myRank")
    public Result getMyRank() {
        return responseService.getSingleResult(rankingService.getMyRank());
    }


}
