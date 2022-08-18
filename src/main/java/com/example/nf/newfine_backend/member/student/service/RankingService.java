package com.example.nf.newfine_backend.member.student.service;

import com.example.nf.newfine_backend.member.student.domain.Tier;
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
import java.util.Objects;
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
                .myTier(student.getTier())
                .build();

//        return ranking+1;
    }

    public String updateLevel() {
        String key = "ranking";

        // ZSet(Sorted Set): 중복X 데이터 Collection, Score(가중치)에 따라 정렬
        ZSetOperations<String, String> stringStringZSetOperations = redisTemplate.opsForZSet();

        // 데이터 개수
        Long count=stringStringZSetOperations.zCard(key);
        System.out.println("데이터 개수:                 "+count);


        Set<ZSetOperations.TypedTuple<String>> typedTuples = stringStringZSetOperations.reverseRangeWithScores(key, 0, count);
        List<RankingResponseDto> collect = typedTuples.stream().map(RankingResponseDto::convertToRankingResponseDto).collect(Collectors.toList());
        for (int i=0; i<count; i++){
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+collect);
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n닉네임: "+studentRepository.findByNickname(collect.get(i).getNickname()));
            System.out.println(Objects.equals((studentRepository.findByNickname(collect.get(i).getNickname())), null));
            System.out.println(((studentRepository.findByNickname(collect.get(i).getNickname()))==null));
            if (Objects.equals((studentRepository.findByNickname(collect.get(i).getNickname())), null)){
                redisTemplate.opsForZSet().remove("ranking", collect.get(i).getNickname());
            }
        }

        // Tier: CHALLENGER
        Set<ZSetOperations.TypedTuple<String>> challengerTuples = stringStringZSetOperations.reverseRangeWithScores(key, 0, count);
        List<RankingResponseDto> challengerCollect = challengerTuples.stream().map(RankingResponseDto::convertToRankingResponseDto).collect(Collectors.toList());
        System.out.println("삭제가 됐어야함.:        "+ challengerCollect);
        // Tier: CHALLENGER
        for (int i=0; i<2; i++){
            if (i==count){
                return "등급 update";
            }
            System.out.println("챌린저 그룹 닉네임: "+challengerCollect.get(i).getNickname());
            Student student=studentRepository.findByNickname(challengerCollect.get(i).getNickname()).orElseThrow(RuntimeException::new);
            student.setTier(Tier.CHALLENGER);
            System.out.println("학생 등급:                 "+student);
            studentRepository.save(student);
        }
        System.out.println("List<RankingResponseDto>"+challengerCollect);
        System.out.println(challengerCollect.get(0).getNickname());
        // Tier: MASTER
        for (int i=2; i<8; i++){
            if (i==count){
                return "등급 update";
            }
            Student student=studentRepository.findByNickname(challengerCollect.get(i).getNickname()).orElseThrow(RuntimeException::new);
            student.setTier(Tier.MASTER);
            System.out.println("학생 등급:                 "+student);
            studentRepository.save(student);

        }
        // Tier: DIA
        for (int i=8; i<20; i++){
            if (i==count){
                return "등급 update";
            }
            Student student=studentRepository.findByNickname(challengerCollect.get(i).getNickname()).orElseThrow(RuntimeException::new);
            student.setTier(Tier.DIA);
            System.out.println("학생 등급:                 "+student);
            studentRepository.save(student);
        }
        // Tier: PLATINUM
        for (int i=20; i<50; i++){
            if (i==count){
                return "등급 update";
            }
            Student student=studentRepository.findByNickname(challengerCollect.get(i).getNickname()).orElseThrow(RuntimeException::new);
            student.setTier(Tier.PLATINUM);
            studentRepository.save(student);
        }
        // Tier: GOLD
        for (int i=50; i<100; i++){
            if (i==count){
                return "등급 update";
            }
            Student student=studentRepository.findByNickname(challengerCollect.get(i).getNickname()).orElseThrow(RuntimeException::new);
            student.setTier(Tier.GOLD);
            studentRepository.save(student);

        }
        // Tier: NEW
        for (int i=100; i<count; i++){
            if (i==count){
                return "등급 update";
            }
            Student student=studentRepository.findByNickname(challengerCollect.get(i).getNickname()).orElseThrow(RuntimeException::new);
            student.setTier(Tier.NEW);
            studentRepository.save(student);
//            try{
//                Student student=studentRepository.findByNickname(challengerCollect.get(i).getNickname()).orElseThrow(RuntimeException::new);
//                student.setTier(Tier.CHALLENGER);
//                System.out.println("학생 등급:                 "+student);
//                studentRepository.save(student);
//            }catch(NullPointerException e){
//                redisTemplate.opsForZSet().remove("ranking", challengerCollect.get(i).getNickname());
//            }
        }
//        // Tier: MASTER
//        Set<ZSetOperations.TypedTuple<String>> masterTuples = stringStringZSetOperations.reverseRangeWithScores(key, 2, 7);
//        // Tier: DIA
//        Set<ZSetOperations.TypedTuple<String>> diaTuples = stringStringZSetOperations.reverseRangeWithScores(key, 8, 19);
//        // Tier: PLATINUM
//        Set<ZSetOperations.TypedTuple<String>> platinumTuples = stringStringZSetOperations.reverseRangeWithScores(key, 20, 49);
//        // Tier: GOLD
//        Set<ZSetOperations.TypedTuple<String>> goldTuples = stringStringZSetOperations.reverseRangeWithScores(key, 50, 99);
//        // Tier: NEW
//        Set<ZSetOperations.TypedTuple<String>> newTuples = stringStringZSetOperations.reverseRangeWithScores(key, 100, stringStringZSetOperations.zCard(key));


        return "등급 update";
    }

    public Student getNullStudent(){
        Student park=studentRepository.getById(1L);
        return park;
    }
}
