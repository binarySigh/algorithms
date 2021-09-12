package com.mjscode.algorithms.interview;

/**
 * @author binarySigh
 * @date 2021/9/11 11:03
 */
public class HW {
    public static void main(String[] args){
        /*int start = 1, end = 100;
        int[] dels = {-1,2,98,200};*/

        int start = 1, end = 5;
        int[] dels = {-1,1,2,98,200,233,300};
        System.out.println(test(start, end, dels));
    }

    public static String test(int start, int end, int[] dels){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; start <= end && i < dels.length; i++){
            if(start == end){
                if(dels[i] != start){
                    sb.append(start).append("\n");
                }
                break;
            }
            if(dels[i] == start + 1){
                sb.append(start).append("\n");
                start = dels[i] + 1;
            } else if(dels[i] > start + 1){
                int curEnd = Math.min(dels[i] - 1, end);
                sb.append(start).append("->").append(curEnd).append("\n");
                start = dels[i] + 1;
            } else if(dels[i] == start){
                start++;
            }
        }
        if(dels[dels.length - 1] < end){
            sb.append(dels[dels.length - 1] + 1).append("->").append(end).append("\n");
        }
        return sb.toString();
    }
}
