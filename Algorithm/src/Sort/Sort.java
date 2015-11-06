package Sort;

import java.util.*;


public class Sort {
	/**
	 * this method will sort the array a by using insert sorting algorithm.
	 * @param a an array of comparable items.
	 */
	public static <T extends Comparable<? super T>> void insertSort(T[] a) {
		int j;
		T tmp;
		for(int p=1 ; p<a.length ; p++) {
			tmp=a[p];
			for(j=p ; j>0 && tmp.compareTo(a[j-1])<0 ; j--)
				a[j]=a[j-1];                 //find out an index which a[j+1]>a[p] and a[j-1]<a[p].
			
			//when the loop is out, it means the index has been found.
			a[j]=tmp;
			
		}
		
	}
		
	/**
	 * this method will sort the array a by using shell sorting algorithm.
	 * @param a an array of comparable items.
	 */
	public static <T extends Comparable<? super T>> void shellSort(T[] a) {
		int j;
		T tmp;
		
		for(int gap=a.length/2; gap>0; gap/=2) 
			//the algorithm below is similar with insert sort.
			//the only difference is the  initial value of p has been changed to gap.
			for(int p=gap; p<a.length; p++) {
				tmp=a[p];
				for(j=p; j>=gap && tmp.compareTo(a[j-gap])<0; j-=gap)
					a[j]=a[j-gap];
				a[j]=tmp;	
			}
	}
	
	
	/**
	 * the percolate down method for building heap.
	 * The array stores items from index 0.
	 * @param a an array of comparable items.
	 * @param i the position from which to percolate down.
	 * @param n the logical size of the binary heap.
	 */
	private static <T extends Comparable<? super T>> void percDown(T[] a, int i, int n) {
		int child;
		T tmp;
		
		for(tmp=a[i] ; 2*i+1<n ; i=child) {
			child=2*i+1;
			//check if child!=n-1 to avoid the index out of bound exception.
			if(child!=n-1 && a[child].compareTo(a[child+1])<0) 
				child++;
			
			if(tmp.compareTo(a[child])<0)
				a[i]=a[child];
			else 
				break;
		}
		
		a[i]=tmp;
	}
	
	public static <T extends Comparable<? super T>> void heapSort(T[] a) {
		
		//build a heap
		for(int i=a.length/2; i>=0 ; i--) 
			percDown(a, i, a.length);
		
		//sort the array by deleting the minimum items from the array.
		for(int currentSize=a.length ; currentSize>0 ;) {
			T min=a[0];
			//put the last index of the heap into a[0] and percolate down from 0.
			a[0]=a[--currentSize];
			percDown(a, 0, currentSize);
			
			 a[currentSize]=min;
		}
		
	}
	
	public static void main(String[] args) {
		
		for(int n=10;n<=10000000;n*=10) {
			
			System.out.println("The number of integer is " +n);
			Integer[] array=new Integer[n];
			
			Random r=new Random(n);
			
			for(int i=0;i<array.length;i++) {
				array[i]=r.nextInt(n)+1;
			}
			
			//Integer[] array=new Integer[]{81,94,11,96,12,35,17,95,28,58,41,75,15};
			Integer[] array1=array.clone();		
			
			//System.out.println(Arrays.toString(array1));
			/*
			long start=System.nanoTime();
			insertSort(array1);
			long end=System.nanoTime();
			
			System.out.println("Insert Sort:"+(end-start)+" ns");		
			//System.out.println(Arrays.toString(array1));
			
			array1=array.clone();*/
			
			long start=System.nanoTime();
			shellSort(array1);
			long end=System.nanoTime();
			
			System.out.println("Shell Sort:"+(end-start)+" ns");		
			//System.out.println(Arrays.toString(array1));
			
			array1=array.clone();
			start=System.nanoTime();
			heapSort(array1);
			end=System.nanoTime();
			
			System.out.println("Heap Sort:"+(end-start)+" ns");		
			//System.out.println(Arrays.toString(array1));
			
			System.out.println();
		}
	}
}
