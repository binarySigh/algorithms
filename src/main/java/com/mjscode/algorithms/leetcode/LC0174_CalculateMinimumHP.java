package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * // 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿
 * //过地下城并通过对抗恶魔来拯救公主。
 * //
 * // 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 * //
 * // 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么
 * //包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 * //
 * // 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 * //
 * // 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 * //
 * // 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
 * //
 * // -2 (K) -3     3
 * // -5     -10    1
 * // 10     30     -5 (P)
 * //
 * // 说明:
 * //
 * // 骑士的健康点数没有上限。
 * //
 * // 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 * // Related Topics 数组 动态规划 矩阵
 * @author binarySigh
 * @date 2021/9/6 19:52
 */
public class LC0174_CalculateMinimumHP {

    //本题简便解法是 从右下往左上逆推dp
    public static void main(String[] args) {
        // --> 7
        /*int[][] dungeon = {
            {-2, -3, 3},
            {-5, -10, 1},
            {10, 30, -5}
        };*/

        /*int[][] dungeon = {
            {7, -4,  3, -7, -5},
            {-1,-5, -5, -5,  7},
            {0, -6, -7, -4,  8},
            {1, -4, -1, -5, -7},
            {0,  7,  4, -1,  2}
        };*/

        int[][] dungeon = {
                { 4,-4,-4},
                { 2,-5, 1},
                {-2, 4,-4}
        };


        System.out.println("Compare : " + compare(dungeon));
        System.out.println("Answer  : " + calculateMinimumHP(dungeon));

        System.out.println("---------Begin--------");
        for(int i = 0; i < 10_0000; i++){
            int m = 10, n = 10;
            int[][] dung = getDungeon(m, n);
            int com = compare(dung);
            //int ans1 = calculateMinimumHP1(dung);
            int ans2 = calculateMinimumHP(dung);
            if(/*ans1 != com || */ans2 != com /*|| ans1 != ans2*/){
                System.out.println("-----Oops-----");
                ArrayUtils.printMatrix(dung);
                System.out.println("Compare : " + com);
                //System.out.println("Answer1 : " + ans1);
                System.out.println("Answer2 : " + ans2);
                break;
            }
        }
        System.out.println("---------End--------");
    }

