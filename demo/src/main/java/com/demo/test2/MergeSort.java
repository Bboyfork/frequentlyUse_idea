package com.demo.test2;
import java.util.Arrays;
/*
* 归并示例2
* */
public class MergeSort {

    /*
    * 拆分、递归调用
    * */
    public int[] mergeSort(int[] array,int length){
        if(length <=1 ){
            return array;
        }
        int mid = (length - 1)/2;
        int[] left = Arrays.copyOfRange(array,0,mid+1);
        int[] right = Arrays.copyOfRange(array,mid+1,length);
        return merge(mergeSort(left,left.length),mergeSort(right,right.length));
    }

    private int[] merge(int[] left, int[] right) {
        if (left == null || left.length == 0) {
            return right;
        }
        if (right == null || right.length == 0) {
            return left;
        }

        int[] temp = new int[left.length + right.length];
        int leftPos = 0;
        int rightPos = 0;
        int tempPos = 0;
        while (leftPos < left.length && rightPos < right.length) {
            if (left[leftPos] < right[rightPos]) {
                temp[tempPos++] = left[leftPos++];
            } else {
                temp[tempPos++] = right[rightPos++];
            }
        }
        if (leftPos == left.length) {
            while (rightPos < right.length) {
                temp[tempPos++] = right[rightPos++];
            }
        } else {
            while (leftPos < left.length) {
                temp[tempPos++] = left[leftPos++];
            }
        }
        return temp;
    }

}
