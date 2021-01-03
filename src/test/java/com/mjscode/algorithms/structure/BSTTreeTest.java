package com.mjscode.algorithms.structure;

import com.mjscode.algorithms.datastructure.AVLTree;
import com.mjscode.algorithms.datastructure.BinaryTree;
import org.junit.Test;

public class BSTTreeTest {

    @Test
    public void AVLTreeTest(){
        AVLTree<Integer> tree = new AVLTree<>();
        tree.add(13);
        tree.add(17);
        tree.add(12);
        tree.add(7);
        tree.add(5);
        tree.add(9);
        tree.add(4);
        tree.add(6);
        tree.add(8);
        tree.add(10);
        tree.add(11);
        tree.add(14);
        tree.add(19);
        tree.add(18);
        tree.add(20);
        System.out.println(tree.getInOrder());
        System.out.println(tree.contains(23));
        System.out.println(tree.contains(17));
        tree.remove(12);
        System.out.println(tree.getInOrder());
    }
}
