package com.mjscode.algorithms.techniques;

/**
 * 判断一个数是否是 2的幂次方
 * @author binarySigh
 * @date 2021/5/30 8:03
 */
public class isPowerOfTwo {

    public static void main(String[] args){
        int n = 16;
        System.out.println(isPowerOfTwo(n));
        System.out.println(Integer.MIN_VALUE);
        System.out.println((1 << 31));
        System.out.println((1 << 30) | (1 << 31));
        System.out.println((1 << 31) / ((1 << 30) | (1 << 31)));
    }

    /**
     * (n & (~n + 1)) : 提取 n最右侧的 1，
     * 如果与 n异或值为0，则说明 n只有一个位上有1，即 N 是 2的幂次方
     *
     * 解答成功:
     * 		执行耗时:1 ms,击败了100.00% 的Java用户
     * 		内存消耗:35.3 MB,击败了90.01% 的Java用户
     * @param n
     * @return
     */
    public static boolean isPowerOfTwo(int n){
        if(n <= 0){
            return false;
        }
        return (n ^ (n & (~n + 1))) == 0;
    }
}
