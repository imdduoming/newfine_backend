package com.example.nf.newfine_backend.Homework.Repository;

import com.example.nf.newfine_backend.Homework.domain.SHomework;
import com.example.nf.newfine_backend.Homework.domain.THomework;
import com.example.nf.newfine_backend.course.Listener;
import com.example.nf.newfine_backend.member.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SHomeworkRepository extends JpaRepository<SHomework, Long> {
    List<SHomework> findAllByThomework(THomework tHomework);
    List<SHomework> findAllByListener(Listener listener);

    @Modifying
    @Query ("UPDATE SHomework s set s.ischecked = true WHERE s.shid = :shid")
    void checkSHomework(Long shid);

    @Query("SELECT s FROM SHomework s WHERE s.ischecked = false ORDER BY s.shid DESC")
    List<SHomework> findAllByStudent1(Student student);

    @Query("SELECT s FROM SHomework s WHERE s.ischecked = true ORDER BY s.shid DESC")
    List<SHomework> findAllByStudent2(Student student);
}
