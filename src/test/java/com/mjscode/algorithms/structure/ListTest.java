package com.mjscode.algorithms.structure;

import com.mjscode.algorithms.datastructure.DoubleList;
import com.mjscode.algorithms.datastructure.SingleList;
import com.mjscode.algorithms.utils.ListUtils;
import org.junit.Test;

public class ListTest {

    @Test
    public void SingleListTest(){
        SingleList sl = new SingleList();
        sl.add(12);
        sl.add(13);
        sl.add(14);
        sl.add(15);
        System.out.println(sl.size());
        System.out.println(sl.contains(15));
        System.out.println(sl.remove(12));
        System.out.println(sl.size());
        System.out.println(sl.reverse());
        System.out.println(sl.size());

        SingleList sl2 = new SingleList();
        SingleList sl3 = new SingleList();
        sl2.add(21);
        sl2.add(22);
        sl2.add(23);
        sl3.addAll(sl2);
        System.out.println(sl3.size());
    }

    @Test
    public void DoubleListTest(){
        DoubleList dl = new DoubleList();
        dl.add(13);
        dl.add(14);
        dl.add(16);
        dl.add(17);
        dl.remove(17);
        System.out.println(dl.contains(16));
        dl.reverse();
        System.out.println(dl.size());
    }

    @Test
    public void LoopListTest(){
        ListUtils.Node loopHead = ListUtils.getCertainList(200, 3, 5, true);
        ListUtils.Node nonLoopHead = ListUtils.getCertainList(200, 3, 5, false);
        ListUtils.Node nonLoopHead2 = ListUtils.getCertainList(200, 3, 0, false);
        System.out.println(ListUtils.isLoop(loopHead));
        System.out.println(ListUtils.isLoop(nonLoopHead));
        System.out.println(ListUtils.getLoopNode(loopHead));
        System.out.println(ListUtils.getLoopNode(loopHead).val());
        ListUtils.intersectNonLoopList(nonLoopHead,nonLoopHead2,true);
        System.out.println("成功");
    }
}
