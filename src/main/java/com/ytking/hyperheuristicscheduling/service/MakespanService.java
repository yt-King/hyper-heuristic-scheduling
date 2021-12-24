package com.ytking.hyperheuristicscheduling.service;

import com.sun.org.apache.bcel.internal.classfile.Code;
import com.ytking.hyperheuristicscheduling.dao.OrderDao;
import com.ytking.hyperheuristicscheduling.dao.ProcessDao;
import com.ytking.hyperheuristicscheduling.dao.ProductDao;
import com.ytking.hyperheuristicscheduling.dao.SemiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

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
    SemiDao[] semis=new SemiDao[21];//编码的信息数组
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
    public SemiDao[] code() {
        for (OrderDao order : orderList) {//读取所需产品个数
            need[Integer.parseInt(order.getProd_id()) - 1] += order.getNum();
        }
        for (int i = 0; i < 6; i++) {//计算所需半成品个数
            List<Integer> list = productList.get(i).getRealNeeds();
            for (Integer j : list) {
                semiNeed[j] += need[i];
            }
        }
        //初始化编码信息数组
        for (int i = 0; i < semis.length; i++) {
            semis[i]=new SemiDao();
        }
        //根据订单截止时间来生成初始的编码
        for (OrderDao order : orderList) {//在读取时已经按升序排序完成
            List<Integer> list = productList.get(Integer.parseInt(order.getProd_id()) - 1).getRealNeeds();//需要的半成品类别
            List<Integer> list1=new ArrayList<>();
            List<Integer> list2=new ArrayList<>();
            for (Integer j : list) { //j:需要的半成品类别编号
                for (int k = 0; k < order.getNum(); k++) {
                    int maxPi=0;//最大批次生产个数
                    for (int t = j*4-4; t < j*4; t++) {//找到maxPi
                        if(Integer.parseInt(processList.get(t).getNum())>maxPi)
                            maxPi=Integer.parseInt(processList.get(t).getNum());
                    }
                    for (int t = j*4-4,pess=1; t < j*4; t++,pess++) {//根据maxPi进行编码
                        for (int p = 0; p < Math.ceil((double) maxPi/Integer.parseInt(processList.get(t).getNum())); p++) {
                            code.add(j); //添加编码，表示当前所加工的是哪一个半成品，具体工序等信息存放在信息数组semis中
                            list1.add(Integer.parseInt(processList.get(t).getNum()));
                            list2.add(pess);
                        }
                    }
                    semis[j].setMaxBat(list1);
                    semis[j].setNowPess(list2);
                }
            }
        }
        return semis;
    }

    /**
     * 计算makespan
     *
     * @param fileList
     */
    public SemiDao[] makespan(List<MultipartFile> fileList) {
        read(fileList);//信息读取
        SemiDao[] code=code();
        reset();
        return code;
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
