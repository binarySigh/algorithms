package com.mjscode.algorithms.leetcode;

import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * //给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * //
 * // '.' 匹配任意单个字符
 * // '*' 匹配零个或多个前面的那一个元素
 * //
 * // 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * //
 * // 示例 1：
 * //
 * //输入：s = "aa" p = "a"
 * //输出：false
 * //解释："a" 无法匹配 "aa" 整个字符串。
 * //
 * // 示例 2:
 * //
 * //输入：s = "aa" p = "a*"
 * //输出：true
 * //解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * //
 * // 示例 3：
 * //
 * //输入：s = "ab" p = ".*"
 * //输出：true
 * //解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * //
 * // 示例 4：
 * //
 * //输入：s = "aab" p = "c*a*b"
 * //输出：true
 * //解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * //
 * // 示例 5：
 * //
 * //输入：s = "mississippi" p = "mis*is*p*."
 * //输出：false
 *
 * // 提示：
 * //
 * // 0 <= s.length <= 20
 * // 0 <= p.length <= 30
 * // s 可能为空，且只包含从 a-z 的小写字母。
 * // p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * // 保证每次出现字符 * 时，前面都匹配到有效的字符
 * //
 * // Related Topics 字符串 动态规划 回溯算法
 *
 * @author binarySigh
 */
public class LC0010_IsMatch {
    public static void main(String[] args){
        /*char c1 = 'a';
        char c2 = 'a';
        System.out.println(c1 == c2);  // true
        System.out.println(c1 - 0);  // 97
        System.out.println(c1 == 97);  // true */
    }

