package com.mjscode.algorithms.techniques;

/**
 * 给定一个整数，写一个函数来判断它是否是 4 的幂次方
 * @author binarySigh
 * @date 2021/5/31 0:16
 */
public class isPowerOfFour {
    public static void main(String[] args){
        /*int n = 252;
        System.out.println(compare(n));*/
        for(int i = 0; i < Integer.MAX_VALUE; i++){
            if(compare(i) != isPowerOfFour(i)){
                System.out.println(" i = " + i);
                System.out.println("compare : " + compare(i));
                System.out.println("result : " + isPowerOfFour(i));
                break;
            }
        }
        System.out.println(" END !");

        /*int mask = 0;
        for(int i = 0; i < 31; i += 2){
            mask |= (1 << i);
        }
        System.out.println(mask);*/
    }

    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了100.00% 的Java用户
     * 		内存消耗:35.1 MB,击败了97.82% 的Java用户
     * @param n
     * @return
     */
    public static boolean isPowerOfFour(int n){
        if(n <= 0){
            return false;
        }
        //是4的幂次方，肯定也是2的幂次方
        if((n ^ (n & (~n + 1))) != 0){
            return false;
        }
        //mask是二进制位偶数位上为1的整数
        int mask = 1431655765;
        return (n & mask) != 0;
    }

    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了100.00% 的Java用户
     * 		内存消耗:35.6 MB,击败了35.77% 的Java用户
     * @param n
     * @return
     */
    public static boolean compare(int n){
        if(n <= 0){
            return false;
        }
        int i = 1;
        while(i > 0){
            if(i == n){
                return true;
            } else {
                i <<= 2;
            }
        }
        return false;
    }
}
