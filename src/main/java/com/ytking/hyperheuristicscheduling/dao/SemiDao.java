package com.ytking.hyperheuristicscheduling.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 应涛
 * @date 2021/12/13
 * @function：  半成品实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SemiDao {
    String id;
    String prod_id;
    int num;
    int limit;
    int if_finish;
    int ftime;
}
