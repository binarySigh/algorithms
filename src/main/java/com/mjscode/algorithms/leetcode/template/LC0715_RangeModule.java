package com.mjscode.algorithms.leetcode.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * //Range 模块是跟踪数字范围的模块。你的任务是以一种有效的方式设计和实现以下接口。
 * //
 * // addRange(int left, int right) 添加半开区间 [left, right)，跟踪该区间中的每个实数。添加与当前跟踪的数字部分重叠
 * //的区间时，应当添加在区间 [left, right) 中尚未跟踪的任何数字到该区间中。
 * // queryRange(int left, int right) 只有在当前正在跟踪区间 [left, right) 中的每一个实数时，才返回 true。
 * //
 * // removeRange(int left, int right) 停止跟踪区间 [left, right) 中当前正在跟踪的每个实数。
 * //
 * // 示例：
 * //
 * // addRange(10, 20): null
 * //removeRange(14, 16): null
 * //queryRange(10, 14): true （区间 [10, 14) 中的每个数都正在被跟踪）
 * //queryRange(13, 15): false （未跟踪区间 [13, 15) 中像 14, 14.03, 14.17 这样的数字）
 * //queryRange(16, 17): true （尽管执行了删除操作，区间 [16, 17) 中的数字 16 仍然会被跟踪）
 * //
 * // 提示：
 * //
 * // 半开区间 [left, right) 表示所有满足 left <= x < right 的实数。
 * // 对 addRange, queryRange, removeRange 的所有调用中 0 < left < right < 10^9。
 * // 在单个测试用例中，对 addRange 的调用总数不超过 1000 次。
 * // 在单个测试用例中，对 queryRange 的调用总数不超过 5000 次。
 * // 在单个测试用例中，对 removeRange 的调用总数不超过 1000 次。
 * //
 * // Related Topics 设计 线段树 有序集合
 * @author binarySigh
 * @date 2021/9/18 19:18
 */
public class LC0715_RangeModule {
    TreeSet<int[]> set;

    public LC0715_RangeModule(){
        this.set = new TreeSet<>((a, b) -> {
            return a[1] == b[1] ? (a[0] - b[0]) : (a[1] - b[1]);
        });
    }

    public void addRange(int left, int right) {
        Iterator<int[]> ite = set.tailSet(new int[]{0, left - 1}).iterator();
        while(ite.hasNext()){
            int[] tmp = ite.next();
            if(tmp[0] > right){
                break;
            }
            left = Math.min(tmp[0], left);
            right = Math.max(tmp[1], right);
            ite.remove();
        }
        set.add(new int[]{left, right});
    }

    public void removeRange(int left, int right) {
        Iterator<int[]> ite = set.tailSet(new int[]{0, left}).iterator();
        ArrayList<int[]> todo = new ArrayList<>();
        while(ite.hasNext()) {
            int[] tmp = ite.next();
            if(tmp[0] > right){
                break;
            }
            if(tmp[0] <= left){
                todo.add(new int[]{tmp[0], left});
            }
            if(tmp[1] > right){
                todo.add(new int[]{right, tmp[1]});
            }
            ite.remove();
        }
        for(int[] tmp : todo){
            set.add(tmp);
        }
    }

    public boolean queryRange(int left, int right) {
        int[] tmp = set.ceiling(new int[]{0, right});
        return tmp != null && tmp[0] <= left;
    }

    public static void main(String[] args) {
        LC0715_RangeModule rm = new LC0715_RangeModule();
        rm.addRange(10, 20);
        rm.removeRange(14, 16);
        System.out.println(rm.queryRange(10, 14));
        System.out.println(rm.queryRange(13, 15));
        System.out.println(rm.queryRange(16, 17));


    }

}
