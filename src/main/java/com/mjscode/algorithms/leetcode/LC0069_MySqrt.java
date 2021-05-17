package com.mjscode.algorithms.leetcode;

/**
 * @author binarySigh
 * @date 2021/5/17 23:21
 */
public class LC0069_MySqrt {
    public static void main(String[] args){
        System.out.println("---------------测试开始---------------");
        for (int i = 0; i < 100_0000; i++) {
            int x = (int) (Math.random() * Integer.MAX_VALUE);
            int res = mySqrt(x);
            int com = (int) Math.sqrt(x);
            if (res != com) {
                System.out.println("x = " + x);
                System.out.println("我的答案: " + res);
                System.out.println("正确答案: " + com);
                break;
            }
        }
        System.out.println("---------------测试结束---------------");
        System.out.println(mySqrt(Integer.MAX_VALUE));
        System.out.println(Math.sqrt(Integer.MAX_VALUE));
    }

    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了100.00% 的Java用户
     * 		内存消耗:35.5 MB,击败了66.43% 的Java用户
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        if(x < 4){
            return x == 0 ? 0 : 1;
        }
        int L = 0, R = x;
        int sqrt = 0;
        while(L <= R){
            int mid = L + ((R- L) >> 1);
            if(x / mid >= mid){
                sqrt = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return sqrt;
    }
}
