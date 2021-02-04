package com.mjscode.algorithms.sort;

public class QuickSort {

    /**
     *
     * 实现思路：递归
     * 时间复杂度 O（N^2）
     * 初始数组中选最后一个数做基准，小于的放右边，等于的放中间，大于的放左边，然后去左右两个子数组中递归
     * 递归时传入的是
     */
    public static void quickSortV1(){


    }

    /**
     *
     * 实现思路：递归
     * 时间复杂度 O（N^2）
     * 初始数组中选最后一个数做基准，小于的放右边，等于的放中间，大于的放左边，然后去左右两个子数组中递归
     * 子数组递归处理逻辑跟主数组一样，子数组内选最右元素做划分基准
     * 递归时，传入等于区域的左右边界值
     */
    public static void quickSortV2(){


    }

    /**
     * 随机快排，即常规情况下提到的快排。上述快排方案仅为演化过程中的中间方案
     * 时间复杂度 O（NlogN）
     * 初始数组中随机选一个数与数组最右数做交换，并作为划分基准，小于的放右边，等于的放中间，大于的放左边，然后去左右两个子数组中递归
     * 子数组递归处理逻辑跟主数组一样，子数组内随机选一个数与数组最右数做交换，并作为划分基准
     * 递归时，传入等于区域的左右边界值
     */
    public static void quickSort(int[] arr, int L, int R){
        if(L == R){
            return;
        }
        //随机选择一个数，将他和R位置交换，并且在本过程中作为比较基准数
        int ran = (int)(Math.random() * (R - L)) + L;
        swap(arr, ran, R);
        //小于区的右边界
        int i = L - 1;
        //大于区的左边界
        int j = R;
        //全程遍历指针
        int index = L;
        while(index < j){
            if(arr[index] < arr[R]){
                swap(arr, index++, ++i);
            } else if(arr[index] > arr[R]){
                swap(arr, index, --j);
            } else {
                index++;
            }
        }
        //不能忘了把R位置的比较基准数换到等于区下一个位置，也就是大于区第一个位置，也就是j位置
        swap(arr, j, R);
        if(i >= L){
            quickSort(arr, L, i);
        }
        if(j + 1 <= R){
            quickSort(arr, j + 1, R);
        }
    }

    public static void swap(int[] arr, int i, int j){
        if(arr[i] == arr[j]){
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}
