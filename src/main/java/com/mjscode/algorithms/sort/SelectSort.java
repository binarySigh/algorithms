package com.mjscode.algorithms.sort;

/**
 * 选择排序<BR/>
 *过程：<BR/>
 * arr[0～N-1]范围上，找到最小值所在的位置，然后把最小值交换到0位置。<BR/>
 * arr[1～N-1]范围上，找到最小值所在的位置，然后把最小值交换到1位置。<BR/>
 * arr[2～N-1]范围上，找到最小值所在的位置，然后把最小值交换到2位置。<BR/>
 * …<BR/>
 * arr[N-1～N-1]范围上，找到最小值位置，然后把最小值交换到N-1位置。<BR/>
 *
 * @author binarySigh
 */
public class SelectSort {

    /**
     * 选择排序
     * @param arr
     */
    public static int[] sort(int[] arr){
        if(arr == null || arr.length <= 1){
            System.out.println("输入数组为空或长度不满足排序要求!");
            return null;
        } else {
            int curMinIndex = 0;
            for (int i = 0; i < arr.length; i++) {
                //for循环遍历arr[i] ~ arr[n],查找并记录这些数之间的最小值的下标
                for (int j = i; j < arr.length; j++) {
                    if (arr[curMinIndex] > arr[j]) {
                        curMinIndex = j;
                    }
                }
                //只有当找到的最小值的下标与当前循环起始下标不一致的时候才进行交换，
                //否则说明arr[i~n-1]上的最小值就是起始值arr[i],就无需交换
                //另外若在 i = curMinIndex 时进行异或运算，本身也是有问题的
                if(curMinIndex > i) {
                    Swap(arr, i, curMinIndex);
                }
                curMinIndex = i + 1;
            }
        }
        return arr;
    }

    /**
     * 两个数交换--异或写法<BR/>
     * 优点：不需申请额外空间<BR/>
     * 注意：传入的 i 和 j 一定要不相同才可以，否则a[i]和a[j]运算结束都变为0，而不是互相交换<BR/>
     * @param i
     * @param j
     */
    public static void Swap(int[] arr, int i, int j) {
        arr[i] = arr[j] ^ arr[i];
        arr[j] = arr[j] ^ arr[i];
        arr[i] = arr[j] ^ arr[i];
    }
}
