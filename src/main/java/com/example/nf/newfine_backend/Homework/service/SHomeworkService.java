package com.example.nf.newfine_backend.Homework.service;

import com.example.nf.newfine_backend.Homework.Repository.SHomeworkRepository;
import com.example.nf.newfine_backend.Homework.Repository.THomeworkRepository;
import com.example.nf.newfine_backend.Homework.domain.SHomework;
import com.example.nf.newfine_backend.Homework.dto.SHomeworkDto;
import com.example.nf.newfine_backend.course.ListenerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SHomeworkService {

    private final SHomeworkRepository sHomeworkRepository;
    private final THomeworkRepository tHomeworkRepository;

    private final ListenerRepository listenerRepository;

    /*
    @Transactional public SHomeworkDto createSHomework(Long ThId, SHomeworkDto sHomeworkDto, Listener listener) {
        SHomework sHomework = new SHomework();
        sHomework.setTitle(sHomeworkDto.getTitle());

        THomework tHomework = tHomeworkRepository.findById(ThId).orElseThrow(() -> new IllegalArgumentException("게시판을 찾을 수 없습니다."));

        sHomework.setListener(listener);
        sHomework.setThomework(tHomework);
        sHomeworkRepository.save(sHomework);

        return SHomeworkDto.toDto(sHomework);

    }
    */


    @Transactional(readOnly = true)
    public List<SHomeworkDto> getSHomeworks(Long thId) {
        List<SHomework> sHomeworks = sHomeworkRepository.findAllByThomeworkId(thId);
        List<SHomeworkDto> sHomeworkDtos = new ArrayList<>();

        sHomeworks.forEach(s -> sHomeworkDtos.add(SHomeworkDto.toDto(s)));
        return sHomeworkDtos;
    }

    @Transactional
    public String deleteSHomework(Long shId) {
        SHomework sHomework = sHomeworkRepository.findById(shId).orElseThrow(()-> new IllegalArgumentException("댓글 Id를 찾을 수 없습니다."));
        sHomeworkRepository.deleteById(shId);
        return "삭제 완료";
    }
}
