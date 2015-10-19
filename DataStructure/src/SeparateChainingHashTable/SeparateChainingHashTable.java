package SeparateChainingHashTable;

import java.util.*;

public class SeparateChainingHashTable<T>
{
	public SeparateChainingHashTable()
	{
		this(DEFAULT_TABLE_SIZE);
	}
	
	public SeparateChainingHashTable(int  size)
	{
		theLists=new LinkedList[nextPrime(size)];
		for(int i=0;i<theLists.length;i++)
			theLists[i]=new LinkedList<T>();
	}
	
	public void makeEmpty()
	{
		for(int i=0;i<theLists.length;i++)
			theLists[i].clear();
		
		currentSize=0;
	}
	
	private static final int DEFAULT_TABLE_SIZE=101;
	
	private LinkedList<T>[] theLists;
	private int currentSize;
	
	public boolean contains(T x)
	{
		for(LinkedList<T> list:theLists)
			if(list.contains(x))
				return true;
		
		return false;
	}
	
	public void insert(T x)
	{
		List<T> whichList=theLists[myhash(x)];
		
		if(!whichList.contains(x))
		{
			whichList.add(x);
			
			//update the currentSize.
			currentSize++;
			
			//when the currentSize is larger than the length of the Lists, call the rehash.
			if(currentSize>theLists.length)
				rehash();
		}
	}
	
	private void remove(T x)
	{
		int hashVal=myhash(x);
		
		if(theLists[hashVal].contains(x))
		{
			theLists[hashVal].remove(x);
			currentSize--;
		}
	}
	
	private void rehash()
	{
		LinkedList<T>[] oldLists=theLists;
		
		//Create new double-sized,empty table
		LinkedList<T>[] newLists=new LinkedList[2*theLists.length];
		
		for(int j=0;j<theLists.length;j++)
			newLists[j]=new LinkedList<T>();
		
		currentSize=0;
		
		for(int i=0; i<oldLists.length; i++)
			for(T item:oldLists[i])
				insert(item);
	}
	
	private int myhash(T x)
	{
		int hashVal=x.hashCode();
		
		hashVal%=theLists.length;
		
		if(hashVal<0)
			hashVal+=theLists.length;
		
		return hashVal;
	}
	
	private int nextPrime(int n)
	{
		//exclude the case which n is an even number.
		if(n % 2 == 0)
			n++;
		
		while(!isPrime(n))
			n+=2;
		
		return n;
	} 
	
	private boolean isPrime(int n)
	{
		if(n<2 || n % 2 == 0)
			return false;
		
		if (n==2 || n==3)
			return true;
		
		for(int i=3;i*i<=n;i+=2)
		{
			if(n%i==0)
				return false;
		}
		
		return true;
	}
	
}
