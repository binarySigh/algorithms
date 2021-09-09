package com.mjscode.algorithms.leetcode;

/**
 * //给出两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个逆序对的不同的数组的个数。
 * //
 * // 逆序对的定义如下：对于数组的第i个和第 j个元素，如果满i < j且 a[i] > a[j]，则其为一个逆序对；否则不是。
 * //
 * // 由于答案可能很大，只需要返回 答案 mod 109 + 7 的值。
 * //
 * // 示例 1:
 * //
 * //输入: n = 3, k = 0
 * //输出: 1
 * //解释:
 * //只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。
 * //
 * // 示例 2:
 * //
 * //输入: n = 3, k = 1
 * //输出: 2
 * //解释:
 * //数组 [1,3,2] 和 [2,1,3] 都有 1 个逆序对。
 * //
 * // 说明:
 * //
 * // n 的范围是 [1, 1000] 并且 k 的范围是 [0, 1000]。
 * //
 * // Related Topics 动态规划
 * @author binarySigh
 * @date 2021/9/9 20:54
 */
public class LC0629_KInversePairs {

    public static void main(String[] args) {
        // --> 440
        int n1 = 10, k1 = 8;

        System.out.println(kInversePairs1(n1, k1));
        System.out.println(kInversePairs(n1, k1));
    }

    /**
     * 四边形不等式优化
     * 解答成功:
     * 		执行耗时:17 ms,击败了74.8% 的Java用户
     * 		内存消耗:38.8 MB,击败了71.1% 的Java用户
     * @param n
     * @param k
     * @return
     */
    public static int kInversePairs(int n, int k){
        int mod = (int)Math.pow(10, 9) + 7;
        int[][] dp = new int[n + 1][k + 1];
        //第一列
        for(int i = 1; i <= n; i++){
            dp[i][0] = 1;
        }
        //使用前缀和数组来降低第三层循环的复杂度
        int[][] sum = new int[2][k + 1];
        int idx = 1;
        // 由于流程不经过 j = 0 对这个过程，因此要单独初始化0位置
        sum[0][0] = 1;
        sum[1][0] = 1;
        for(int i = 2; i <= n; i++){
            // 1 ~ i之间不重复数字组成的数组，所能拥有的逆序对的最多对数
            int limit = i * (i - 1) / 2;
            for(int j = 1; j <= limit && j <= k; j++){
                if(j == limit){
                    dp[i][j] = 1;
                    sum[idx][j] = (dp[i][j] + sum[idx][j - 1]) % mod;
                    // 由于i 行和 i - 1行的有效位置差距可能大于1(例如i为2时limit是1，i为3时limit就跳到了3)，导致主流程dp[i][j] = sum[j]这一句会赋值到0
                    //  故在每一行终止有效位之后的部分前缀和都刷新成本行终止有效位上的值
                    while(++j <= k){
                        sum[idx][j] = sum[idx][j - 1];
                    }
                    continue;
                }
                //优化思路：前缀和数组直接获取上一行 [i-j+1, j]之间的和，避免遍历
                dp[i][j] = (sum[idx ^ 1][j] - (j - i < 0 ? 0 : sum[idx ^ 1][j - i]) + mod) % mod;
                sum[idx][j] = (sum[idx][j - 1] + dp[i][j]) % mod;
            }
            idx ^= 1;
        }
        return dp[n][k];
    }

    /**
     * 朴素 动态规划
     * @param n
     * @param k
     * @return
     */
    public static int kInversePairs1(int n, int k){
        int mod = (int)Math.pow(10, 9) + 7;
        int[][] dp = new int[n + 1][k + 1];
        for(int i = 0; i <= n; i++){
            dp[i][0] = 1;
        }
        for(int i = 2; i <= n; i++){
            // 1 ~ i之间不重复数字组成的数组，所能拥有的逆序对的最多对数
            int limit = i * (i - 1) / 2;
            for(int j = 1; j <= limit && j <= k; j++){
                if(j == limit){
                    dp[i][j] = 1;
                    continue;
                }
				/*// 相当于直接在 1~i-1形成的拥有j个逆序对的数组后面放一个i，不生成新逆序对
				dp[i][j] = (dp[i][j] + dp[i - 1][j]) % mod;
				// 相当于直接在 1~i-1形成的拥有j - 1个逆序对的数组最后一个数前面放一个i，会生成1个新逆序对，变成j个逆序对
				dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % mod;
				//...以此类推
				*/
                //改为循环写法
                // 注意这里起始点并不是0，因为对于前 i-1的数字排列来说，不管i加在什么地方，都最多只能增加i-1对新的逆序对
                // 起点应该为 j - (i - 1)的地方，从那之后开始计算的和
                int t = j - i + 1 < 0 ? 0 : (j - i + 1);
                for(; t <= j; t++){
                    dp[i][j] = (dp[i][j] + dp[i - 1][t]) % mod;
                }
            }
        }
        return dp[n][k];
    }

}

