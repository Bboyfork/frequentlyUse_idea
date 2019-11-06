package com.demo.test4;

import com.demo.test4.test4_1.PairInt;

import java.util.ArrayList;
import java.util.Stack;

public class test113 {
    public static void main (String[] args){
        ArrayList<Stack<PairInt>> result = new ArrayList();
        Stack<PairInt> trace = new Stack<>();
        trace.push(new PairInt(1,1));
        result.add(trace);
        System.out.println(result);
        trace.push(new PairInt(1,1));
        System.out.println(result);
    }
}
