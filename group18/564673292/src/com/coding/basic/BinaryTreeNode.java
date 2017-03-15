package com.coding.basic;

public class BinaryTreeNode<E> {
    
    private E data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
    
    // constructor
    public BinaryTreeNode(E data){
        this.data = data;
    }

    public E getData() {
        return this.data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public BinaryTreeNode getLeft() {
        return this.left;
    }

    public boolean setLeft(BinaryTreeNode<E> left) {
        if(this.compareWithRoot(left.data) >= 0 || this.left != null){
            System.err.println("The left node data should be smaller than root node.");
            return false;
        }else{
            this.left = left;
            return true;
        }
    }

    public BinaryTreeNode getRight() {
        return this.right;
    }

    public boolean setRight(BinaryTreeNode<E> right) {
        if(this.compareWithRoot(right.data) <= 0 || this.right != null) {
            System.err.println("The right node data should be larger than root node.");
            return false;
        }else{
            this.right = right;
            return true;
        }
    }
    
    private int compareWithRoot(E o){
        return (Integer)o - (Integer)this.getData();
    }

    @SuppressWarnings("unchecked")
    public void insert(E o){
        BinaryTreeNode newNode = new BinaryTreeNode(o);
        if(!this.setLeft(newNode)){
            if(!this.setRight(newNode)){
                if(this.left.getData() == o){
                    this.right.insert(o);
                }else{
                    this.left.insert(o);
                }
            }
        }
    }
}