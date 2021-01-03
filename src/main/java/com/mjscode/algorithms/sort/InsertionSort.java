package com.mjscode.algorithms.sort;

/**
 * 插入排序（经典插入排序）<BR/>
 * 过程：<BR/>
 * 想让arr[0~0]上有序，这个范围只有一个数，当然是有序的。<BR/>
 * 想让arr[0~1]上有序，所以从arr[1]开始往前看，如果{@code arr[1]<arr[0]}，就交换。否则什么也不做。<BR/>
 * …<BR/>
 * 想让arr[0~i]上有序，所以从arr[i]开始往前看，arr[i]这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。<BR/>
 * 最后一步，想让arr[0~N-1]上有序， arr[N-1]这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。<BR/>
 *
 * @author binarySigh
 */
public class InsertionSort {

    public static int[] sort(int[] arr) {
        if(arr == null || arr.length <= 1) {
            System.out.println("输入数组为空或长度不满足排序要求!");
            return null;
        } else {
            int tmp = 0;
            for (int i = 1; i < arr.length; i++) {
                for (int j = i - 1; j > 0 && arr[j] > arr[j + 1]; j--) {
                    Swap2(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    /**
     * 两个数交换--常规方法<BR/>
     * @param i
     * @param j
     */
    public static void Swap(int[] arr, int i, int j) {
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    /**
     * 两个数交换--异或写法<BR/>
     * 优点：不需申请额外空间<BR/>
     * 注意：传入的 i 和 j 一定要不相同才可以，否则a[i]和a[j]运算结束都变为0，而不是互相交换<BR/>
     * @param i
     * @param j
     */
    public static void Swap2(int[] arr, int i, int j) {
        arr[i] = arr[j] ^ arr[i];
        arr[j] = arr[j] ^ arr[i];
        arr[i] = arr[j] ^ arr[i];
    }
}
