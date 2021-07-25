package com.mjscode.algorithms.leetcode;

/**
 * //给你一个二维整数数组 ranges 和两个整数 left 和 right 。每个 ranges[i] = [starti, endi] 表示一个从 star
 * //ti 到 endi 的 闭区间 。
 * //
 * // 如果闭区间 [left, right] 内每个整数都被 ranges 中 至少一个 区间覆盖，那么请你返回 true ，否则返回 false 。
 * //
 * // 已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi ，那么我们称整数x 被覆盖了
 * //。
 * //
 * // 示例 1：
 * //
 * //输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
 * //输出：true
 * //解释：2 到 5 的每个整数都被覆盖了：
 * //- 2 被第一个区间覆盖。
 * //- 3 和 4 被第二个区间覆盖。
 * //- 5 被第三个区间覆盖。
 * //
 * // 示例 2：
 * //
 * //输入：ranges = [[1,10],[10,20]], left = 21, right = 21
 * //输出：false
 * //解释：21 没有被任何一个区间覆盖。
 * //
 * // 提示：
 * //
 * // 1 <= ranges.length <= 50
 * // 1 <= starti <= endi <= 50
 * // 1 <= left <= right <= 50
 * //
 * // Related Topics 数组 哈希表 前缀和
 * @author binarySigh
 * @date 2021/7/23 21:30
 */
public class LC1893_IsCovered {
    public static void main(String[] args){
        int[][] ranges = {
                {36,50},
                {14,28},
                {4,31},
                {24,37},
                {13,36},
                {27,33},
                {23,32},
                {23,27},
                {1,35}
        };
        int left = 35, right = 40;
        System.out.println(isCovered(ranges, left, right));
    }

    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了67.54% 的Java用户
     * 		内存消耗:37.4 MB,击败了96.95% 的Java用户
     * @param ranges
     * @param left
     * @param right
     * @return
     */
    public static boolean isCovered(int[][] ranges, int left, int right) {
        sort(ranges, 0, ranges.length - 1);
        for(int[] range : ranges){
            if(left >= range[0] && left <= range[1]){
                left = range[1] + 1;
                if(left > right){
                    return true;
                }
            }
        }
        return false;
    }

    public static void sort(int[][] ranges, int L, int R){
        if(L >= R || L < 0 || R >= ranges.length){
            return;
        }
        int ranPos = (int)(Math.random() * (R - L)) + L + 1;
        swap(ranges, ranPos, R);
        int i = L - 1, j = R;
        for(int cur = L; cur < j;){
            int compare = compare(ranges[cur], ranges[R]);
            if(compare < 0){
                swap(ranges, cur++, ++i);
            } else if(compare > 0){
                swap(ranges, cur, --j);
            } else {
                cur++;
            }
        }
        swap(ranges, j, R);
        sort(ranges, L, i);
        sort(ranges, j + 1, R);
    }

    public static int compare(int[] arr1, int[] arr2){
        if(arr1[0] == arr2[0]){
            return arr1[1] - arr2[1];
        } else {
            return arr1[0] - arr2[0];
        }
    }

    public static void swap(int[][] ranges, int i, int j){
        if(i != j){
            int[] tmp = ranges[i];
            ranges[i] = ranges[j];
            ranges[j] = tmp;
        }
    }
}
