package com.demo.test3;

import java.util.ArrayList;

/**
 * 大概意思：
 *  写一个双链表
 *  即：IDLList为其工具类 其内部类 node<E>为实际的存储
 * */
public class IDLList<E>{
    private class Node<E>{
        private E data;//值
        Node<E> prev;//上
        Node<E> next;//下

        //这个方法应该是没有的 毕竟 添加一个数据
        // 怎么能没有和前后的关联呢 没有的话 那就是垃圾啊 单个的山楂 不在糖葫芦上 怎么卖？对吧 所以并不知道写点什么
        Node (E elem){
            this.data = elem;
            this.prev = null;
            this.next = null;
        }
        Node (E elem, Node<E> prev, Node<E> next){
            this.data = elem;
            this.prev = prev;
            this.next = next;
        }
    }

    //这就是 这个链表的头元素的地址值嘛
    private Node<E> head;
    //这个是尾元素的地址值
    private Node<E> tail;
    //双链表长度
    private int size;
    //所谓“加强”索引
    private ArrayList<Node<E>> indices;
    public void lookList(){
        for (int i = 0 ; i<indices.size()-1;i++){
            System.out.println(indices.get(i).data);
        }
    }
/*
现在就是一个问题
当双链表空：  头尾数据都为空     没问题
双链表有数据：头存什么呢 尾存什么呢？

现假设第一个数据头指向null  最后数据尾指向null
 */

    //方法
    //它创建一个空的双链表。
    /*
    public IDLList() {//创建一个头元素
        head = new Node<>(null, null, null);
        head.head = head.tail;//单独一个头元素 所以首尾互指 自己和自己报个团
        indices = new ArrayList<Node<E>>();
        indices.add(head);
        size = 1;
    }*/
    public  IDLList(){
        head = new Node<>(null, null, null);//声明一个空的节点 当成所谓头结点
        tail = head;
        head.prev = head.next;//头结点尾指向头 但都是null 这步算没啥意义
        size = 0;//size等于多少自己定义的 主要看你这个头算不算值
        indices = new ArrayList<Node<E>>();//声明一个所谓索引
        indices.add(head);//这个头结点 要不要放在索引里？
    }

    //头部追加元素
    public boolean add(E elem){
        if(size == 0){
            head= new Node<>(elem);
            head.next=null;
            head.prev=null;
            indices.set(0,head);
        }else{
            Node<E> newNode = new Node<E>(elem);
            newNode.next=head;
            head.prev=newNode;
            newNode.prev = null;
            head=newNode;
            indices.add(0,newNode);
        }
        size++;
        return true;
    }

    //尾部追加
    public boolean append(E elem){
        //创建新元素
        Node tempaddr = new Node<>(elem,tail,null);
        tail.next = tempaddr;
        tail = tempaddr;//刷新尾节点地址
        //维护索引
        indices.add(tempaddr);
        size++;
        return true;
    }

    //通过索引追加
    public boolean add(int index,E elem){


        //判断索引是否越界
        if(this.TestIndexOutBounds(index)){
            throw new IndexOutOfBoundsException();
        }
        if(index == 0)
            return add(elem);
        else if(index == size)
            return append(elem);
        else{
            //声明元素
            Node tempaddr = new Node<>(elem,indices.get(index-1),indices.get(index));
            //和前连接
            indices.get(index-1).next = tempaddr;
            //和后连接
            indices.get(index).prev = tempaddr;
            //更改索引
            indices.add(index,tempaddr);
            size++;
            return true;
        }
    }


    //判断要插入的索引是合法
    public boolean TestIndexOutBounds(int index){
        if(index < 0 || index >= size)
            return true;
        return false;
    }

    //返回位置索引的对象。
    public E get (int index){
        if (TestIndexOutBounds(index))
            return null;//这里就是越界了 返回个空吧
        return indices.get(index).data;
    }

    //返回头部的对象
    public E getHead (){
        return head.data;
    }

    //函数，它返回位于尾部的对象。
    public E getLast (){
        return tail.data;
    }

    //返回列表大小。
    public int size(){
        return size;
    }

    //删除并返回头部的元素。
    public E remove(){
        System.out.println(indices.size()+"<========");
        E xx = indices.get(0).data;
        if(size < 1){
            System.out.println("size长度小于1，无法删除");
            return null;
        }else if(size == 1){
            /*head = null;
                tail = null;//清空指向就完了就算删除了 但是不太好 算了改一下 */
            head.prev = null;
            head.data = null;//这么清 更符合逻辑
            size--;
            return xx;
        }else{//正常删除
            head = indices.get(1);
            indices.get(1).prev = null;
            indices.remove(0);//维护索引
            size--;
            return xx;
        }
    }

    //删除并返回尾部的元素。
    public E removeLast (){
        E yy = indices.get(size-1).data;
        if(size < 1){
            System.out.println("size长度小于1，无法删除");
            return null;
        }else if(size == 1){
            tail.prev = null;
            tail.data = null;
            size--;
            return yy;
        }else{//正常删掉
            tail = indices.get(size-2);
            tail.next = null;
            size--;
            indices.remove(size);
            return yy;
        }
    }

    //用于删除和返回索引索引处的元素。
    public E removeAt (int index){
        if(TestIndexOutBounds(index)){
            System.out.println("索引越界");
            return null;
        }
        System.out.println(indices.size()+"<========");
        E zz = indices.get(index).data;
        //size = 4  4个元素 0 1 2 3
        if(index == size-1){//删除尾
            return removeLast();
        }else if(index == 0){//删除首
            return remove();
        }else {
            indices.get(index+1).prev = indices.get(index-1);
            indices.get(index-1).next = indices.get(index+1);
            indices.remove(index);
            size--;
            return zz;
        }
    }

    //删除列表中第一个出现的elem返回true。   如果elem不在列表中，则返回false。
    public boolean remove (E elem){
        System.out.println(indices.size()+"<========");
        boolean flag = true;
        //遍历这个双链表嘛
        Node<E> temp1 = head.next;
        do{
            if(temp1.data.equals(elem)){
                flag = false;
                if(removeAt(indices.indexOf(temp1)).equals(elem))
                    return true;
            }
            temp1 = temp1.next;
            if(temp1 == null)flag = false;
        }while (flag);
        return false;
    }

    //它表示列表的字符串表示形式。
    public String toString(){
        String tempStr = "";
        boolean flag = true;
        //遍历这个双链表嘛
        Node<E> temp1 = head;
        do{
            tempStr += "->";
            tempStr += temp1.data;

            temp1 = temp1.next;
            if(temp1 == null)flag = false;
        }while (flag);
        return tempStr;
    }
    //即：头部追加 //将elem添加在头部(即它成为第一个元素)的列表)。
    //public boolean add (E elem){}

    //即：尾部追加 //将elem添加为列表的最后一个新元素(即at)尾巴)。
    //public boolean append (E elem){}

    //从头部返回位置索引的对象。它使用快速访问的索引。
    //public E get (int index){}

    //返回头部的对象。
    //public E getHead ()

    //函数，它返回位于尾部的对象。
    //public E getLast (){}

//    //返回列表大小。
//    public int size(){}

//    //删除并返回头部的元素。
//    public E remove(){}
//
//    //删除并返回尾部的元素。
//    public E removeLast (){}

    //用于删除和返回索引索引处的元素。
    //public E removeAt (int index){}

    //删除列表中第一个出现的elem返回true。   如果elem不在列表中，则返回false。
    //public boolean remove (E elem){}


}
