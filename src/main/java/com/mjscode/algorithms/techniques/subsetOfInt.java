package com.mjscode.algorithms.techniques;

/**
 * 列举一个整型数的子集二进制子集
 * 例：对于整数-0011 0011 111，称 0000 0011 111是它的其中一个子集
 * @author binarySigh
 * @date 2021/3/8 23:20
 */
public class subsetOfInt {

    public static void main(String[] args){
        listSubsets(5);
    }

    /**
     * 列举整型数 i 的所有二进制子集,包括全集（即 i本身）和空集（也即 0）
     * @param i
     */
    public static void listSubsets(int i){
        int sub = i;
        do{
            System.out.println(sub);
            sub = (sub - 1) & i;
        } while(sub != i);
    }
}
