package com.mjscode.algorithms.newCoder.huawei;

/**
 * @author binarySigh
 * @date 2021/8/23 12:51
 */
public class HJ33_Integer2IP {
    public static void main(String[] args){
        String[] str = "234.237.37.104".split("\\.");
        long a = 0;
        for(int i = 0; i < str.length; i++){
            a = a << 8 | Integer.parseInt(str[i]);
        }
        System.out.println(a);
    }
}
