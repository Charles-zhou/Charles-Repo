package LeftistHeap;

import java.util.*;

public class LeftistHeap<T extends Comparable<? super T>> {

	public LeftistHeap() {
		root=null;
	}
	
	public void merge(LeftistHeap<T> rhs) {
		
	}
	
	public T findMin() {
		return root.element;
	}
	
	public T deleteMin() {
		
	}
	
	public boolean isEmpty() {
		return root==null;
	} 
	
	public void makeEmpty() {
		root=null;
	}
	
	private static class Node<T> {
		
		public Node(T theElement) {
			this(theElement,null,null);
		}
		
		public Node(T theElement,Node<T> lt,Node<T> rt) {
			this.element=theElement;
			this.left=lt;
			this.right=rt;
		}
		
		T element;
		Node<T> left;
		Node<T> right;
		int npl;				//null path length
	}
	
	private Node<T> root;
}
