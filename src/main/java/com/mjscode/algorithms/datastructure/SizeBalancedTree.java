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
        int flag = 0;
        SBTNode added = new SBTNode(t);
        SBTNode cur = root;
        Stack<SBTNode> pathNodes = new Stack<>();
        while(cur != null){
            flag = compareTo(cur, t);
            cur.nodesCount++;
            pathNodes.push(cur);
            if(flag > 0){
                cur = (SBTNode)cur.left;
            } else {
                cur = (SBTNode)cur.right;
            }
        }
        pathNodes.push(added);
        while(!pathNodes.isEmpty()) {
            cur = pathNodes.pop();
            maintain(cur, pathNodes.isEmpty() ? null : pathNodes.peek());
        }
        return root;
    }

    public void remove(T t){

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

    private void maintain(SBTNode node, SBTNode father){
        int leftCounts = getCounts((SBTNode)node.left);
        int rightCounts = getCounts((SBTNode)node.right);
        SBTNode newNode = null;
        if(leftCounts > rightCounts){
            if(getCounts((SBTNode)(node.left.left)) > rightCounts){
                //LL型 - 对于右子节点而言，它的左侄子节点数大于了它
                // 直接将当前节点右旋
                newNode = rightRotate(node); // tmp为原来node节点的左孩子
                // 递归调用maintain,对调整过程中被影响到的节点进行再次检查调整（递归执行的顺序一定是从最下面的结点开始）
                maintain(node, newNode);
                maintain(newNode, father);
            } else if(getCounts((SBTNode)(node.left.right)) > rightCounts){
                //LR型 - 对于右子节点而言，它的右侄子节点数大于了它
                SBTNode preLeftSon = (SBTNode)node.left; //先记录下当前的左子节点
                // 先将左子节点左旋
                newNode = leftRotate(preLeftSon);
                // 串好指针
                node.left = newNode;
                // 再将 当前节点 node 右旋
                newNode = rightRotate(node);
                // 对调整过程中 受到影响的节点进行再次递归检查调整（受影响节点调整顺序 从最底层的结点开始调整，同层的无所谓先后）
                maintain(preLeftSon, newNode);
                maintain(node, newNode);
                maintain(newNode, father);
            }
        } else if(leftCounts < rightCounts){
            if(getCounts((SBTNode) node.right.right) > leftCounts){
                //RR型 - 对于左子节点而言，它的右侄子节点数大于了它
                // 直接将当前节点左旋
                newNode = leftRotate(node);
                maintain(node, newNode);
                maintain(newNode, father);
            } else if(getCounts((SBTNode) node.right.left) > leftCounts){
                //RL型 - 对于左子节点而言，它的左侄子节点数大于了它
                SBTNode preRightSon = (SBTNode) node.right;
                newNode = rightRotate(preRightSon);
                node.right = newNode;
                newNode = leftRotate(node);
                maintain(preRightSon, newNode);
                maintain(node, newNode);
                maintain(newNode, father);
            }
        }
        // 重新串指针的时候需要特别注意，如果newNode为空，说明没有进行平衡性调整，则串指针还是要以node节点来串
        newNode = newNode != null ? newNode : node;
        if(father == null){
            //说明是当前节点是根节点，将root重定位
            root = newNode;
        } else {
            // 串好替换上来的新节点和本棵子树父节点的指针关系
            int flag = compareTo(father, (T)newNode.val);
            if(flag > 0){
                father.left = newNode;
            } else {
                father.right = newNode;
            }
        }
    }

    /**
     * 将当前位置的节点左旋，并返回左旋操作后当前位置的新节点
     * @param node
     * @return
     */
    private SBTNode leftRotate(SBTNode node){
        SBTNode rightSon = (SBTNode)node.right;
        SBTNode tmp = (SBTNode)rightSon.left;
        node.right = tmp;
        rightSon.left = node;
        node.nodesCount = getCounts(node);
        rightSon.nodesCount = getCounts(rightSon);
        return rightSon;
    }

    /**
     * 将当前节点右旋，并返回右旋后当前位置新节点
     * @param node
     * @return
     */
    private SBTNode rightRotate(SBTNode node){
        SBTNode leftSon = (SBTNode)node.left;
        SBTNode tmp = (SBTNode)leftSon.right;
        node.left = tmp;
        leftSon.right = node;
        node.nodesCount = getCounts(node);
        leftSon.nodesCount = getCounts(leftSon);
        return leftSon;
    }

    /**
     * 从根节点开始查找元素t所在节点，并把沿途遍历到的节点（包括起讫节点）都加入队列中<BR/>
     *      若树中不存在t元素，则一直遍历到空节点，并将沿途节点都加入队列中（不包括空节点）<BR/>
     * @param t
     * @return
     */
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
