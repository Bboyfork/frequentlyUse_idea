package com.demo.test3;

public class listTest {
    public static void main (String[] args){
        IDLList ll = new IDLList();
        ll.add("555");
        ll.add("222");
        //ll.lookList();
        ll.append("654");
        //ll.lookList();

        ll.add(2,"-55-");

        ll.lookList();

        System.out.println("测试开始：");
        System.out.println("链表为："+ll.toString());
        ll.lookList();
        System.out.println("链表为："+ll.toString());
        System.out.println("---------------");
        System.out.println(ll.removeAt(2));
        System.out.println("链表为："+ll.toString());
        System.out.println(ll.get(1));
        System.out.println(ll.getHead());
        System.out.println(ll.getLast());
        System.out.println(ll.size()+"<===size+string===>"+ll.toString());
        System.out.println(ll.remove());
        System.out.println(ll.size()+"<===size+string===>"+ll.toString());
        System.out.println(ll.removeLast());
        System.out.println(ll.size()+"<===size+string===>"+ll.toString());


    }

}
