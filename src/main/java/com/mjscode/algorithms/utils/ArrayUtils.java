package com.mjscode.algorithms.utils;

import java.util.ArrayList;
import java.util.Arrays;
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
     * 数组打印
     * @param list
     */
    public static void showArray(ArrayList list){
        String result = "[";
        for(int i = 0; i < list.size(); i++){
            if(i != list.size() - 1){
                result = result + String.valueOf(list.get(i)) + ",";
            } else {
                result = result + String.valueOf(list.get(i)) + "]";
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
        ArrayList<Integer> copyOfList2 = copyArray(list2);
        while(ite.hasNext()){
            if(!copyOfList2.remove(ite.next())) {
                return flag;
            }
        }
        if(copyOfList2 == null || copyOfList2.size() == 0){
            flag = true;
        }
        return flag;
    }

    /**
     * 对输入数组进行深度拷贝，并返回拷贝后的新数组
     * @param list
     * @return
     */
    private static ArrayList<Integer> copyArray(ArrayList<Integer> list) {
        if(list == null || list.size() == 0){
            return list == null ? null : new ArrayList<Integer>();
        }
        ArrayList<Integer> copy = new ArrayList<>(list.size());
        int tmp = 0;
        for(int i = 0; i < list.size(); i++){
            tmp = list.get(i);
            copy.set(i, tmp);
        }
        return copy;
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
                arr[i] = (int)(Math.random() * (range + 1));
            } else{
                arr[i] = (int)(Math.random() * (range + 1)) - (int)(Math.random() * (range + 1));
            }
        }
        return arr;
    }
}
