package com.mjscode.algorithms.utils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 数组公共类
 *
 * @author binarySigh
 */
public class ArrayUtils {

    /**
     * 数组打印
     */
    public static void showArray(int[] arr){
        String result = "[";
        for(int i=0; i < arr.length; i++){
            if(i != arr.length-1) {
                result = result + String.valueOf(arr[i]) + ",";
            } else {
                result = result + String.valueOf(arr[i]) + "]";
            }
        }
        System.out.println(result);
    }

    /**
     * 对输入的两个整型的ArrayList进行比较，看看所含元素是否相同<BR/>
     * 相同返回 true，不同返回false
     * @return
     */
    public static boolean compareArrayList(ArrayList list1, ArrayList list2) {
        boolean flag = false;
        if(list1 == null || list2 == null || list1.size() != list2.size()){
            return flag;
        }
        Iterator<Integer> ite = list1.iterator();
        while(ite.hasNext()){
            if(!list2.remove(ite.next())) {
                return flag;
            }
        }
        if(list2 == null || list2.size() == 0){
            flag = true;
        }
        return flag;
    }

    /**
     * 按要求生成随机整型数组<BR/>
     *  如果 negative == true,则元素范围 [-range，range]<BR/>
     *  如果 negative == false,则元素范围 [0，range]<BR/>
     * @param range 数组元素值范围
     * @param length 数组长度
     * @param negative 是否包含负数
     * @return
     */
    public static int[] generateArray(int range, int length, boolean negative){
        int[] arr = new int[length];
        for(int i = 0; i < length; i++){
            if(!negative) {
                arr[i] = (int)(Math.random() * range + 1);
            } else{
                arr[i] = (int)(Math.random() * range + 1) - (int)(Math.random() * range + 1);
            }
        }
        return arr;
    }
}
