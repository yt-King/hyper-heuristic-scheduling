package com.ytking.hyperheuristicscheduling.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    List<Integer> nowPess; //当前加工的是第几道工序
    List<Integer> maxBat; //当前加工工序的批次加工个数
}
