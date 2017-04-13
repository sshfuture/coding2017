package com.coding.basic.stack;

/**
 * Created by hushuai on 2017/4/12.
 */
public class TestStack {

    public static void main(String[] args) {

        Stack s = new Stack();
        s.push("[");
        s.push("]");
        StackUtil.remove(s,"]");
//        s.print();


        System.out.println(StackUtil.isValidPairs("["));

    }
}
