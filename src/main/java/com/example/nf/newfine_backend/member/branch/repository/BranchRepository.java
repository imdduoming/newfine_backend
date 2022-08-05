package com.example.nf.newfine_backend.member.branch.repository;

import com.example.nf.newfine_backend.member.branch.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
}
