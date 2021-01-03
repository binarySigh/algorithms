package com.mjscode.algorithms.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 普通二叉树公共类
 * @author binarySigh
 */
public class TreeNode {
    private int val;
    private TreeNode left;
    private TreeNode right;

    public TreeNode (){}

    private TreeNode (int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public int val(){
        return this.val;
    }

    public TreeNode left(){
        return this.left;
    }

    public TreeNode right(){
        return this.right;
    }

    /**
     * 创建一棵随机 满二叉树/普通二叉树<BR/>
     * 设定各节点元素不重复是为了仅从二叉树打印信息判断是否是同一棵树，而不必关心其节点内存地址<BR/>
     * @param maxVal 节点值范围[-maxVal,maxVal]
     * @param maxLevel 树最大层数
     * @param set 防止生成重复元素的集合,用户输入可为null
     * @param isFull 是否生成满二叉树；true-是，false-否
     * @return
     */
    public static TreeNode constructBinaryTree(int maxVal, int maxLevel, HashSet set, boolean isFull){
        //前面的条件是为了防止用户异常输入；后一个条件是防止set中已有元素达到所有可用元素数量的 3/4，降低do-while效率
        if(maxLevel <= 0 || (set != null && set.size() >= maxVal * 1.5)){
            return null;
        }
        if(set == null){
            set = new HashSet<Integer>();
        }
        int curNodeVal;
        do {
            curNodeVal = getRandomVal(maxVal);
        } while(set.contains(curNodeVal));
        TreeNode now = new TreeNode(curNodeVal);
        set.add(curNodeVal);
        if(isFull){
            now.left = constructBinaryTree(maxVal, maxLevel - 1, set, isFull);
            now.right = constructBinaryTree(maxVal, maxLevel - 1, set, isFull);
        } else {
            int leftNull = (int)(Math.random() * 19 + 1);
            int rightNull = (int)(Math.random() * 19 + 1);
            int leftOrRight = (int)(Math.random() * 19 + 1);
            // 4/5 概率为其创建左子节点，1/5 概率维持其左子节点为null
            if(leftNull % 5 != 4){
                now.left = constructBinaryTree(maxVal, maxLevel - 1, set, isFull);
            }
            // 4/5 概率为其创建右子节点，1/5 概率维持其右子节点为null
            if(rightNull % 5 != 4){
                now.right = constructBinaryTree(maxVal, maxLevel - 1, set, isFull);
            }
            //若左右子节点均为null,且当前不是最后一层，则根据 leftOrRight 奇偶性强制创建出 左/右子节点
            if(now.left == null && now.right == null && maxLevel >= 0){
                if(leftOrRight % 2 == 1){
                    now.left = constructBinaryTree(maxVal, maxLevel - 1, set, isFull);
                } else {
                    now.right = constructBinaryTree(maxVal, maxLevel - 1, set, isFull);
                }
            }
        }
        return now;
    }

    /**
     * 按照 先序/中序/后序 的遍历方式打印二叉树
     * @param head 二叉树头节点
     * @param Order 遍历方式：pre-先序；in-中序；pos-后续
     * @return
     */
    public static String showTreeNodeByCertainOrder(TreeNode head, String Order){
        if(head == null){
            return "[]";
        }
        if(head.left == null && head.right == null){
            return "[" + head.val + "]";
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        if("pre".equalsIgnoreCase(Order)){
            showNodeByPreOrder(head, list);
        } else if("in".equalsIgnoreCase(Order)){
            showNodeByInOrder(head, list);
        } else if("pos".equalsIgnoreCase(Order)){
            showNodeByPosOrder(head, list);
        } else {
            System.out.println("不支持的二叉树遍历方式！");
            return null;
        }
        String res = showListInString(list);
        return res;
    }

    /**
     * 递归法 先序遍历二叉树，并记录其节点值
     * @param head
     * @param list
     * @return
     */
    public static ArrayList<Integer> showNodeByPreOrder(TreeNode head, ArrayList<Integer> list){
        if(head == null){
            list.add(null);
            return list;
        }
        list.add(head.val);
        list = showNodeByPreOrder(head.left, list);
        list = showNodeByPreOrder(head.right, list);
        return list;
    }

    /**
     * 递归法 中序遍历二叉树，并记录其节点值
     * @param head
     * @param list
     * @return
     */
    public static ArrayList<Integer> showNodeByInOrder(TreeNode head, ArrayList<Integer> list){
        if(head == null){
            list.add(null);
            return list;
        }
        list = showNodeByInOrder(head.left, list);
        list.add(head.val);
        list = showNodeByInOrder(head.right, list);
        return list;
    }

    /**
     * 递归法 后序遍历二叉树，并记录其节点值
     * @param head
     * @param list
     * @return
     */
    public static ArrayList<Integer> showNodeByPosOrder(TreeNode head, ArrayList<Integer> list){
        if(head == null){
            list.add(null);
            return list;
        }
        list = showNodeByPosOrder(head.left, list);
        list = showNodeByPosOrder(head.right, list);
        list.add(head.val);
        return list;
    }

    public static String showListInString(List list){
        if(list == null || list.size() == 0){
            return "[]";
        }
        String res = "[";
        for(int i = 0; i < list.size(); i++){
            res += list.get(i) == null ? "null," : list.get(i) + ",";
        }
        res = res.substring(0, res.length() - 1) + "]";
        return res;
    }

    public static int getRandomVal(int maxVal){
        return (int)(Math.random() * maxVal + 1) - (int)(Math.random() * maxVal + 1);
    }
}
