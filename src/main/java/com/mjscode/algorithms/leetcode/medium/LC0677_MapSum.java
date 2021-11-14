package com.mjscode.algorithms.leetcode.medium;

import java.util.Stack;

/**
 * //实现一个 MapSum 类，支持两个方法，insert 和 sum：
 * //
 * // MapSum() 初始化 MapSum 对象
 * // void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键
 * //key 已经存在，那么原来的键值对将被替代成新的键值对。
 * // int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 * //
 * // 示例：
 * //
 * //输入：
 * //["MapSum", "insert", "sum", "insert", "sum"]
 * //[[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * //输出：
 * //[null, null, 3, null, 5]
 * //
 * //解释：
 * //MapSum mapSum = new MapSum();
 * //mapSum.insert("apple", 3);
 * //mapSum.sum("ap");           // return 3 (apple = 3)
 * //mapSum.insert("app", 2);
 * //mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 * //
 * // 提示：
 * //
 * // 1 <= key.length, prefix.length <= 50
 * // key 和 prefix 仅由小写英文字母组成
 * // 1 <= val <= 1000
 * // 最多调用 50 次 insert 和 sum
 * //
 * // Related Topics 设计 字典树 哈希表 字符串
 * @author binarySigh
 * @date 2021/11/14 16:41
 */
public class LC0677_MapSum {
    private Node root;

    public LC0677_MapSum() {
        this.root = new Node(0);
    }

    public void insert(String key, int val) {
        int pre = find(this.root, key);
        Node cur = this.root;
        for(int i = 0; i < key.length(); i++) {
            int idx = key.charAt(i) - 'a';
            if(cur.next[idx] != null) {
                cur.next[idx].sum += val - pre;
            } else {
                cur.next[idx] = new Node(val);
            }
            cur = cur.next[idx];
        }
        cur.val = val;
    }

    public int sum(String prefix) {
        if(this.root == null) return 0;
        Node cur = this.root;
        int i = 0;
        for(; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if(cur.next[idx] != null) {
                cur = cur.next[idx];
            } else {
                break;
            }
        }
        return i == prefix.length() ? cur.sum : 0;
    }

    private int find(Node cur, String c) {
        int i = 0;
        for(; i < c.length(); i++) {
            int idx = c.charAt(i) - 'a';
            if(cur.next[idx] != null) {
                cur = cur.next[idx];
            } else {
                break;
            }
        }
        return i == c.length() ? cur.val : 0;
    }

    public class Node {
        Node[] next;
        int sum;
        int val;
        public Node(int s) {
            this.next = new Node[26];
            this.sum = s;
            this.val = 0;
        }
    }

    /**
     * 解答成功:
     * 		执行耗时:11 ms,击败了99.71% 的Java用户
     * 		内存消耗:38.4 MB,击败了55.95% 的Java用户
     * @param args
     */
    public static void main(String[] args) {
        LC0677_MapSum ms = new LC0677_MapSum();
        ms.insert("abcd", 12);
        ms.insert("abcde", 2);
        System.out.println(ms.sum("ab"));
        ms.insert("abcd", 1);
        System.out.println(ms.sum("ab"));
        System.out.println(ms.find(ms.root, "abcde"));
    }
}
