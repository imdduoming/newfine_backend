package com.example.nf.newfine_backend.student.service;

import com.example.nf.newfine_backend.student.domain.Student;
import com.example.nf.newfine_backend.student.dto.RankingResponseDto;
import com.example.nf.newfine_backend.student.exception.PhoneNumberNotFoundException;
import com.example.nf.newfine_backend.student.repository.StudentRepository;
import com.example.nf.newfine_backend.student.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingService {
    private final RedisTemplate redisTemplate;
    private final StudentRepository studentRepository;

    public List<RankingResponseDto> getRankingList() {
        String key = "ranking";

        // ZSet(Sorted Set): 중복X 데이터 Collection, Score(가중치)에 따라 정렬
        ZSetOperations<String, String> stringStringZSetOperations = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples = stringStringZSetOperations.reverseRangeWithScores(key, 0, 10);
        List<RankingResponseDto> collect = typedTuples.stream().map(RankingResponseDto::convertToRankingResponseDto).collect(Collectors.toList());
        return collect;
    }

    public Long getMyRank(){
        Long ranking=0L;
        Student student=studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        Double ranking1 = redisTemplate.opsForZSet().score("ranking", student.getNickname());
        Set<String> ranking2 = redisTemplate.opsForZSet().reverseRangeByScore("ranking", ranking1, ranking1, 0, 1);
        for (String s : ranking2) {
            ranking = redisTemplate.opsForZSet().reverseRank("ranking", s);
        }
        return ranking+1;
    }
}
