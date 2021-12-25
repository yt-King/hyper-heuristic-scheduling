package com.ytking.hyperheuristicscheduling.service;

import com.sun.org.apache.bcel.internal.classfile.Code;
import com.ytking.hyperheuristicscheduling.dao.*;
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
    List<Integer> code = new ArrayList<>(); //半成品编码,从1开始
    SemiDao[] semis=new SemiDao[21];//编码的信息数组
    List<List<Integer>> lists1 = new ArrayList<>();//编码的临时信息数组
    List<List<Integer>> lists2= new ArrayList<>();//编码的临时信息数组
    ResDao[] res = new ResDao[10]; //加工资源
    ResDao[] resend = new ResDao[2];//装配资源
    List<ResultDao> result;
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
        for (OrderDao order : orderList) {//在读取时已经按升序排序完成
            List<Integer> list = productList.get(Integer.parseInt(order.getProd_id()) - 1).getRealNeeds();//需要的半成品类别
            for (Integer j : list) { //j:需要的半成品类别编号
                int maxPi=0;//最大批次生产个数
                for (int t = j*4-4; t < j*4; t++) {//找到maxPi
                    if(Integer.parseInt(processList.get(t).getNum())>maxPi)
                        maxPi=Integer.parseInt(processList.get(t).getNum());
                }
                for (int k = 0; k < order.getNum(); k++) {
                    for (int t = j*4-4,pess=1; t < j*4; t++,pess++) {//根据maxPi进行编码
                        for (int p = 0; p < Math.ceil((double) maxPi/Integer.parseInt(processList.get(t).getNum())); p++) {
                            code.add(j); //添加编码，表示当前所加工的是哪一个半成品，具体工序等信息存放在信息数组semis中
                            int a=Integer.parseInt(processList.get(t).getNum());
                            lists1.get(j).add(a);
                            lists2.get(j).add(pess);
                        }
                    }
                    semis[j].setMaxBat(lists1.get(j));
                    semis[j].setNowPess(lists2.get(j));
                }
            }
        }
    }

    /**
     * 计算makespan
     *
     * @param fileList
     */
    public void makespan(List<MultipartFile> fileList) {
        init();
        read(fileList);//信息读取
        code();//编码以及信息数组生成
        System.out.println("code.size() = " + code.size());
        int size=0;
        for (int i = 1; i < semis.length; i++) {
            size+=semis[i].getNowPess().size();
            System.out.println("size = " + size);
            System.out.println("semis[i].getNowPess().size() = " + semis[i].getNowPess().size());
        }
        for (int i:code) {
            int flag = semis[i].getFlag();
            int pess = semis[i].getNowPess().get(flag); //获取当前工序
            int time = processList.get((i-1)*4+pess).getTime();



            flag++;
            semis[i].setFlag(flag);

//            System.out.println("i = " + i+"   "+flag);
        }
        reset();
    }

    /**
     * 重置所有全局变量
     */
    public void reset() {
        need = new int[10];
        semiNeed = new int[21];
        code = new ArrayList<>();
        lists1 = new ArrayList<>();
        lists2 = new ArrayList<>();
    }
    /**
     * 初始化全局变量
     */
    public void init() {
        need = new int[10];
        semiNeed = new int[21];
        code = new ArrayList<>();
        //初始化编码信息数组
        for (int i = 0; i < semis.length; i++) {
            semis[i]=new SemiDao();
        }
        //初始化临时编码信息数组
        for (int i = 0; i < 21; i++) {
            lists1.add(new ArrayList<>());
        }
        //初始化临时编码信息数组
        for (int i = 0; i < 21; i++) {
            lists2.add(new ArrayList<>());
        }
        //初始化加工资源数组
        for (int i = 0; i < res.length; i++) {
            res[i]=new ResDao();
        }
        //初始化装配资源数组
        for (int i = 0; i < resend.length; i++) {
            resend[i]=new ResDao();
        }

    }
}
