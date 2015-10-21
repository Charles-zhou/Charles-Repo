package BinaryHeap;

import java.util.*;

public class BinaryHeap<T extends Comparable<? super T>> {
	
	public BinaryHeap() {
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public BinaryHeap(int size) {
		currentSize=0;
		array=(T[])new Comparable[size+1];
	}
	
	public BinaryHeap(T[] items) {
		currentSize=items.length;
		
		array=(T []) new Comparable[(currentSize+2)*11/10];
		
		int i=1;
		
		for(T item:items) 
			array[i++]=item;
		
		buildHeap();
	}
	
	public void insert(T x) {
		
		if(++currentSize>array.length-1)
			enlargeArray(2*array.length+1);
		
		int hole=currentSize;
		
		while(hole>1 && x.compareTo(array[hole/2])<0) {
			array[hole]=array[hole/2];
			hole/=2;
		}
		
		array[hole]=x;
	}
	
	public T deleteMin() {
		if(isEmpty())
			throw new NoSuchElementException();
		
		T minItem=findMin();
		
		array[1]=array[currentSize--];
		
		percolateDown(1);
		
		return minItem;
	}
	
	public T findMin() {
		if(isEmpty())
			return null;
		else 
			return array[1];
	}
	
	public boolean isEmpty() {
		return currentSize==0;
	}
	
	public void makeEmpty() {
		for(int i=0;i<array.length;i++)
			array[i]=null;
		
		currentSize=0;
	}
	
	private static final int DEFAULT_CAPACITY=10;
	private T[] array;
	private int currentSize;
	
	private void percolateDown(int hole) {
		
		int child;
		T temp;
		
		while((2*hole+1)<=currentSize) {
			child=2*hole;
			
			if(array[child].compareTo(array[child+1])>0)
				child++;
			
			if(array[hole].compareTo(array[child])>0) {
				temp=array[hole];
				array[hole]=array[child];
				array[child]=temp;
			}
			else {
				break;
			}
			
			hole=child;			
		}		
		
	}
	
	private void buildHeap() {
		for(int i=currentSize/2;i>0;i--) {
			percolateDown(i);
		}
	}
	
	private void enlargeArray(int newSize) {
		T[] old=array;
		T[] newArray=(T[])new Object[newSize];
		
		for (int i = 0; i < old.length; i++) {
			newArray[i]=old[i];
		}
	}
	
	 public static void main( String [ ] args )
     {
         int numItems = 10000;
         BinaryHeap<Integer> h = new BinaryHeap<Integer>( numItems );
         int i = 37;

         try
         {
             for( i = 37; i != 0; i = ( i + 37 ) % numItems )
                 h.insert( i );
             
             for( i = 1; i < numItems; i++ )
                 if( h.deleteMin() != i )
                     System.out.println( "Oops! " + i );

             for( i = 37; i != 0; i = ( i + 37 ) % numItems )
                 h.insert( i );
             
             h.insert( 0 );
             i = 9999999;
             h.insert( i );
             
             for( i = 1; i <= numItems; i++ )
                 if( h.deleteMin() != i )
                     System.out.println( "Oops! " + i + " " );
         }
         catch( Exception e )
           { System.out.println( "Overflow (expected)! " + i  ); }
     }
}
