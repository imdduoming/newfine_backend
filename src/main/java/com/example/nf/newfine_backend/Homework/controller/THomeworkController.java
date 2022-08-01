package com.example.nf.newfine_backend.Homework.controller;

import com.example.nf.newfine_backend.Homework.dto.THomeworkDto;
import com.example.nf.newfine_backend.Homework.service.THomeworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class THomeworkController {

    private final THomeworkService tHomeworkService;


    /**
     * 게시글 생성
     */
    @PostMapping("/homework/post/{courseId}")
    public THomeworkDto save(@PathVariable("courseId") Long courseId, @RequestBody THomeworkDto tHomeworkDto) {
        return tHomeworkService.save(courseId, tHomeworkDto);
    }


    /**
     * 게시글 리스트 조회
     */
    @GetMapping("/homework/list/{courseId}")
    public List<THomeworkDto> getTHomeworks(@PathVariable("courseId") Long courseId) {
        return tHomeworkService.getTHomeworks(courseId);
    }
//    public List<ResponseDto> findAllByPageRequest(@PageableDefault(page = 1, size=5, sort="createdTime") Pageable pageable) {
//        return tHomeworkService.findAllByPageRequest(pageable);
//
//    }



    //개별 조회
    @GetMapping("/homework/{Id}")
    public THomeworkDto findById(@PathVariable Long Id) {
        //tHomeworkService.updateCount(Id);
        return tHomeworkService.findById(Id);
    }

    /**
     * 게시글 수정
     */
    @PutMapping("/homework/{Id}")
    public Long update(@PathVariable final Long Id, @RequestBody THomeworkDto tHomeworkDto) {
        return tHomeworkService.update(Id, tHomeworkDto);
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/homework/{Id}")
    public void delete(@PathVariable Long Id){
        tHomeworkService.delete(Id);
    }
}




