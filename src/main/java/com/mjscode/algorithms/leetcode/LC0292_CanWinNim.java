package com.mjscode.algorithms.leetcode;

/**
 * //你和你的朋友，两个人一起玩 Nim 游戏：
 * //
 * // 桌子上有一堆石头。
 * // 你们轮流进行自己的回合，你作为先手。
 * // 每一回合，轮到的人拿掉 1 - 3 块石头。
 * // 拿掉最后一块石头的人就是获胜者。
 * //
 * // 假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 n 的情况下赢得游戏。如果可以赢，返回 true；否则，返回 false 。
 * //
 * // 示例 1：
 * //
 * //输入：n = 4
 * //输出：false
 * //解释：如果堆中有 4 块石头，那么你永远不会赢得比赛；
 * //     因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
 * //
 * // 示例 2：
 * //
 * //输入：n = 1
 * //输出：true
 * //
 * // 示例 3：
 * //
 * //输入：n = 2
 * //输出：true
 * //
 * // 提示：
 * //
 * // 1 <= n <= 231 - 1
 * //
 * // Related Topics 脑筋急转弯 极小化极大
 * @author binarySigh
 * @date 2021/5/22 12:31
 */
public class LC0292_CanWinNim {

    public static void main(String[] args){
        for(int i = 1; i < 101; i++){
            //System.out.println("石头个数[" + i + "] : " + compare(i));
            System.out.println("石头个数[" + i + "] : " + canWinNim(i));
        }
    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:35.2 MB,击败了41.81% 的Java用户
     * @param n
     * @return
     */
    public static boolean canWinNim(int n) {
        return n % 4 != 0;
    }

    public static boolean compare(int n){
        if(n <= 3){
            return true;
        }
        return !compare(n - 1) || !compare(n - 2) || !compare(n - 3);
    }
}
