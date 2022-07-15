package com.example.nf.newfine_backend.Homework.Repository;

import com.example.nf.newfine_backend.Homework.domain.THomework;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface THomeworkRepository extends JpaRepository<THomework, Long> {
    @Modifying
    @Query("update THomework p set p.count = p.count + 1 where p.id = :id")
    int updateCount(Long id);

    Page<THomework> findAll(Pageable pageable);
}
