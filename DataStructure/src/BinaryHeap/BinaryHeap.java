package BinaryHeap;

public class BinaryHeap<T extends Comparable<? super T>> {
	
	public BinaryHeap() {
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public BinaryHeap(int size) {
		currentSize=0;
		array=(T[])new Object[size+1];
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
	
	private void enlargeArray(int newSize) {
		T[] old=array;
		T[] newArray=(T[])new Object[newSize];
		
		for (int i = 0; i < old.length; i++) {
			newArray[i]=old[i];
		}
	}
	
}
