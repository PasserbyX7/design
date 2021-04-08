package cn.demo.application;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

public class application {
    public static void main(String[] args) {
        var str=IdWorker.getIdStr();
        System.out.println(str.length());
    }
}