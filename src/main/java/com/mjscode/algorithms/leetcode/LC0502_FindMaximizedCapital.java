package com.mjscode.algorithms.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author binarySigh
 * @date 2021/9/8 19:15
 */
public class LC0502_FindMaximizedCapital {

    public static void main(String[] args) {
        // --> 4
      /*int[] profits = {1,2,3};
      int[] capital = {0,1,1};
      int k = 2, w = 0;*/

        // --> 6
      /*int[] profits = {1,2,3};
      int[] capital = {0,1,2};
      int k = 3, w = 0;*/

        int[] profits = {1,2,3};
        int[] capital = {1,1,2};
        int k = 1, w = 0;
        System.out.println(findMaximizedCapital(k, w, profits, capital));

    }

    /**
     * 解答成功:
     * 		执行耗时:81 ms,击败了52.3% 的Java用户
     * 		内存消耗:56.9 MB,击败了55.0% 的Java用户
     * @param k
     * @param w
     * @param profits
     * @param capital
     * @return
     */
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital){
        int n = profits.length;
        int[][] projects = new int[n][2];
        for(int i = 0; i < n; i++){
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }
        Arrays.sort(projects, (int[] a, int[] b) -> a[0] - b[0]);
        PriorityQueue<int[]> q = new PriorityQueue<>(n, (a, b) -> b[1] - a[1]);
        int idx = 0;
        while(k > 0 && idx < n){
            while(idx < n && projects[idx][0] <= w){
                q.add(projects[idx++]);
            }
            if(!q.isEmpty()){
                w += q.poll()[1];
                k--;
            } else {
                break;
            }
        }
        while(k > 0 && !q.isEmpty()){
            w += q.poll()[1];
            k--;
        }
        return w;
    }

}
