package com.demo.test2;

import java.util.Arrays;

public class Test {
    public static void main (String[] args){
/*
        //示例2测试
        MergeSort ms = new MergeSort();
        int[] array1 = {9, 6, 5, 7};
        System.out.println(Arrays.toString(ms.mergeSort(array1, array1.length)));
*/

        TimeComplexity tc = new TimeComplexity();
        //tc.method1(3);
        //logn 测试
        //tc.method3(64);//成功
        //nlogn 测试
        //tc.method4(4);//成功
        //tc.method6(4);
        tc.method5(100);

    }
}
