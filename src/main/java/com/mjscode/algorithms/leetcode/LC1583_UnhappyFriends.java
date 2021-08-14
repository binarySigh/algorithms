package com.mjscode.algorithms.leetcode;

/**
 * //给你一份 n 位朋友的亲近程度列表，其中 n 总是 偶数 。
 * //
 * // 对每位朋友 i，preferences[i] 包含一份 按亲近程度从高到低排列 的朋友列表。换句话说，排在列表前面的朋友与 i 的亲近程度比排在列表后面的
 * //朋友更高。每个列表中的朋友均以 0 到 n-1 之间的整数表示。
 * //
 * // 所有的朋友被分成几对，配对情况以列表 pairs 给出，其中 pairs[i] = [xi, yi] 表示 xi 与 yi 配对，且 yi 与 xi 配对
 * //。
 * // 但是，这样的配对情况可能会是其中部分朋友感到不开心。在 x 与 y 配对且 u 与 v 配对的情况下，如果同时满足下述两个条件，x 就会不开心：
 * //
 * // x 与 u 的亲近程度胜过 x 与 y，且
 * // u 与 x 的亲近程度胜过 u 与 v
 * //
 * // 返回 不开心的朋友的数目 。
 * //
 * // 示例 1：
 * //
 * // 输入：n = 4, preferences = [[1, 2, 3], [3, 2, 0], [3, 1, 0], [1, 2, 0]], pairs =
 * // [[0, 1], [2, 3]]
 * //输出：2
 * //解释：
 * //朋友 1 不开心，因为：
 * //- 1 与 0 配对，但 1 与 3 的亲近程度比 1 与 0 高，且
 * //- 3 与 1 的亲近程度比 3 与 2 高。
 * //朋友 3 不开心，因为：
 * //- 3 与 2 配对，但 3 与 1 的亲近程度比 3 与 2 高，且
 * //- 1 与 3 的亲近程度比 1 与 0 高。
 * //朋友 0 和 2 都是开心的。
 * //
 * // 示例 2：
 * //
 * // 输入：n = 2, preferences = [[1], [0]], pairs = [[1, 0]]
 * //输出：0
 * //解释：朋友 0 和 1 都开心。
 * //
 * // 示例 3：
 * //
 * // 输入：n = 4, preferences = [[1, 3, 2], [2, 3, 0], [1, 3, 0], [0, 2, 1]], pairs =
 * // [[1, 3], [0, 2]]
 * //输出：4
 * //
 * // 提示：
 * //
 * // 2 <= n <= 500
 * // n 是偶数
 * // preferences.length == n
 * // preferences[i].length == n - 1
 * // 0 <= preferences[i][j] <= n - 1
 * // preferences[i] 不包含 i
 * // preferences[i] 中的所有值都是独一无二的
 * // pairs.length == n/2
 * // pairs[i].length == 2
 * // xi != yi
 * // 0 <= xi, yi <= n - 1
 * // 每位朋友都 恰好 被包含在一对中
 * //
 * // Related Topics 数组 模拟
 * @author binarySigh
 * @date 2021/8/14 10:09
 */
public class LC1583_UnhappyFriends {

    public static void main(String[] args){
        // --> 2
        /*int n = 4;
        int[][] preferences = {
                {1, 2, 3},
                {3, 2, 0},
                {3, 1, 0},
                {1, 2, 0}
        };
        int[][] pairs = {
                {0, 1},
                {2, 3}
        };*/

        // --> 0
        /*int n = 2;
        int[][] preferences = {
                {1},
                {0}
        };
        int[][] pairs = {
                {0, 1}
        };*/

        // --> 4
        /*int n = 4;
        int[][] preferences = {
                {1, 3, 2},
                {2, 3, 0},
                {1, 3, 0},
                {0, 2, 1}
        };
        int[][] pairs = {
                {1, 3},
                {0, 2}
        };*/

        // --> 3
        int n = 4;
        int[][] preferences = {
                {3,2,1},
                {3,2,0},
                {3,1,0},
                {1,2,0}
        };
        int[][] pairs = {
                {0, 3},
                {1, 2}
        };
        System.out.println(unhappyFriends(n, preferences, pairs));
    }

    /**
     * 解答成功:
     * 		执行耗时:20 ms,击败了25.30% 的Java用户
     * 		内存消耗:54 MB,击败了96.39% 的Java用户
     * @param n
     * @param preferences
     * @param pairs
     * @return
     */
    public static int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        // pair[i] = j : 朋友i的配对对象是j
        int[] pair = new int[n];
        for(int[] tmp : pairs){
            pair[tmp[0]] = tmp[1];
            pair[tmp[1]] = tmp[0];
        }
        // 去重表，每个统计过的朋友标为true，尚未统计的朋友标记为false
        boolean[] del = new boolean[n];
        int ans = 0;
        for(int i = 0; i < pair.length; i++) {
            //在朋友i的喜欢列表中列举排在当前配对对象之前的所有朋友
            for(int j = 0; j < preferences[i].length && preferences[i][j] != pair[i]; j++){
                //逐一列举可能不高兴的朋友
                int curPrefer = preferences[i][j];
                //验证他们是否真的不高兴
                boolean notPair = canPair(preferences[curPrefer], pair[curPrefer], i);
                if(notPair) {
                    ans += del[i] ? 0 : 1;
                    ans += del[curPrefer] ? 0 : 1;
                    del[i] = true;
                    del[curPrefer] = true;
                }
            }
        }
        return ans;
    }

    /**
     * 在喜欢列表 arr中，新候选人c是否排位比已配对人s更高
     * @param arr
     * @param s
     * @param c
     * @return
     */
    public static boolean canPair(int[] arr, int s, int c){
        if(arr.length == 0){
            return false;
        }
        for(int i : arr){
            if(i == s){
                return false;
            }
            if(i == c) {
                return true;
            }
        }
        return false;
    }
}
