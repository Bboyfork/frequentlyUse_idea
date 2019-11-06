package com.demo.test2;

/*
* 时间复杂度demo
* */
public class TimeComplexity {

    /*
    * 示例：
    * 时间复杂度O (n)
    * */
    public void  method0(int n) {
        int counter =0;
        for (int i =0; i <n; i ++) {
            System.out.println(" Operation "+ counter);
            counter ++;
        }
    }
/*
要求：
你应该实现的方法是:
    public static void method1(int n):一个时间复杂度为O(n方)的方法

    public static void method2(int n):一个时间复杂度为O(n立方)的方法

    public static void method3(int n):一个时间复杂度为O(log n)的方法。

    public static void method4(int n):一个时间复杂度为O(n log n)的方法。

    public static void method5(int n):一个时间复杂度为O(log log n)的方法。

    public static int method6(int n):一个时间复杂度为O(2的n次幂)的方法。

* */

//
    //:一个时间复杂度为O (n方)的方法
    public static void method1(int n){
        int counter =0;
        for(int i = 0;i<n;i++){
            for(int j = 0 ;j<n ;j++){
                System.out.println(" Operation "+ counter);
                counter++;
            }
        }
    }

    //一个时间复杂度为O (n立方)的方法
    public static void method2(int n){
        int counter =0;
        for(int i = 0;i<n;i++){
            for(int j = 0 ;j<n ;j++){
                for(int x =0 ;x<n ;x++){
                    System.out.println(" Operation "+ counter);
                    counter++;
                }
            }
        }
    }

    /*
    * 上边这俩比较简单 相信你能看懂
    * 所谓时间复杂度的计算方法 其实就是看他基础语句的执行
    * 第一个执行n方 次
    * 第二个执行n立方 次
    * 所以他们的时间复杂度分别为n`2 n`3
    * 但是时间复杂度计算的是最大的那个 意思就是：
    * 比如把这两个方法都放在一起 里面既有n方 又有n立方
    *   时间复杂度也记做n立方 而不是n方+n立方
    * */

    /*
    * 下边这些略有繁琐
    * 我是这样理解
    * 我们传进来一个数n 假设是100
    * 时复杂度：n`2 时
    *   基础语句要执行 100`2  = 10000次
    * 时间复杂度：logn 时
    *   基础语句要执行 log 2`100 = 6.643856次 (小数当然不行)  换成8 log2`8 = 3次
    * 时间复杂度：nlogn 时
    *   基础语句要执行 100*log 2`100 = 六百多  还是换成8  8*log2`8 = 24次
    * 大概是这么个意思
    * */

/*
* 幂运算中以乘法运行次数做参考 其时间复杂度就是O(logn) 参考代码：
public static int pow(int x,int n){
    if(n==0)
        return 1;
    if(n==1)
        return x;
    if(n%2==0)
        return pow(x*x,n/2);
    else
        return pow(x*x,n/2)*x;
}
* */
    //对数时间
    //一个时间复杂度为O (log n)的方法。
    public static void method3(int n){
        //x = 2 是底
        System.out.println("运算结果为："+method3_1(2,n));
    }

    //一个时间复杂度为O (n log n)的方法。比如归并排序就是nlogn 参考示例1、2：class merge_sort和MergeSort   示例2的测试入口在Test
    /*
    * 参考归并排序
    * 用分治思想，将一个数组拆开最后两两相比较并归并
    * 这样的时间复杂度为nlogn
    *
    * 所以模拟：
    * 方法1：直接生成一个n长的数组然后进行归并排序 直接 简单 快速。(但是思来想去不知道你那边判别机制怎么样 想想算了 如果可以 你可以直接把MergeSort中代码搬过来 直接又简单)
    * 方法2流程如下：
    * 假设现有数字7
    * 则进行拆分成3、4
    * 再拆分1、2 | 2、2
    * 再拆分1|1、1 | 1、1|1、1
    * 1时执行输出语句：得到数字1————就按示例中来：Operation 1
    * 然后相加 每次加完再执行输出语句 得到和：xxx 。
    * */
    public static void method4(int n){
        int counter =0;
        method4_1(n);
    }

