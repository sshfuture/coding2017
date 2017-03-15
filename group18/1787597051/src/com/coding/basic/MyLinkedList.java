package com.coding.basic;


public class MyLinkedList implements MyList {
	private int size;
	private Node head;

	public MyLinkedList() {
		head = new Node();
		head.data = "头结点";
		head.next = null;
	}

	public void add(Object o) {
		Node p = head;
		while (p.next != null) {
			p = p.next;
		}
		Node p3 = new Node();
		p3.data = o;
		p.next = p3;
		size++;
	}

	public void add(int index, Object o) {
		int num = 0;
		Node p = head;
		while (p.next != null) {
			if (num == index) {
				Node p2 = new Node();
				p2.data = o;
				p2.next = p.next;
				p.next = p2;
				size++;
			}
			p = p.next;
			num++;
		}
	}

	public Object get(int index) {
		int num = 0;
		Node p = head.next;
		while (p != null) {
			if (num == index) {
				return p.data;
			}
			p = p.next;
			num++;
		}
		return null;
	}

	public Object remove(int index) {
		int num = 0;
		Node p = head;
		while (p.next != null) {
			if (num == index) {
				Node p2 = p.next;
				p.next = p.next.next;
				size--;
				return p2.data;
			}
			p = p.next;
			num++;
		}
		return null;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node p = new Node();
		p.data = o;
		p.next = head.next;
		head.next = p;
		size++;
	}

	public void addLast(Object o) {
		Node p = head;
		while (p.next != null) {
			p = p.next;
		}
		Node p2 = new Node();
		p2.data = o;
		p.next = p2;
		size++;
	}

	public Object removeFirst() {
		Node p = head;
		if (p.next != null) {
			Node p2 = head.next;
			p.next = p.next.next;
			size--;
			return p2.data;
		}
		return null;
	}

	public Object removeLast() {
		Node p = head;
		if (p.next != null) {
			while (p.next.next != null) {
				p = p.next;
			}
			Node p2 = new Node();
			p2 = p.next;
			p.next = null;
			size--;
			return p2.data;
		}
		return null;
	}
	/*
	 * public Iterator iterator(){ return null; }
	 */

	private static class Node {
		Object data;
		Node next;
	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {
		MyLinkedList myl = new MyLinkedList();
		for (int i = this.size() - 1, j = 0; i >= 0; i--, j++) {
			myl.add(this.removeLast());
			this.add(myl.get(j));
		}
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 */
	public void removeFirstHalf() {
		for (int i = 0; i <= this.size() / 2; i++) {
			this.remove(0);
		}
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		for (int j = 0; j < length; j++) {
			this.remove(i);
		}
	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表
	 * =11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(MyLinkedList list) {
		int[] result = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = (Integer) this.get((Integer) list.get(i));
		}
		return result;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(MyLinkedList list) {
		for (int i = 0; i < this.size(); i++) {
			Object midObj = this.get(i);
			for (int j = 0; j < list.size(); j++) {
				if (midObj == list.get(j)) {
					this.remove(i);
					i--;
				}
			}
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		for (int i = 0; i < this.size(); i++) {
			Object midObj = this.get(i);
			for (int j = i + 1; j < this.size(); j++) {
				if (midObj == this.get(j)) {
					this.remove(j);
					j--;
				}
			}
		}
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * 
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {
		for (int i = 0; i < this.size(); i++) {
			if ((Integer) this.get(i) > min && (Integer) this.get(i) < max) {
				this.remove(i);
				i--;
			}
		}
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */
	public MyLinkedList intersection(MyLinkedList list) {
		MyLinkedList myl = new MyLinkedList();
		int i = 0;
		int j = 0;
		while (i < list.size() && j < this.size()) {
			int flag = list.get(i).toString().compareTo(this.get(j).toString());
			if (flag == 0) {
				myl.add(list.get(i));
				i++;
				j++;
			} else if (flag < 0) {
				myl.add(list.get(i++));
			} else {
				myl.add(this.get(j++));
			}
		}
		while (i < list.size()) {
			myl.add(list.get(i++));
		}
		while (j < this.size()) {
			myl.add(this.get(j++));
		}
		return myl;
	}

}
