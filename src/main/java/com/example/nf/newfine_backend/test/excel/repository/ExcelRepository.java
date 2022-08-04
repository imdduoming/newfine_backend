package com.example.nf.newfine_backend.test.excel.repository;

import com.example.nf.newfine_backend.test.excel.domain.Excel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelRepository extends JpaRepository<Excel, Long> {
}
