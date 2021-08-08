package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * //给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 * //
 * // 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * //
 * // 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312"
 * //和 "192.168@1.1" 是 无效 IP 地址。
 * //
 * // 示例 1：
 * //
 * //输入：s = "25525511135"
 * //输出：["255.255.11.135","255.255.111.35"]
 * //
 * // 示例 2：
 * //
 * //输入：s = "0000"
 * //输出：["0.0.0.0"]
 * //
 * // 示例 3：
 * //
 * //输入：s = "1111"
 * //输出：["1.1.1.1"]
 * //
 * // 示例 4：
 * //
 * //输入：s = "010010"
 * //输出：["0.10.0.10","0.100.1.0"]
 * //
 * // 示例 5：
 * //
 * //输入：s = "101023"
 * //输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * //
 * // 提示：
 * //
 * // 0 <= s.length <= 3000
 * // s 仅由数字组成
 * //
 * // Related Topics 字符串 回溯
 * @author binarySigh
 * @date 2021/8/8 23:12
 */
public class LC0093_RestoreIpAddresses {
    public static void main(String[] args){
        // --> ["255.255.11.135","255.255.111.35"]
        //String s = "25525511135";

        // --> ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
        //String s = "101023";

        // --> ["0.10.0.10","0.100.1.0"]
        //String s = "010010";

        // --> ["1.1.1.1"]
        //String s = "1111";

        // --> ["0.0.0.0"]
        String s = "0000";
        List<String> ans = restoreIpAddresses(s);
        System.out.println(ans);
    }

    /**
     * 解答成功:
     * 		执行耗时:5 ms,击败了37.85% 的Java用户
     * 		内存消耗:38.4 MB,击败了64.76% 的Java用户
     * @param s
     * @return
     */
    public static List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        String sb = "";
        findIps(s, 0, 0, sb, ans);
        return  ans;
    }

    public static void findIps(String s, int para, int idx, String sb, List<String> ans){
        if(para == 4) {
            if(idx == s.length()) {
                ans.add(sb.substring(1));
            }
            return;
        }
        if(idx == s.length()){
            return;
        }
        int cur = 0;
        for(int i = idx; i < idx + 3 && i < s.length(); i++){
            cur = cur * 10 + s.charAt(i) - '0';
            if(s.charAt(idx) == '0' && i > idx){
                break;
            }
            if(cur > 255){
                break;
            }
            findIps(s, para + 1, i + 1, sb + "." + cur, ans);
        }
    }
}
