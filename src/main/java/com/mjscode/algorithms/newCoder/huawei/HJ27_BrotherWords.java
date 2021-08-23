package com.mjscode.algorithms.newCoder.huawei;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author binarySigh
 * @date 2021/8/23 10:48
 */
public class HJ27_BrotherWords {

    public static void main(String[] args){
        TreeSet<String> set = new TreeSet<>();
        set.add("abs");
        set.add("abs");
        set.add("abc");
        System.out.println(set);

        getKthBroWord();
    }

    public static void getKthBroWord(){
        TreeMap<String, Integer> map = new TreeMap<>();
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()){
            String[] inputs = in.nextLine().split(" ");
            int n = Integer.parseInt(inputs[0]);
            for(int i = 1; i <= n; i++){
                map.put(inputs[i], map.getOrDefault(inputs[i], 0) + 1);
            }
            String target = inputs[inputs.length - 2];
            int k = Integer.parseInt(inputs[inputs.length - 1]);
            String ret = "";
            n = 0;
            for(Map.Entry<String, Integer> entry : map.entrySet()){
                if(check(entry.getKey(), target)){
                    n += entry.getValue();
                }
                if(n >= k && ret.length() == 0){
                    ret = entry.getKey();
                }
            }
            System.out.println(n);
            System.out.println(ret);
        }
    }

    public static boolean check(String s1, String s2){
        if(s1.length() != s2.length() || s1.equals(s2)){
            return false;
        }
        int[] cnt = new int[256];
        for(int i = 0; i < s1.length(); i++){
            cnt[s1.charAt(i)]++;
        }
        for(int i = 0; i < s2.length(); i++){
            if(--cnt[s2.charAt(i)] < 0){
                return false;
            }
        }
        return true;
    }
}
