package com.mjscode.algorithms.datastructure;

import java.util.Comparator;
import java.util.Stack;

/**
 * SizeBalancedTree
 *      不允许重复节点
 * @author binarySigh
 */
public class SizeBalancedTree<T> {
    private SBTNode root;
    private final Comparator<T> comparator;

    public SizeBalancedTree(){this.comparator = null;}

    public SizeBalancedTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }


    public SBTNode add(T t){
        if(root == null){
            root = new SBTNode(t);
            return root;
        }
        if(isContains(t)){
            System.out.println("Element exists!");
            return root;
        }
        SBTNode added = new SBTNode(t);
        SBTNode cur = null;
        Stack<SBTNode> pathNodes = findPath(t);
        return root;
    }

    private void maintain(SBTNode node, SBTNode fatherNode){

    }

    private Stack<SBTNode> findPath(T t){
        if(root == null || t == null){
            return null;
        }
        Stack<SBTNode> pathNodes = new Stack<>();
        SBTNode node = root;
        int flag = 0;
        while(node != null){
            pathNodes.push(node);
            flag = compareTo(node, t);
            if(flag > 0){
                node = (SBTNode)node.left;
            } else if(flag < 0){
                node = (SBTNode)node.right;
            } else {
                break;
            }
        }
        return pathNodes;
    }

    /**
     * 判断树中是否含有元素 t
     * @param t
     * @return
     */
    public boolean isContains(T t){
        if(root == null){
            return false;
        }
        BinarySearchTree node = root;
        int flag = 0;
        while(node != null) {
            flag = compareTo((SBTNode) node, t);
            if(flag > 0) {
                node = node.left;
            } else if(flag < 0){
                node = node.right;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据比较规则判断当前节点元素值与目标元素值的大小关系<BR/>
     *  >0  表示当前节点比目标值大<BR/>
     *  ==0 表示当前节点跟目标值相同<BR/>
     *  <0  表示当前节点比目标值小<BR/>
     * @param node 当前节点
     * @param t 目标元素
     * @return
     */
    private int compareTo(SBTNode node, T t){
        if(comparator != null){
            return comparator.compare((T)node.val, t);
        } else {
            Comparable<? super T> key = (Comparable<? super T>)node.val;
            return key.compareTo(t);
        }
    }

    /**
     * 获取以node节点为头结点的整棵子树的节点数
     * @param node
     * @return
     */
    private int getCounts(SBTNode node){
        if(node == null){
            return 0;
        }
        int left = node.left == null ? 0 : ((SBTNode)node.left).nodesCount;
        int right = node.right == null ? 0 : ((SBTNode)node.right).nodesCount;
        return left + 1 + right;
    }

    private class SBTNode extends BinarySearchTree{
        private int nodesCount;
        private SBTNode(T t){
            super(t);
            this.nodesCount = 1;
        }
        @Override
        protected String showOrder() {
            return null;
        }
    }
}
