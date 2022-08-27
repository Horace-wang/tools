package com.horace.tool.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.horace.tool.entity.LeetCode;
import com.horace.tool.enums.LeetCodeLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 2022/8/27
 * com.horace.tool.service
 * horace
 */
@Service
@Slf4j
public class LeetCodeService {
    public LeetCode getLeetCodeTodayFirst() {
        Map<String, String> map = new HashMap<>();
        map.put("query", "\n query questionOfToday {\n todayRecord {\n date\n userStatus\n question {\n questionId\n frontendQuestionId: questionFrontendId\n difficulty\n      title\n      titleCn: translatedTitle\n      titleSlug\n      paidOnly: isPaidOnly\n      freqBar\n      isFavor\n      acRate\n      status\n      solutionNum\n      hasVideoSolution\n  }\n   }\n}\n ");
        HttpRequest request = HttpRequest.post("https://leetcode.cn/graphql/").body(JSONUtil.toJsonStr(map));
        JSONObject obj = JSONUtil.parseObj(request.execute().body());
        log.info("----------get obj today question is {}", obj);
        JSONArray jsonArray = obj.getJSONObject("data").getJSONArray("todayRecord");
        JSONObject o = (JSONObject) jsonArray.get(0);
        JSONObject question = o.getJSONObject("question");
        LeetCode leetCode = new LeetCode();
        leetCode.setChance(Double.parseDouble(question.getStr("acRate").substring(0, 4)) * 100);
        leetCode.setLevel(LeetCodeLevel.valueOf(question.getStr("difficulty")));
        leetCode.setQuestionId(question.getStr("questionId"));
        leetCode.setQuestionName(question.getStr("titleCn"));
        return leetCode;
    }
}
