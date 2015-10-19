package SeparateChainingHashTable;

import java.util.*;

public class SeparateChainingHashTable<K,V>
{
	public SeparateChainingHashTable()
	{
		this(DEFAULT_TABLE_SIZE);
	}
	
	public SeparateChainingHashTable(int  size)
	{
		theLists=new LinkedList[nextPrime(size)];
		for(int i=0;i<theLists.length;i++)
			theLists[i]=new LinkedList<V>();
	}
	
	public void makeEmpty()
	{
		for(int i=0;i<theLists.length;i++)
			theLists[i].clear();
		
		currentSize=0;
	}
	
	private static final int DEFAULT_TABLE_SIZE=101;
	
	private LinkedList<V>[] theLists;
	private int currentSize;
	
	public boolean contains(V x)
	{
		for(LinkedList<V> list:theLists)
			if(list.contains(x))
				return true;
		
		return false;
	}
	
	public void insert(K key,V value)
	{
		List<V> whichList=theLists[myhash(key)];
		
		if(!whichList.contains(value))
		{
			whichList.add(value);
			
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
		LinkedList<V>[] oldLists=theLists;
		
		//Create new double-sized,empty table
		LinkedList<V>[] newLists=new LinkedList[2*theLists.length];
		
		for(int j=0;j<theLists.length;j++)
			newLists[j]=new LinkedList<V>();
		
		currentSize=0;
		
		for(int i=0; i<oldLists.length; i++)
			for(V item:oldLists[i])
				insert(item);
	}
	
	private int myhash(K x)
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
