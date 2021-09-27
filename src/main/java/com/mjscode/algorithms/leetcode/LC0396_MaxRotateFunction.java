package com.mjscode.algorithms.leetcode;

/**
 * //给定一个长度为 n 的整数数组 A 。
 * //
 * // 假设 Bk 是数组 A 顺时针旋转 k 个位置后的数组，我们定义 A 的“旋转函数” F 为：
 * //
 * // F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1]。
 * //
 * // 计算F(0), F(1), ..., F(n-1)中的最大值。
 * //
 * // 注意:
 * //可以认为 n 的值小于 10⁵。
 * //
 * // 示例:
 * //
 * //A = [4, 3, 2, 6]
 * //
 * //F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 * //F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 * //F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 * //F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
 * //
 * //所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26 。
 * //
 * // Related Topics 数学 动态规划
 * @author binarySigh
 * @date 2021/9/27 23:30
 */
public class LC0396_MaxRotateFunction {

    public static void main(String[] args) {
        int[] nums1 = {4,3,2,6};

        System.out.println(maxRotateFunction(nums1));
        System.out.println(compare(nums1));

        System.out.println("----------Begin---------");
        for(int i = 0; i < 10_0000; i++){
            int n = (int)(Math.random() * 100);
            int[] nums = getNums(n);
            int ans = maxRotateFunction(nums);
            int com = compare(nums);
            if(com != ans){
                System.out.println("----Oops----");
                System.out.println(showArray(nums));
                System.out.println("ans = " + ans);
                System.out.println("com = " + com);
            }
        }
        System.out.println("----------End---------");

    }

    public static int maxRotateFunction(int[] nums) {
        if(nums == null || nums.length < 2){
            return 0;
        }
        int n = nums.length;
        int sum = 0;
        int[] dp = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            dp[0] = dp[0] + i * nums[i];
        }
        dp[1] = dp[0] - (sum - nums[0]) + (n - 1) * nums[0];
        int ans = Math.max(dp[0], dp[1]);
        for(int i = 2; i < nums.length; i++) {
            dp[i] = dp[i - 1] - (sum - nums[i - 1]) + (n - 1) * nums[i - 1];
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

    public static int compare(int[] nums) {
        if(nums == null || nums.length < 2){
            return 0;
        }
        int n = nums.length;
        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++){
            int cur = 0;
            for(int j = i + 1; j % n != i; j++){
                cur += (j - i) * nums[j % n];
            }
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    public static int[] getNums(int n) {
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = (int)(Math.random() * 200) - (int)(Math.random() * 200);
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
