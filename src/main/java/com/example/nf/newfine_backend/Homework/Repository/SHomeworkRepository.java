package com.example.nf.newfine_backend.Homework.Repository;

import com.example.nf.newfine_backend.Homework.domain.SHomework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SHomeworkRepository extends JpaRepository<SHomework, Long> {
    List<SHomework> findAllByThomeworkId(Long thomeworkId);
    //shId 아닌 것 같은데..
}
