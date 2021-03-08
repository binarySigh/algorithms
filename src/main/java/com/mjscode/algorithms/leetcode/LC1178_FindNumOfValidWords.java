package com.mjscode.algorithms.leetcode;

import java.util.*;

/**
 * //外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
 * //
 * // 字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：
 * //
 * // 单词 word 中包含谜面 puzzle 的第一个字母。
 * // 单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
 * // 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"
 * //（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）都不能作为谜底。
 * //
 * // 返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜
 * //底的单词数目。
 * //
 * // 示例：
 * //
 * //输入：
 * //words = ["aaaa","asas","able","ability","actt","actor","access"],
 * //puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * //输出：[1,1,3,2,4,0]
 * //解释：
 * //1 个单词可以作为 "aboveyz" 的谜底 : "aaaa"
 * //1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
 * //3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
 * //2 个单词可以作为 "absoryz" 的谜底 : "aaaa", "asas"
 * //4 个单词可以作为 "actresz" 的谜底 : "aaaa", "asas", "actt", "access"
 * //没有单词可以作为 "gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
 * //
 * // 提示：
 * //
 * // 1 <= words.length <= 10^5
 * // 4 <= words[i].length <= 50
 * // 1 <= puzzles.length <= 10^4
 * // puzzles[i].length == 7
 * // words[i][j], puzzles[i][j] 都是小写英文字母。
 * // 每个 puzzles[i] 所包含的字符都不重复。
 * //
 * // Related Topics 位运算 哈希表
 * @author binarySigh
 */
public class LC1178_FindNumOfValidWords {

    public static void main(String[] args){
        /*int maxOf26 = 0;
        int i = 0;
        while(i < 26){
            maxOf26 |= (1 << i);
            i++;
        }
        // 67108863
        System.out.println(maxOf26);*/
        //String[] words = {"aaaa","asas","able","ability","actt","actor","access"};
        String[] words = {"aaaaaaaa","aaaa","asas","able","ability","actt","actor","access"};
        String[] puzzles = {"aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"};
        List<Integer> list = findNumOfValidWords(words, puzzles);
    }

    public static List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        //max是低26位全为1对应的数字
        int max = 67108863;
        int cur = 0;
        //处理puzzles
        //forst数组记录每个谜面首字母
        int[] first = new int[puzzles.length];
        //puzzs数组记录每个谜面处理后的整型值
        int[] puzzs = new int[puzzles.length];
        for(int i = 0; i < puzzles.length; i++){
            first[i] = 1 << (puzzles[i].charAt(0) - 97);
            for(int j = 0; j < puzzles[i].length(); j++){
                puzzs[i] |= (1 << (puzzles[i].charAt(j) - 97));
            }
        }
        //处理words
        //map key-单词处理后的数字 value-该单词处理后数字重复的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            for(int j = 0; j < words[i].length() && cur < max; j++){
                cur |= (1 << (words[i].charAt(j) - 97));
            }
            //此处是根据官方解法的优化
            //优化思路：谜面最多7个字母，因此谜底中只要有超过7个字母的，就不加入map
            //map.put(cur, map.getOrDefault(cur, 0) + 1);
            if(Integer.bitCount(cur) <= 7){
                map.put(cur, map.getOrDefault(cur, 0) + 1);
            }
            //每个word处理完记得恢复
            cur = 0;
        }
        //匹配查找
        //填充返回List
        List<Integer> list = new ArrayList<>(puzzles.length);
        for(int i = 0; i < puzzles.length; i++){
            for(Map.Entry<Integer, Integer> curMap : map.entrySet()){
                //满足满足两个条件，则判定该词条是该谜面的谜底
                if(((puzzs[i] | curMap.getKey()) == puzzs[i]) &&
                        (first[i] | curMap.getKey()) == curMap.getKey()){
                    cur += curMap.getValue();
                }
            }
            list.add(cur);
            cur = 0;
        }
        return list;
    }

    /**
     * 根据官方解法改进版。
     *      改进思路：
     *      1.不提前遍历处理谜面，在最后整合时遍历处理谜面，并同时匹配谜底，节约空间和一轮遍历谜面的时间
     *      2.匹配谜底时，不采取逐个遍历谜底匹配的方式，而是将谜面的7位进行子集列举，
     *              每枚举出一个子集就去谜底map中找该子集是否存在，若存在就把对应的数量加上
     *      说明：列举子集是指，假设谜面为 - 0011 0011 111，那么作为它的子集之一的 0000 0011 111肯定能作为它的谜底。
     *              所以在map中找是否有 0000 0011 111 对应的记录。
     *      第二种优化是本题最重要的优化点，能够将大量的谜底遍历过程简化为有限次的谜面子集列举过程，能够大量节约算法时间
     * @param words
     * @param puzzles
     * @return
     */
    public static List<Integer> findNumOfValidWordsV2(String[] words, String[] puzzles) {
        //max是低26位全为1对应的数字
        int max = 67108863;
        int cur = 0;
        //处理words
        //map key-单词处理后的数字 value-该单词处理后数字重复的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            for(int j = 0; j < words[i].length() && cur < max; j++){
                cur |= (1 << (words[i].charAt(j) - 97));
            }
            //此处是根据官方解法的优化
            //优化思路：谜面最多7个字母，因此谜底中只要有超过7个字母的，就不加入map
            //map.put(cur, map.getOrDefault(cur, 0) + 1);
            if(Integer.bitCount(cur) <= 7){
                map.put(cur, map.getOrDefault(cur, 0) + 1);
            }
            //每个word处理完记得恢复
            cur = 0;
        }
        //匹配查找
        //填充返回List
        List<Integer> list = new ArrayList<>(puzzles.length);
        //这里因为谜底必须包含谜面首字母，因此我们列举子集时先不带上首字母，对于每个被列举出来的子集，我们再单独加上首字母
        for(String puzz : puzzles){
            int puzzle = 0;
            for(int i = 1; i < 7; i++){
                puzzle |= (1 << (puzz.charAt(i) - 97));
            }
            int sub = puzzle;
            do{
                int s = sub | (1 << (puzz.charAt(0) - 97));
                if(map.containsKey(s)){
                    cur += map.get(s);
                }
                sub = (sub - 1) & puzzle;
            } while(sub != puzzle);
            list.add(cur);
            cur = 0;
        }
        return list;
    }
}
