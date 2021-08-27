package com.mjscode.algorithms.newCoder.huawei;

import java.util.*;

/**
 * @author binarySigh
 * @date 2021/8/27 23:01
 */
public class HJ42_LearnEnglish {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Long> list = new ArrayList<>();
        while (scanner.hasNext()) {
            long number = scanner.nextLong();
            list.add(number);
        }
        for (Long number : list) {
            StringBuffer sb = new StringBuffer();
            int million = number.intValue() / 1000000;
            number %= 1000000;
            int thousand = number.intValue() / 1000;
            int hunderd = number.intValue() % 1000;
            if(million != 0) {
                sb.append(translate(million) + " million ");
            }
            if(thousand != 0) {
                sb.append(translate(thousand) + " thousand ");
            }
            if(hunderd != 0) {
                sb.append(translate(hunderd));
            }
            System.out.println(sb.toString());
        }
    }

    public static String translate(int number) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");
        map.put(8, "eight");
        map.put(9, "nine");
        map.put(10, "ten");
        map.put(11, "eleven");
        map.put(12, "twelve");
        map.put(13, "thirteen");
        map.put(14, "fourteen");
        map.put(15, "fifteen");
        map.put(16, "sixteen");
        map.put(17, "seventeen");
        map.put(18, "eighteen");
        map.put(19, "nineteen");
        map.put(20, "twenty");
        map.put(30, "thirty");
        map.put(40, "forty");
        map.put(50, "fifty");
        map.put(60, "sixty");
        map.put(70, "seventy");
        map.put(80, "eighty");
        map.put(90, "ninety");
        StringBuffer sb = new StringBuffer();
        if(number >= 100) {
            int i = number / 100;
            int j = number % 100;
            sb.append(map.get(i)).append(" hundred");
            if(j != 0) {
                sb.append(" and");
                if(map.containsKey(j)) {
                    sb.append(" " + map.get(j));
                } else {
                    int k = j % 10;
                    sb.append(" " + map.get(j-k) + " " + map.get(k));
                }
            }
        } else {
            if(map.containsKey(number)) {
                sb.append(map.get(number));
            } else {
                int k = number % 10;
                sb.append(map.get(number-k) + " " + map.get(k));
            }
        }
        return sb.toString();
    }
}
