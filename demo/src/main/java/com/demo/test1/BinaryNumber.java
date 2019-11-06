package com.demo.test1;


import java.util.ArrayList;

/*
* 文档前面说了一个叫"little-endian"的格式
* 我看了下 大概意思就是一种表达二进制数的格式
* 和正常二进制数不一样
*   --在java中正常表示数字13 用二进制方式是 0b1101
* 他这种格式 其实就是去掉0b 然后反过来
* 所以他这里是1011 对滴 但是"1011" 这不是个数啊
* 所以 本想用String类型存储
* 但是他第三章给了个全局变量 一个int数组
* 我估摸着 可能是要用这个数组存储 应该是这意思
*【我猜人家上课指定讲了  你找找看 到底啥意思    这里我就用这个int数组了】
* */
public class BinaryNumber {
    private int data[];
    private boolean overflow;

    /*
    * 此方法单纯就是看看他们的值什么样
    * 都搞完了 才发现好像和那个toString差不多
    * 应该是有那个toString就行 而且这个是测试用的方法 你自己再测测 最后删了就行
    * */
    public void sysoresult(){
        for (int i:data
             ) {
            System.out.println(i);
        }
        System.out.println(overflow);
    }

    /*
    * 构造函数BinaryNumber(int length)，用于创建长度为length的二进制数只有0组成。
    *
    * 我理解奥：就是输入5 那就创建"ob00000" 或者是创建"00000"
    */
    public BinaryNumber(int length){
        data = new int[length];
        for (int i = 0;i<length;i++){
            data[i] = 0;
        }
    }

    /*
        •构造函数BinaryNumber(String str)，用于创建给定字符串的二进制数字。为
        例如，给定字符串“1011”，应该创建相应的二进制数。
    * */
    public BinaryNumber(String str){
        data = new int[str.length()];
        char chars[] = str.toCharArray();
        //for (char cha:chars) {
        for(int i = 0 ; i<chars.length ; i++){
            int integer = Integer.parseInt(chars[i]+"");
            if(integer==1 || integer == 0){
                data[i] = integer;
            }else{
                System.out.println(integer+"BinaryNumber(String str) 构造方法传入参数有误");
            }
        }
    }

    /*
    *  一个用于确定二进制数长度的int getLength()操作。
    * */
    public int getLength(){
        return data.length;
    }

    /*
    *   一个操作int getDigit(int index)，用于获取给定的二进制数的一个数字
        索引。起始索引是0。如果索引超出界限，则消息应该超出界限
        打印在屏幕上，表明这一事实。
    * */
    public int getDigit(int index){
        int i = 0;
        if(!(index<data.length)){
            System.out.println("索引超出界限");
        }else {
            i = data[index];
        }
        return i;
    }

    /*
     * 一种将二进制数转换为十进制记数法的int toDecimal()操作
     * */
    public int toDecimal(){
        int sum = 0;
        // 1    1    0    1  length：4
        // 0    1    2    3
        // 8    4    2    1
        // 2`3  2`2  2`1  2`0
        for(int i = data.length ; i > 0 ; i--){
            int temp = 1;
            for(int j = 0 ; j< i-1;j++){    //其实就是为了弄一个平方的效果
                temp = 2*temp;
            }
            // 1*8  1*4 1*1相加这样
            sum += data[i-1]* temp ;

        }
        return sum;
    }

    /*
    *   一个操作void shiftR(int amount)，用于移动二进制数any中的所有数字
        右边的位置数量，由参数amountToShift表示。新
        数字应该是0。
        例如，将“1011”向右移动3个位置的结果
        应该产生“0001011”。

        这个是真没看懂！ 意思就是往左边加多少个0？ 应该是吧
    * */
    public void shiftR(int amount){ //  length =4   amount = 0
        boolean flag = true;
        int[] datatemp = new int[amount+data.length];
        for(int i = 0 ;i<datatemp.length;i++){
            if(i==amount){flag = false;}
            System.out.println(flag);
            if(flag){//添加0
                datatemp[i] = 0;
            }else{
                datatemp[i] = data[i-amount];
            }
        }
        data = datatemp;
    }

    /*
    *   void add(BinaryNumber aBinaryNumber)
        用于两个二进制数求和，
        其中一个是二进制数接收消息的数字和

        另一个作为参数给出。
        如果二进制数的长度不一致，则应在屏幕上打印一条消息
        说明这个事实。
        否则，它将使用结果修改接收的二进制数的加法。
    * */
    public void add(BinaryNumber aBinaryNumber){
        int length = aBinaryNumber.getLength();
        if(length != data.length){  //判断
            System.out.println("长度不等，无法相加");
        }else{//相加
            int[] newData = new int[data.length];
            for(int i = 0 ; i < data.length ; i++){
                int temp = 0;
                if(overflow){       //如果有进位 则应算1
                    temp = 1;
                    overflow = false;
                }

                temp += data[i] + aBinaryNumber.data[i];
                if(temp == 2){      //进位
                    overflow = true;
                    temp = 0;
                }else if(temp == 3){
                    temp = 1;
                    overflow = true;
                }else{
                    clearOverflow();
                }
                newData[i] = temp;
            }
            data = newData;
        }
    }

    /*
    * •toString()，
用于将二进制数字转换为字符串。如果
数字是溢出的结果，应该返回字符串“overflow”。
    * */
    public String toString(){
        String strTemp= "";
        /*
        * 用foreach遍历的 foreach我记得是按index顺序来着
        * 测两遍没问题 但是不确定
        * foreach显得高端 而且简单 拿分
        * 求稳就用注释里那个
        * */
        for (int i: data
             ) {
            strTemp += i;
        }

        /*
        for(int i = 0 ; i< data.length ; i++){
            strTemp += data[i];
        }
        */
        return strTemp;
    }

    /*
    *   清除溢出标志的操作
        clearOverflow()。
    * */
    public void clearOverflow(){
        this.overflow = false;
    }


}
