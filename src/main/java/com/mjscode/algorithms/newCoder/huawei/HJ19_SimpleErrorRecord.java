package com.mjscode.algorithms.newCoder.huawei;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author binarySigh
 * @date 2021/8/21 12:32
 */
public class HJ19_SimpleErrorRecord {

    /**
     * 提交时间：2021-08-21 语言：Java 运行时间： 47 ms 占用内存：12268K 状态：答案正确
     * @param args
     */
    public static void main(String[] args) {
        HashMap<String, Integer> map =  new HashMap<>();
        HashSet<String> set = new HashSet<>();
        LinkedList<String> q = new LinkedList<>();
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String a = in.nextLine();
            int i = a.lastIndexOf(' ');
            int j = a.lastIndexOf('\\');
            j = i - 16 > j ? (i - 16) : (j + 1);
            String key = a.substring(j);
            if(set.contains(key)){
                continue;
            }
            if(q.size() == 8){
                if(!map.containsKey(key)){
                    String drop = q.pollFirst();
                    map.remove(drop);
                    set.add(drop);
                    q.addLast(key);
                }
                map.put(key, map.getOrDefault(key, 0) + 1);
            } else {
                map.put(key, map.getOrDefault(key, 0) + 1);
                q.addLast(key);
            }
        }
        while(!q.isEmpty()){
            String key = q.pollFirst();
            System.out.println(key + " " + map.get(key));
        }
    }
}
