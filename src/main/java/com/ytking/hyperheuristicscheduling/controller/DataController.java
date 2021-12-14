package com.ytking.hyperheuristicscheduling.controller;

import com.ytking.hyperheuristicscheduling.dao.OrderDao;
import com.ytking.hyperheuristicscheduling.dao.ProcessDao;
import com.ytking.hyperheuristicscheduling.dao.ProductDao;
import com.ytking.hyperheuristicscheduling.service.ReadDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 应涛
 * @date 2021/12/13
 * @function：
 */
@RestController
@RequestMapping("/test")
public class DataController {
    @Autowired
    private ReadDataService readDataService;
    @PostMapping("/readorder")
    public List<OrderDao> readorder(MultipartFile file)  {
        List<OrderDao> orderList ;
        orderList= readDataService.readOrder(file);
        return orderList;
    }
    @PostMapping("/readproduct")
    public List<ProductDao> readproduct(MultipartFile file)  {
        List<ProductDao> productList ;
        productList= readDataService.readProduct(file);
        return productList;
    }
    @PostMapping("/readprocess")
    public List<ProcessDao> readprcess(MultipartFile file)  {
        List<ProcessDao> processList ;
        processList= readDataService.readProcess(file);
        return processList;
    }
}
