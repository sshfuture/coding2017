package com.coding.basic.stack;

/**
 * Created by hushuai on 2017/4/12.
 */
public class TestStack {

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.print();
        System.out.println("---------------------------");

        StackUtil stackUtil = new StackUtil();
        stackUtil.reverse(stack);
        stack.print();

    }
}
