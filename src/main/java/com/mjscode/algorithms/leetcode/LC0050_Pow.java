package com.mjscode.algorithms.leetcode;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
 */
public class LC0050_Pow {

    public static void main(String[] args){
        /*for(int i = 0; i < 100; i++) {
            double x = getX();
            int n = getN();
            if(myPow(x, n) != baseSolution(x, n)) {
                System.out.println("x = " + x + ";n = " + n);
                System.out.println("myPow : " + myPow(x, n));
                System.out.println("baseSolution : " + baseSolution(x, n));
                break;
            }
        }*/
        double x = 49.2002;
        int n = Integer.MIN_VALUE;
        System.out.println("myPow : " + myPow(x, n));
        System.out.println("baseSolution : " + baseSolution(x, n));
        System.out.println(myPow(x, n) == baseSolution(x, n));
        System.out.println(myPow(x, n) - baseSolution(x, n));
       /* int n = 1 << 31;
        System.out.println(n);
        System.out.println(-n);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Math.pow(99.0d,Integer.MAX_VALUE));*/
    }


    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了73.63% 的Java用户
     * 		内存消耗:37.5 MB,击败了58.14% 的Java用户
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n){
        double ans = 1;
        double pow = x;
        int i = n == Integer.MIN_VALUE ? -(n + 1) : Math.abs(n);
        while(i > 0){
            if((i & 1) == 1){
                ans *= pow;
            }
            pow *= pow;
            i >>= 1;
        }
        ans = n < 0 ? (1 / ans) : ans;
        ans = n == Integer.MIN_VALUE ? (ans / x) : ans;
        return ans;
    }

    /**
     * leetcode提交已通过
     * @param x
     * @param n
     * @return
     */
    public static double myPow1(double x, int n) {
        //处理特殊值 0 次幂和 1次幂
        if(n == 0) {
            return 1.0d;
        }
        if(n == 1) {
            return x;
        }
        //记录原始n的值，目的是防止 n== -2^31 次方时取负超出整型最大值导致结果依然为 -2^31
        int originN = n;
        if((originN < 0)) {
            x = 1 / x;
            if(originN == (1 << 31)) {
                n = -(n + 1);
            } else {
                n = -n;
            }
        }
        int step = 1;
        double result = 1.0d;
        while(step <= n ) {
            if(step == 1) {
                result = result * x;
            } else {
                result = result * result;
            }
            if(step <= (n >> 1)) {
                step = step << 1;
            } else {
                break;
            }
        }
        //这里每一轮处理分为两半；左边二分遍历法，右边扔到递归里执行
        // 之所以不把两边同样递归处理，是因为如果n很大的话 完全递归会导致栈内存溢出
        result = result * myPow(x, n - step);
        //对于临界值 n==-2^31次方的情况，需要多一次*x操作
        if(originN == (1 << 31)){
            result = result * x;
        }
        return result;
    }

    //以下是本题对数器部分
    /**
     * 暴力实现法
     * @param x
     * @param n
     * @return
     */
    public static double baseSolution(double x, int n){
        if(n == 0) {
            return 1.0d;
        }
        //记录原始n的值，目的是防止 n== -2^31 次方时取负超出整型最大值导致结果依然为 -2^31
        int originN = n;
        if((originN < 0)) {
            x = 1 / x;
            if(originN == (1 << 31)) {
                n = -(n + 1);
            } else {
                n = -n;
            }
        }
        double result = 1;
        for(int i = 0; i < n; i++) {
            result *= x;
        }
        //对于临界值 n==-2^31次方的情况，需要多一次*x操作
        if(originN == (1 << 31)) {
            result = result * x;
        }
        return result;
    }

    /**
     * 获取随机数X。范围(-100,100)
     * @return
     */
    public static double getX(){
        return Math.random() * 100 - Math.random() * 100;
    }

    /**
     * 生成随机数N
     * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1]
     * @return
     */
    public static int getN(){
        int maxN = Integer.MAX_VALUE;
        int result = (int)(Math.random() * maxN + 1) - (int)(Math.random() * maxN + 1);
        return result == maxN ? result - 1 : result;
    }
}
