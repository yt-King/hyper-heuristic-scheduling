package com.ytking.hyperheuristicscheduling.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 应涛
 * @date 2021/12/13
 * @function：  半成品实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SemiDao {
    int id;
    int flag=0;//指向当前正在进行的工序
    int time;//上一道工序的完成时间
    int pess;//上一道工序的批次
    List<Integer> nowPess = new ArrayList<>(); //当前加工的是第几道工序
    List<Integer> maxBat = new ArrayList<>(); //当前加工工序的批次加工个数
}
