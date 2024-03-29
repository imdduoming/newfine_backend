package com.example.nf.newfine_backend.Homework.Repository;

import com.example.nf.newfine_backend.Homework.domain.SHomework;
import com.example.nf.newfine_backend.Homework.domain.THomework;
import com.example.nf.newfine_backend.course.Listener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SHomeworkRepository extends JpaRepository<SHomework, Long> {
    List<SHomework> findAllByThomework(THomework tHomework);

//    @Modifying
//    @Query ("UPDATE SHomework s set s.ischecked = true WHERE s.shid = :id")
//    void checkSHomework(@Param("id") Long shid);

    @Query("SELECT s FROM SHomework s WHERE s.ischecked = false and s.listener.Id = :id ORDER BY s.shid")
    List<SHomework> findAllByListener1(@Param("id") Long listenerId);

    @Query("SELECT s FROM SHomework s WHERE s.ischecked = true and s.listener.Id = :id ORDER BY s.shid")
    List<SHomework> findAllByListener2(@Param("id") Long listenerId);
    List<SHomework> deleteAllByListener(Listener listener);


    Optional<SHomework> findByListener(Listener listener);
}
