package com.mjscode.algorithms.utils;

/**
 * 链表公共处理类
 *
 * @author binarySigh
 */
public class ListUtils{
    public static class Node{
        private int val;
        private Node next;

        public Node(int val){
            this.val = val;
        }

        public int val(){
            return this.val;
        }
    }
    /**
     * 按要求构建随机链表，并返回链表首节点<BR/>
     * 1. maxVal: 每个节点数据值范围[-maxVal,maxVal];<BR/>
     * 2. isLoop: 是否构建一个有环链表，true-是，false-否<BR/>
     * 3. outSize： 链表环外节点数<BR/>
     * 4. innerSize： 链表环内节点数<BR/>
     * 注：若isLoop==false,则构建无环链表，链表总节点数为 outSize + innerSize<BR/>
     * @param maxVal
     * @param outSize
     * @param innerSize
     * @param isLoop
     * @return
     */
    public static Node getCertainList(int maxVal, int outSize, int innerSize, boolean isLoop) {
        Node head = null;
        Node pre;
        Node cur = null;
        Node loopNode = null;
        for(int i = outSize; i > 0; i--){
            pre = cur;
            cur =  new Node(getRandomInt(maxVal));
            if(pre != null) {
                pre.next = cur;
            } else {
                //记录链表头结点
                head = cur;
            }
        }
        for(int i = innerSize; i > 0; i--){
            pre = cur;
            cur =  new Node(getRandomInt(maxVal));
            if(pre != null) { //若pre != null，说明第一段有节点，即头节点为第一段头节点
                pre.next = cur;
                if (i == innerSize){
                    //将环内首节点单独记录下来，后面制作有环链表需用到该节点的地址引用
                    loopNode = cur;
                }
            } else {//若pre == null，说明第一段无节点，即头节点为第二段首节点
                //记录链表头结点
                head = cur;
            }
        }
        //只有当两段不全为空的时候才有讨论是否创建有环链表的意义
        if(cur != null) {
            //根据 isLoop 决定是否创建有环链表
            cur.next = isLoop ? loopNode : null;
        }
        return head;
    }

