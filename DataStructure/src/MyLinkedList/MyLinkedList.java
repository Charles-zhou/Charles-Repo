package MyLinkedList;

import java.util.*;

public class MyLinkedList<T> implements Iterable<T>
{
	private static class Node<T>
	{
		public Node(T d,Node<T> pre,Node<T> next)
		{
			this.data=d;
			this.prevNode=pre;
			this.nextNode=next;
		}
		
		public T data;
		public Node<T> prevNode;
		public Node<T> nextNode;
	}
	
	public MyLinkedList()
	{ clear();}
	
	public boolean add(T item)
	{ 
		this.add(theSize,item); 
		return true;
	}
	
	public void add(int idx,T x)
	{ 
		addBefore(getNode(idx),x);
	}
	
	public void clear()
	{
		beginMarker = new Node<T>(null, null, null);
		endMarker = new Node<T>(null, beginMarker,null);
		
		beginMarker.nextNode=endMarker;
		
		theSize=0;
		
		modCount++;
	}
	
	public int size()
	{ return theSize; }
	
	public boolean isEmpty()
	{ return size()==0; }
	
	public T get(int idx)
	{ 	if(idx<0||idx>size())
			throw new IndexOutOfBoundsException();
		return getNode(idx).data; }
	
	public T set(int idx,T newVal)
	{ 	
		T old=get(idx);		
		getNode(idx).data=newVal; 
		return old;
	}
	
	public T remove(int idx)
	{ 	return remove(getNode(idx)); }
	
	private void addBefore(Node<T> p,T x)
	{
		Node<T> newNode=new Node<T>(x, p.prevNode, p);
		
		p.prevNode.nextNode=newNode;
		
		p.prevNode=newNode;
		
		theSize++;
		modCount++;
	}
	
	public T remove(Node<T> p)
	{
		p.prevNode.nextNode=p.nextNode;
		p.nextNode.prevNode=p.prevNode;
		
		theSize--;
		modCount++;
		
		return p.data;
	}
	
	public Node<T> getNode(int idx)
	{
		Node<T> p;
		
		if(idx<0 || idx>size())
			throw new IndexOutOfBoundsException();
		
		if (idx<size()/2)
		{
			p=beginMarker.nextNode;
			for(int i=0;i<idx;i++)
				p=p.nextNode;
		}
		else {
			p=endMarker;
			for(int i=size();i>idx;i--)
				p=p.prevNode;
		}
		
		return p;
		
	}
	
	public Iterator<T> iterator()
	{
		return new Iterator<T>(){
			private Node<T> current=beginMarker.nextNode;
			private int expectedModCount=modCount;
			private boolean okToRemove=false;
			
			public boolean hasNext()
			{
				return current!=endMarker;
			}
			
			public T next()
			{
				if(modCount!=expectedModCount)
					throw new java.util.ConcurrentModificationException();
				if(!hasNext())
					throw new java.util.NoSuchElementException();
				
				T nextItem=current.data;
				current=current.nextNode;
				okToRemove=true;
				return nextItem;
			}
			
			public void remove()
			{
				if(modCount!=expectedModCount)
					throw new java.util.ConcurrentModificationException();
				if(!okToRemove)
					throw new IllegalStateException();
				
				MyLinkedList.this.remove(current.prevNode);
				okToRemove=false;
				expectedModCount++;
			}
			
		};
	}
	
	private int theSize;
	private int modCount=0;
	private Node<T> beginMarker;
	private Node<T> endMarker;
	
}
