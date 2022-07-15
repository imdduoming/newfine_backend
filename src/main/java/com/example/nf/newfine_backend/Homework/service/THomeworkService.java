package com.example.nf.newfine_backend.Homework.service;


import com.example.nf.newfine_backend.Homework.Repository.THomeworkRepository;
import com.example.nf.newfine_backend.Homework.domain.THomework;
import com.example.nf.newfine_backend.Homework.dto.ResponseDto;
import com.example.nf.newfine_backend.Homework.dto.SaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class THomeworkService {

    private final THomeworkRepository tHomeworkRepository;
    /**
     * 게시글 생성
     */
    @Transactional
    public Long save(final SaveRequestDto params) {

        THomework entity = tHomeworkRepository.save(params.toEntity());
        return entity.getId();
    }

    /**
     * 게시글 리스트 조회
     */
//    public List<ResponseDto> findAllByPageRequest(Pageable pageable) {
//        Page<THomework> page = tHomeworkRepository.findAll(pageable);
//        return page.stream().map(ResponseDto::new).collect(Collectors.toList());
//    }
    public List<ResponseDto> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id", "createdDate");
        List<THomework> list = tHomeworkRepository.findAll(sort);
        return list.stream().map(ResponseDto::new).collect(Collectors.toList());
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
    public ResponseDto findById(Long id) {
        THomework entity = tHomeworkRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
//        entity.increaseCounts();
        return new ResponseDto(entity);
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public Long update(final Long id, final SaveRequestDto params) {

        THomework entity = tHomeworkRepository.findById(id).orElseThrow(() -> new  IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        entity.update(params.getTitle(), params.getContent(), params.getWriter(), params.toEntity().getFilePath());
        return id;
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void delete(Long id){
        THomework entity = tHomeworkRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        tHomeworkRepository.delete(entity);
    }

    /**
     * 조회수 증가
     */
    @Transactional public int updateCount(Long id) { return tHomeworkRepository.updateCount(id); }


}
