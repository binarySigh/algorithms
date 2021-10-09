package com.mjscode.algorithms.leetcode.template;

import java.util.PriorityQueue;

/**
 * //给你一个字符串 num 和一个整数 k 。其中，num 表示一个很大的整数，字符串中的每个字符依次对应整数上的各个 数位 。
 * //
 * // 你可以交换这个整数相邻数位的数字 最多 k 次。
 * //
 * // 请你返回你能得到的最小整数，并以字符串形式返回。
 * //
 * // 示例 1：
 * //
 * //输入：num = "4321", k = 4
 * //输出："1342"
 * //解释：4321 通过 4 次交换相邻数位得到最小整数的步骤如上图所示。
 * //
 * // 示例 2：
 * //
 * //输入：num = "100", k = 1
 * //输出："010"
 * //解释：输出可以包含前导 0 ，但输入保证不会有前导 0 。
 * //
 * // 示例 3：
 * //
 * //输入：num = "36789", k = 1000
 * //输出："36789"
 * //解释：不需要做任何交换。
 * //
 * // 示例 4：
 * //
 * //输入：num = "22", k = 22
 * //输出："22"
 * //
 * // 示例 5：
 * //
 * //输入：num = "9438957234785635408", k = 23
 * //输出："0345989723478563548"
 * //
 * // 提示：
 * //
 * // 1 <= num.length <= 30000
 * // num 只包含 数字 且不含有 前导 0 。
 * // 1 <= k <= 10^9
 * //
 * // Related Topics 贪心 树状数组 线段树 字符串
 * @author binarySigh
 * @date 2021/10/8 19:49
 */
public class LC1505_MinInteger {

