package com.mjscode.algorithms.leetcode;

/**
 * //给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
 * //
 * // 设计一个算法使得这 m 个子数组各自和的最大值最小。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [7,2,5,10,8], m = 2
 * //输出：18
 * //解释：
 * //一共有四种方法将 nums 分割为 2 个子数组。 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
 * //因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 * //
 * // 示例 2：
 * //
 * //输入：nums = [1,2,3,4,5], m = 2
 * //输出：9
 * //
 * // 示例 3：
 * //
 * //输入：nums = [1,4,4], m = 3
 * //输出：4
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 1000
 * // 0 <= nums[i] <= 10⁶
 * // 1 <= m <= min(50, nums.length)
 * //
 * // Related Topics 贪心 数组 二分查找 动态规划
 * @author binarySigh
 * @date 2021/9/27 23:28
 */
public class LC0410_SplitArray {

    public static void main(String[] args) {
        // --> 18
        /*int[] nums1 = {7,2,5,10,8};
        int m1 = 2;*/

        // --> 9
        /*int[] nums1 = {1,2,3,4,5};
        int m1 = 2;*/

        // --> 4
        /*int[] nums1 = {1,4,4};
        int m1 = 3;*/

        int[] nums1 = {1,2,3,2,5,2};
        int m1 = 3;
        System.out.println(splitArray(nums1, m1));
        System.out.println(compare(nums1, m1));

        System.out.println("----------Begin---------");
        for(int i = 0; i < 10_0000; i++){
            int n = (int)(Math.random() * 20) + 1;
            int[] nums = getNums(n);
            int m = (int)(Math.random() * (Math.min(50, n))) + 1;
            int ans = splitArray(nums, m);
            int com = compare(nums, m);
            if(com != ans){
                System.out.println("----Oops----");
                System.out.println(showArray(nums));
                System.out.println("m : " + m);
                System.out.println("ans = " + ans);
                System.out.println("com = " + com);
            }
        }
        System.out.println("----------End---------");

    }

    public static int splitArray(int[] nums, int m) {
        int N = nums.length;
        int[][] dp = new int[N][m];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < m; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        // 第一列
        int curSum = 0;
        for(int i = 0; i <= N - m; i++){
            curSum += nums[i];
            dp[i][0] = curSum;
        }
        // 后续列填充
        for(int j = 1; j < m; j++){
            for(int i = N - (m - j); i >= j; i--){
                curSum = nums[i];
                for(int t = i - 1; t >= j - 1; t--){
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[t][j - 1], curSum));
                    curSum += nums[t];
                }
                //如果已经是最后一列，那么填完最下面的格子就直接退出。因为最后一刀必须只能划在数组最后一个位置
                if(j == m - 1){
                    break;
                }
            }
        }
        return dp[N - 1][m - 1];
    }

    public static int compare(int[] nums, int m) {
        return f(nums, 0, Integer.MIN_VALUE, m);
    }

    public static int f(int[] nums, int idx, int preMax, int m){
        if(idx == nums.length && m == 0){
            return preMax;
        }
        if(idx == nums.length || m == 0){
            return Integer.MAX_VALUE;
        }
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        for(int i = idx; i < nums.length; i++){
            sum += nums[i];
            ans = Math.min(ans, f(nums, i + 1, Math.max(preMax, sum), m - 1));
        }
        return ans;
    }

    public static int[] getNums(int n) {
        int[] nums = new int[n];
        for(int i = 0; i < n; i++){
            nums[i] = (int)(Math.random() * 200);
        }
        return nums;
    }

    public static String showArray(int[] arr){
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(int i = 0; i < arr.length; i++){
            sb.append(arr[i]);
            if(i < arr.length - 1){
                sb.append(',');
            } else {
                sb.append(']');
            }
        }
        return sb.toString();
    }

}
