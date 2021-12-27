package com.ytking.hyperheuristicscheduling.controller;

import com.ytking.hyperheuristicscheduling.dao.OrderDao;
import com.ytking.hyperheuristicscheduling.dao.ProcessDao;
import com.ytking.hyperheuristicscheduling.dao.ProductDao;
import com.ytking.hyperheuristicscheduling.dao.R;
import com.ytking.hyperheuristicscheduling.service.MakespanService;
import com.ytking.hyperheuristicscheduling.service.ReadDataService;
import com.ytking.hyperheuristicscheduling.util.result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author 应涛
 * @date 2021/12/13
 * @function：
 */
@RestController
@RequestMapping("/read")
public class DataController {
    @Autowired
    private ReadDataService readDataService;
    @Autowired
    private MakespanService makespanService;
    @PostMapping("/readorder")
    public R readorder(MultipartFile file)  {
        List<OrderDao> orderList ;
        orderList= readDataService.readOrder(file);
        return result.success(orderList);
    }
    @PostMapping("/readproduct")
    public R readproduct(MultipartFile file)  {
        List<ProductDao> productList ;
        productList= readDataService.readProduct(file);
        return result.success(productList);
    }
    @PostMapping("/readprocess")
    public R readprcess(MultipartFile file)  {
        List<ProcessDao> processList ;
        processList= readDataService.readProcess(file);
        return result.success(processList);
    }
    @PostMapping("/ga")
    public R test(List<MultipartFile> fileList)  {
        List<ProcessDao> processList ;
        makespanService.hhGA(fileList);
        return result.success("aa");
    }
}
