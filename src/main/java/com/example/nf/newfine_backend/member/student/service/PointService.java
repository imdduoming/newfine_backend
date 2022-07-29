package com.example.nf.newfine_backend.member.student.service;

import com.example.nf.newfine_backend.member.student.domain.Point;
import com.example.nf.newfine_backend.member.student.repository.PointRepository;
import com.example.nf.newfine_backend.member.domain.Authority;
import com.example.nf.newfine_backend.member.student.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;
    private final RedisTemplate redisTemplate;

    @Transactional
    public void create(Student student, String content, int score) {
        Point point = Point.createPoint(student, content, score);
        student.setPoint(student.getPoint()+score);
        if (student.getAuthority() != Authority.ROLE_ADMIN) {
            redisTemplate.opsForZSet().add("ranking", student.getNickname(), student.getPoint());
        }
        pointRepository.save(point);
    }

    public List<Point> getAllPointList(Long student_id) {
        return pointRepository.findByStudentID(student_id);
    }

    public List<Point> getAllPointListDesc(Long student_id) {
        return pointRepository.findByStudentIDDesc(student_id);
    }
}
