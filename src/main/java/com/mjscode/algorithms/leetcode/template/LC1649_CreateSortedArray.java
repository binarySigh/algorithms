package com.mjscode.algorithms.leetcode.template;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * //给你一个整数数组 instructions ，你需要根据 instructions 中的元素创建一个有序数组。一开始你有一个空的数组 nums ，你需要 从
 * //左到右 遍历 instructions 中的元素，将它们依次插入 nums 数组中。每一次插入操作的 代价 是以下两者的 较小值 ：
 * //
 * // nums 中 严格小于 instructions[i] 的数字数目。
 * // nums 中 严格大于 instructions[i] 的数字数目。
 * //
 * // 比方说，如果要将 3 插入到 nums = [1,2,3,5] ，那么插入操作的 代价 为 min(2, 1) (元素 1 和 2 小于 3 ，元素 5
 * //大于 3 ），插入后 nums 变成 [1,2,3,3,5] 。
 * //
 * // 请你返回将 instructions 中所有元素依次插入 nums 后的 总最小代价 。由于答案会很大，请将它对 109 + 7 取余 后返回。
 * //
 * // 示例 1：
 * //
 * // 输入：instructions = [1,5,6,2]
 * //输出：1
 * //解释：一开始 nums = [] 。
 * //插入 1 ，代价为 min(0, 0) = 0 ，现在 nums = [1] 。
 * //插入 5 ，代价为 min(1, 0) = 0 ，现在 nums = [1,5] 。
 * //插入 6 ，代价为 min(2, 0) = 0 ，现在 nums = [1,5,6] 。
 * //插入 2 ，代价为 min(1, 2) = 1 ，现在 nums = [1,2,5,6] 。
 * //总代价为 0 + 0 + 0 + 1 = 1 。
 * //
 * // 示例 2:
 * //
 * // 输入：instructions = [1,2,3,6,5,4]
 * //输出：3
 * //解释：一开始 nums = [] 。
 * //插入 1 ，代价为 min(0, 0) = 0 ，现在 nums = [1] 。
 * //插入 2 ，代价为 min(1, 0) = 0 ，现在 nums = [1,2] 。
 * //插入 3 ，代价为 min(2, 0) = 0 ，现在 nums = [1,2,3] 。
 * //插入 6 ，代价为 min(3, 0) = 0 ，现在 nums = [1,2,3,6] 。
 * //插入 5 ，代价为 min(3, 1) = 1 ，现在 nums = [1,2,3,5,6] 。
 * //插入 4 ，代价为 min(3, 2) = 2 ，现在 nums = [1,2,3,4,5,6] 。
 * //总代价为 0 + 0 + 0 + 0 + 1 + 2 = 3 。
 * //
 * // 示例 3：
 * //
 * // 输入：instructions = [1,3,3,3,2,4,2,1,2]
 * //输出：4
 * //解释：一开始 nums = [] 。
 * //插入 1 ，代价为 min(0, 0) = 0 ，现在 nums = [1] 。
 * //插入 3 ，代价为 min(1, 0) = 0 ，现在 nums = [1,3] 。
 * //插入 3 ，代价为 min(1, 0) = 0 ，现在 nums = [1,3,3] 。
 * //插入 3 ，代价为 min(1, 0) = 0 ，现在 nums = [1,3,3,3] 。
 * //插入 2 ，代价为 min(1, 3) = 1 ，现在 nums = [1,2,3,3,3] 。
 * //插入 4 ，代价为 min(5, 0) = 0 ，现在 nums = [1,2,3,3,3,4] 。
 * //​​​​​插入 2 ，代价为 min(1, 4) = 1 ，现在 nums = [1,2,2,3,3,3,4] 。
 * //插入 1 ，代价为 min(0, 6) = 0 ，现在 nums = [1,1,2,2,3,3,3,4] 。
 * //插入 2 ，代价为 min(2, 4) = 2 ，现在 nums = [1,1,2,2,2,3,3,3,4] 。
 * //总代价为 0 + 0 + 0 + 0 + 1 + 0 + 1 + 0 + 2 = 4 。
 * //
 * // 提示：
 * //
 * // 1 <= instructions.length <= 105
 * // 1 <= instructions[i] <= 105
 * //
 * // Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序
 * @author binarySigh
 * @date 2021/9/4 11:11
 */
