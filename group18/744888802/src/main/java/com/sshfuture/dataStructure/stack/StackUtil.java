package com.sshfuture.dataStructure.stack;

public class StackUtil {


    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static void reverse(Stack s) {
        if (s == null || s.isEmpty()) {
            return;
        }

        Stack stack = new Stack();
        Stack stack2 = new Stack();
        move(s, stack);
        move(stack, stack2);
        move(stack2, s);

    }

    public static void move(Stack s, Stack stack) {
        int count = s.size();
        for (int i = 0; i < count; i++) {
            stack.push(s.pop());
        }
    }

    public static void peekCopy(Stack s, Stack stack) {
        int count = s.size();
        Stack tmp = new Stack();
        Stack tmp2 = new Stack();
        for (int i = 0; i < count; i++) {
            tmp.push(s.peek());
            tmp2.push(s.pop());
        }
        move(tmp,s);
        move(tmp2,stack);
    }


    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param o
     */
    public static void remove(Stack s, Object o) {
        int count = s.size();
        Stack stack = new Stack();
        for (int i = 0; i < count; i++) {
            Object obj = s.pop();
            if( obj.hashCode() == o.hashCode()){
                continue;
            }
            stack.push(obj);
        }
        move(stack,s);
    }

    /**
     * 从栈顶取得len个元素, 原来的栈中元素保持不变
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param len
     * @return
     */
    public static Object[] getTop(Stack s, int len) {


        if (s == null || s.isEmpty()) {
            return null;
        }

        Stack stack = new Stack();
        peekCopy(s, stack);
        Object[] res = new Object[len];
        for(int i=0;i<len;i++){
            res[i] = stack.pop();
        }


        return res;
    }

    /**
     * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
     * 使用堆栈检查字符串s中的括号是不是成对出现的。
     * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
     * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
     *
     * @param s
     * @return
     */
    public static boolean isValidPairs(String s) {
        Stack parentheses = new Stack();
        parentheses.push("(");
        parentheses.push(")");

        Stack bracket = new Stack();
        bracket.push("[");
        bracket.push("]");

        Stack braces = new Stack();
        braces.push("{");
        braces.push("}");

        char [] strs = s.toCharArray();
        for(int i=0;i<strs.length;i++){
            remove(parentheses,strs[i]);
            remove(bracket,strs[i]);
            remove(braces,strs[i]);
        }

        if((parentheses.size() % 2) == 0
                && (bracket.size() % 2) == 0
                && (braces.size() % 2) == 0 ){

            return true;
        }



        return false;
    }


}
