import java.util.*;

import org.omg.CORBA.PUBLIC_MEMBER;

public class MyArrayList<T> implements Iterable<T>{
	private static final int DEFAULT_CAPACITY=10;
	private int theSize;
	private T[] items;
		
	public MyArrayList()
	{
		clear();
		ensureCapacity(DEFAULT_CAPACITY);
	}
	
	public int size()
	{
		return this.theSize;
	}
	
	public void clear()
	{
		theSize=0;
		
	}
	
	public boolean isEmpty()
	{
		return size()==0;
	}
	
	public boolean contains(Object obj)
	{
		return indexOf(obj)>=0;
	}
	
	public int indexOf(Object obj)
	{
		if (obj==null) {
			for(int i=0;i<size();i++)
			{
				if (items[i]==null) {
					return i;
				}
			}
		}
		else {
			for(int i=0;i<size();i++)
			{
				if (items[i].equals(obj)) {
					return i;
				}
			}
		}
		
		return -1;
	}
	

	
	public T get(int i)
	{
		if (i<0 || i>=size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return items[i];
	}
	
	public void set(int i,T newVal)
	{
		if (i<0 || i>=size()) 
			throw new ArrayIndexOutOfBoundsException();
		items[i]=newVal;
	}
	
	public void add(T obj)
	{
		add(size(),obj);		
	}
	
	public void add(int i,T obj)
	{
		if (i<0) 
			throw new IndexOutOfBoundsException();
		else if(theSize==items.length)
			ensureCapacity(items.length*2+1);
		
		for(int j=size();j>i;j--)
			items[j]=items[j-1];
		
		items[i]=obj;
		
		theSize++;
	}
	
	public void remove(int i)
	{
		if(i<0 || i>=size())
			throw new IndexOutOfBoundsException();
		
		for(int j=i;j<size();j++)
			items[j]=items[j+1];
		
		theSize--;
	}
	
	public void ensureCapacity(int newCapacity)
	{
		if(newCapacity<theSize)
			return;
		else
		{	
			T[] old=items;
 			items=(T [])new Object[newCapacity];
 			for(int i=0;i<size();i++)
 			{
 				items[i]=old[i];
 			} 			
		}		
	}
	
	public Iterator<T> iterator()
	{
		return new Iterator<T>(){
			private int current=0;
			
			public boolean hasNext()
			{
				return current<size();
			}
			
			public T next()
			{
				if(!hasNext())
					throw new NoSuchElementException();
				
				return items[current++];
			}
			
			public void remove()
			{
				MyArrayList.this.remove(--current);
			}
			
		};
	}
	
	
}
