package com.ytking.hyperheuristicscheduling.service;

import com.ytking.hyperheuristicscheduling.dao.OrderDao;
import com.ytking.hyperheuristicscheduling.dao.ProcessDao;
import com.ytking.hyperheuristicscheduling.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 应涛
 * @date 2021/12/21
 * @function：
 */
@Service
public class MakespanService {
    @Autowired
    private ReadDataService readDataService;
    List<OrderDao> orderList;
    List<ProductDao> productList;
    List<ProcessDao> processList;
    int[] need = new int[10];//成品所需个数
    int[] semiNeed = new int[21];//半成品所需个数
    List<Integer> code = new ArrayList<>(); //半成品编码

    /**
     * 读取各种初始数据
     *
     * @param fileList
     */
    public void read(List<MultipartFile> fileList) {
        for (MultipartFile file : fileList) {//读取初始信息
            if (file.getOriginalFilename().contains("order")) {
                orderList = readDataService.readOrder(file);
//                System.out.println("orderList.toString() = " + orderList.toString());
            }
            if (file.getOriginalFilename().contains("process")) {
                processList = readDataService.readProcess(file);
//                System.out.println("orderList.toString() = " + processList.toString());
            }
            if (file.getOriginalFilename().contains("product")) {
                productList = readDataService.readProduct(file);
//                System.out.println("orderList.toString() = " + productList.toString());
            }
        }
    }

    /**
     * 进行初始化编码
     */
    public void code() {
        for (OrderDao order : orderList) {//读取所需产品个数
            need[Integer.parseInt(order.getProd_id()) - 1] += order.getNum();
        }
        for (int i = 0; i < 6; i++) {//计算所需半成品个数
            List<Integer> list = productList.get(i).getRealNeeds();
            for (Integer j : list) {
                semiNeed[j] += need[i];
            }
        }
        //根据订单截止时间来生成初始的编码

    }

    /**
     * 计算makespan
     *
     * @param fileList
     */
    public void makespan(List<MultipartFile> fileList) {
        read(fileList);//信息读取
        code();
        reset();
    }

    /**
     * 重置所有全局变量
     */
    public void reset() {
        need = new int[10];
        semiNeed = new int[21];
        code = new ArrayList<>();
    }
}
