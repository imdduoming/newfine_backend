package com.example.nf.newfine_backend.member.student.repository;

import com.example.nf.newfine_backend.member.student.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PointRepository extends JpaRepository<Point,Long> {
    @Query("select p from Point p join fetch p.owner m where p.owner.id=:student_id")
    List<Point> findByStudentID(Long student_id);


    @Query("select p from Point p join fetch p.owner m where p.owner.id=:student_id order by p.id DESC ")
    List<Point> findByStudentIDDesc(Long student_id);
}
