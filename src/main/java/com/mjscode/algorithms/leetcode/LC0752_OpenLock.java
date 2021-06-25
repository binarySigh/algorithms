package com.mjscode.algorithms.leetcode;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * //你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
 * // 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * //
 * // 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * //
 * // 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * //
 * // 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 * //
 * // 示例 1:
 * //
 * //输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * //输出：6
 * //解释：
 * //可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * //注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * //因为当拨动到 "0102" 时这个锁就会被锁定。
 * //
 * // 示例 2:
 * //
 * //输入: deadends = ["8888"], target = "0009"
 * //输出：1
 * //解释：
 * //把最后一位反向旋转一次即可 "0000" -> "0009"。
 * //
 * // 示例 3:
 * //
 * //输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], targ
 * //et = "8888"
 * //输出：-1
 * //解释：
 * //无法旋转到目标数字且不被锁定。
 * //
 * // 示例 4:
 * //
 * //输入: deadends = ["0000"], target = "8888"
 * //输出：-1
 * //
 * // 提示：
 * //
 * // 1 <= deadends.length <= 500
 * // deadends[i].length == 4
 * // target.length == 4
 * // target 不在 deadends 之中
 * // target 和 deadends[i] 仅由若干位数字组成
 * //
 * // Related Topics 广度优先搜索 数组 哈希表 字符串
 * @author binarySigh
 * @date 2021/6/25 21:08
 */
public class LC0752_OpenLock {
    public static void main(String[] args){
        // --> 6
        /*String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";*/

        // --> 1
        /*String[] deadends = {"8888"};
        String target = "0009";*/

        // --> -1
        /*String[] deadends = {"8887","8889","8878","8898","8788","8988","7888","9888"};
        String target = "8888";*/

        // --> -1
        /*String[] deadends = {"0000"};
        String target = "8888";*/

        String[] deadends = {"0001"};
        String target = "8888";
        System.out.println(openLock(deadends, target));
    }

    /**
     * 解答成功:
     * 		执行耗时:218 ms,击败了15.83% 的Java用户
     * 		内存消耗:46.9 MB,击败了5.03% 的Java用户
     * @param deadends
     * @param target
     * @return
     */
    public static int openLock(String[] deadends, String target) {
        HashSet<String> sets = new HashSet<>(deadends.length);
        for(String s : deadends){
            sets.add(s);
        }
        if(sets.contains("0000")){
            return -1;
        }
        HashSet<String> distinct = new HashSet<>();
        LinkedList<String> queue = new LinkedList<>();
        queue.addFirst(target);
        distinct.add(target);
        String curEnds = target;
        String nextEnds = target;
        int level = 0;
        while(!queue.isEmpty()){
            String cur = queue.pollLast();
            if("0000".equals(cur)){
                return level;
            }
            for(int i = 0; i < 4; i++){
                char pos = cur.charAt(i);
                String son1 = cur.substring(0, i) + (pos == '9' ? '0' : (char)(pos + 1)) + cur.substring(i + 1, 4);
                if(!sets.contains(son1) && !distinct.contains(son1)){
                    nextEnds = son1;
                    distinct.add(son1);
                    queue.addFirst(son1);
                }
                String son2 = cur.substring(0, i) + (pos == '0' ? '9' : (char)(pos - 1)) + cur.substring(i + 1, 4);
                if(!sets.contains(son2) && !distinct.contains(son2)){
                    nextEnds = son2;
                    distinct.add(son2);
                    queue.addFirst(son2);
                }
            }
            if(cur.equals(curEnds)){
                curEnds = nextEnds;
                level++;
            }
        }
        return -1;
    }
}
