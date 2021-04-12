package com.mjscode.algorithms.leetcode;

/**
 * //给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * //
 * // 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 * //
 * // 示例 1：
 * //
 * //输入：n = 10
 * //输出：12
 * //解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 * //
 * // 示例 2：
 * //
 * //输入：n = 1
 * //输出：1
 * //解释：1 通常被视为丑数。
 * //
 * // 提示：
 * //
 * // 1 <= n <= 1690
 * //
 * // Related Topics 堆 数学 动态规划
 * @author binarySigh
 * @date 2021/4/11 18:08
 */
public class LC0264_NthUglyNumber {

    public static void main(String[] args){
        System.out.println("-------------测试开始-----------");
        for(int i = 1; i < 1690; i++){
            int result = myNthUglyNumber(i);
            int compare = nthUglyNumber(i);
            if(compare != result){
                System.out.println("---第[" + i + "]个丑数查找失败！---");
                System.out.println("我的结果：" + result);
                System.out.println("正确结果：" + compare);
                break;
            }
        }
        System.out.println("-------------测试结束-----------");


        /*int n = 1547;
        System.out.println(nthUglyNumber(n));
        System.out.println(compare(n));

        System.out.println("---------------");*/

        int n2 = 1690;
        //int n2 = 15;
        int result = myNthUglyNumber(n2);
        System.out.println("我的结果：" + result);
        //System.out.println(isUgly(result));
        int stander = nthUglyNumber(n2);
        System.out.println("标准结果：" + stander);
        /*int compare = compare(n2);
        System.out.println("正确结果：" + compare);*/
        //System.out.println(isUgly(compare));
        /*System.out.println("----------------------------");
        //int pre = 1074954240;
        int pre  = 5556665;
        System.out.println("原来的数：" + pre);
        int c = pre * 5;
        System.out.println("乘五之后：" + c);
        System.out.println(c % 5 == 0);*/
    }

    /**
     * 力扣官方思路解
     * 解答成功:
     * 		执行耗时:2 ms,击败了94.05% 的Java用户
     * 		内存消耗:37.7 MB,击败了33.99% 的Java用户
     * @param n
     * @return
     */
    public static int nthUglyNumber(int n) {
        int[] res = new int[n];
        res[0] = 1;
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;
        for(int i = 1; i < n; i++){
            int min = Math.min(Math.min(res[index2] * 2, res[index3] * 3), res[index5] * 5);
            if(min == res[index2] * 2){
                index2++;
            }
            if(min == res[index3] * 3){
                index3++;
            }
            if(min == res[index5] * 5){
                index5++;
            }
            res[i] = min;
        }
        return res[n - 1];
    }

    /**
     * 解答成功:
     * 		执行耗时:133 ms,击败了6.97% 的Java用户
     * 		内存消耗:37.5 MB,击败了65.14% 的Java用户
     * @param n
     * @return
     */
    public static int myNthUglyNumber(int n) {
        int[] res = new int[n];
        res[0] = 1;
        int min = Integer.MAX_VALUE;
        boolean before2 = true;
        boolean before3 = true;
        boolean before5 = true;
        for(int i = 1; i < n; i++){
            int start = res[i - 1];
            for(int j = 0; j < i; j++){
                if (before2 && res[j] * 2 > start) {
                    min = Math.min(min, res[j] * 2);
                    res[i] = min;
                    min = Integer.MAX_VALUE;
                    before3 = true;
                    before5 = true;
                    break;
                }
                if (before3 && res[j] * 3 > start) {
                    min = Math.min(min, res[j] * 3);
                    before3 = false;
                }
                if (before5 && res[j] * 5 > start) {
                    min = Math.min(min, res[j] * 5);
                    before5 = false;
                }
            }
        }
        return res[n - 1];
    }

    //对数器部分
    public static int compare(int n) {
        int count = 1;
        int i = 1;
        while(true){
            if(isUgly(i)){
                if(count == n){
                    return i;
                }
                count++;
            }
            i++;
        }
    }

    public static boolean isUgly(int n){
        if(n <= 1){
            return true;
        }
        int path = n;
        while(path > 1){
            if(path % 5 == 0){
                path /= 5;
            } else if(path % 3 == 0){
                path /= 3;
            } else if(path % 2 == 0){
                path /= 2;
            } else {
                return false;
            }
        }
        return path == 1;
    }
}
