package com;


import com.coding.basic.Iterator;
import com.coding.basic.linklist.LinkedList;

/**
 * Created by hushuai on 2017/3/9.
 */
public class TestMain {
    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);
        linkedList.addFirst(4);
        linkedList.addFirst(5);

        Iterator it =  linkedList.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }


    }
}
