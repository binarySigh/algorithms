package com.mjscode.algorithms.structure;

import com.mjscode.algorithms.datastructure.BinaryTree;
import com.mjscode.algorithms.utils.TreeNode;
import org.junit.Test;

import java.util.HashSet;

public class TreeTest {

    @Test
    public void test01(){
        //TreeNode head2 = TreeNode.constructBinaryTree(200,3,null,false);
        HashSet<Integer> set = new HashSet<Integer>();
        TreeNode head = TreeNode.constructBinaryTree(200,3, set,false);
        //String tree = TreeNode.showTreeNodeByCertainOrder(head, "pre");
        //String tree1 = TreeNode.showListInString(BinaryTree.showByPreOrder(head));
       /* String tree = TreeNode.showTreeNodeByCertainOrder(head, "pos");
        String tree1 = TreeNode.showListInString(BinaryTree.showByPosOrder(head));*/
        String tree = TreeNode.showTreeNodeByCertainOrder(head, "in");
        String tree1 = TreeNode.showListInString(BinaryTree.showByInOrder(head));
        System.out.println(tree);
        System.out.println(tree1);
    }

    @Test
    public void test02(){
        Integer i = null;
        System.out.println(i);
        System.out.println(Integer.MIN_VALUE + (-2));
    }
}