    public static void main(String[] args) {
        // --> 1342
  /*String s = "4321";
  int k = 4;*/

        // --> 010
  /*String s = "100";
  int k = 1;*/

        // --> 36789
  /*String s = "36789";
  int k = 1000;*/

        // --> 22
  /*String s = "22";
  int k = 22;*/

  /*String s = "678457";
  int k = 2;*/

        String s = "640600177";
        int k = 10;

        System.out.println("s : " + s);
        System.out.println("k : " + k);
        System.out.println("com : " + compare(s, k));
        System.out.println("ans : " + minInteger(s, k));

        System.out.println("--------BEGIN-------");
        for(int i = 0; i < 10_0000; i++){
            int n = (int)(Math.random() * 100) + 1;
            String s1 = generateString(n);
            int k1 = (int)(Math.random() * 3000) + 1;
            String ans = minInteger(s1, k1);
            String com = compare(s1, k1);
            if(!ans.equals(com)) {
                System.out.println("------Oops------");
                System.out.println("s : " + s1);
                System.out.println("k : " + k1);
                System.out.println("ans : " + ans);
                System.out.println("com : " + com);
                break;
            }
        }
        System.out.println("--------END-------");

        //性能测试
        System.out.println("------性能测试-------");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 20000; i++){
            sb.append('9');
        }
        for(int i = 0; i < 1000; i++){
            sb.append('0');
        }
        int k2 = 30000;
        long comStart = System.nanoTime();
        String c = compare(sb.toString(), k2);
        long comEnd = System.nanoTime();
        long ansStart = System.nanoTime();
        String a = minInteger(sb.toString(), k2);
        long ansEnd = System.nanoTime();
        System.out.println("结果是否正确： " + c.equals(a));
        System.out.println("对照组结果： " + c);
        System.out.println("对照组耗时：" + (comEnd - comStart));
        System.out.println("实验组结果： " + a);
        System.out.println("实验组耗时：" + (ansEnd - ansStart));
    }

    public static String minInteger(String s, int k) {
        StringBuilder sb = new StringBuilder();
        boolean[] used = new boolean[s.length()];
        // 线段树作用：由于是相邻位置交换，故后置位字符被挑选后，其前置位所有字符位置会+1，这个是区间操作，因此需要线段树
        SegmentTree st = new SegmentTree(s.length());
        // 小根堆按照字符字典序升序排序，相同字符按照在字符串中位置靠前的排在前面
        // a[0] -> 字符；a[1] -> 在字符串中下标位置
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            else return a[0] - b[0];
        });
        int idx = 0;
        for(; idx < Math.min(k + 1, s.length()); idx++) {
            heap.add(new int[]{(int)s.charAt(idx), idx});
        }
        // 辅助小根堆，用于暂存过程中提前弹出的小元素。排序规则仅按照下标升序
        PriorityQueue<int[]> stack = new PriorityQueue<>((a, b) -> {return a[1] - b[1];});
        while(!heap.isEmpty()) {
            while(!heap.isEmpty()) {
                int[] tmp = heap.poll();
                int path = st.query(tmp[1]) - sb.length();
                if(path <= k) {
                    sb.append((char)tmp[0]);
                    used[tmp[1]] = true;
                    if(path > 0) {
                        st.move(tmp[1]);
                    }
                    k -= path;
                    break;
                } else {
                    // 当前最小字符调整过来所需步骤大于k，将其暂时弹出保存到容器中
                    stack.add(tmp);
                }
            }
            // 将之前提前弹出的字符重新加入,这里仅加入调整步骤在k步以内的
            while(!stack.isEmpty() && st.query(stack.peek()[1]) - sb.length() <= k) {
                heap.add(stack.poll());
            }
            // 还有字符尚未加入堆中，且此时主堆内剩余容量小于k，则继续往后加字符，直到堆大小==k
            while(idx < s.length() && heap.size() <= k){
                heap.add(new int[]{(int)s.charAt(idx), idx++});
            }
        }
        // 按顺序拼接上剩余的字符
        for(int i = 0; i < used.length; i++){
            if(!used[i]) sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static class SegmentTree {
        int[] lazy;
        int len;
        public SegmentTree(int n) {
            this.len = getLen(n);
            this.lazy = new int[this.len << 1];
            for(int i = 0; i < n; i++) {
                this.lazy[this.len + i] = i;
            }
        }

        public int query(int originIdx) {
            int idx = this.len + originIdx;
            int ans = 0;
            while(idx >= 1) {
                ans += this.lazy[idx];
                if((idx & 1) == 0) {
                    idx >>= 1;
                } else {
                    idx = (idx - 1) >> 1;
                }
            }
            return ans;
        }

        public void move(int r){
            add(1, this.len, 1, r, 1);
        }

        private void add(int b, int e, int l, int r, int idx){
            if(b >= l && e <= r) {
                this.lazy[idx]++;
                return;
            }
            pushDown(idx);
            int mid = b + ((e - b) >> 1);
            if(l <= mid) {
                add(b, mid, l, r, idx << 1);
            }
            if(r >= mid + 1) {
                add(mid + 1, e, l, r, idx << 1 | 1);
            }
        }

        private void pushDown(int idx) {
            if((idx << 1) < this.lazy.length){
                this.lazy[idx << 1] += this.lazy[idx];
                this.lazy[idx << 1 | 1] += this.lazy[idx];
                this.lazy[idx] = 0;
            }
        }

        public int getLen(int n) {
            n |= n >> 1;
            n |= n >> 2;
            n |= n >> 4;
            n |= n >> 8;
            n |= n >> 16;
            return ++n;
        }
    }

    ///////////
    // 对数器 //
    ///////////

    public static String compare(String s, int k) {
        StringBuilder sb = new StringBuilder();
        boolean[] used = new boolean[s.length()];
        while(sb.length() < s.length()) {
            int idx = 0;
            for(; idx < s.length(); idx++){
                if(!used[idx]) break;
            }
            int minIdx = idx;
            int path = 0;
            int minPath = 0;
            for(int i = idx; i < s.length() && path <= k; i++) {
                if(used[i]) continue;
                if(s.charAt(minIdx) > s.charAt(i)) {
                    minIdx = i;
                    minPath = path;
                }
                path++;
            }
            sb.append(s.charAt(minIdx));
            used[minIdx] = true;
            k -= minPath;
        }
        return sb.toString();
    }

    public static String generateString(int n) {
        char[] str = new char[n];
        str[0] = (char)((int)(Math.random() * 9) + '1');
        for(int i = 1; i < n; i++) {
            str[i] = (char)((int)(Math.random() * 10) + '0');
        }
        return new String(str);
    }

}
