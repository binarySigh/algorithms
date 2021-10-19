package com.mjscode.algorithms.leetcode;

/**
 * //请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * //
 * // 实现词典类 WordDictionary ：
 * //
 * // WordDictionary() 初始化词典对象
 * // void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * // bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回 false 。word 中可能包含一些
 * //'.' ，每个 . 都可以表示任何一个字母。
 * //
 * // 示例：
 * //
 * //输入：
 * //["WordDictionary","addWord","addWord","addWord","search","search","search",
 * //"search"]
 * //[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * //输出：
 * //[null,null,null,null,false,true,true,true]
 * //
 * //解释：
 * //WordDictionary wordDictionary = new WordDictionary();
 * //wordDictionary.addWord("bad");
 * //wordDictionary.addWord("dad");
 * //wordDictionary.addWord("mad");
 * //wordDictionary.search("pad"); // return False
 * //wordDictionary.search("bad"); // return True
 * //wordDictionary.search(".ad"); // return True
 * //wordDictionary.search("b.."); // return True
 * //
 * // 提示：
 * //
 * // 1 <= word.length <= 500
 * // addWord 中的 word 由小写英文字母组成
 * // search 中的 word 由 '.' 或小写英文字母组成
 * // 最多调用 50000 次 addWord 和 search
 * //
 * // Related Topics 深度优先搜索 设计 字典树 字符串
 * @author binarySigh
 * @date 2021/10/19 22:39
 */
public class LC0211_WordDictionary {

    private Node root;

    public LC0211_WordDictionary(){
        this.root = new Node();
    };

    public void addWord(String word) {
        Node cur = this.root;
        for(int i = 0; i < word.length(); i++) {
            char tmp = word.charAt(i);
            if(tmp == '.') {
                if(cur.next[26] == null) {
                    cur.next[26] = new Node();
                }
                cur = cur.next[26];
            } else {
                if(cur.next[tmp - 'a'] == null) {
                    cur.next[tmp - 'a'] = new Node();
                }
                cur = cur.next[tmp - 'a'];
            }
        }
        cur.isEnd = true;
    }

    public boolean search(String word) {
        return dfs(this.root, word, 0);
    }

    private boolean dfs(Node cur, String word, int idx) {
        if(idx == word.length()) {
            return cur.isEnd;
        }
        char tmp = word.charAt(idx);
        if(tmp == '.') {
            for(int i = 0; i < 27; i++) {
                if(cur.next[i] == null) {
                    continue;
                }
                if(dfs(cur.next[i], word, idx + 1)) {
                    return true;
                }
            }
        } else {
            if(cur.next[26] != null) {
                if(dfs(cur.next[26], word, idx + 1)) {
                    return true;
                }
            }
            if(cur.next[tmp - 'a'] != null) {
                if(dfs(cur.next[tmp - 'a'], word, idx + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static class Node {
        boolean isEnd;
        // 第26个位置存'.'字符
        Node[] next;
        Node() {
            this.isEnd = false;
            this.next = new Node[27];
        }
    }

    public static void main(String[] args) {
        LC0211_WordDictionary wd = new LC0211_WordDictionary();
        wd.addWord("badd");
        wd.addWord("dad");
        wd.addWord("mad");
        System.out.println(wd.search("pad"));
        System.out.println(wd.search("bad"));
        System.out.println(wd.search(".ad"));
        System.out.println(wd.search("b.."));

    }

}
