package com.example.nf.newfine_backend.test.repository;

import com.example.nf.newfine_backend.test.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}