    /**
     * 在 2 的基础上继续优化，将 maxSum 改为 实际路径中可能的最大值，而非矩阵中所有正整数和
     *
     * 解答成功:
     * 		执行耗时:267 ms,击败了5.3% 的Java用户
     * 		内存消耗:42.4 MB,击败了5.0% 的Java用户
     * @param dungeon
     * @return
     */
    public static int calculateMinimumHP(int[][] dungeon){
        int m = dungeon.length, n = dungeon[0].length;
        int hold = getHold(dungeon) + 1;
        int[][][] dp = new int[hold][2][n];
        //初始化dp
        for(int i = 0; i < hold; i++){
            for(int j = 0; j < 2; j++){
                for(int t = 0; t < n; t++){
                    dp[i][j][t] = Integer.MAX_VALUE;
                }
            }
        }
        //初始化起点位置
        if(dungeon[0][0] <= 0){
            dp[1][0][0] = 1 - dungeon[0][0];
        } else {
            dp[dungeon[0][0]][0][0] = 0;
        }
        //计算后续位置。计算方式是由前一位置往后面的位置推
        int idx = 0;
        for(int i = 0; i < m - 1; i++){
            for(int j = 0; j < n; j++){
                for(int t = 1; t < hold; t++){
                    if(dp[t][idx][j] == Integer.MAX_VALUE){
                        continue;
                    }
                    //右边的格子
                    if(j < n - 1){
                        int nextHold = t + dungeon[i][j + 1];
                        if(nextHold > 0){
                            dp[nextHold][idx][j + 1] = Math.min(dp[nextHold][idx][j + 1], dp[t][idx][j]);
                        } else {
                            dp[1][idx][j + 1] = Math.min(dp[1][idx][j + 1], dp[t][idx][j] - nextHold + 1);
                        }
                    }
                    //下边的格子。流程控制下面肯定会有格子，因此无需判断越界
                    int nextHold = t + dungeon[i + 1][j];
                    if(nextHold > 0){
                        dp[nextHold][idx ^ 1][j] = Math.min(dp[nextHold][idx ^ 1][j], dp[t][idx][j]);
                    } else {
                        dp[1][idx ^ 1][j] = Math.min(dp[1][idx ^ 1][j], dp[t][idx][j] - nextHold + 1);
                    }
                    //当前格子利用完恢复为
                    dp[t][idx][j] = Integer.MAX_VALUE;
                }
            }
            idx ^= 1;
        }
        //上述流程没有对最后一行做完善处理，这里需要将最后一行加上从左往右的推导过程
        for(int j = 0; j < n - 1; j++){
            for(int t = 1; t < hold; t++){
                if(dp[t][idx][j] == Integer.MAX_VALUE){
                    continue;
                }
                int nextHold = t + dungeon[m - 1][j + 1];
                if(nextHold > 0){
                    dp[nextHold][idx][j + 1] = Math.min(dp[nextHold][idx][j + 1], dp[t][idx][j]);
                } else {
                    dp[1][idx][j + 1] = Math.min(dp[1][idx][j + 1], dp[t][idx][j] - nextHold + 1);
                }
            }
        }
        //收集答案
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < hold; i++){
            ans = Math.min(dp[i][idx][n - 1], ans);
        }
        return ans == 0 ? 1 : ans;
    }

    /**
     * dp求矩阵中每条路径过程中可能产生的最大值
     * @param dungeon
     * @return
     */
    public static int getHold(int[][] dungeon){
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[2][n];
        dp[0][0] = dungeon[0][0] <= 0 ? 1 : dungeon[0][0];
        int max = dp[0][0];
        for(int j = 1; j < n; j++){
            dp[0][j] = dp[0][j - 1] + dungeon[0][j];
            dp[0][j] = dp[0][j] <= 0 ? 1 : dp[0][j];
            max = Math.max(max, dp[0][j]);
        }
        int idx = 1;
        for(int i = 1; i < m; i++){
            dp[idx][0] = dp[idx ^ 1][0] + dungeon[i][0];
            dp[idx][0] = dp[idx][0] <= 0 ? 1 : dp[idx][0];
            max = Math.max(max, dp[idx][0]);
            for(int j = 1; j < n; j++){
                dp[idx][j] = Math.max(dp[idx ^ 1][j], dp[idx][j - 1]) + dungeon[i][j];
                dp[idx][j] = dp[idx][j] <= 0 ? 1 : dp[idx][j];
                max = Math.max(max, dp[idx][j]);

            }
            idx ^= 1;
        }
        return max;
    }

    /**
     * 利用滚动数组 优化：maxSum * m * n -> maxSum * 2 * n
     * m行的空间优化至 2行
     *  44/45   超出内存限制
     * @param dungeon
     * @return
     */
    public static int calculateMinimumHP2(int[][] dungeon){
        int m = dungeon.length, n = dungeon[0].length;
        int hold = 2;
        for(int i = 0; i < dungeon.length; i++){
            for(int j = 0; j < dungeon[0].length; j++){
                if(dungeon[i][j] > 0){
                    hold += dungeon[i][j];
                }
            }
        }
        int[][][] dp = new int[hold][2][n];
        //初始化dp
        for(int i = 0; i < hold; i++){
            for(int j = 0; j < 2; j++){
                for(int t = 0; t < n; t++){
                    dp[i][j][t] = Integer.MAX_VALUE;
                }
            }
        }
        //初始化起点位置
        if(dungeon[0][0] <= 0){
            dp[1][0][0] = 1 - dungeon[0][0];
        } else {
            dp[dungeon[0][0]][0][0] = 0;
        }
        //计算后续位置。计算方式是由前一位置往后面的位置推
        int idx = 0;
        for(int i = 0; i < m - 1; i++){
            for(int j = 0; j < n; j++){
                for(int t = 1; t < hold; t++){
                    if(dp[t][idx][j] == Integer.MAX_VALUE){
                        continue;
                    }
                    //右边的格子
                    if(j < n - 1){
                        int nextHold = t + dungeon[i][j + 1];
                        if(nextHold > 0){
                            dp[nextHold][idx][j + 1] = Math.min(dp[nextHold][idx][j + 1], dp[t][idx][j]);
                        } else {
                            dp[1][idx][j + 1] = Math.min(dp[1][idx][j + 1], dp[t][idx][j] - nextHold + 1);
                        }
                    }
                    //下边的格子。流程控制下面肯定会有格子，因此无需判断越界
                    int nextHold = t + dungeon[i + 1][j];
                    if(nextHold > 0){
                        dp[nextHold][idx ^ 1][j] = Math.min(dp[nextHold][idx ^ 1][j], dp[t][idx][j]);
                    } else {
                        dp[1][idx ^ 1][j] = Math.min(dp[1][idx ^ 1][j], dp[t][idx][j] - nextHold + 1);
                    }
                    //当前格子利用完恢复为
                    dp[t][idx][j] = Integer.MAX_VALUE;
                }
            }
            idx ^= 1;
        }
        //上述流程没有对最后一行做完善处理，这里需要将最后一行加上从左往右的推导过程
        for(int j = 0; j < n - 1; j++){
            for(int t = 1; t < hold; t++){
                if(dp[t][idx][j] == Integer.MAX_VALUE){
                    continue;
                }
                int nextHold = t + dungeon[m - 1][j + 1];
                if(nextHold > 0){
                    dp[nextHold][idx][j + 1] = Math.min(dp[nextHold][idx][j + 1], dp[t][idx][j]);
                } else {
                    dp[1][idx][j + 1] = Math.min(dp[1][idx][j + 1], dp[t][idx][j] - nextHold + 1);
                }
            }
        }
        //收集答案
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < hold; i++){
            ans = Math.min(dp[i][idx][n - 1], ans);
        }
        return ans == 0 ? 1 : ans;
    }

    /**
     * 超出内存限制
     * @param dungeon
     * @return
     */
    public static int calculateMinimumHP1(int[][] dungeon){
        int m = dungeon.length, n = dungeon[0].length;
        int len = m * n;
        int hold = 2;
        for(int i = 0; i < dungeon.length; i++){
            for(int j = 0; j < dungeon[0].length; j++){
                if(dungeon[i][j] > 0){
                    hold += dungeon[i][j];
                }
            }
        }
        int[][] dp = new int[hold][len];
        //初始化dp
        for(int i = 0; i < hold; i++){
            for(int j = 0; j < len; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        //初始化起点位置
        if(dungeon[0][0] <= 0){
            dp[1][0] = 1 - dungeon[0][0];
        } else {
            dp[dungeon[0][0]][0] = 0;
        }
        //计算后续位置。计算方式是由前一位置往后面的位置推
        for(int j = 0; j < len - 1; j++){
            for(int i = 0; i < hold; i++){
                if(dp[i][j] == Integer.MAX_VALUE){
                    continue;
                }
                //右边的格子
                if(j % n < n - 1){
                    int x = j / n, y = j % n + 1;
                    if(i + dungeon[x][y] > 0){
                        dp[i + dungeon[x][y]][j + 1] = Math.min(dp[i + dungeon[x][y]][j + 1], dp[i][j]);
                    } else {
                        dp[1][j + 1] = Math.min(dp[1][j + 1], dp[i][j] - (i + dungeon[x][y]) + 1);
                    }
                }
                //下边的格子
                if(j / n < m - 1){
                    int x = j / n + 1, y = j % n;
                    if(i + dungeon[x][y] > 0){
                        dp[i + dungeon[x][y]][j + n] = Math.min(dp[i + dungeon[x][y]][j + n], dp[i][j]);
                    } else {
                        dp[1][j + n] = Math.min(dp[1][j + n], dp[i][j] - (i + dungeon[x][y]) + 1);
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < hold; i++){
            ans = Math.min(dp[i][len - 1], ans);
        }
        return ans == 0 ? 1 : ans;
    }

    public static int compare(int[][] dun){
        int ans = process(dun, 0, 0, 0, 0);
        return ans == 0 ? 1 : ans;
    }

    public static int process(int[][] dun, int x, int y, int preHold, int preAdd){
        int curAdd = dun[x][y] + preHold > 0 ? preAdd : (preAdd - (dun[x][y] + preHold) + 1);
        int curHold = dun[x][y] + preHold > 0 ? (preHold + dun[x][y]) : 1;
        if(x == dun.length - 1 && y == dun[0].length - 1){
            return curAdd;
        }
        int ans = Integer.MAX_VALUE;
        //往右走
        if(y + 1 <= dun[0].length - 1){
            ans = process(dun, x, y + 1, curHold, curAdd);
        }
        //往下走
        if(x + 1 <= dun.length - 1){
            ans = Math.min(ans, process(dun, x + 1, y, curHold, curAdd));
        }
        return ans;
    }

    public static int[][] getDungeon(int m, int n){
        int[][] dungeon = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                dungeon[i][j] = (int)(Math.random() * 10) - (int)(Math.random() * 10);
            }
        }
        return dungeon;
    }

}
