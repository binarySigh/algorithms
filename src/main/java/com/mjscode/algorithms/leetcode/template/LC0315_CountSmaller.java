package com.mjscode.algorithms.leetcode.template;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * //给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于 num
 * //s[i] 的元素的数量。
 * //
 * // 示例：
 * //
 * // 输入：nums = [5,2,6,1]
 * //输出：[2,1,1,0]
 * //解释：
 * //5 的右侧有 2 个更小的元素 (2 和 1)
 * //2 的右侧仅有 1 个更小的元素 (1)
 * //6 的右侧有 1 个更小的元素 (1)
 * //1 的右侧有 0 个更小的元素
 * //
 * // 提示：
 * //
 * // 0 <= nums.length <= 10^5
 * // -10^4 <= nums[i] <= 10^4
 * //
 * // Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序
 * @author binarySigh
 * @date 2021/9/3 20:41
 */
public class LC0315_CountSmaller {

    public static void main(String[] args) {
        //int[] nums = {1,2,3,2,1};

        int[] nums1 = {5,2,6,3,7,8,12,31,1};
        List<Integer> ans1 = countSmaller(nums1);
        System.out.println(ans1.toString());

        System.out.println("----------Begin-----------");
        for(int i = 0; i < 10_0000; i++){
            int len = (int)(Math.random() * 20) + 1;
            int[] nums = getNums(len);
            List<Integer> com = compare(nums);
            List<Integer> ans = countSmaller(nums);
            if(!ans.toString().equals(com.toString())){
                System.out.println("------Oops-----");
                ArrayUtils.showArray(nums);
                System.out.println(ans.toString());
                System.out.println(com.toString());
                break;
            }
        }
        System.out.println("----------End-----------");
    }

    public static List<Integer> countSmaller(int[] nums){
        SBTree tree = new SBTree();
        List<Integer> ans = new ArrayList<>(nums.length);
        for(int i = 0; i < nums.length; i++){
            ans.add(0);
        }
        for(int i = nums.length - 1; i >= 0; i--){
            ans.set(i, tree.getCntsOfLessThan(nums[i]));
            tree.add(nums[i]);
        }
        return ans;
    }

    public static class SBTree{
        Node root;

        public SBTree(){
            this.root = null;
        }

        public void add(int v){
            if(root == null){
                root = new Node(v);
                return;
            }
            Stack<Node> roads = new Stack<>();
            //预先将沿途节点的节点数都增加，当做新节点处理。
            // 最后如果不是新节点，则在路径回溯做平衡性调整的时候再把节点数减回来
            boolean isExists = false;
            Node cur = root;
            while(cur != null && cur.val != v){
                cur.cnts++;
                cur.nodes++;
                roads.push(cur);
                cur = v > cur.val ? cur.right : cur.left;
            }
            if(cur == null){
                cur = new Node(v);
                if(roads.peek().val < v){
                    roads.peek().right = cur;
                } else {
                    roads.peek().left = cur;
                }
            } else if(cur.val == v){
                isExists = true;
                cur.cnts++;
                cur.nodes++;
            }
            roads.push(cur);
            Node f = null;
            while(!roads.isEmpty()){
                cur = roads.pop();
                cur.nodes -= isExists ? 1 : 0;
                f = roads.isEmpty() ? null : roads.peek();
                balance(f, cur);
            }
        }

        public int getCntsOfLessThan(int n){
            if(root == null){
                return 0;
            }
            int ans = root.cnts;
            Node cur = root;
            while(cur != null && cur.val != n){
                if(cur.val < n){
                    //往右划
                    cur = cur.right;
                } else {
                    ans -= (cur.cnts - (cur.left != null ? cur.left.cnts : 0));
                    cur = cur.left;
                }
            }
            if(cur == null){
                return ans;
            } else {
                return ans - (cur.left != null ? (cur.cnts - cur.left.cnts) : cur.cnts);
            }
        }

        private Node balance(Node f, Node cur){
            int l = cur.left != null ? cur.left.nodes : 0;
            int r = cur.right != null ? cur.right.nodes : 0;
            int ll = cur.left != null && cur.left.left != null ? cur.left.left.nodes : 0;
            int lr = cur.left != null && cur.left.right != null ? cur.left.right.nodes : 0;
            int rl = cur.right != null && cur.right.left != null ? cur.right.left.nodes : 0;
            int rr = cur.right != null && cur.right.right != null ? cur.right.right.nodes : 0;
            //执行调整
            if(r < ll){
                cur = rightRotate(cur);
            } else if(r < lr){
                cur.left = leftRotate(cur.left);
                cur = rightRotate(cur);
            } else if(l < rr){
                cur = leftRotate(cur);
            } else if(l < rl){
                cur.right = rightRotate(cur.right);
                cur = leftRotate(cur);
            }
            //串好指针
            if(f == null){
                this.root = cur;
            } else {
                if(f.val > cur.val){
                    f.left = cur;
                } else {
                    f.right = cur;
                }
            }
            return cur;
        }

        private Node leftRotate(Node cur){
            int rNodes = cur.right != null ? cur.right.nodes : 0;
            int rlNodes = cur.right != null && cur.right.left != null ? cur.right.left.nodes : 0;
            int rCnts = cur.right != null ? cur.right.cnts : 0;
            int rlCnts = cur.right != null && cur.right.left != null ? cur.right.left.cnts : 0;
            Node r = cur.right;
            if(r != null){
                cur.cnts = cur.cnts - rCnts + rlCnts;
                cur.nodes = cur.nodes - rNodes + rlNodes;
                r.nodes = rNodes - rlNodes + cur.nodes;
                r.cnts = rCnts - rlCnts + cur.cnts;
                cur.right = r.left;
                r.left = cur;
                cur = r;
            }
            return cur;
        }

        private Node rightRotate(Node cur){
            int lNodes = cur.left != null ? cur.left.nodes : 0;
            int lrNodes = cur.left != null && cur.left.right != null ? cur.left.right.nodes : 0;
            int lCnts = cur.left != null ? cur.left.cnts : 0;
            int lrCnts = cur.left != null && cur.left.right != null ? cur.left.right.cnts : 0;
            Node l = cur.left;
            if(l != null){
                cur.nodes = cur.nodes - lNodes + lrNodes;
                cur.cnts = cur.cnts - lCnts + lrCnts;
                l.nodes = lNodes - lrNodes + cur.nodes;
                l.cnts = lCnts - lrCnts + cur.cnts;
                cur.left = l.right;
                l.right = cur;
                cur = l;
            }
            return cur;
        }

        private static class Node{
            int val;
            int cnts;  //实际数字数
            int nodes; //实际节点数，相同数字算作同一节点。同时也是平衡因子
            Node left;
            Node right;
            Node(int v){
                this.val = v;
                this.cnts = 1;
                this.nodes = 1;
                this.left = null;
                this.right = null;
            }
        }
    }

    // 对数器部分
    public static List<Integer> compare(int[] nums){
        List<Integer> ans = new ArrayList<>(nums.length);
        for(int i = 0; i < nums.length; i++){
            ans.add(0);
        }
        for(int i = nums.length - 1; i >= 0; i--){
            int cur = 0;
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] > nums[j]){
                    cur++;
                }
            }
            ans.set(i, cur);
        }
        return ans;
    }

    public static int[] getNums(int len){
        int[] nums = new int[len];
        for(int i = 0; i < len; i++){
            nums[i] = (int)(Math.random() * 300) - (int)(Math.random() * 300);
        }
        return nums;
    }

}
