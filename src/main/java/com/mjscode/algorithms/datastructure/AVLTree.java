package com.mjscode.algorithms.datastructure;

import java.util.Comparator;
import java.util.Stack;

/**
 * AVL树
 *     不允许重复节点
 * @author binarySigh
 */
public class AVLTree<T> {
    private AVLNode root;
    private final Comparator<? super T> comparator;

    public AVLTree(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }
    public AVLTree(){
        this.comparator = null;
    }

    /**
     * 添加节点，并保持树结构及平衡
     * @param t
     * @return
     */
    public AVLNode add(T t){
        if(root == null){
            root = new AVLNode(t);
            return root;
        }
        if(contains(t)){
            System.out.println("Element exists!");
            return root;
        }
        Stack<AVLNode> stack = new Stack<>();
        AVLNode add = new AVLNode(t);
        AVLNode pre = root;
        AVLNode cur = root;
        int flag = 0;
        while(cur != null){
            pre = cur;
            stack.push(pre);
            flag = compareTo(cur, t);
            cur = flag > 0 ? (AVLNode)cur.left : (AVLNode)cur.right;
        }
        if(flag > 0){
            pre.left = add;
        } else {
            pre.right = add;
        }
        stack.push(add);
        while(!stack.isEmpty()){
            cur = stack.pop();
            cur.height = getHeight(cur);
            //维持平衡和各节点高度
            maintain(cur, stack.isEmpty() ? null : stack.peek());
        }
        return root;
    }

    /**
     * 删除指定节点
     * @param t
     * @return
     */
    public AVLNode remove(T t){
        Stack<AVLNode> pathNodes = findPath(t);
        Stack<AVLNode> reHeightNodes = new Stack<>();
        if(root == null || pathNodes == null){
            System.out.println("Element not exists!");
            return null;
        }
        AVLNode deleted = pathNodes.pop();
        AVLNode delFather = pathNodes.isEmpty() ? null : pathNodes.peek();
        int flag = 0;
        AVLNode cur = null;
        AVLNode pre = null;
        if(deleted.left == null && deleted.right == null){
            //被删除的正好是叶节点，那么直接删除即可
            if(delFather == null){
                //被删除的节点刚好是整棵树唯一的节点
                root = null;
                return deleted;
            } else {
                //将删除节点 父节点的对应指针置空
                flag = compareTo(delFather, (T)cur.val);
                if(flag > 0){
                    delFather.left = null;
                } else {
                    delFather.right = null;
                }
            }
        } else if(deleted.right != null){
            // 右子树不为空，找后继节点顶替待删除节点
            pre = deleted;
            cur = (AVLNode)pre.right;
            while(cur.left != null){
                pre = cur;
                reHeightNodes.push(pre);
                cur = (AVLNode)cur.left;
            }
            if(pre != deleted) {
                // 找到的后继节点不是 待删除节点的右子节点
                //将找到的后继节点的右子树移交给自己的父节点
                pre.left = cur.right;
                // 用后继节点顶替待删除节点
                cur.right = deleted.right;
                cur.left = deleted.left;
            } else {
                //找到的后继节点正好是待删除节点的右子节点
                // 直接用右子节点顶替自己位置
                cur.left = deleted.left;
            }
            // 将待删除节点到后继节点之间的沿途节点重新计算高度信息
            while(!reHeightNodes.isEmpty()){
                pre = reHeightNodes.pop();
                pre.height = getHeight(pre);
            }
        } else {
            // 如果待删除节点整棵右子树为空，则直接用左子树顶替自己
            cur = (AVLNode)deleted.left;
        }
        // 将cur 压入 pathNodes，以便遍历pathNodes完成整条路径上的 rebalance
        // 这里顶替上来的新节点和delFather之间的关系会在maintain中重新串联好，因此这里只把顶替节点压入pathNodes即可
        pathNodes.push(cur);
        // 从cur节点开始 往上查找并重新计算高度以及rebalance
        while(!pathNodes.isEmpty()){
            cur = pathNodes.pop();
            cur.height = getHeight(cur);
            //维持平衡和各节点高度
            maintain(cur, pathNodes.isEmpty() ? null : pathNodes.peek());
        }
        // 释放待删除节点的 左右孩子指针,并将删除节点返回
        deleted.left = null;
        deleted.right = null;
        return deleted;
    }

    /**
     * 返回从根节点到要查找元素节点之间所有节点组成的栈，包括起讫节点
     * @param t
     * @return
     */
    private Stack<AVLNode> findPath(T t){
        if(root == null || t == null){
            return null;
        }
        Stack<AVLNode> stack = new Stack<>();
        AVLNode cur = root;
        int flag = 0;
        while(cur != null){
            flag = compareTo(cur, t);
            stack.push(cur);
            if(flag == 0){
                return stack;
            }
            cur = flag > 0 ? (AVLNode)cur.left : (AVLNode)cur.right;
        }
        return null;
    }

