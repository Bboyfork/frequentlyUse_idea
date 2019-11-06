package com.demo.test3back;

import java.util.LinkedList;

/**
 * 大概意思：
 *  写一个双链表
 *  即：node<E>为实际的存储 然后
 *  IDLList为其工具类
 * */
public class IDLList<E> {

    private class Node<E>{
        private E data;//值
        Node<E> head;//头
        Node<E> tail;//尾

        Node (E elem, Node<E> prev, Node<E> next){
            this.data = elem;
            this.head = prev;
            this.tail = next;
        }
    }

    private Node<E> head;
    //双链表长度
    private int size;

    //方法
    //它创建一个空的双链表。
    public void IDLList() {
        head = new Node<>(null, null, null);
        head.head = head.tail ;
        head = head.tail;
        size = 0;
    }



/*    //通过索引追加
    public boolean add (int index, E elem){
        if(index == 0){
            Node<E> cur = new Node<>(elem,head,head.tail);
            //head.head     //不行 这是完全两种思想 不能再想了 一个一个来
        }

    }

    //即：头部追加 //将elem添加在头部(即它成为第一个元素)的列表)。
    public boolean add (E elem){}

    //即：尾部追加 //将elem添加为列表的最后一个新元素(即at)尾巴)。
    public boolean append (E elem){}

    //从头部返回位置索引的对象。它使用快速访问的索引。
    // 索引从0开始，因此get(0)返回head元素的列表。
    public E get (int index){}

    //返回头部的对象。
    public E getHead ()

    //函数，它返回位于尾部的对象。
    public E getLast (){}

    //返回列表大小。
    public int size(){}

    //删除并返回头部的元素。
    public E remove(){}

    //删除并返回尾部的元素。
    public E removeLast (){}

    //用于删除和返回索引索引处的元素。使用索引进行快速访问。
    public E removeAt (int index){}

    //删除列表中第一个出现的elem返回true。   如果elem不在列表中，则返回false。
    public boolean remove (E elem){}

    //它表示列表的字符串表示形式。以下操作需要维护索引(即必须分配或修改索引)该指数
    public String toString(){

    }*/

}
