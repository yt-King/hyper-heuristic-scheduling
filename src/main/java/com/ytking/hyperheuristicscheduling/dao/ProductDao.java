package com.ytking.hyperheuristicscheduling.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 应涛
 * @date 2021/12/13
 * @function：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDao {
    int id;
    String prod_id;
    List<String> needs;
    List<Integer> realNeeds; //将字符串转化为编号
}
