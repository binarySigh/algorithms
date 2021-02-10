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

    public SBTNode remove(T t){
        Stack<SBTNode> pathNodes = findPath(t);
        if(pathNodes.isEmpty() || compareTo(pathNodes.peek(), t) != 0){
            //整棵树为空，或者树中没有t元素的节点
            System.out.println("Element not exists!");
            return null;
        }
        SBTNode delNode = pathNodes.pop();
        Stack<SBTNode> nextPaths = findNext(delNode);
        if(nextPaths.isEmpty()){
            //待删除节点没有右子树，或者待删除节点根本就是叶子节点
            if(delNode.left != null){
                //待删除节点只是没有右子树。将其左子节点压栈，等待后续maintain过程维护好树关系和平衡
                pathNodes.push((SBTNode) delNode.left);
            } else {
                //待删除节点恰好是叶节点
                if(pathNodes.isEmpty()){
                    //待删除节点恰好也是头节点。则说明该树当前仅有一个待删除节点
                    this.root = null;
                    return delNode;
                } else {
                    //待删除节点不是头节点，那就直接删除，将其父节点相应位置指针置空
                    if(pathNodes.peek().left == delNode)
                        pathNodes.peek().left = null;
                    else
                        pathNodes.peek().right = null;
                }
            }
        } else if(nextPaths.peek() == delNode.right){
            //如果找到的后继节点就是待删除节点的右子节点，那么直接顶上然后rebalance即可
            nextPaths.peek().left = delNode.left;
            //将顶上来的节点压回去，以便后续平衡性调整
            pathNodes.push(nextPaths.pop());
        } else {
            SBTNode next = nextPaths.pop();
            nextPaths.peek().left = next.right;
            next.right = delNode.right;
            next.left = delNode.left;
            pathNodes.push(next);
        }
        //对沿途受到影响的节点从下至上逐一进行平衡性检查和调整
        SBTNode cur = null;
        while(!nextPaths.isEmpty()){
            cur = nextPaths.pop();
            maintain(cur, nextPaths.isEmpty() ? (pathNodes.isEmpty() ? null : pathNodes.peek()) : nextPaths.peek());
        }
        while(!pathNodes.isEmpty()){
            cur = pathNodes.pop();
            maintain(cur, pathNodes.isEmpty() ? null : pathNodes.peek());
        }
        //解除被删除节点的引用关系,方便垃圾回收
        delNode.right = null;
        delNode.left = null;
        return delNode;
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
     * 寻找删除结节点的后继节点（这里是指以删除节点为头的子树中的后继）<BR/>
     * 结果中包括后继节点，但不包括待删除节点<BR/>
     * @param node
     * @return
     */
    private Stack<SBTNode> findNext(SBTNode node){
        Stack<SBTNode> nextPaths = new Stack<>();
        if(node.right == null){
            return nextPaths;
        }
        SBTNode cur = (SBTNode)node.right;
        nextPaths.push(cur);
        while(cur.left != null){
            cur = (SBTNode)cur.left;
            nextPaths.push(cur);
        }
        return nextPaths;
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
