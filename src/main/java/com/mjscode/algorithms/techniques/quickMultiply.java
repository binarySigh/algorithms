package com.mjscode.algorithms.techniques;

/**
 * 快速相乘 / 倍增乘法
 * @author binarySigh
 * @date 2021/7/12 21:29
 */
public class quickMultiply {
    public static void main(String[] args){
        System.out.println(quickMul(12,7));
    }

    /**
     * 倍增乘法计算 a * b
     * @param a
     * @param b
     * @return
     */
    public static long quickMul(long a, long b){
        long ans = 0;
        while(b > 0){
            if((b & 1) == 1){
                ans += a;
            }
            b >>= 1;
            a += a;
        }
        return ans;
    }
}
