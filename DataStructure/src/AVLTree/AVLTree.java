package AVLTree;

import java.util.*;

public class AVLTree<T extends Comparable<? super T>>
{
	private static class AvlNode<T>
	{
		AvlNode(T theElement, AvlNode<T> lt, AvlNode<T> rt)
		{
			element=theElement;
			left=lt;
			right=rt;
			height=0;
		}
		
		T element;
		AvlNode<T> left;
		AvlNode<T> right;
		int height;
	}
	
	private int height(AvlNode<T> t)
	{
		return t==null?-1:t.height;
	}
	
	public boolean isEmplty()
	{
		return root==null;
	}
	
	public void clear()
	{
		root=null;
	}
	
	public boolean contains(T x)
	{ 	return contains(x,root);}
	
	private boolean contains(T x,AvlNode<T> t)
	{
		if(t==null)
			return false;
		
		int compareResult=x.compareTo(t.element);
		
		if (compareResult<0)
		{
			return contains(x,t.left);
		}
		else if (compareResult>0)
		{
			return contains(x,t.right);
		}
		else {
			return true;
		}
		
	}
	
	public void insert(T x)
	{
		root=insert(x,root);
	}
	
	private AvlNode<T> insert(T x,AvlNode<T> t)
	{
		if(t==null)
			return new AvlNode<T>(x,null,null);
		
		int compareResult=x.compareTo(t.element);
		
		if(compareResult<0)
		{
			t.left=insert(x,t.left);
			
			if((height(t.left)-height(t.right)==2))
			{
				if(x.compareTo(t.left.element)<0)
					t=rotateWithLeftChild(t);
				else 
					t=doubleWithLeftChild(t);
			}
	
		}
		else if (compareResult>0)
		{
			t.right=insert(x,t.right);
			if((height(t.right)-height(t.left)==2))
			{
				if(x.compareTo(t.right.element)>0)
					t=rotateWithRightChild(t);
				else 
					t=doubleWithRightChild(t);
			}
		}
		
		t.height=Math.max(height(t.left),height(t.right))+1;
		
		return t;	
		
	}
	
	public void remove(T x)
	{
		root=remove(x,root);
	}
	
	private AvlNode<T> remove(T x,AvlNode<T> t)
	{
		if(t==null)
			return t;
		
		int compareResult=x.compareTo(t.element);
		
		if(compareResult<0)
		{
			t.left=remove(x,t.left);
			t.height=Math.max(height(t.left),height(t.right))+1;
			
			if((height(t.right)-height(t.left))==2)
			{
				//if the height of the right subtree of t's right subtree is higher than its left subtree, do the single right rotation.
				//otherwise, do the double right rotation.
				if(height(t.right.right)>=height(t.right.left))
					t=rotateWithRightChild(t);
				else 
					t=doubleWithRightChild(t);
			}
		}
		else if(compareResult>0)
		{
			t.right=remove(x,t.right);
			t.height=Math.max(height(t.left),height(t.right))+1;
			if((height(t.left)-height(t.right))==2)
			{
				//this is the symmetry version of compareResult<0.
				if(height(t.left.left)>=height(t.left.right))
					t=rotateWithLeftChild(t);
				else
					t=doubleWithLeftChild(t);
			}
			
		}
		else {
			if((t.left!=null)&&(t.right!=null))
			{
				t.element=findMin(t.right).element;
				t.right=remove(t.element,t.right);
			}
			else if((t.left==null)&&(t.right==null))
				t=null;									//no child case, delete the node directly, return null
			else 
				t=t.left!=null?t.left:t.right;			//one child case, return its child.
		}
		

		
		return t;
	}
	
	
	public T findMax()
	{
		if(isEmplty())
			throw new NoSuchElementException();
		return findMax(root).element;
	}
	
	private AvlNode<T> findMax(AvlNode<T> t)
	{
		if(t==null)
			return null;
		else if(t.right==null)
			return t;
		else
			return findMax(t.right);
	}
	
	public T findMin()
	{
		if(isEmplty())
			throw new NoSuchElementException();
		return findMin(root).element;
	}
	
	public void printTree()
	{
		if(isEmplty())
			System.out.println("This tree is empty");
		else {
			printTree(root);
		}
	}
	
	private void printTree(AvlNode<T> t)
	{
		if(t!=null)
		{
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}
	
	public void printTree2()
	{
		if(isEmplty())
			System.out.println("The tree is empty");
		else {
			Queue<AvlNode<T>> newQ=new LinkedList<AvlNode<T>>();
			newQ.add(root);
			printTree2(newQ);
		}
	}
	
	public void printTree2(Queue<AvlNode<T>> q)
	{
		if(!q.isEmpty())
		{
			Queue<AvlNode<T>> newQ=new LinkedList<AvlNode<T>>();
			
			for(AvlNode<T> t :q)
			{
				System.out.print(t.element+" ");
				if(t.left!=null)
					newQ.add(t.left);
				if(t.right!=null)
					newQ.add(t.right);
			}
			
			System.out.print("\n");
			printTree2(newQ);			
		}
	}
	
	private AvlNode<T> findMin(AvlNode<T> t)
	{
		if(t==null)
			return null;
		else if(t.left==null)
			return t;
		else
			return findMin(t.left);
	}
	
	public AvlNode<T> rotateWithLeftChild(AvlNode<T> k2)
	{
		AvlNode<T> k1=k2.left;
		k2.left=k1.right;
		k1.right=k2;
		k2.height=Math.max(height(k2.left),height(k2.right))+1;
		k1.height=Math.max(height(k1.left),height(k1.right))+1;
		
		return k1;
	}
	
	private AvlNode<T> rotateWithRightChild(AvlNode<T> k2)
	{
		AvlNode<T> k1=k2.right;
		k2.right=k1.left;
		k1.left=k2;
		k2.height=Math.max(height(k2.left),height(k2.right))+1;
		k1.height=Math.max(height(k1.left),height(k1.right))+1;
		
		return k1;
	}
	
	private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3)
	{
		k3.left=rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}
	
	private AvlNode<T> doubleWithRightChild(AvlNode<T> k3)
	{
		k3.right=rotateWithLeftChild(k3.right);
		return rotateWithRightChild(k3);
	}
	
	private AvlNode<T> root;
	
}
