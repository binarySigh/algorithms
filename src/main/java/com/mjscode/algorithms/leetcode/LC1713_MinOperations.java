package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * //给你一个数组 target ，包含若干 互不相同 的整数，以及另一个整数数组 arr ，arr 可能 包含重复元素。
 * //
 * // 每一次操作中，你可以在 arr 的任意位置插入任一整数。比方说，如果 arr = [1,4,1,2] ，那么你可以在中间添加 3 得到 [1,4,3,1,
 * //2] 。你可以在数组最开始或最后面添加整数。
 * //
 * // 请你返回 最少 操作次数，使得 target 成为 arr 的一个子序列。
 * //
 * // 一个数组的 子序列 指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。比方说，[2,7,4] 是 [4,2,3,
 * //7,2,1,4] 的子序列（加粗元素），但 [2,4,2] 不是子序列。
 * //
 * // 示例 1：
 * //
 * // 输入：target = [5,1,3], arr = [9,4,2,3,4]
 * //输出：2
 * //解释：你可以添加 5 和 1 ，使得 arr 变为 [5,9,4,1,2,3,4] ，target 为 arr 的子序列。
 * //
 * // 示例 2：
 * //
 * // 输入：target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]
 * //输出：3
 * //
 * // 提示：
 * //
 * // 1 <= target.length, arr.length <= 105
 * // 1 <= target[i], arr[i] <= 109
 * // target 不包含任何重复元素。
 * //
 * // Related Topics 贪心 数组 哈希表 二分查找
 * @author binarySigh
 * @date 2021/7/26 21:01
 */
public class LC1713_MinOperations {

    public static void main(String[] args){
        System.out.println("--------------TEST START-----------");
        for(int i = 0; i < 50_0000; i++){
            int len1 = (int)(Math.random() * 50) + 1;
            int len2 = (int)(Math.random() * 50) + 1;
            int[] target = getTarget(len1);
            int[] arr = getArr(target, len2, len1);
            int ans = minOperations(target, arr);
            int com = compare(target, arr);
            if(ans != com){
                System.out.println("-------Oops!---------");
                ArrayUtils.showArray(target);
                ArrayUtils.showArray(arr);
                System.out.println("ans = " + ans + "; com = " + com);
                break;
            }
        }
        System.out.println("--------------TEST END-----------");
    }

    /**
     * 思路：最长公共子序列 -> 最长递增子序列
     * 1.由于 t 数组中元素不重复，因此可将 t 元素值和下标做成对应的哈希；将arr中对应元素修改为在t中的下标(忽略非公共元素)，得到arr'
     * 2.公共子序列在 t 中一定是严格下标递增的，所以问题就被转化成了求 arr' 中最长递增子序列
     * 3.最长递增子序列问题是可以通过 贪心 + 二分 快速解决的
     * 解答成功:
     * 		执行耗时:73 ms,击败了54.68% 的Java用户
     * 		内存消耗:60.1 MB,击败了46.04% 的Java用户
     * @param target
     * @param arr
     * @return
     */
    public static int minOperations(int[] target, int[] arr) {
        int N = target.length;
        HashMap<Integer, Integer> map = new HashMap<>(N);
        for(int i = 0; i < N; i++){
            map.put(target[i], i);
        }
        List<Integer> d = new ArrayList<>();
        for(int cur : arr){
            if(map.containsKey(cur)){
                int idx = map.get(cur);
                if(d.size() == 0 || idx > d.get(d.size() - 1)){
                    d.add(idx);
                } else {
                    int replacePos = binarySearch(d, idx);
                    d.set(replacePos, idx);
                }
            }
        }
        return N - d.size();
    }

    public static int binarySearch(List<Integer> d, int target){
        int L = 0, R = d.size() - 1;
        int ans = 0;
        while(L <= R){
            int mid = L + ((R - L) >> 1);
            if(d.get(mid) >= target){
                R = mid - 1;
                ans = mid;
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 思路：求解两个数组最长公共子序列，该子序列长度与target长度差值即为arr最少操作数
     * 因数据量过大，超时
     * @param target
     * @param arr
     * @return
     */
    public static int compare(int[] target, int[] arr) {
        int N = target.length, M = arr.length;
        int[][] dp = new int[2][M];
        dp[0][0] = target[0] == arr[0] ? 1 : 0;
        for(int i = 1; i < M; i++){
            dp[0][i] = dp[0][i - 1] == 1 ? 1 : (target[0] == arr[i] ? 1 : 0);
        }
        int row = 1;
        for(int i = 1; i < N; i++){
            for(int j = 0; j < M; j++){
                if(j == 0){
                    dp[row][j] = dp[row ^ 1][j] == 1 ? 1 : (target[i] == arr[j] ? 1 : 0);
                } else if(target[i] == arr[j]){
                    dp[row][j] = dp[row ^ 1][j - 1] + 1;
                } else {
                    dp[row][j] = Math.max(dp[row ^ 1][j], dp[row][j - 1]);
                }
            }
            row ^= 1;
        }
        return N - dp[row ^ 1][M - 1];
    }

    public static int[] getTarget(int len){
        int[] target = new int[len];
        //严格递增，以保证每个数都不同
        target[0] = (int)(Math.random() * 10) + 1;
        for(int i = 1; i < len; i++){
            target[i] = target[i - 1] + (int)(Math.random() * 10) + 1;
        }
        //打乱顺序
        for(int i = 0; i < len; i++){
            swap(target, i, (int)(Math.random() * len));
        }
        return target;
    }

    public static void swap(int[] target, int i, int j){
        if(target[i] != target[j]){
            target[i] = target[i] ^ target[j];
            target[j] = target[i] ^ target[j];
            target[i] = target[i] ^ target[j];
        }
    }

    public static int[] getArr(int[] target, int len2, int len1){
        int[] arr = new int[len2];
        for(int i = 0; i < len2; i++){
            arr[i] = target[(int)(Math.random() * len1)];
            if(Math.random() > 0.5){
                arr[i] += (int)(Math.random() * 20) + 1;
            }
        }
        return arr;
    }
}
