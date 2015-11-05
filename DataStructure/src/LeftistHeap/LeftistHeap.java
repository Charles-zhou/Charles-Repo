package LeftistHeap;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class LeftistHeap<T extends Comparable<? super T>> {

	public LeftistHeap() {
		root=null;
	}
	
	public void merge(LeftistHeap<T> rhs) {
		if(this==rhs)
			return;
		
		root=merge(root, rhs.root);
		rhs.root=null;
	}
	
	public void insert(T x) {
		root=merge(new Node<T>(x), root);
	}
	
	public T findMin() {
		return root.element;
	}
	
	public T deleteMin() {
		if(isEmpty())
			throw new NoSuchElementException();
		
		T minItem=root.element;
		
		root=merge(root.left, root.right);
		
		return minItem;
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
			npl=0;
		}
		
		T element;
		Node<T> left;
		Node<T> right;
		int npl;				//null path length
	}
	
	private Node<T> root;
	
	private Node<T> merge(Node<T> h1, Node<T> h2) {
		if(h1==null)
			return h2;
		if(h2==null)
			return h1;
		if(h1.element.compareTo(h2.element)<0) {
			return merge1(h1, h2);
		}
		else {
			return merge1(h2, h1);
		}
	}
	
	private Node<T> merge1(Node<T> h1, Node<T> h2) {
		if(h1.left==null)
			h1.left=h2;
		else {
			h1.right=merge(h1.right, h2);
			if(h1.right.npl>h1.left.npl)
				swapChildren(h1);
			h1.npl=h1.right.npl+1;
		}
		
		return h1;
	}
	
	private void swapChildren(Node<T> t) {
		Node<T> tmp=t.left;
		t.left=t.right;
		t.right=tmp;
	}
	
    public static void main( String [ ] args )
    {
        int numItems = 100;
        LeftistHeap<Integer> h  = new LeftistHeap<Integer>( );
        LeftistHeap<Integer> h1 = new LeftistHeap<Integer>( );
        int i = 37;

            for( i = 37; i != 0; i = ( i + 37 ) % numItems )
                if( i % 2 == 0 )
                    h1.insert(i);
                else
                    h.insert(i);

            h.merge( h1 );
            for( i = 1; i < numItems; i++ )
                if( h.deleteMin() != i )
                    System.out.println( "Oops! " + i );
    }
}
