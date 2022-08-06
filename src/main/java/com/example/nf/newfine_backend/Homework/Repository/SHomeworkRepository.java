package com.example.nf.newfine_backend.Homework.Repository;

import com.example.nf.newfine_backend.Homework.domain.SHomework;
import com.example.nf.newfine_backend.Homework.domain.THomework;
import com.example.nf.newfine_backend.course.Listener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SHomeworkRepository extends JpaRepository<SHomework, Long> {
    List<SHomework> findAllByThomework(THomework tHomework);

    @Modifying
    @Query ("UPDATE SHomework s set s.ischecked = true WHERE s.shid = :shid")
    void checkSHomework(Long shid);

    @Query("SELECT s FROM SHomework s WHERE s.ischecked = false and s.listener = :id ORDER BY s.shid DESC")
    List<SHomework> findAllByListener1(@Param("id") Long listenerId);

    @Query("SELECT s FROM SHomework s WHERE s.ischecked = true ORDER BY s.shid DESC")
    List<SHomework> findAllByListener2(Listener listener);
}
