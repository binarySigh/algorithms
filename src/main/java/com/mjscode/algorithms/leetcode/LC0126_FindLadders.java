package com.mjscode.algorithms.leetcode;

import java.util.*;

/**
 * //按字典 wordList 完成从单词 beginWord 到单词 endWord 转化，一个表示此过程的 转换序列 是形式上像 beginWord -> s
 * //1 -> s2 -> ... -> sk 这样的单词序列，并满足：
 * //
 * // 每对相邻的单词之间仅有单个字母不同。
 * // 转换过程中的每个单词 si（1 <= i <= k）必须是字典 wordList 中的单词。注意，beginWord 不必是字典 wordList 中的单
 * //词。
 * // sk == endWord
 * //
 * // 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。请你找出并返回所有从 beginWord 到 endWord 的
 * // 最短转换序列 ，如果不存在这样的转换序列，返回一个空列表。每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回。
 * //
 * // 示例 1：
 * //
 * //输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
 * //g","cog"]
 * //输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * //解释：存在 2 种最短的转换序列：
 * //"hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * //"hit" -> "hot" -> "lot" -> "log" -> "cog"
 * //
 * // 示例 2：
 * //
 * //输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
 * //g"]
 * //输出：[]
 * //解释：endWord "cog" 不在字典 wordList 中，所以不存在符合要求的转换序列。
 * //
 * // 提示：
 * //
 * // 1 <= beginWord.length <= 7
 * // endWord.length == beginWord.length
 * // 1 <= wordList.length <= 5000
 * // wordList[i].length == beginWord.length
 * // beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * // beginWord != endWord
 * // wordList 中的所有单词 互不相同
 * //
 * // Related Topics 广度优先搜索 哈希表 字符串 回溯
 * @author binarySigh
 * @date 2021/9/12 18:25
 */
public class LC0126_FindLadders {
    public static void main(String[] args){
        // --> [[hit, hot, dot, dog, cog], [hit, hot, lot, log, cog]]
        String bw = "hit";
        String ew = "cog";
        String[] ws = {"hot","dot","dog","lot","log","cog"};

        // --> []
        /*String bw = "hit";
        String ew = "cog";
        String[] ws = {"hot","dot","dog","lot","log"};*/
        List<List<String>> ans = findLadders(bw, ew, Arrays.asList(ws.clone()));
        System.out.println(ans);
    }

    /**
     * 解答成功:
     * 		执行耗时:28 ms,击败了16.79% 的Java用户
     * 		内存消耗:40.2 MB,击败了5.14% 的Java用户
     * 	重点：对于已经出了队列的单词而言，就不允许它再次进入队列。因为它如果再次进入的话形成的答案链一定更长
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);
        List<List<String>> ans = new ArrayList<>();
        if(!set.contains(endWord)){
            return ans;
        }
        LinkedList<List<String>> q = new LinkedList<>();
        q.addFirst(Collections.singletonList(beginWord));
        boolean isEnd = false;
        int curLen = 1;
        int nextLen = 0;
        while(!q.isEmpty()){
            curLen--;
            List<String> cur = q.pollLast();
            String curEndStr = cur.get(cur.size() - 1);
            //对于已经出现过的单词，在以后的可能性中不做考虑，因为如果考虑了，则形成的链一定更长
            set.remove(curEndStr);
            if(curEndStr.equals(endWord)){
                //已到目标词，设置标志位并收集答案
                isEnd = true;
                ans.add(cur);
            } else {
                //寻找下一个转换序列
                char[] cs = curEndStr.toCharArray();
                for(int i = 0; i < cs.length; i++){
                    char c = cs[i];
                    for(int j = 0; j < 26; j++){
                        char rc = (char)(j + 'a');
                        if(rc == c){
                            continue;
                        }
                        cs[i] = rc;
                        String newStr = new String(cs);
                        if(set.contains(newStr)){
                            List<String> tmp = new ArrayList<>(cur);
                            tmp.add(newStr);
                            q.addFirst(tmp);
                            nextLen++;
                        }
                    }
                    cs[i] = c;
                }
            }
            if(curLen == 0){
                if(isEnd){
                    break;
                }
                curLen = nextLen;
                nextLen = 0;
            }
        }
        return ans;
    }

    /**
     * TLE 超时
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static List<List<String>> findLadders1(String beginWord, String endWord, List<String> wordList) {
        int n = beginWord.length();
        HashSet<String> set = new HashSet<>(wordList);
        List<List<String>> ans = new ArrayList<>();
        if(!set.contains(endWord)){
            return ans;
        }
        LinkedList<String> q = new LinkedList<>();
        q.addFirst(beginWord);
        boolean isEnd = false;
        int curLen = 1;
        int nextLen = 0;
        while(!q.isEmpty()){
            curLen--;
            String cur = q.pollLast();
            String curEndStr = cur.substring(cur.length() - n);
            if(curEndStr.equals(endWord)){
                //已到目标词，设置标志位并收集答案
                isEnd = true;
                ans.add(Arrays.asList(cur.split(",").clone()));
            } else {
                //寻找下一个转换序列
                char[] cs = curEndStr.toCharArray();
                for(int i = 0; i < n; i++){
                    char c = cs[i];
                    for(int j = 0; j < 26; j++){
                        char rc = (char)(j + 'a');
                        if(rc == c){
                            continue;
                        }
                        cs[i] = rc;
                        String newStr = new String(cs);
                        if(set.contains(newStr)){
                            q.addFirst(cur + "," + newStr);
                            nextLen++;
                        }
                    }
                    cs[i] = c;
                }
            }
            if(curLen == 0){
                if(isEnd){
                    break;
                }
                curLen = nextLen;
                nextLen = 0;
            }
        }
        return ans;
    }
}
