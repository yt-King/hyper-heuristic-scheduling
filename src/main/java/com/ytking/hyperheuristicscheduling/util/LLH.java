package com.ytking.hyperheuristicscheduling.util;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author 应涛
 * @date 2021/12/26
 * @function： 低级启发式集
 */
public class LLH {
    Random rand = new Random();
    public void todo(int i,List<Integer> code){
        switch (i){
            case 1:
                LLH1(code);
                break;
            case 2:
                LLH2(code);
                break;
            case 3:
                LLH3(code);
                break;
            case 4:
                LLH4(code);
                break;
            case 5:
                LLH5(code);
                break;
            case 6:
                LLH6(code);
                break;
            case 7:
                LLH7(code);
                break;
            case 8:
                LLH8(code);
                break;
            case 9:
                LLH9(code);
                break;
            case 10:
                LLH1(code);
                break;
            default:
                break;
        }
    }
    public void LLH1(List<Integer> code) {
        int a = rand.nextInt(code.size());
        int b = rand.nextInt(code.size());
        Collections.swap(code, a, b);
    }

    public void LLH2(List<Integer> code) {
        int a = rand.nextInt(code.size());
        int b = rand.nextInt(code.size());
        for (int i = 0; i < 4; i++) {
            Collections.swap(code, a + i, b + i);
        }
    }

    public void LLH3(List<Integer> code) {
        int a = rand.nextInt(code.size());
        int b = rand.nextInt(code.size());
        for (int i = 0; i < 4; i++) {
            Collections.swap(code, a - i, b + i);
        }
    }

    public void LLH4(List<Integer> code) {
        int a = rand.nextInt(code.size());
        int b = rand.nextInt(code.size());
        for (int i = 0; i < 4; i++) {
            Collections.swap(code, a + i, b - i);
        }
    }

    public void LLH5(List<Integer> code) {
        int a = rand.nextInt(code.size());
        int b = rand.nextInt(code.size());
        code.add(b, code.get(a));
        code.remove(a);


    }

    public void LLH6(List<Integer> code) {
        int a = rand.nextInt(code.size());
        int b = rand.nextInt(code.size());
        for (int i = 0; i < 4; i++) {
            code.add(b + i, code.get(a + i));
            code.remove(a + i);
        }
    }

    public void LLH7(List<Integer> code) {
        int a = rand.nextInt(code.size());
        int b = rand.nextInt(code.size());
        Collections.swap(code, a, a + 1);
        Collections.swap(code, b, b + 1);
    }

    public void LLH8(List<Integer> code) {
        int a = rand.nextInt(code.size()-4);
        int b = rand.nextInt(code.size()-4);
        for (int i = 0; i < 4; i++) {
            Collections.swap(code, a + i, a + i + 4);
            Collections.swap(code, b + i, b + i + 4);
        }
    }

    public void LLH9(List<Integer> code) {
        int a = rand.nextInt(code.size()-10);
        int b = rand.nextInt(code.size()-10);
        for (int i = 0; i < 4; i++) {
            Collections.swap(code, a + i, a + i + 10);
            Collections.swap(code, b + i, b + i + 10);
        }
    }

    public void LLH10(List<Integer> code) {
        int a = rand.nextInt(code.size()-40);
        int b = rand.nextInt(code.size()-40);
        for (int i = 0; i < 4; i++) {
            Collections.swap(code, a + i, a + i + 40);
            Collections.swap(code, b + i, b + i + 40);
        }
    }
}
