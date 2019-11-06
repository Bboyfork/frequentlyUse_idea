package com.demo.test1;

public class test {
    public static void main (String[] args){
        BinaryNumber bNumber = new BinaryNumber(5);
        bNumber.sysoresult();
        System.out.println("--------------");
        BinaryNumber bNumber2 = new BinaryNumber("1101");
        bNumber2.sysoresult();
        int length = bNumber2.getLength();
        System.out.println(length+"<----lengthhhh");
        System.out.println("--------------");
        System.out.println(bNumber2.getDigit(1));//应该1
        System.out.println(bNumber2.getDigit(2));//应该0
        System.out.println(bNumber2.getDigit(4));//应该超出界限
        System.out.println("上面都没问题了");
        bNumber2.sysoresult();
        System.out.println(bNumber2.toDecimal());//1101 应该为11？  --注意：正经的0b1101应该是13
        System.out.println("------看准了底线--------");

        bNumber2.sysoresult();
        System.out.println("左添加");
        bNumber2.shiftR(3);
        bNumber2.sysoresult();

        System.out.println("添加方法---在下面-----");
        BinaryNumber bNumber3 = new BinaryNumber("10010");
        BinaryNumber bNumber4 = new BinaryNumber("1010");
        BinaryNumber bNumber5 = new BinaryNumber("1011");
        BinaryNumber bNumber6 = new BinaryNumber("10110");

        bNumber5.add(bNumber3);//应该因为位数不等
        bNumber5.sysoresult();
        System.out.println("《《《");
        bNumber4.sysoresult();
        System.out.println("==");
        bNumber5.sysoresult();
        System.out.println("bbb");
        bNumber4.add(bNumber5);//应该位数相等 但应溢出//0100
        System.out.println("ccc");
        bNumber4.sysoresult();
        System.out.println("==");
        bNumber5.sysoresult();
        System.out.println("《《《");
        bNumber3.add(bNumber6);//应该正常//01101
        bNumber3.sysoresult();

    }
}
