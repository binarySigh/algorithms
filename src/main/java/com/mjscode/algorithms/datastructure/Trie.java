package com.mjscode.algorithms.datastructure;

/**
 * 前缀树
 *      加入的字符串中只包含小写英文字母
 * @author binarySigh
 * @date 2021/4/14 20:23
 */
public class Trie {
    TrieNode root;

    public Trie(){
        root = new TrieNode(0, 0);
    }

    /**
     * 往前缀树中添加字符串
     * @param word
     */
    public void insert(String word) {
        if(word == null || word.length() == 0){
            return;
        }
        TrieNode cur = root;
        for(int i = 0; i <= word.length(); i++){
            if(i == word.length()){
                cur.end++;
                break;
            }
            int branch = word.charAt(i) - 'a';
            cur.pass++;
            if(cur.next[branch] == null){
                cur.next[branch] = new TrieNode(0, 0);
            }
            cur = cur.next[branch];
        }
    }

    /**
     * 前缀树已添加的字符串中是否包含 word
     * @param word
     * @return
     */
    public boolean search(String word) {
        if(word == null || word.length() == 0){
            return true;
        }
        boolean res = false;
        TrieNode cur = root;
        for(int i = 0; i <= word.length(); i++){
            if(i == word.length()){
                res = cur.end > 0;
                break;
            }
            int branch = word.charAt(i) - 'a';
            if(cur.next[branch] != null){
                cur = cur.next[branch];
            } else {
                res = false;
                break;
            }
        }
        return res;
    }

    /**
     * 前缀树已添加的字符串中是否有以 prefix 为前缀的字符串
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        if(prefix == null || prefix.length() == 0){
            return true;
        }
        boolean res = false;
        TrieNode cur = root;
        for(int i = 0; i <= prefix.length(); i++){
            if(i == prefix.length()){
                res = true;
                break;
            }
            int branch = prefix.charAt(i) - 'a';
            if(cur.next[branch] != null){
                cur = cur.next[branch];
            } else {
                res = false;
                break;
            }
        }
        return res;
    }

    private class TrieNode{
        TrieNode[] next;
        int pass;
        int end;
        public TrieNode(int p, int e){
            this.next = new TrieNode[26];
            this.pass = p;
            this.end = e;
        }
    }
}
