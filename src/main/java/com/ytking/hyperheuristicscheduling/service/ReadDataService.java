package com.ytking.hyperheuristicscheduling.service;

import com.ytking.hyperheuristicscheduling.dao.OrderDao;
import com.ytking.hyperheuristicscheduling.dao.ProcessDao;
import com.ytking.hyperheuristicscheduling.dao.ProductDao;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author 应涛
 * @date 2021/12/13
 * @function：
 */
@Service
public class ReadDataService {
    public List<OrderDao> readOrder(MultipartFile file) {
        List<OrderDao> orderList = new ArrayList();//订单读取

        File tempfile = null;
        try {
            //将MultipartFile转化为file进行操作
            String originalFilename = file.getOriginalFilename();
            tempfile= File.createTempFile(originalFilename, "csv");
            file.transferTo(tempfile);
            tempfile.deleteOnExit();
            //读取数据
            InputStreamReader isr = new InputStreamReader(new FileInputStream(tempfile), "GBK");//设置编码防止中文乱码
            BufferedReader reader= new BufferedReader(isr);
            reader.readLine();
            String line = null;
            int x = 0;
            while ((line = reader.readLine()) != null) {
                OrderDao order = new OrderDao();
                String item[] = line.split(",");
                order.setId(item[0]);
                order.setProd_id(item[1].substring(item[1].length()-1));
                order.setNum(Integer.parseInt(item[2]));
                orderList.add(order);
                //获取时间差
                Calendar nowDate=Calendar.getInstance();
                Calendar oldDate=Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                nowDate.setTime(simpleDateFormat.parse(item[3]));//设置为当前系统时间
                oldDate.setTime(simpleDateFormat.parse(item[4]));//设置为想要比较的日期
                Long timeNow=nowDate.getTimeInMillis();
                Long timeOld=oldDate.getTimeInMillis();
                int time = -(int)(timeNow-timeOld)/(1000*60);//相差毫秒数
                order.setLimit(time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }
    public List<ProductDao> readProduct(MultipartFile file) {
        List<ProductDao> productList = new ArrayList();//订单读取

        File tempfile = null;
        try {
            //将MultipartFile转化为file进行操作
            String originalFilename = file.getOriginalFilename();
            tempfile= File.createTempFile(originalFilename, "csv");
            file.transferTo(tempfile);
            tempfile.deleteOnExit();
            //读取数据
            InputStreamReader isr = new InputStreamReader(new FileInputStream(tempfile), "GBK");//设置编码防止中文乱码
            BufferedReader reader= new BufferedReader(isr);
            reader.readLine();
            String line = null;
            int i =0;
            while ((line = reader.readLine()) != null) {
                String item[] = line.split(",");
                ProductDao product = new ProductDao();
                item = line.split(",");
                List<String> needs = new ArrayList<>();
                List<Integer> realNeeds = new ArrayList<>();
                while (!item[0].equals(item[1])){
                    product.setId(i);
                    product.setProd_id(item[0]);  //读取所需半成品
                    needs.add(item[1]);
                    realNeeds.add((int)item[1].charAt(item[1].length()-1)-96); //读取到的半成品转换成对应编码
                    product.setNeeds(needs);
                    product.setRealNeeds(realNeeds);
                    item = reader.readLine().split(",");
                }
                i++;
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
    public List<ProcessDao> readProcess(MultipartFile file) {
        List<ProcessDao> processList = new ArrayList();//订单读取
        File tempfile = null;
        try {
            //将MultipartFile转化为file进行操作
            String originalFilename = file.getOriginalFilename();
            tempfile= File.createTempFile(originalFilename, "csv");
            file.transferTo(tempfile);
            tempfile.deleteOnExit();
            //读取数据
            InputStreamReader isr = new InputStreamReader(new FileInputStream(tempfile), "GBK");//设置编码防止中文乱码
            BufferedReader reader= new BufferedReader(isr);
            reader.readLine();
            String line = null;
            int i =0;
            while ((line = reader.readLine()) != null) {
                ProcessDao process = new ProcessDao();
                String item[] = line.split(",");
                if(item[12].contains("加工")){
                    process.setId(i++);
                    process.setProcessId(item[3]);
                    process.setPreProcessId(item[4]);
                    process.setMode(item[8]);
                    process.setTime(Integer.parseInt(item[9]));
                    process.setNum(item[11]);
                    process.setType(item[12]);
                    processList.add(process);
                    continue;
                }
                process.setId(i++);
                process.setProcessId(item[3]);
                process.setPreProcessId(item[4]);
                process.setMode(item[8]);
                process.setTime(Integer.parseInt(item[9]));
                process.setNum(item[11]);
                process.setType(item[12]);
                processList.add(process);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return processList;
    }
}