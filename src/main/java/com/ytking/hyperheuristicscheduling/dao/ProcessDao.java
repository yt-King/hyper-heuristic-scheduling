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
public class ProcessDao {
    int id;
    String processId;
    String preProcessId;
    String mode;
    int time;
    String num;
    String type;
}
