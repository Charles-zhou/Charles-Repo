package BinaryHeap;

public class BinaryHeap<T> {
	
	public BinaryHeap(int size) {
		array=(T[]) Object[size];
	}
	
	public boolean isEmpty() {
		return array[1]==null;
	}
	
	public void makeEmpty() {
		for(int i=0;i<array.length;i++)
			array[i]=null;
	}
	
	private static final int DEFAULT_CAPACITY=10;
	private T[] array;
	private int currentSize;
	
}
