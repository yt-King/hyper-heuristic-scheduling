package com.ytking.hyperheuristicscheduling.service;

import com.ytking.hyperheuristicscheduling.dao.OrderDao;
import com.ytking.hyperheuristicscheduling.dao.ProcessDao;
import com.ytking.hyperheuristicscheduling.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public void read(List<MultipartFile> fileList){
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
    public void makespan(List<MultipartFile> fileList) {
        read(fileList);//信息读取
    }
}
