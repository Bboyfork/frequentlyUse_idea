package com.demo.test5;

import java.util.concurrent.ConcurrentHashMap;

public class jl5 {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("Hello ");
        System.out.println("Before change, sb = " + sb);
        changeData(sb);
        System.out.println("After change Data(n), sb = " + sb);
    }

    public static void changeData(StringBuffer strBuf) {
        strBuf = new StringBuffer("aaaaaa");

//        StringBuffer sb2 = new StringBuffer("Hi ");
//        strBuf = sb2;
//        sb2.append("World!");

        ConcurrentHashMap map = new ConcurrentHashMap();
    }

}
