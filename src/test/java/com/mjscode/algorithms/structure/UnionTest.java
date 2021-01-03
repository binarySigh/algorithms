package com.mjscode.algorithms.structure;

import com.mjscode.algorithms.datastructure.UnionMap;
import org.junit.Test;

public class UnionTest {

    @Test
    public void functionTest(){
        UnionMap map = new UnionMap();
        Integer[] array = {12, 14, 15, 24, 35, 44, 36, 67, 35, 78};
        map.initArray(array);
        System.out.println(map.isSameSet(12, 14));
        map.union(12, 14);
        map.union(24, 35);
        map.union(35, 14);
        System.out.println(map.isSameSet(24, 12));
    }
}