    /**
     * 判断元素 t 是否已在树中<BR/>
     *   这里的判定存在与否只根据定义的比较器的结果，不看是否是相同对象<BR/>
     * @param t
     * @return
     */
    public boolean contains(T t){
        if(root == null || t == null){
            return false;
        }
        AVLNode cur = root;
        int flag = 0;
        while(cur != null){
            flag = compareTo(cur, t);
            if(flag == 0){
                return true;
            }
            cur = flag > 0 ? (AVLNode)cur.left : (AVLNode)cur.right;
        }
        return false;
    }

    /**
     * 获取整棵树头节点信息
     * @return
     */
    public AVLNode root(){
        return root;
    }

    /**
     * 中序遍历整棵树
     * @return
     */
    public String getInOrder(){
        String str = root.showOrder();
        return str;
    }

    /**
     * 判断当前节点元素值比目标小还是大<BR/>
     *      负数 - 比目标小<BR/>
     *      正数 - 比目标大<BR/>
     *        0 - 与目标相等<BR/>
     * @param cur 当前节点
     * @param t 目标元素值
     * @return
     */
    private int compareTo(AVLNode cur, T t){
        int flag = 0;
        if(comparator != null){
            flag = comparator.compare((T)cur.val, t);
        } else {
            Comparable<? super T> key = (Comparable<? super T>) cur.val;
            flag = key.compareTo(t);
        }
        return flag;
    }

    /**
     * 检查并维持树的平衡；并维护好树结构和高度信息
     * @param node 需要进行rebalance的节点
     * @param father 待rebalance节点的父节点。若待rebalance节点为头节点，则该参数传空
     * @return
     */
    private AVLNode maintain(AVLNode node, AVLNode father){
        boolean isRoot = father == null;
        AVLNode newNode = node;
        //处理LL,LR  RR,RL四种情况下的rebalance策略
        //如果同时满足LL,LR一定要按照LL型来处理；RR/RL也同理。因此每小组内部优先处理情况LL或RR
        if(getHeight(node.left) - getHeight(node.right) > 1){
            if(getHeight(node.left.left) > getHeight(node.left.right)) {
                //LL型,直接将当前节点右旋
                newNode = rightRotate(node);
            } else if(getHeight(node.left.left) < getHeight(node.left.right)){
                //LR型,先将cur.left左旋，再将cur右旋
                newNode = leftRotate(node.left);
                //第一步旋转后重新串指针，保证树结构不乱
                node.left = newNode;
                //第二次旋转，将node节点右旋
                newNode = rightRotate(node);
            }
        } else if(getHeight(node.right) - getHeight(node.left) > 1){
            if(getHeight(node.right.right) > getHeight(node.right.left)){
                //RR型，直接左旋node节点
                newNode = leftRotate(node);
            } else if(getHeight(node.right.right) < getHeight(node.right.left)){
                //RL型，先将node.right右旋，再将node左旋
                newNode = rightRotate(node.right);
                //第一次旋转后重新串好指针，防止树结构混乱
                node.right = newNode;
                //第二步左旋node
                newNode = leftRotate(node);
            }
        }
        //重新串好当前位置新节点和父节点的指针关系
        if(isRoot){
            //当前位置已是头节点,重新串好头节点，防止整棵树结构丢失
            root = newNode;
            return root;
        } else {
            if(compareTo(father, (T)newNode.val) > 0){
                father.left = newNode;
            } else {
                father.right = newNode;
            }
        }
        return newNode;
    }

    /**
     * 将当前节点进行左旋
     * @param node
     * @return
     */
    private AVLNode leftRotate(BinarySearchTree node){
        AVLNode rightSon = (AVLNode)node.right;
        AVLNode tmp = (AVLNode)rightSon.left;
        rightSon.left = node;
        node.right = tmp;
        ((AVLNode)node).height = getHeight(node);
        rightSon.height = getHeight(rightSon);
        return rightSon;
    }

    /**
     * 将当前节点进行右旋
     * @param node
     * @return
     */
    private AVLNode rightRotate(BinarySearchTree node){
        AVLNode leftSon = (AVLNode)node.left;
        AVLNode tmp = (AVLNode)leftSon.right;
        leftSon.right = node;
        node.left = tmp;
        ((AVLNode)node).height = getHeight(node);
        leftSon.height = getHeight(leftSon);
        return leftSon;
    }

    /**
     * 获取以当前节点为头结点的子树的高度
     * @param node
     * @return
     */
    private int getHeight(BinarySearchTree node){
        if(node == null){
            return 0;
        }
        int leftHeight = node.left != null ? ((AVLNode)node.left).height : 0;
        int rightHeight = node.right != null ? ((AVLNode)node.right).height : 0;
        return leftHeight >= rightHeight ? leftHeight + 1 : rightHeight + 1;
    }

    private class AVLNode extends BinarySearchTree{
        private int height;

        private AVLNode(T t){
            super(t);
            height = 1;
        }

        @Override
        protected String showOrder() {
            String str = BinaryTree.showBinarySearchTree(this);
            return str;
        }
    }
}
