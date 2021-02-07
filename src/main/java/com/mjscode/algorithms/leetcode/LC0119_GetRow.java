package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * //给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * //
 * // 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * //
 * // 示例:
 * //
 * // 输入: 3
 * //输出: [1,3,3,1]
 * //
 * // 进阶：
 * //
 * // 你可以优化你的算法到 O(k) 空间复杂度吗？
 * // Related Topics 数组
 * @author binarySigh
 */
public class LC0119_GetRow {

    public static void main(String[] args){
        System.out.println(getRow(0));
    }

    /**
     * 注意这里的行数是从 0 开始计数的
     * 解答成功:
     * 		执行耗时:1 ms,击败了83.83% 的Java用户
     * 		内存消耗:36 MB,击败了89.73% 的Java用户
     * @param rowIndex
     * @return
     */
    public static List<Integer> getRow(int rowIndex) {
        if(rowIndex < 0){
            return new ArrayList<>();
        }
        int upperLeft = 0;
        int upperRight = 0;
        Integer[] row = new Integer[rowIndex + 1];
        for(int i = 1; i <= rowIndex + 1; i++){
            for(int j = 0; j < i; j++){
                if(j == 0 || j == i - 1){
                    row[j] = 1;
                    upperLeft = 1;
                } else {
                    upperRight = row[j];
                    row[j] = upperLeft + row[j];
                    upperLeft = upperRight;
                }
            }
        }
        return Arrays.asList(row);
    }
}
