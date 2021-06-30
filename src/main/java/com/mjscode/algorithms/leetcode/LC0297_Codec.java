package com.mjscode.algorithms.leetcode;

import java.util.Stack;

/**
 * //序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
 * //式重构得到原数据。
 * //
 * // 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
 * //反序列化为原始的树结构。
 * //
 * // 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的
 * //方法解决这个问题。
 * //
 * // 示例 1：
 * //
 * //输入：root = [1,2,3,null,null,4,5]
 * //输出：[1,2,3,null,null,4,5]
 * //
 * // 示例 2：
 * //
 * //输入：root = []
 * //输出：[]
 * //
 * // 示例 3：
 * //
 * //输入：root = [1]
 * //输出：[1]
 * //
 * // 示例 4：
 * //
 * //输入：root = [1,2]
 * //输出：[1,2]
 * //
 * // 提示：
 * //
 * // 树中结点数在范围 [0, 104] 内
 * // -1000 <= Node.val <= 1000
 * //
 * // Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树
 * @author binarySigh
 * @date 2021/6/30 21:05
 */
public class LC0297_Codec {

    /**
     * 解答成功:
     * 		执行耗时:12 ms,击败了78.36% 的Java用户
     * 		内存消耗:39.6 MB,击败了97.63% 的Java用户
     * @param args
     */
    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(12);
        root.right = new TreeNode(-13);
        root.right.left = new TreeNode(-24);
        root.right.right = new TreeNode(25);

        String s1 = serialize(root);
        TreeNode newRoot = deserialize(s1);
        String s2 = serialize(newRoot);
        System.out.println(s1);
        System.out.println(s2);
    }

    /**
     * 先序方式序列化
     * @param root
     * @return
     */
    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            if(cur != null){
                sb.append(cur.val).append(',');
                stack.push(cur.right);
                stack.push(cur.left);
            } else {
                sb.append('#').append(',');
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 按照先序序列化结果进行反序列化
     * @param data
     * @return
     */
    public static TreeNode deserialize(String data) {
        if(data == null || data.length() == 0){
            return null;
        }
        return restoreTree(data, 0).root;
    }

    private static Struct restoreTree(String data, int index){
        if(data.charAt(index) == '#'){
            return new Struct(null, index + 2);
        }
        int val = 0;
        boolean flag = data.charAt(index) == '-';
        if(flag){
            index++;
        }
        while(data.charAt(index) != ','){
            val = val * 10 + (data.charAt(index++) - '0');
        }
        val = flag ? (val * -1) : val;
        TreeNode root = new TreeNode(val);
        Struct leftSide = restoreTree(data, index + 1);
        root.left = leftSide.root;
        Struct rightSide = restoreTree(data, leftSide.index);
        root.right = rightSide.root;
        return new Struct(root, rightSide.index);
    }

    private static class Struct{
        TreeNode root;
        int index;
        public Struct(){}
        public Struct(TreeNode r, int i){
            this.root = r;
            this.index = i;
        }
    }

    public static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
   }
}
