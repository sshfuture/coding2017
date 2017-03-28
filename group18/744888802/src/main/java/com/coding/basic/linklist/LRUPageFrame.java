package com.coding.basic.linklist;

/**
 * 用双向链表实现LRU算法
 * @author liuxin
 *
 */
public class LRUPageFrame {

	
	private static class Node {
		
		Node prev;
		Node next;
		int pageNum;

		Node() {
		}
		@Override
		public boolean equals(Object object) {
			Node node = (Node)object;
			return this.pageNum==node.pageNum;
		}
	}

	public static void main(String[] args) {
		Node node1 = new Node();
		Node node2 = new Node();
		node1.pageNum = 1;
		node2.pageNum = 1;
		System.out.println(node1.equals(node2));
	}

	private int capacity;
	
	
	private Node first;// 链表头
	private Node last;// 链表尾

	private int size ;

	
	public LRUPageFrame(int capacity) {
		
		this.capacity = capacity;
		
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param
	 * @return
	 */
	public void access(int pageNum) {

		Node node = new Node();
		node.pageNum = pageNum;
		if(first == null){
			first = node;
			last = node;
			this.size++;
			return;
		}

		boolean hasValue = moveUp(node);
		if(hasValue){
			return;
		}

		if(this.size == this.capacity){
			removeLast();
		}else{
			this.size++;
		}
		first.prev = node;
		node.next = first;
		first = node;
	}

	private void removeLast(){
		last = last.prev;
		last.next.prev = null;
		last.next = null;
	}



	private boolean moveUp(Node node){
		Node thisNode = first;
		for(int i=0;i<size;i++){
			if(node.equals(thisNode)){
				if(thisNode.equals(first)){
					return true;
				}
				if(thisNode.equals(last)){
					thisNode.next = first;
					first.prev = thisNode;

					first = thisNode;

					thisNode.prev.next = null;
					last = thisNode.prev;
					thisNode.prev = null;

					return true;
				}
				thisNode.prev.next = thisNode.next;
				thisNode.next.prev = thisNode.prev;

				first.prev = thisNode;
				thisNode.next = first;
				thisNode.prev = null;
				first = thisNode;
				return true;
			}
			thisNode = thisNode.next;
		}

		return false;


	}
	
	

	public String toString(){
		StringBuilder buffer = new StringBuilder();
		Node node = first;
		while(node != null){
			buffer.append(node.pageNum);			
			
			node = node.next;
			if(node != null){
				buffer.append(",");
			}
		}
		return buffer.toString();
	}
	
}
