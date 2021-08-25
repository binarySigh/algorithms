package com.mjscode.algorithms.newCoder.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *描述
 * 计算最少出列多少位同学，使得剩下的同学排成合唱队形
 *
 * 说明：
 *
 * N位同学站成一排，音乐老师要请其中的(N-K)位同学出列，使得剩下的K位同学排成合唱队形。
 * 合唱队形是指这样的一种队形：
 *      设K位同学从左到右依次编号为1，2…，K，他们的身高分别为T1，T2，…，TK，
 *      则他们的身高满足存在i（1<=i<=K）使得T1<T2<......<Ti-1<Ti>Ti+1>......>TK。
 *
 * 你的任务是，已知所有N位同学的身高，计算最少需要几位同学出列，可以使得剩下的同学排成合唱队形。
 *
 * 注意：不允许改变队列元素的先后顺序 且 不要求最高同学左右人数必须相等
 * 请注意处理多组输入输出！
 *
 * 备注：
 * 1<=N<=3000
 * 输入描述：
 * 有多组用例，每组都包含两行数据，第一行是同学的总数N，第二行是N位同学的身高，以空格隔开
 *
 * 输出描述：
 * 最少需要几位同学出列
 * @author binarySigh
 * @date 2021/8/25 20:36
 */
public class HJ24_Chorus {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
            // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int[] heights = new int[in.nextInt()];
            for(int i = 0; i < heights.length; i++){
                heights[i] = in.nextInt();
            }
            System.out.println(minOut(heights));
        }
    }

    /**
     * 运行时间：169ms 超过 79.59% 用Java提交的代码
     * 占用内存：18212KB 超过 59.66% 用Java提交的代码
     * @param nums
     * @return
     */
    public static int minOut(int[] nums){
        ArrayList<Integer> help = new ArrayList<>(nums.length);
        //从左到右找一遍最长递增子序列
        int[] left = new int[nums.length];
        left[0] = 1;
        help.add(nums[0]);
        for(int i = 1; i < nums.length; i++){
            if(help.size() >= 1 && help.get(help.size() - 1) < nums[i]){
                left[i] = help.size() + 1;
                help.add(nums[i]);
            } else {
                left[i] = getFloorIdx(help, nums[i]) + 2;
                help.set(left[i] - 1, nums[i]);
            }
        }
        help.clear();
        //从右到左再玩一遍最长递增子序列
        int[] right = new int[nums.length];
        right[nums.length - 1] = 1;
        help.add(nums[nums.length - 1]);
        for(int i = nums.length - 2; i >= 0; i--){
            if(help.size() >= 1 && help.get(help.size() - 1) < nums[i]){
                right[i] = help.size() + 1;
                help.add(nums[i]);
            } else {
                right[i] = getFloorIdx(help, nums[i]) + 2;
                help.set(right[i] - 1, nums[i]);
            }
        }
        //查找最大值
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            max = Math.max(max, left[i] + right[i] - 1);
        }
        return nums.length - max;
    }

    public static int getFloorIdx(ArrayList<Integer> help, int num){
        if(help.size() == 0){
            return 0;
        }
        int l = 0, r = help.size() - 1;
        int ans = -1;
        while(l <= r){
            int mid = l + ((r - l) >> 1);
            if(help.get(mid) < num){
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}