    /**
     * 判断一个链表是否有环
     * @param head
     * @return
     */
    public static boolean isLoop(Node head){
        if(head == null || head.next == null || head.next.next == null) {
            return false;
        }
        //思路：定义快慢指针，当快慢指针相遇时，表明链表一定有环，相遇发生在环内
        Node slow = head.next;
        Node fast = head.next.next;
        while(slow != fast) {
            if(fast.next == null || fast.next.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 获取第一个入环节点；若链表无环则返回null
     * @param head
     * @return
     */
    public static Node getLoopNode(Node head) {
        Node loop = null;
        if(head == null || head.next == null || head.next.next == null){
            return loop;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while(slow != fast) {
            if(fast.next == null || fast.next.next == null) {
                return loop;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        //当跳出上述循环时，表明一定是有环链表，且快慢指针已经在环内某一节点相遇
        //此时将快指针重新指回头节点，与慢指针一道以相同速度(一次一步)向下走，当两个节点再次相遇时，则相遇节点即为该链表首次入环节点
        fast = head;
        while(fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        //当代码跳出上述循环时表明两个指针再次相遇，则按照上面内容说明此时相遇节点即为首次入环节点，返回其中一个即可
        return slow;
    }

    /**
     * 判断两个链表是否相交<BR/>
     * 注意：若两个链表相交，则这两个链表一定同为无环链表或同为有环链表，不存在其他情况<BR/>
     * @param head1
     * @param head2
     * @return
     */
    public static boolean isIntersect(Node head1, Node head2){
        if(head1 == null || head2 == null){
            return false;
        }
        boolean isIntersect = false;
        Node loopNode1 = getLoopNode(head1);
        Node loopNode2 = getLoopNode(head2);
        Node cur1;
        Node cur2;
        //两个无环链表的相交判定
        if(loopNode1 == null && loopNode2 == null){
            //无环链表是否相交只需要看两个链表尾节点是否相同即可
            //循环两个链表，得到两个尾节点，判定是否相同
            cur1 = head1;
            cur2 = head2;
            while(cur1.next != null){
                cur1 = cur1.next;
            }
            while(cur2.next != null){
                cur2 = cur2.next;
            }
            if(cur1 == cur2){
                isIntersect = true;
            }
        }
        //两个有环链表的相交判定
        if(loopNode1 != null && loopNode2 != null){
            //两个有环链表若相交，则必公用环内所有节点，因此只需判定其中一个链表的首次入环节点是否能在另一个链表的环结构内找到即可
            cur1 = loopNode1;
            cur2 = loopNode2;
            if(loopNode1 == loopNode2){
                return true;
            }
            //while循环表示在链表1的环结构内查找一圈，逐个节点与 loopNode2 比较是否相等
            while(cur1.next != loopNode1){
                if(cur1 == loopNode2){
                    isIntersect = true;
                    break;
                }
                cur1 = cur1.next;
            }
            //防止 链表1 环内只有一个点，此种情况会直接跳出上面的while，
            //   此时直接在下面while中判断 loopNode1 是否在链表2的环结构中
            while(isIntersect == false && cur2.next != loopNode2){
                if(cur2 == loopNode1){
                    isIntersect = true;
                    break;
                }
                cur2 = cur2.next;
            }
        }
        return isIntersect;
    }

    /**
     * 返回两个相交链表的首次相交节点。若不相交则返回null<BR/>
     * 注：若两链表相交，则必同为无环链表，或者同为有环链表，且为有环链表时必公用环内所有节点<BR/>
     * @param head1
     * @param head2
     * @return
     */
    public static Node getFirstIntersectNode(Node head1, Node head2){
        Node intersectNode = null;
        if(head1 == null || head2 == null){
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        Node cur1 = head1;
        Node cur2 = head2;
        //两个无环链表的情况
        if(loop1 == null && loop2 == null){
            int length1 = 1; //记录链表1长度
            int length2 = 1; //记录链表2长度
            while(cur1.next != null) {
                length1++;
                cur1 = cur1.next;
            }
            while(cur2.next != null){
                length2++;
                cur2 = cur2.next;
            }
            //尾节点都不相同说明两个链表不相交，返回null
            if(cur1 != cur2){
                return null;
            }
            //尾节点相同的情况的情况下说明有相交，则按以下逻辑查找第一个交点
            //将cur1 人为指向长链表；cur2指向短链表
            cur1 = length1 >= length2 ? head1 : head2;
            cur2 = length1 >= length2 ? head2 : head1;
            //长链表先走完多出的那部分，等剩余部分节点长度与短链表相同时，短链表也开始走，在节点相同时停下，此时该节点即为首次相交节点
            for(int i = Math.abs(length1 - length2); i > 0; i--){
                cur1 = cur1.next;
            }
            while(cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            //当代码退出上面的while循环，说明当前的 cur1 == cur2，即当前已遍历到首次相交节点；则随便返回哪个节点
            intersectNode = cur1;
        }
        //两个有环链表的首次相交节点查找
        if(loop1 != null && loop2 != null){
            //有环链表若相交，则情况有两种：首次相交节点在环外；首次相交节点在环内
            //两个链表在首次入环节点相交的情况在下述两种处理中都有被包含到，不会遗漏掉，所以代码中不做单独分类处理
            //若两个链表首次入环节点相同，则说明两个链表首次相交节点在环外（这里的环外包括首次入环节点）
            if(loop1 == loop2){
                //该情况可将两个链表的环外部分单独拆分看待，则将变成两个无环链表找首次相交节点的问题
                //此处可将上面的代码抽出一个独立方法，然后直接复用即可。这里重新写一遍只是为了练习code
                int length1 = 1;
                int length2 = 1;
                cur1 = head1;
                cur2 = head2;
                while(cur1 != loop1){
                    length1++;
                    cur1 = cur1.next;
                }
                while(cur2 != loop2){
                    length2++;
                    cur2 = cur2.next;
                }
                //将cur1 人为指向长链表，cur2人为指向短链表（这里的长短指的是两个有环链表的环外部分，这里的环外包括首次入环节点）
                cur1 = length1 >= length2 ? head1 : head2;
                cur2 = length1 >= length2 ? head2 : head1;
                for(int i = Math.abs(length1 - length2); i > 0; i--){
                    cur1 = cur1.next;
                }
                while(cur1 != cur2){
                    cur1 = cur1.next;
                    cur2 = cur2.next;
                }
                //退出上面while循环说明cur1和cur2都已经来到首次相交节点了，此时随便返回cur1/cur2即可（这里的随便是因为此时的cur1和cur2是同一个节点）
                intersectNode = cur1;
            }
            //两个链表首次入环节点不同，则说明两个链表如果相交，则首次相交节点必定出现在环内。
            if(loop1 != loop2){
                // 这种情况下只要能证明两个链表确实相交，就可以随机返回loop1或loop2都可以，因为此时loop1或loop2都是正确答案（这两个可能是不同节点，但是不影响）
                cur1 = loop1;
                cur2 = loop2;
                while(cur1.next != loop1){
                    if(cur1 == loop2){
                        //这里为了简略，人为限定有环链表于环内相交时返回链表1 的首次入环节点作为首次相交节点
                        //当然也可以做个随机数，根据其奇偶性随机返回链表1/链表2的首次入环节点作为首次相交节点
                        intersectNode = loop1;
                        break;
                    }
                    cur1 = cur1.next;
                }
                //防止 链表1 环内只有一个点，此种情况会直接跳出上面的while，
                //   此时直接在下面while中判断 loopNode1 是否在链表2的环结构中
                while(intersectNode == null && cur2.next != loop2){
                    if(cur2 == loop1){
                        //这里为了简略，人为限定有环链表于环内相交时返回链表1 的首次入环节点作为首次相交节点
                        //当然也可以做个随机数，根据其奇偶性随机返回链表1/链表2的首次入环节点作为首次相交节点
                        intersectNode = loop1;
                        break;
                    }
                    cur2 = cur2.next;
                }
            }
        }
        return intersectNode;
    }

    /**
     * 将两个无环单链表在随机节点相交<BR/>
     * isTailIntersect : 是否在尾节点相交<BR/>
     * 注：两个链表相交后重合部分取 head1 链表的交点后部分<BR/>
     * @param head1
     * @param head2
     * @param isTailIntersect
     */
    public static void intersectNonLoopList(Node head1, Node head2, boolean isTailIntersect) {
        //本方法仅做交叉，不考虑两个链表完全重合的情况，因此只要其中一个为空或仅有一个节点即不适用本方法做交叉
        //过滤掉两个链表有环的情况，防止下面代码因非法输入进入死循环
        if(head1 != null && head2 != null &&
                head1.next != null && head2.next != null &&
                (!isLoop(head1)) && (!isLoop(head2))) {
            int length1 = 0;
            int length2 = 0;
            Node cur = head1;
            //遍历获得两个链表长度
            while(cur != null) {
                length1++;
                cur = cur.next;
            }
            cur = head2;
            while(cur != null) {
                length2++;
                cur = cur.next;
            }
            //随机roll出两个链表交点位置，若指定在尾节点相交，则均为各自链表的总长度
            int intersectIndex1 = isTailIntersect ? length1 : getRandomPositive(length1);
            int intersectIndex2 = isTailIntersect ? length2 : getRandomPositive(length2);
            //用于在链表1中定位相交节点
            Node intersectNode = head1;
            //用于在链表2中找到交点位置的上一个节点，以便修改其next指向相交节点
            Node indexList2 = head2;
            //在链表1中定位到 intersectIndex1 位置的节点并记录
            for(int i = intersectIndex1 - 1; i > 0; i--) {
                intersectNode = intersectNode.next;
            }
            //在链表2中定位到第 intersectIndex2 个节点的上一个节点，并记录
            for(int i = intersectIndex2 - 1; i > 1; i--) {
                indexList2 = indexList2.next;
            }
            //修改链表2中第 intersectIndex2 个节点的上一个节点的next指针，使其指向链表1中 intersectIndex1 位置的节点
            indexList2.next = intersectNode;
        }
    }

    /**
     * 将两个有环单链表在（环内/环外）的随机节点相交<BR/>
     * isOutIntersect : 首个相交节点是否在环外。true-是，交点在环外；false-否，交点在环内<BR/>
     * 注：1. 人为规定若两个链表在入环处相交，也叫做在环内相交<BR/>
     *     2. 两个链表相交后重合部分取 head1 链表的交点后部分<BR/>
     * @param head1
     * @param head2
     * @param isOutIntersect
     */
    public static void intersectLoopList(Node head1, Node head2, boolean isOutIntersect) {
        Node loopNode1 = getLoopNode(head1);
        Node loopNode2 = getLoopNode(head2);
        Node cur1 = head1; //用于在链表1中记录相交节点
        int intersectIndex = 0; //交点在链表1中的相对位置
        int index = 0;
        Node cur2 = head2; //用于在链表2中定位到相交节点位置的上一节点
        //仅对两个有环链表进行操作
        if(loopNode1 != null && loopNode2!= null){
            //首先roll出 链表2中随机某个节点，记录下来
            //因为有环链表没有尾节点，所以随便roll一个数都行，这里人为指定最大值为6，防止随机值过大导致查找时浪费时间
            int index2 = getRandomPositive(6);
            for(int i = index2 - 1; i > 1; i--){
                cur2 = cur2.next;
            }
            //按要求在链表1中找到首个相交节点
            if(isOutIntersect) {
                //首个相交节点在环外的情况
                //获取链表1环外节点长度，用以roll随机节点位置
                while(cur1.next != loopNode1){
                    index++;
                    cur1 = cur1.next;
                }
                intersectIndex = getRandomPositive(index);
                cur1 = head1;
                for(int i = intersectIndex - 1; i > 0; i--) {
                    cur1 = cur1.next;
                }
                //将链表2中指定节点的next指针重定向到链表中的cur1节点上，至此全部完成两个有环链表的环外相交操作
                cur2.next = cur1;
            } else {
                //首个相交节点在环内的情况
                //链表1中从 首次入环节点开始往后第index个节点作为相交节点，这里index因为在环内，所以随便给个值都行，依然为了方便限定最大值为6
                index = getRandomPositive(6);
                //定位到首次入环节点往后的第index个节点，并记录
                cur1 = loopNode1;
                for(int i = intersectIndex - 1; i > 0; i--){
                    cur1 = cur1.next;
                }
                cur2.next = cur1;
            }
        }
    }

    /**
     * 获得随机整数，范围[-maxVal,maxVal]
     * @param maxVal
     * @return
     */
    public static int getRandomInt(int maxVal){
        return (int)(Math.random() * maxVal + 1) - (int)(Math.random() * maxVal + 1);
    }

    /**
     * 随机返回一个正整数，范围[2，maxVal]<BR/>
     * 即 相交节点至少是各自链表的第二个节点，否则链表就会重合<BR/>
     * @param maxVal
     * @return
     */
    public static int getRandomPositive(int maxVal) {
        int random = 0;
        do {
            random = (int) (Math.random() * maxVal + 1);
        } while(random < 2);
        return random;
    }
}



