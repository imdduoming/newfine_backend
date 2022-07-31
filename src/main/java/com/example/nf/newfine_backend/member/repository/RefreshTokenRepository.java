package com.example.nf.newfine_backend.member.repository;


import com.example.nf.newfine_backend.member.jwt.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByKey(String key);   // Member ID 값으로 토큰 가져오기
}