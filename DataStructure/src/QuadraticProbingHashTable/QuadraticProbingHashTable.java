package QuadraticProbingHashTable;

import javax.print.attribute.ResolutionSyntax;

public class QuadraticProbingHashTable<T> {
	
	public QuadraticProbingHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}
	
	public QuadraticProbingHashTable(int size) {
		allocateArray(nextPrime(size));
		makeEmpty();
	}
	
	public void makeEmpty() {
		for(int i=0;i<array.length;i++)
			array[i]=null;
		
		currentSize=0;
	}
	
	public boolean contains(T x) {
		int currentPos=findPos(x);
		
		return isActive(currentPos);
	}
	
	public void insert(T x) {
		int currentPos=findPos(x);
		
		if(isActive(currentPos))
			return;
		
		array[currentPos] =new HashEntry<T>(x);
		
		if(++currentSize>array.length/2)
			rehash();
	}
	
	public void remove(T x) {
		int currentPos=findPos(x);
		
		if(isActive(currentPos))
			array[currentPos].isAcitve=false;
	}
	
	public T getItem(int Pos) {
		return array[Pos].element;
	}
	
	private static class HashEntry<T> {
		public T element;
		public boolean isAcitve;
		
		public HashEntry(T e) {
			this(e, true);
		}
		
		public HashEntry(T e, boolean i)
		{
			this.element=e;
			this.isAcitve=i;
		}
	}
	
	private static final int DEFAULT_TABLE_SIZE=11;
	
	private HashEntry<T>[] array;
	private int currentSize;
	
	private void allocateArray(int arraySize) {
		array=new HashEntry[arraySize];
	}
	
	public boolean isActive(int currentPos) {
		return array[currentPos]!=null && array[currentPos].isAcitve;
	}
	
	public int findPos(T x) {
		int currentPos=myhash(x);
		int i=1;
		
		
		while(array[currentPos]!=null && !array[currentPos].element.equals(x)) {
			currentPos+=2*i-1;
			
			i++;
			
			if(currentPos>=array.length)
				currentPos+=array.length;
		}
		
		return currentPos;
			
	}
	
	private void rehash() {
		HashEntry<T>[] old=array;
		
		allocateArray(2*array.length);
		
		currentSize=0;
		
		for(HashEntry<T> item:old)
			if(item!=null && item.isAcitve)
				insert(item.element);
	}
	
	private int myhash(T x) {
		int hashVal=x.hashCode();
		
		hashVal %= array.length;
		
		if(hashVal<0)
			hashVal+=array.length;
		
		return hashVal;
	}
	
	private static int nextPrime(int n) {
		if(n%2==0)
			n++;
		
		while(!isPrime(n))
			n++;
		
		return n;
	}
	
	private static boolean isPrime(int n) {
		if(n<2)
			return false;
		
		if(n%2==0)
			return false;
		
		if(n==2 || n ==3)
			return true;
		
		for(int i=3;i*i<=n;i+=2)
			if(n%i==0)
				return false;
		
		return true;
	}
}
