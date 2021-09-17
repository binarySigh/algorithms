package com.mjscode.algorithms.classcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @author binarySigh
 * @date 2021/9/16 22:28
 */
public class Mod3max {

    public static void main(String[] args) {
        int N = 9;
        int testTimes = 10000;
        System.out.println("-----------Begin----------");
        /*for (int i = 0; i < testTimes; i++) {
            int len = (int) (Math.random() * N);
            int[] arr1 = randomArray(len);
            int[] arr2 = copyArray(arr1);
            String ans1 = max1(arr1);
            String ans2 = mod3max(arr2);
            if (!ans1.equals(ans2)) {
                System.out.println("-----Oops-----");
                ArrayUtils.showArray(arr1);
                ArrayUtils.showArray(arr2);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }*/
        System.out.println("-----------End----------");

        int[] nums = {7,5,2,2};
        String s = mod3max(nums);
        System.out.println("s : " + s);
    }

    public static String mod3max(int[] arr){
        PriorityQueue<Integer> mod0 = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> mod1 = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> mod2 = new PriorityQueue<>((a, b) -> b - a);
        for(int i : arr){
            if(i % 3 == 0){
                mod0.add(i);
            } else if(i % 3 == 1){
                mod1.add(i);
            } else {
                mod2.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        int cnts1 = 0, cnts2 = 0;
        while(!mod0.isEmpty() && !mod1.isEmpty() && !mod2.isEmpty()){
            if(mod0.peek() > mod1.peek() && mod0.peek() > mod2.peek()){
                sb.append(mod0.poll());
            } else if(mod1.peek() > mod0.peek() && mod1.peek() > mod2.peek()){
                if(cnts2 > 0 || cnts1 + 1 <= mod2.size()){
                    sb.append(mod1.poll());
                    if(cnts2 > 0){
                        cnts2--;
                    } else {
                        cnts1++;
                    }
                } else {
                    mod1.clear();
                    mod2.clear();
                }
            } else if(mod2.peek() > mod0.peek() && mod2.peek() > mod1.peek()){
                if(cnts1 > 0 || cnts2 + 1 <= mod1.size()){
                    sb.append(mod2.poll());
                    if(cnts1 > 0){
                        cnts1--;
                    } else {
                        cnts2++;
                    }
                } else {
                    mod1.clear();
                    mod2.clear();
                }
            }
        }
        //出了上面循环，一种mod0为空，一种 mod1,2为空
        while(!mod0.isEmpty()){
            sb.append(mod0.poll());
        }
        while(!mod1.isEmpty() && !mod2.isEmpty()){
            if(mod2.peek() > mod1.peek()){
                if(cnts1 > 0 || cnts2 + 1 <= mod1.size()){
                    sb.append(mod2.poll());
                    if(cnts1 > 0){
                        cnts1--;
                    } else {
                        cnts2++;
                    }
                } else {
                    mod1.clear();
                    mod2.clear();
                }
            } else if(mod1.peek() > mod2.peek()){
                if(cnts2 > 0 || cnts1 + 1 <= mod2.size()){
                    sb.append(mod1.poll());
                    if(cnts2 > 0){
                        cnts2--;
                    } else {
                        cnts1++;
                    }
                } else {
                    mod1.clear();
                    mod2.clear();
                }
            }
        }
        return sb.toString();
    }




    public static String max1(int[] arr) {
        Arrays.sort(arr);
        for (int l = 0, r = arr.length - 1; l < r; l++, r--) {
            int tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
        StringBuilder builder = new StringBuilder();
        TreeSet<String> set = new TreeSet<>((a, b) -> Integer.valueOf(b).compareTo(Integer.valueOf(a)));
        process1(arr, 0, builder, set);
        return set.isEmpty() ? "" : set.first();
    }

    public static void process1(int[] arr, int index, StringBuilder builder, TreeSet<String> set) {
        if (index == arr.length) {
            if (builder.length() != 0 && Integer.valueOf(builder.toString()) % 3 == 0) {
                set.add(builder.toString());
            }
        } else {
            process1(arr, index + 1, builder, set);
            builder.append(arr[index]);
            process1(arr, index + 1, builder, set);
            builder.deleteCharAt(builder.length() - 1);
        }
    }


    public static int[] randomArray(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }
}