public class LC1649_CreateSortedArray {
    public static void main(String[] args){
        // --> 1
        //int[] ins1 = {1,5,6,2};

        // --> 3
        //int[] ins1 = {1,2,3,6,5,4};

        // --> 4
        //int[] ins1 = {1,3,3,3,2,4,2,1,2};

        //int[] ins1 = {99,3,5,92,36,14,65,42,20,39,14,95,33,87,20,31,16,18,75,64,96,49,77,54,40,69,1,78,91,59,26,33,49,62,84,97,99,72,47,10};

        int[] ins1 = {7,86,23,9,33,16,45,56,44,36,69,39,62,67,18,92,72,14,77,95,50,91,57,22,49,37,83,9,61,13,29,58,52,33,94};
        System.out.println(createSortedArray(ins1));
        System.out.println(compare(ins1));

        System.out.println("----------Begin-----------");
        for(int i = 0; i < 10_0000; i++){
            int len = (int)(Math.random() * 100) + 1;
            int[] nums = getIns(len);
            int com = compare(nums);
            int ans = createSortedArray(nums);
            if(ans != com){
                System.out.println("------Oops-----");
                ArrayUtils.showArray(nums);
                System.out.println("ans : " + ans);
                System.out.println("com : " + com);
                break;
            }
        }
        System.out.println("----------End-----------");
    }

    /**
     *
     解答成功:
     执行耗时:559 ms,击败了24.32% 的Java用户
     内存消耗:53.6 MB,击败了75.68% 的Java用户
     * @param ins
     * @return
     */
    public static int createSortedArray(int[] ins) {
        int mod = (int)Math.pow(10, 9) + 7;
        SBTree t = new SBTree();
        int ans = 0;
        for(int i = 0; i < ins.length; i++){
            ans = (ans + Math.min(t.cntsOfLessThan(ins[i]), t.cntsOfMoreThan(ins[i]))) % mod;
            t.add(ins[i]);
        }
        return ans;
    }

    public static class SBTree{
        Node root;

        public SBTree(){
            this.root = null;
        };

        public void add(int n){
            if(this.root == null){
                this.root = new Node(n);
                return;
            }
            Stack<Node> path = new Stack<>();
            boolean isExists = false;
            Node cur = this.root;
            while(cur != null && cur.val != n){
                cur.nodes++;
                cur.cnts++;
                path.push(cur);
                cur = cur.val < n ? cur.right : cur.left;
            }
            if(cur == null){
                cur = new Node(n);
            } else {
                isExists = true;
                cur.cnts++;
                cur.nodes++;
            }
            path.push(cur);
            while(!path.isEmpty()){
                cur = path.pop();
                cur.nodes -= isExists ? 1 : 0;
                Node f = path.isEmpty() ? null : path.peek();
                balance(cur, f);
            }
        }

        public int cntsOfLessThan(int n){
            if(this.root == null){
                return 0;
            }
            int ans = 0;
            Node cur = this.root;
            while(cur != null && cur.val != n) {
                if(cur.val < n){
                    ans += cur.right != null ? (cur.cnts - cur.right.cnts) : cur.cnts;
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }
            }
            if(cur == null){
                return ans;
            }
            ans += cur.left != null ? cur.left.cnts : 0;
            return ans;
        }

