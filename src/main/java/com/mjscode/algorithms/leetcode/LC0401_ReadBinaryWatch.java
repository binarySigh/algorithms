package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * //二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
 * //
 * // 例如，下面的二进制手表读取 "3:25" 。
 * //
 * // （图源：WikiMedia - Binary clock samui moon.jpg ，许可协议：Attribution-ShareAlike 3.0
 * //Unported (CC BY-SA 3.0) ）
 * //
 * // 给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。
 * //
 * // 小时不会以零开头：
 * //
 * // 例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。
 * //
 * // 分钟必须由两位数组成，可能会以零开头：
 * //
 * // 例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。
 * //
 * // 示例 1：
 * //
 * //输入：turnedOn = 1
 * //输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
 * //
 * // 示例 2：
 * //
 * //输入：turnedOn = 9
 * //输出：[]
 * //
 * // 提示：
 * //
 * // 0 <= turnedOn <= 10
 * //
 * // Related Topics 位运算 回溯算法
 * @author binarySigh
 * @date 2021/6/21 20:37
 */
public class LC0401_ReadBinaryWatch {
    public static void main(String[] args){
        int turnedOn = 0;
        List<String> ans = readBinaryWatch(turnedOn);
        System.out.println(ans);
    }

    /**
     * 解答成功:
     * 		执行耗时:9 ms,击败了51.88% 的Java用户
     * 		内存消耗:37.2 MB,击败了50.03% 的Java用户
     * @param turnedOn
     * @return
     */
    public static List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<>();
        if(turnedOn >= 9 || turnedOn < 0){
            return ans;
        }
        process(turnedOn, 0, 4, 0, 6, ans);
        return ans;
    }

    public static void process(int counts, int hour, int hourBits, int minute, int minBits, List<String> ans){
        if((hourBits == 0 && minBits == 0) || counts < 0){
            if(counts == 0 && hour <= 11 && minute <= 59){
                String time = String.valueOf(hour);
                if(minute < 10){
                    time += ":0" + minute;
                } else {
                    time += ":" + minute;
                }
                ans.add(time);
            }
            return;
        }
        if(hourBits > 0){
            process(counts, hour << 1, hourBits - 1, minute, minBits, ans);
            process(counts - 1, hour << 1 | 1, hourBits - 1, minute, minBits, ans);
        } else{
            if(hour <= 11 && counts <= 6){
                process(counts, hour, hourBits, minute << 1, minBits - 1, ans);
                process(counts - 1, hour, hourBits, minute << 1 | 1, minBits - 1, ans);
            }
        }
    }
}
