package com.horace.tool.entity;

import com.horace.tool.enums.LeetCodeLevel;
import lombok.Data;

/**
 * 2022/8/27
 * com.horace.tool.entity
 * horace
 * 一个小的关于leetcode必要信息的实体类
 */
@Data
public class LeetCodeEntity {
    //题目等级
    private LeetCodeLevel level;
    //题目名称
    private String questionName;
    //题目编号
    private String questionId;
    //成功率
    private Double chance;
}
