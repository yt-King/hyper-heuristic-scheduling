package com.ytking.hyperheuristicscheduling.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 应涛
 * @date 2021/12/13
 * @function：  资源实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResDao {
    int id; //加工的半成品编码
    int pess;//加工的工序
    int start;
    int end;
}
