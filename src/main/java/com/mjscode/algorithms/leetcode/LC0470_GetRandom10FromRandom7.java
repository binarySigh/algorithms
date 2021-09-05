package com.mjscode.algorithms.leetcode;

/**
 * //已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。
 * //
 * // 不要使用系统的 Math.random() 方法。
 * //
 * // 示例 1:
 * //
 * //输入: 1
 * //输出: [7]
 * //
 * // 示例 2:
 * //
 * //输入: 2
 * //输出: [8,4]
 * //
 * // 示例 3:
 * //
 * //输入: 3
 * //输出: [8,1,10]
 * //
 * // 提示:
 * //
 * // rand7 已定义。
 * // 传入参数: n 表示 rand10 的调用次数。
 * //
 * // 进阶:
 * //
 * // rand7()调用次数的 期望值 是多少 ?
 * // 你能否尽量少调用 rand7() ?
 * //
 * // Related Topics 数学 拒绝采样 概率与统计 随机化
 * @author binarySigh
 * @date 2021/9/5 17:12
 */
public class LC0470_GetRandom10FromRandom7 {

    /**
     * 解答成功:
     * 		执行耗时:9 ms,击败了16.57% 的Java用户
     * 		内存消耗:43.3 MB,击败了77.73% 的Java用户
     * @return
     */
    public static int rand10() {
        // --> a 模拟 1~5 的随机数
        int a = 0;
        do{
            a = random7();
        } while(a > 5);
        // --> a 模拟 6~10 的随机数
        int b = 0;
        do {
           b = random7();
        } while(b < 3);
        //随机因子
        int c = 0;
        do{
            c = random7();
        } while(c == 7);
        if(c < 4){
            return a;
        } else {
            return b + 3;
        }
    }

    public static int random7(){
        return (int)(Math.random() * 7) + 1;
    }
}
