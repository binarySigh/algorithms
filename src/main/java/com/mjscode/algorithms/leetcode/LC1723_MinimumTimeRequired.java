package com.mjscode.algorithms.leetcode;

/**
 * //给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
 * //
 * // 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。请你
 * //设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。
 * //
 * // 返回分配方案中尽可能 最小 的 最大工作时间 。
 * //
 * // 示例 1：
 * //
 * //输入：jobs = [3,2,3], k = 3
 * //输出：3
 * //解释：给每位工人分配一项工作，最大工作时间是 3 。
 * //
 * // 示例 2：
 * //
 * //输入：jobs = [1,2,4,7,8], k = 2
 * //输出：11
 * //解释：按下述方式分配工作：
 * //1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
 * //2 号工人：4、7（工作时间 = 4 + 7 = 11）
 * //最大工作时间是 11 。
 * //
 * // 提示：
 * //
 * // 1 <= k <= jobs.length <= 12
 * // 1 <= jobs[i] <= 107
 * //
 * // Related Topics 递归 回溯算法
 * @author binarySigh
 * @date 2021/5/8 20:37
 */
public class LC1723_MinimumTimeRequired {

    public static void main(String[] args){
        //出错样本 --> 12；我的答案是 13
        /*int[] jobs = {5,5,4,4,4};
        int k = 2;*/

        //超时样本 --> 9899456
        int[] jobs = {9899456,8291115,9477657,9288480,5146275,7697968,8573153,3582365,3758448,9881935,2420271,4542202};
        int k = 9;

        /*int[] jobs = {3,2,3};
        int k = 3;*/
        System.out.println(minimumTimeRequired(jobs, k));
    }

    /**
     * 解答成功:
     * 		执行耗时:15 ms,击败了49.43% 的Java用户
     * 		内存消耗:35.8 MB,击败了77.59% 的Java用户
     * @param jobs
     * @param k
     * @return
     */
    public static int minimumTimeRequired(int[] jobs, int k) {
        //过滤输入情况
        int len = jobs.length;
        if(len == 1){
            return jobs[0];
        }
        int sum = 0, maxTime = 0;
        for(int i : jobs){
            sum += i;
            maxTime = Math.max(maxTime, i);
        }
        if(len == k || k == 1){
            return k == 1 ? sum : maxTime;
        }
        //将数组从大到小排序
        sort(jobs);
        //建立辅助数组，记录每个工人分配到的工作的总耗时
        int[] record = new int[k];
        //当 k 、len较大时，可能还会超时，因此预先处理掉一部分
        // 优化逻辑：预先将损耗时长前 k-1 的部分装填好，减少后续参与回溯的数据量
        // 之所以不填k个是因为需要留一个空，去保证所有最短组合的可能性都能被抓到
        for(int i = 0; i < k - 1; i++){
            record[i] = jobs[i];
        }
        //回溯查找最优解
        int min = process(jobs, k - 1, k, record);
        return min;
    }

    public static int process(int[] jobs, int index, int k, int[] record){
        if(index == jobs.length){
            int max = 0;
            for(int i = 0; i < k; i++){
                max = Math.max(max, record[i]);
            }
            return max;
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < k; i++){
            record[i] += jobs[index];
            min = Math.min(min, process(jobs, index + 1, k, record));
            record[i] -= jobs[index];
        }
        return min;
    }

    public static void sort(int[] jobs){
        for(int i = 0; i < jobs.length; i++){
            int j = i;
            while(j > 0 && jobs[j] > jobs[j - 1]){
                jobs[j] = jobs[j] ^ jobs[j - 1];
                jobs[j - 1] = jobs[j] ^ jobs[j - 1];
                jobs[j] = jobs[j] ^ jobs[j - 1];
                j--;
            }
        }
    }
}
