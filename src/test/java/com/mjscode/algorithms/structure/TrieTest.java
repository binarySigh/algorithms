package com.mjscode.algorithms.structure;

import com.mjscode.algorithms.datastructure.Trie;
import org.junit.Test;

/**
 * @author binarySigh
 * @date 2021/4/14 22:01
 */
public class TrieTest {

    @Test
    public void test(){
        Trie trie = new Trie();
        trie.insert("test");
        //trie.insert("teste");
        trie.insert("next");
        System.out.println(trie.search("test"));
        System.out.println(trie.search("next"));
        System.out.println(trie.startsWith("teste"));
        System.out.println(trie.startsWith("nextr"));
        System.out.println(trie.startsWith("next"));
        System.out.println(trie.startsWith("t"));
        System.out.println(trie.startsWith("x"));
    }
}
