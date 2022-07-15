package com.example.nf.newfine_backend.Homework.controller;

import com.example.nf.newfine_backend.Homework.dto.ResponseDto;
import com.example.nf.newfine_backend.Homework.dto.SaveRequestDto;
import com.example.nf.newfine_backend.Homework.service.THomeworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class THomeworkController {

    private final THomeworkService tHomeworkService;
//    private final FileService fileService;

    /**
     * 게시글 생성
     */
    @PostMapping("/homework/post")
    public Long save(@RequestBody final SaveRequestDto params) {
        return tHomeworkService.save(params);
    }


    /**
     * 게시글 리스트 조회
     */
    @GetMapping("/homework/list")
    public List<ResponseDto> findAll() {
        return tHomeworkService.findAll();
    }
//    public List<ResponseDto> findAllByPageRequest(@PageableDefault(page = 1, size=5, sort="createdTime") Pageable pageable) {
//        return tHomeworkService.findAllByPageRequest(pageable);
//
//    }



    //개별 조회
    @GetMapping("/homework/{id}")
    public ResponseDto findById(@PathVariable Long id) {
        tHomeworkService.updateCount(id);
        return tHomeworkService.findById(id);
    }

    /**
     * 게시글 수정
     */
    @PutMapping("/homework/{id}")
    public Long save(@PathVariable final Long id, @RequestBody final SaveRequestDto params) {
        return tHomeworkService.update(id, params);
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/homework/{id}")
    public void delete(@PathVariable Long id){
        tHomeworkService.delete(id);
    }
}




