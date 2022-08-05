package com.example.nf.newfine_backend.Homework.Repository;

import com.example.nf.newfine_backend.Homework.domain.THomework;
import com.example.nf.newfine_backend.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface THomeworkRepository extends JpaRepository<THomework, Long> {
    @Modifying
    @Query("update THomework p set p.count = p.count + 1 where p.id = :id")
    int updateCount(Long id);

    //Page<THomework> findAll(Pageable pageable);

    List<THomework> findTHomeworksByCourse(Course course);
}
