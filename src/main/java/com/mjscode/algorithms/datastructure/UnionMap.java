package com.mjscode.algorithms.datastructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 并查集——哈希表实现
 * @author binarySigh
 */
public class UnionMap {
    HashMap<Object, Object> node = new HashMap<>();
    HashMap<Object, Integer> sizeMap = new HashMap<>();

    public UnionMap(){}

    public void initArray(Object[] objs){
        for(int i = 0; i < objs.length; i++){
            node.put(objs[i], objs[i]);
            sizeMap.put(objs[i], 1);
        }
    }

    public boolean isSameSet(Object obj1, Object obj2){
        return findFather(obj1) == findFather(obj2);
    }

    public void union(Object obj1, Object obj2){
        Object father1 = findFather(obj1);
        Object father2 = findFather(obj2);
        int size = sizeMap.get(father1) + sizeMap.get(father2);
        if(sizeMap.get(father1) >= sizeMap.get(father2)){
            sizeMap.put(father1, size);
            sizeMap.remove(father2);
            node.put(father2, father1);
        } else {
            sizeMap.put(father2, size);
            sizeMap.remove(father1);
            node.put(father1, father2);
        }
    }

    private Object findFather(Object obj){
        if(!node.containsKey(obj)){
            return null;
        }
        List<Object> list = new LinkedList<>();
        while(node.get(obj) != obj){
            list.add(obj);
            obj = node.get(obj);
        }
        for(int i = 0; i < list.size(); i++){
            node.put(list.get(i), obj);
        }
        return obj;
    }
}
