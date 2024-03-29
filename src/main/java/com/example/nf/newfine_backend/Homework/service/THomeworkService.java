package com.example.nf.newfine_backend.Homework.service;


import com.example.nf.newfine_backend.FCM.FCMService;
import com.example.nf.newfine_backend.FCM.RequestDTO;
import com.example.nf.newfine_backend.Homework.Repository.SHomeworkRepository;
import com.example.nf.newfine_backend.Homework.Repository.THomeworkRepository;
import com.example.nf.newfine_backend.Homework.domain.SHomework;
import com.example.nf.newfine_backend.Homework.domain.THomework;
import com.example.nf.newfine_backend.Homework.dto.THomeworkDto;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.course.CourseRepository;
import com.example.nf.newfine_backend.course.CourseService;
import com.example.nf.newfine_backend.course.Listener;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class  THomeworkService {

    private final THomeworkRepository tHomeworkRepository;
    private final CourseRepository courseRepository;
    private final CourseService courseService;
    private final SHomeworkRepository sHomeworkRepository;

    private final StudentRepository studentRepository;

    private final FCMService fcmService;
    /**
     * 게시글 생성
     */
    @Transactional
    public Long save(Long courseId, THomeworkDto tHomeworkDto) throws IOException {
        Course course=courseRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("강의를 찾을 수 없습니다."));

        THomework tHomework = new THomework();
        tHomework.setTitle(tHomeworkDto.getTitle());
        tHomework.setContent(tHomeworkDto.getContent());
        tHomework.setCourse(course);
        tHomework.setFdeadline(tHomeworkDto.getFdeadline());
        tHomework.setSdeadline(tHomeworkDto.getSdeadline());
        tHomeworkRepository.save(tHomework);

        // shomeworklist 자동으로 생성하는 부분
        List <Listener> listeners = courseService.getListeners(courseId);
        List <SHomework> sHomeworks = new ArrayList<>();

        System.out.println("수강생");
        System.out.println( listeners);
        for(Listener listener : listeners) {
            System.out.println("수강생이름");
            System.out.println(listener.getStudent().getName());
            System.out.println("thomework id");
            System.out.println(tHomework.getId());
            SHomework sHomework = new SHomework();
            sHomework.setTitle(tHomeworkDto.getTitle());
            sHomework.setStudentId(listener.getStudent().getId());
            System.out.println(sHomework.getStudentId());
            System.out.println(listener.getStudent().getId());
            sHomework.setListener(listener);
            sHomework.setThomework(tHomework);
            sHomework.setIschecked(false);
            sHomeworkRepository.save(sHomework);
            sHomeworks.add(sHomework);

            if (listener.getStudent().getDeviceToken()!=null) {
                RequestDTO requestDTO = new RequestDTO();
                requestDTO.setTargetToken(listener.getStudent().getDeviceToken());
                requestDTO.setTitle("과목 " + tHomework.getCourse().getCName());
                requestDTO.setBody("새로운 과제가 등록되었습니다.");

                System.out.println(requestDTO.getTargetToken() + " "
                        + requestDTO.getTitle() + " " + requestDTO.getBody());

                fcmService.sendMessageTo(
                        requestDTO.getTargetToken(),
                        requestDTO.getTitle(),
                        requestDTO.getBody());
            }
        }

        return THomeworkDto.toDto(tHomework).getId();
    }

    /**
     * 게시글 리스트 조회
     */
//    public List<ResponseDto> findAllByPageRequest(Pageable pageable) {
//        Page<THomework> page = tHomeworkRepository.findAll(pageable);
//        return page.stream().map(ResponseDto::new).collect(Collectors.toList());
//    }
    @Transactional(readOnly = true)
    public List<THomeworkDto> getTHomeworks(Long courseId) {
        Course course=courseRepository.findById(courseId).get();
        List<THomework> tHomeworks = tHomeworkRepository.findTHomeworksByCourse(course);
        List<THomeworkDto> tHomeworkDtos = new ArrayList<>();

        tHomeworks.forEach(s -> tHomeworkDtos.add(THomeworkDto.toDto(s)));
        return tHomeworkDtos;

    }
    /*
    public List<BoardResponseDto> findAllByPageRequest(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
        pageable = PageRequest.of(page, 10);
        Page<Board> boardpage = boardRepository.findAll(pageable);
        return boardpage.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }
    */



//    public Page<BoardResponseDto> findAllByPageRequest(Pageable pageable) {
//        Page<Board> postList = boardRepository.findAll(pageable);
//        return postList.map(
//                post -> new BoardResponseDto(
//                        post.getId(),post.getTitle(),
//                        post.getWriter(), post.getCount(), post.getCreatedDate()
//                ));
//    }


    /**
     * 게시글 개별 조회
     */
    @Transactional(readOnly = true)
    public THomeworkDto findById(Long Id) {
        THomework tHomework = tHomeworkRepository.findById(Id).orElseThrow(()-> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        tHomeworkRepository.findById(Id);
        return THomeworkDto.toDto(tHomework);
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public Long update(final Long Id, THomeworkDto tHomeworkDto) {

        THomework tHomework = tHomeworkRepository.findById(Id).orElseThrow(() -> new  IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        tHomework.update(tHomeworkDto.getTitle(), tHomeworkDto.getContent(), tHomeworkDto.getFdeadline(), tHomeworkDto.getSdeadline());
        return Id;
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void delete(Long Id){
        THomework tHomework = tHomeworkRepository.findById(Id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        tHomeworkRepository.delete(tHomework);
    }

    /**
     * 조회수 증가
     */
    @Transactional public int updateCount(Long id) { return tHomeworkRepository.updateCount(id); }


}
