package com.mjscode.algorithms.leetcode;

/**
 * //最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作：
 * //
 * // Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
 * // Paste（粘贴）：粘贴 上一次 复制的字符。
 * //
 * // 给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。
 * //
 * // 示例 1：
 * //
 * //输入：3
 * //输出：3
 * //解释：
 * //最初, 只有一个字符 'A'。
 * //第 1 步, 使用 Copy All 操作。
 * //第 2 步, 使用 Paste 操作来获得 'AA'。
 * //第 3 步, 使用 Paste 操作来获得 'AAA'。
 * //
 * // 示例 2：
 * //
 * //输入：n = 1
 * //输出：0
 * //
 * // 提示：
 * //
 * // 1 <= n <= 1000
 * //
 * // Related Topics 数学 动态规划
 * @author binarySigh
 * @date 2021/9/19 10:16
 */
public class LC0650_MinSteps {
    public static void main(String[] args){
        System.out.println("---------Begin---------");
        for (int i = 1; i <= 1000; i++) {
            int com = compare(i);
            int ans = minSteps(i);
            if(ans != com){
                System.out.println("---------Oops!---------");
                System.out.println("n = " + i);
                System.out.println("ans : " + ans);
                System.out.println("com : " + com);
            }
        }
        System.out.println("---------End---------");
    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:35.2 MB,击败了63.02% 的Java用户
     * @param n
     * @return
     */
    public static int minSteps(int n) {
        int ans = 0;
        while(n > 1) {
            for(int i = n - 1; i >= 1; i--){
                if(n % i == 0){
                    ans += n / i;
                    n = i;
                    break;
                }
            }
        }
        return ans;
    }

    public static int compare(int n){
        if(n == 1){
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for(int i = n - 1; i >= 1; i--){
            if(n % i == 0){
                ans = Math.min(ans, compare(i) + n / i);
            }
        }
        return ans;
    }
}
