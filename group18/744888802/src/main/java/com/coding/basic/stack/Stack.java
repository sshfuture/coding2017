package com.coding.basic.stack;

import com.coding.basic.Iterator;
import com.coding.basic.linklist.LinkedList;

public class Stack extends LinkedList {
//	private ArrayList elementData = new ArrayList();


	/**
	 * 栈顶插入元素
	 * @param o
	 */
	public void push(Object o){
		super.addFirst(o);
	}

	/**
	 * 获取栈顶元素 并且删除
	 */
	public Object pop(){
		if(super.size()==0)
		{
			return null;
		}
		Object o = super.get(0);
		super.removeFirst();
		return o;
	}


	/**
	 * 获取栈顶元素 不删除
	 * @return
	 */
	public Object peek(){
		if(super.size()==0)
		{
			return null;
		}
		return super.get(0);
	}

	public boolean isEmpty(){
		return super.size()==0;
	}
	public int size(){
		return super.size();
	}

	public void print(){

		Iterator  iterator = super.iterator();
		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}

	}
}