        public int cntsOfMoreThan(int n){
            if(this.root == null){
                return 0;
            }
            int ans = 0;
            Node cur = this.root;
            while(cur != null && cur.val != n){
                if(cur.val > n){
                    ans += cur.left != null ? (cur.cnts - cur.left.cnts) : cur.cnts;
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            if(cur == null){
                return ans;
            }
            ans += cur.right != null ? cur.right.cnts : 0;
            return ans;
        }

        /**
         * 平衡函数一定要设计成带返回值的，每次返回当前子树调整后的新头部<BR/>
         * 因为在调整结束后的递归检查平衡性的过程中当前子树头节点有可能会再次被换掉，
         *      此时需要用 newNode 接住新头，然后串联当前子树父节点<BR/>
         * @param cur 当前待调整子树的头节点
         * @param f 待调整子树的父节点
         * @return
         */
        private Node balance(Node cur, Node f){
            int l = cur.left == null ? 0 : cur.left.nodes;
            int ll = cur.left != null && cur.left.left != null ? cur.left.left.nodes : 0;
            int lr = cur.left != null && cur.left.right != null ? cur.left.right.nodes : 0;
            int r = cur.right != null ? cur.right.nodes : 0;
            int rl = cur.right != null && cur.right.left != null ? cur.right.left.nodes : 0;
            int rr = cur.right != null && cur.right.right != null ? cur.right.right.nodes : 0;
            //执行平衡性调整
            Node newNode = null;
            if(r < ll){
                newNode = rightRotate(cur);
                balance(cur, newNode);
                newNode = balance(newNode, f);
            } else if(r < lr){
                Node preLeft = cur.left;
                cur.left = leftRotate(preLeft);
                newNode = rightRotate(cur);
                balance(preLeft, newNode);
                balance(cur, newNode);
                newNode = balance(newNode, f);
            } else if(l < rr){
                newNode = leftRotate(cur);
                balance(cur, newNode);
                newNode = balance(newNode, f);
            } else if(l < rl){
                Node preRight = cur.right;
                cur.right = rightRotate(preRight);
                newNode = leftRotate(cur);
                balance(preRight, newNode);
                balance(cur, newNode);
                newNode = balance(newNode, f);
            }
            newNode = newNode == null ? cur : newNode;
            if(f == null){
                this.root = newNode;
            } else {
                if(f.val > newNode.val){
                    f.left = newNode;
                } else {
                    f.right = newNode;
                }
            }
            return newNode;
        }

        private Node leftRotate(Node cur){
            int rNodes = cur.right != null ? cur.right.nodes : 0;
            int rlNodes = cur.right != null && cur.right.left != null ? cur.right.left.nodes : 0;
            int r = cur.right != null ? cur.right.cnts : 0;
            int rl = cur.right != null && cur.right.left != null ? cur.right.left.cnts : 0;
            Node right = cur.right;
            if(right != null){
                cur.cnts = cur.cnts - r + rl;
                cur.nodes = cur.nodes - rNodes + rlNodes;
                cur.right = right.left;
                right.cnts = r - rl + cur.cnts;
                right.nodes = rNodes - rlNodes + cur.nodes;
                right.left = cur;
                cur = right;
            }
            return cur;
        }

        private Node rightRotate(Node cur){
            int lNodes = cur.left != null ? cur.left.nodes : 0;
            int lrNodes = cur.left != null && cur.left.right != null ? cur.left.right.nodes : 0;
            int l = cur.left != null ? cur.left.cnts : 0;
            int lr = cur.left != null && cur.left.right != null ? cur.left.right.cnts : 0;
            Node left = cur.left;
            if(left != null){
                cur.nodes = cur.nodes - lNodes + lrNodes;
                cur.cnts = cur.cnts - l + lr;
                cur.left = left.right;
                left.nodes = lNodes - lrNodes + cur.nodes;
                left.cnts = l - lr + cur.cnts;
                left.right = cur;
                cur = left;
            }
            return cur;
        }

        private static class Node{
            int val;
            int nodes;
            int cnts;
            Node left;
            Node right;
            public Node(int v){
                this.val = v;
                this.nodes = 1;
                this.cnts = 1;
                this.left = null;
                this.right = null;
            }
        }
    }

    //对数器部分
    public static int compare(int[] ins){
        int ans = 0;
        List<Integer> l = new ArrayList<>();
        for(int i = 0; i < ins.length; i++){
            int[] tmp = moreOrLess(l, ins[i]);
            ans += Math.min(tmp[0], tmp[1]);
            l.add(ins[i]);
        }
        return ans;
    }

    public static int[] moreOrLess(List<Integer> l, int n){
        int[] ans = new int[2];
        for(Integer i : l){
            ans[0] += (i > n ? 1 : 0);
            ans[1] += (i < n ? 1 : 0);
        }
        return ans;
    }

    public static int[] getIns(int len){
        int[] ins = new int[len];
        for(int i = 0; i < len; i++){
            ins[i] = (int)(Math.random() * 100) + 1;
        }
        return ins;
    }
}
