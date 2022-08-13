package com.example.nf.newfine_backend.Homework.service;

import com.example.nf.newfine_backend.Homework.Repository.SHomeworkRepository;
import com.example.nf.newfine_backend.Homework.Repository.THomeworkRepository;
import com.example.nf.newfine_backend.Homework.domain.SHomework;
import com.example.nf.newfine_backend.Homework.domain.THomework;
import com.example.nf.newfine_backend.Homework.dto.SHomeworkDto;
import com.example.nf.newfine_backend.course.Listener;
import com.example.nf.newfine_backend.course.ListenerRepository;
import com.example.nf.newfine_backend.member.student.domain.Student;
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
        THomework tHomework = tHomeworkRepository.findById(thId).get();
        List<SHomework> sHomeworks = sHomeworkRepository.findAllByThomework(tHomework);
        List<SHomeworkDto> sHomeworkDtos = new ArrayList<>();

        sHomeworks.forEach(s -> sHomeworkDtos.add(SHomeworkDto.toDto(s)));
        return sHomeworkDtos;
    }

    public List<SHomeworkDto> getSHomeworksByStudent1(Student student) {
        List<Listener> listeners = listenerRepository.findListenersByStudent(student);
        List<SHomeworkDto> sHomeworkDtos = new ArrayList<>();
        for (Listener listener : listeners) {
            List<SHomework> sHomeworks = sHomeworkRepository.findAllByListener1(listener.getId());
            sHomeworks.forEach(s -> sHomeworkDtos.add(SHomeworkDto.toDto(s)));
        }
        return sHomeworkDtos;
    }

    public List<SHomeworkDto> getSHomeworksByStudent2(Student student) {
        List<Listener> listeners = listenerRepository.findListenersByStudent(student);
        List<SHomeworkDto> sHomeworkDtos = new ArrayList<>();
        for (Listener listener : listeners) {
            List<SHomework> sHomeworks = sHomeworkRepository.findAllByListener2(listener.getId());
            sHomeworks.forEach(s -> sHomeworkDtos.add(SHomeworkDto.toDto(s)));
        }
        return sHomeworkDtos;
    }

    @Transactional
      public SHomework updateSHomework(Long Id, String state) {
            System.out.println(state);
            SHomework sHomework = sHomeworkRepository.findById(Id).get();
            sHomework.setIschecked(true);
            sHomeworkRepository.save(sHomework);
        if (state.equals("A")){
            System.out.println(state);
            sHomework.setIschecked(true);
            sHomework.setGrade('A');
            sHomeworkRepository.save(sHomework);
        }
        else if(state.equals("B")){
            System.out.println(state);
            sHomework.setIschecked(true);
            sHomework.setGrade('B');
            sHomeworkRepository.save(sHomework);
        }
        else if(state.equals("C")){
            System.out.println(state);
            sHomework.setIschecked(true);
            sHomework.setGrade('C');
            sHomeworkRepository.save(sHomework);
        }

        return sHomework;
    }
    //@Transactional public void checkSHomework(Long id) { sHomeworkRepository.checkSHomework(id); }

//    @Transactional
//    public Long updateSHomework(final Long Id, SHomeworkDto sHomeworkDto) {
//
//        SHomework sHomework = sHomeworkRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
//        sHomework.update(sHomeworkDto.isIschecked(), sHomeworkDto.getGrade(), sHomeworkDto.getCheckedDate());
//        return Id;
   /*
    @Transactional
    public String deleteSHomework(Long shId) {
        SHomework sHomework = sHomeworkRepository.findById(shId).orElseThrow(()-> new IllegalArgumentException("댓글 Id를 찾을 수 없습니다."));
        sHomeworkRepository.deleteById(shId);
        return "삭제 완료";
    }
    */
}

