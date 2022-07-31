package com.example.nf.newfine_backend.member.student.service;

import com.example.nf.newfine_backend.member.student.dto.RankingResponseDto;
import com.example.nf.newfine_backend.member.student.exception.PhoneNumberNotFoundException;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.member.student.dto.MyRankDto;
import com.example.nf.newfine_backend.member.student.repository.StudentRepository;
import com.example.nf.newfine_backend.member.util.SecurityUtil;
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
        Set<ZSetOperations.TypedTuple<String>> typedTuples = stringStringZSetOperations.reverseRangeWithScores(key, 0, stringStringZSetOperations.zCard(key));
        List<RankingResponseDto> collect = typedTuples.stream().map(RankingResponseDto::convertToRankingResponseDto).collect(Collectors.toList());
        return collect;
    }

    public MyRankDto getMyRank(){
        Long ranking=0L;
        Student student=studentRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(PhoneNumberNotFoundException::new);
        Double ranking1 = redisTemplate.opsForZSet().score("ranking", student.getNickname());
        Set<String> ranking2 = redisTemplate.opsForZSet().reverseRangeByScore("ranking", ranking1, ranking1, 0, 1);
        for (String s : ranking2) {
            ranking = redisTemplate.opsForZSet().reverseRank("ranking", s);
        }
        return MyRankDto.builder()
                .myRank((int) (ranking+1))
                .myLevel(student.getLevel())
                .build();

//        return ranking+1;
    }
}
