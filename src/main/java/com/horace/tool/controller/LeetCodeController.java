package com.horace.tool.controller;

import com.horace.tool.service.LeetCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2022/8/27
 * com.horace.tool.controller
 * horace
 * 所有关于leetcode的工具都在这里了
 */
@RestController
@Slf4j
public class LeetCodeController {
    private final LeetCodeService leetCodeService;

    public LeetCodeController(LeetCodeService leetCodeService) {
        this.leetCodeService = leetCodeService;
    }

    @GetMapping("/leetcode/first")
    public String getFirst() {
        return leetCodeService.getQuestion();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void getQuestion() {
        leetCodeService.insertRedis();
        log.info("------------定时任务结束-----------");
    }
}
