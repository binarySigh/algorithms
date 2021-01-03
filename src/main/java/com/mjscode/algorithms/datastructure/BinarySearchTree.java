package com.mjscode.algorithms.datastructure;

import java.util.Comparator;

/**
 * @author binarySigh
 */
abstract class BinarySearchTree<T> {
    protected T val;
    protected BinarySearchTree left;
    protected BinarySearchTree right;

    public BinarySearchTree(T t) {
        this.val = t;
        this.left = null;
        this.right = null;
    }

    /**
     * 中序遍历
     */
    protected abstract String showOrder();
}