    /**
     *
     * ASCII:
     *   . : 46
     *   * : 42
     * @param s
     * @param p
     * @return
     */
    public static boolean isMacth(String s, String p){
        if((s == null && p == null) || ("".equals(s) && "".equals(p))){
            return true;
        }
        if((s == null || p == null) || ("".equals(s) || "".equals(p))){
            return false;
        }
        char[] str = s.toCharArray();
        char[] pStr = p.toCharArray();
        //辅助数组。sMax[i]表示str[]中以i位置字符往右查找，在不被打断的情况下最多有几个字符与他相同(不包括自己)
        int[] sMax = new int[str.length];
        int[] pMax = new int[pStr.length];
        //遍历指针
        int i = str.length - 2;
        int j = pStr.length - 2;
        // 当匹配串中出现“.*”时，用来记录最近一个“.*”通配符的下一个位置
        // 防止出现这种情况："acbdjaabbadcfaabb" -  ".*aabb"
        //   这时候i来到adc的a位置，j已经结束了，按照流程返回的是false，但实际上这两个串是匹配成功的；
        //   因此这个时候要把j指针回退到a位置接着匹配
        int indenxFlag = -1;
        //填充辅助数组
        for(; i >= 0 ; i--){
            //看看i+1位置字符是否与i位置字符相同，相同则i位置长度就是i+1位置长度+1，否则就是0
            sMax[i] = str[i] - str[i+1] == 0 ? sMax[i+1] + 1 : 0;
        }
        for(; j >= 0; j--){
            //pMax[] 通配符辅助数组稍微麻烦一点，因为有特殊字符存在
            // . 字符与正常字母一样统计，*一律给0
            if(pStr[j] - 42 == 0){
                pMax[j] = 0;
            } else {
                pMax[j] = pStr[j] - pStr[j] == 0 ? pMax[j+1] + 1 : 0;
            }
        }
        i = 0; j = 0;
        while(i < str.length && j < pStr.length){
            //特别说明，本流程会严格控制不会让通配符指针指到*，所以大情况只会有两种：1.匹配到. ；2.匹配到a~z
            if(pStr[j] - 46 == 0){ //当前匹配到.符号
                if((j+1 < pStr.length) && (pStr[j + 1] - 42 == 0)){ //如果.后面接着的是*，特殊处理
                    //如果.*已经是通配符结尾了，那么直接结束，认为匹配完成
                    if(j+2 >= pStr.length){
                        return true;
                    }
                    //如果.*后面还有其他字符，则继续分情况
                    if(pStr[j+2] - 46 == 0){
                        //如果j+2位置还是.(可能会有n个)那么就跳到这n个点的最后一个上，同时i也要跳相同的步幅
                        j += pMax[j+2] + 2; // 这里步幅其实是pMax[j+2]，额外＋2是因为要额外跳过j+1位置的*
                        i += pMax[j+2];
                    } else {
                        //如果j+2位置是a~z的字符，j跳到j+2位置
                        j += 2;
                        //i一直跳，跳到第一个满足以下条件的
                        while((i < str.length) && (str[i] != pStr[j] || sMax[i] < pMax[j])){
                            i++;
                        }
                    }
                    //记录这个时候的j位置，方便后面匹配失败时给j指针回退
                    indenxFlag = j;
                } else {
                    //只要后面不是*，就i,j++
                    i++; j++;
                }
            } else { // 当前匹配到的是a~z
                if(str[i] != pStr[j]){
                    //如果两个字符不一样，那么检查j+1是不是*，如果是，那就直接j跳到j+2
                    if(j+1 >= pStr.length){
                        //j+1是越界位置，那么检查之前是否有过".*",如果有，就跳到当时的记录位置;如果没有就认为匹配失败
                        if(indenxFlag >= 0){
                            j = indenxFlag;
                            continue;
                        } else {
                            return false;
                        }
                    }
                    //检查j+1是否为*
                    if(pStr[j] - 42 == 0){
                        //后面接的是*,说明当前可能是 aac,a*aac的情况，即*是0次，那么直接跳到j+2位置
                        j += 2;
                    } else {
                        //当前匹配不同，后面也不是*
                        // 先查看flag是否有过修改，如果有，j回退至修改位置；如果没有，认为匹配失败，返回false
                        if(indenxFlag >= 0){
                            j = indenxFlag;
                            continue;
                        } else {
                            return false;
                        }
                    }
                } else {
                    if(sMax[i] == pMax[j]){  //两个字符相同，且两个字符长度也一样
                        //认为这一段匹配成功，同时保证要跳出这一段
                        i += sMax[i] + 1;
                        j += pMax[j] + 1;
                        //额外检查下一区域是否为*,如果是，那么j要额外再走一步；否则就不动。保证我们的大流程j不会遍历到*位置
                        j += pStr[j] - 42 == 0 ? 1 : 0;
                    } else if(sMax[i] < pMax[j]) { //两个字符相同，且原串该位置字符长度比匹配串还小
                        // 先查看flag是否有过修改，如果有，j回退至修改位置；如果没有，认为匹配失败，返回false
                        if(indenxFlag >= 0){
                            j = indenxFlag;
                            continue;
                        } else {
                            return false;
                        }
                    } else { //两个字符相同，且原串该位置字符长度比匹配串大
                        //情况比较多，如：1. "aaaac"  -  "aac"
                        //              2. "aaaac"  -  "aa"
                        //              3. "aaaac"  -  "aa*c"
                        //              4. "aaaac"  -  "aa*ac"(或者通配符是"aa*.c")
                        //看匹配串区域外的字符是不是*
                        if((j + pMax[j] + 1 >= pStr.length) || (pStr[j + pMax[j] + 1] - 42 != 0)){
                            //对应情况2，越界
                            //对应情况1，不越界的情况下,下一段字符也不为*
                            // 先查看flag是否有过修改，如果有，j回退至修改位置；如果没有，认为匹配失败，返回false
                            if(indenxFlag >= 0){
                                j = indenxFlag;
                                continue;
                            } else {
                                return false;
                            }
                        } else { //既不越界，匹配符下一段字符还是*
                            //先把j跳到*前那个位置，同时i也跳相同步幅
                            j += pMax[j];
                            i += pMax[j];
                            //检查*下一段是否还是 a（或者.）
                            //检查此时的j+2是否越界  防止出现"aaaac"  -  "aa*"
                            if(j+2 >= pStr.length){
                                //如果真的越界，那么就让i直接跳出这一区域；让j来到越位位置
                                //因为每次最外层while最后会执行检查操作，所以这里j直接跳到越位位置没关系
                                j += 2;
                                i += sMax[i] + 1; //是否应该让i跳出当前区域？
                            } else { //不越位的情况
                                if(pStr[j+2] - 46 == 0){
                                    //不越位，但是*下一个字符是.。对应情况4 括号内的那种情况。需要单独处理

                                } else if(pStr[j+2] != pStr[j]){
                                    //不越位，但是*下一个字符既不是.也不是a。对应情况3
                                    // 先查看flag是否有过修改，如果有，j回退至修改位置；如果没有，认为匹配失败，返回false
                                    if(indenxFlag >= 0){
                                        j = indenxFlag;
                                        continue;
                                    } else {
                                        return false;
                                    }
                                } else if(pStr[j+2] == pStr[j]){
                                    //不越位，且*下一个位置是a，对应情况4. "aaaac"  -  "aa*ac"
                                    //查看这个a的区域大小，如果≤此时原串中a的长度str[i],则两个串同时跳到本区域的最后一个字符
                                    //即i跳到最后一个a的位置；j跳到*下面那段的最后一位(因为也有可能匹配串是"aa*aac")
                                    if(pMax[j+2] <= sMax[i]){
                                        //i,j都跳出自己当前字符所在区域
                                        i += (sMax[i] + 1);
                                        j += (2 + pMax[j+2] + 1);
                                    } else {
                                        //匹配串*后面那段长度 比 此时原串中剩余部分长度还长
                                        // 先查看flag是否有过修改，如果有，j回退至修改位置；如果没有，认为匹配失败，返回false
                                        if(indenxFlag >= 0){
                                            j = indenxFlag;
                                            continue;
                                        } else {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //如果此时j已到结尾，而i还没到，那么检查flag是否有效，
            //         如果有效，j回退至flag位置，接着匹配；如果无效，则什么也不做
            if(j >= pStr.length && indenxFlag >= 0){
                j = indenxFlag;
            }
        }
        //如果跳出循环时，还有一个串没匹配完，就认为匹配失败。
        if(i < str.length || j < pStr.length){
            return false;
        }
        return true;
    }
}
