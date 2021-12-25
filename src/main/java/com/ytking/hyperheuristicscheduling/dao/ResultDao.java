package com.ytking.hyperheuristicscheduling.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 应涛
 * @date 2021/12/13
 * @function：  结果实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDao {
    int id; //加工的半成品编码
    int pess;//加工的工序
    int start;
    int end;
    int res;//加工或装配车间的编号
    int type;//加工或装配
}