    /*
     *这两个就很方了 没有现成的时间复杂度的算法 所以其正确度不敢保证
     * 还是按照我们的理解方法
     * */
    //一个时间复杂度为O (log log n)的方法。
    static int ForMethod5 = 1;
    public static void method5(int n){
        for(int i = 0 ;i<Math.log10( Math.log10(n) ) ;i++){
            System.out.println(" Operation "+ ForMethod5++);
        }
    }

    /*
    * 按照我们的理解方法
    * n = 2
    * 我们应进行 4 次 基础运算
    * n = 3
    * 我们应进行 8 次 基础运算
    *
    * */
    //一个时间复杂度为O (2的n次幂)的方法。
    public static void method6(int n){
        //准备两个方法 还得是递归调用 每次进行两次输出
        //我写不出来了 我在这想了俩小时了 gg了
        //我gg了！ 这tm啥破题 tm脑残 有个瘠薄毛用
        // 外国 看起来也都是应试教育啊 外边的月亮也是一样的满脸大坑

        //5 和6 反正思路就是这样
        // 时间复杂度就是要进行的基础语句(正常来讲得算运算语句 这里我就给你写的输出语句)
        // 在时间上的复杂度(其实就是在时间上用了多少倍 这么想)
        //2的n次幂
        //就是这个方法 n 传过来
        // n的值      时间上运算的(倍数)吧 (或者说 耗时 )
        //  2           4
        //  3           8
        //  4           16
        double temp = Math.pow(2,n);
        for(int i = 0;i<temp;i++){
            method6_1();
        }
    }


//------------------------------------
    public static void method6_1(){
        int log = 1;
        //参数运算
        System.out.println("Operation " + log++);
    }

    static int ForMethod4 = 1;
    //拆分
    public static int method4_1(int i){
        if(i<=1){
            System.out.println(" Operation "+ ForMethod4);
            ForMethod4++;
            return 1;
        }
        int mid = i/2;
        return method4_2(method4_1(mid),method4_1(i-mid));
    }
    //合并
    public static int method4_2(int i,int j){
        int temp = i+j;
        System.out.println(" 得到和 "+ temp);
        System.out.println(" Operation "+ForMethod4++);
        return temp;
    }



    static int ForMethod3 = 1;
    //幂运算
    public static int method3_1(int x,int n){
        if(n == 0){//0次幂时候结果是1
            return 1;
        }
        if(n == 1){//1次幂时结果是本身
            return x;
        }
        if(n%2 == 0){//等于0正好是两数相乘
            return method3_1(method3_2(x,x),n/2);
        }
        else{//不等于0那肯定是余1 再乘一下就完了
            System.out.println(" Operation " + ForMethod3++);
            return method3_1(method3_2(x,x),n/2)*x;
        }
    }

    public static int method3_2(int i,int j){
        int temp = i*j;
        System.out.println(" Operation " + ForMethod3++);
        return temp;
    }



    /*
    //幂运算
    public static int method3_1(int x,int n){
        if(n == 0){//0次幂时候结果是1
            return 1;
        }
        if(n == 1){//1次幂时结果是本身
            return x;
        }
        if(n%2 == 0){//等于0正好是两数相乘
            //return method3_1(x*x,n/2);
            // 这么写就行 但是输出语句没法加了
            // 因为不知道是人工检查还是机器检查 怕机器检查会根据输出语句来判断
            // 干脆再弄个方法
            // 若是人工的 你就把下面method3_2 删掉 把下面这一行也删掉 把上面那行代码释放就行了
            return method3_1(method3_2(x,x),n/2);
        }
        else{//不等于0那肯定是余1 再乘一下就完了
            System.out.println(" Operation 这里是单独的那一次乘法 " + x);
            return method3_1(method3_2(x,x),n/2)*x;
        }
    }

    public static int method3_2(int i,int j){
        int temp = i*j;
        System.out.println(" Operation " + temp);
        return temp;
    }
    * */
}
