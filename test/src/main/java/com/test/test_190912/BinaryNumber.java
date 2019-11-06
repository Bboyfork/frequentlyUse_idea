package com.test.test_190912;

public class BinaryNumber {
    public void BinaryNumber(){}
    //构造1

    public String BinaryNumber(int length){
        String str = Integer.toBinaryString(0);
        for(int i = 1 ; i< length ;i++){
            str = "0"+str;
        }
        System.out.println(str);
        return str;
    }


    //构造2
    public String BinaryNumber(String str){
        String strTemp = Integer.toBinaryString(Integer.parseInt(str));
        return strTemp;
    }
    //getLength 确定二进制数的长度
    public static int getLength(int num){
        int i = 0;
        for(;num;num){

        }
    }
    //
}
