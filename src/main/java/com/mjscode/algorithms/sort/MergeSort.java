package com.mjscode.algorithms.sort;

/**
 * 归并排序
 * @author binarySigh
 */
public class MergeSort {

    /**
     * 递归方式实现的归并排序
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int[] mergeSort01(int[] arr, int l, int r) {
        int[] sorted = new int[r - l + 1];
        if(l == r) {
            sorted[0] = arr[l];
            return sorted;
        }
        int mid = l + ((r - l) >> 1);
        int[] left = mergeSort01(arr, l, mid);
        int[] right = mergeSort01(arr, mid + 1, r);
        sorted = merge(left, right);
        return sorted;
    }

    public static int[] merge(int[] left, int[] right) {
        int[] sorted = new int[left.length + right.length];
        int p = 0;
        int lp = 0;
        int rp = 0;
        while(lp < left.length && rp < right.length) {
            sorted[p++] = left[lp] <= right[rp] ? left[lp++] : right[rp++];
        }
        //右边数组拷贝完成的情况
        while(lp < left.length) {
            sorted[p++] = left[lp++];
        }
        //左边数组拷贝完成的情况
        while(rp < right.length) {
            sorted[p++] = right[rp++];
        }
        return sorted;
    }

    public static int[] mergeSort02(int[] arr) {
        int step = 1;
        int point = 0;
        while(step <= ((arr.length + 1) >> 1)) {
            while(point + step <= arr.length) {
                merge02(arr, point, step);
            }
        }
        return null;
    }

    public static void merge02(int[] arr, int point, int step) {
        int rightP = point + step;
        int mid = point + step;
        while(rightP < arr.length){
        }
    }
}
