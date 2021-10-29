package com.mjscode.algorithms.leetcode.hard;

import java.util.HashMap;
import java.util.HashSet;

/**
 * //给你一个整数数组 distance 。
 * //
 * // 从 X-Y 平面上的点 (0,0) 开始，先向北移动 distance[0] 米，然后向西移动 distance[1] 米，向南移动 distance[2
 * //] 米，向东移动 distance[3] 米，持续移动。也就是说，每次移动后你的方位会发生逆时针变化。
 * //
 * // 判断你所经过的路径是否相交。如果相交，返回 true ；否则，返回 false 。
 * //
 * // 示例 1：
 * //
 * //输入：distance = [2,1,1,2]
 * //输出：true
 * //
 * // 示例 2：
 * //
 * //输入：distance = [1,2,3,4]
 * //输出：false
 * //
 * // 示例 3：
 * //
 * //输入：distance = [1,1,1,1]
 * //输出：true
 * //
 * // 提示：
 * //
 * // 1 <= distance.length <= 10⁵
 * // 1 <= distance[i] <= 10⁵
 * //
 * // Related Topics 几何 数组 数学
 * @author binarySigh
 * @date 2021/10/29 19:58
 */
public class LC0335_IsSelfCrossing {

    public static void main(String[] args) {
        // --> true
//  int[] dis = {2,1,1,2};

        // --> false
//  int[] dis = {1,2,3,4};

        // --> true
//  int[] dis = {1,1,1,1};

        // --> false    21/29
//  int[] dis = {3,3,3,2,1,1};

        // --> false  24/29
//  int[] dis = {1,1,2,2,3,3,4,4,10,4,4,3,3,2,2,1,1};

        int[] dis = getArr(12);

        System.out.println("Answer  : " + isSelfCrossing(dis));
        System.out.println("Compare : " + compare(dis));

        System.out.println("-------Begin------");
          for(int i = 0; i < 10_0000; i++) {
           int len = (int)(Math.random() * 10) + 1;
           int[] dist = getArr(len);
           boolean ans = false;
           boolean com = false;
           try {
            ans = isSelfCrossing(dist);
            com = compare(dist);
           } catch (Exception e) {
            showArr(dist);
            break;
           }
           if(ans ^ com) {
            System.out.println("-------Oops------");
            showArr(dist);
            System.out.println("Answer  : " + ans);
            System.out.println("Compare : " + com);
            break;
           }
          }
        System.out.println("-------End------");
    }

    public static boolean isSelfCrossing(int[] dis) {
        for(int i = 3; i < dis.length; i++) {
            if(dis[i - 1] > dis[i - 3]) {
                continue;
            }
            if(dis[i - 1] <= dis[i - 3] && dis[i] >= dis[i - 2]) {
                return true;
            }
            if(i >= 4) {
                if(dis[i] + dis[i - 4] >= dis[i - 2] && dis[i - 3] == dis[i - 1]) {
                    return true;
                }
            }
            if(i >= 5) {
                if(dis[i - 2] < dis[i - 4]) {
                    continue;
                }
                if(dis[i - 5] + dis[i - 1] >= dis[i - 3] && dis[i] + dis[i - 4] >= dis[i - 2]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean compare(int[] dis) {
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        map.put(0, new HashSet<>());
        map.get(0).add(0);
        int x = 0, y = 0;
        for(int i = 0; i < dis.length; i++) {
            if(!addLine(map, x, y, i, dis[i])) return true;
            // 调整坐标
            if(i % 4 == 0) y += dis[i];
            else if(i % 4 == 1) x -= dis[i];
            else if(i % 4 == 2) y -= dis[i];
            else x += dis[i];
        }
        return false;
    }

    /**
     * 将当前路径上的所有整数点坐标对 存入map中.
     * 如果当前路径会导致撞车，则返回false
     * @param map k - 横坐标x；v - 纵坐标y
     * @param x 当前起点横坐标
     * @param y 当前起点纵坐标
     * @param dis 方向
     * @param path 当前路径长度
     */
    public static boolean addLine(HashMap<Integer, HashSet<Integer>> map, int x, int y, int dis, int path) {
        dis %= 4;
        if(dis == 0) {
            // 向上
            for(int i = 1; i <= path; i++) {
                if(!map.containsKey(x)) {
                    HashSet<Integer> set = new HashSet<>();
                    map.put(x, set);
                }
                if(map.get(x).contains(y + i)) return false;
                else map.get(x).add(y + i);
            }
        } else if(dis == 1) {
            // 向左
            for(int i = 1; i <= path; i++) {
                if(!map.containsKey(x - i)) {
                    HashSet<Integer> set = new HashSet<>();
                    map.put(x - i, set);
                }
                if(map.get(x - i).contains(y)) return false;
                else map.get(x - i).add(y);
            }
        } else if(dis == 2) {
            // 向下
            for(int i = 1; i <= path; i++) {
                if(!map.containsKey(x)) {
                    HashSet<Integer> set = new HashSet<>();
                    map.put(x, set);
                }
                if(map.get(x).contains(y - i)) return false;
                else map.get(x).add(y - i);
            }
        } else {
            // 向右
            for(int i = 1; i <= path; i++) {
                if(!map.containsKey(x + i)) {
                    HashSet<Integer> set = new HashSet<>();
                    map.put(x + i, set);
                }
                if(map.get(x + i).contains(y)) return false;
                else map.get(x + i).add(y);
            }
        }
        return true;
    }

    public static int[] getArr(int len) {
        int[] dis = new int[len];
        for(int i = 0; i < len; i++) {
            dis[i] = (int)(Math.random() * 100) + 1;
        }
        return dis;
    }

    public static void showArr(int[] nums) {
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < nums.length - 1; i++) {
            sb.append(nums[i]).append(',');
        }
        sb.append(nums[nums.length - 1]).append(']');
        System.out.println(sb.toString());
    }

}