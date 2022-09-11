package com.example.nf.newfine_backend;

import com.example.nf.newfine_backend.member.student.service.RankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {
    private final RankingService rankingService;

    //    @Scheduled(cron = "0 0 0/1 * * *")	// 1시간마다
    @Scheduled(cron = "0 */5 * * * *")	// 5분마다
    public void updateLevel() throws Exception {
//        if(!env.equals("prod")) {
//            log.info("매일 5분마다 - " + env);
//        }
        System.out.println(rankingService.updateLevel());

    }
}
