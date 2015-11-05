package Sort;

public class Sort {
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
	
	public static void main(String[] args) {
		Integer[] array=new Integer[]{34,8,64,51,32,21};
		
		for (int a:array) {
			System.out.println(a);
		} 
		
		insertSort(array);
		
		System.out.println();
		
		for (int a:array) {
			System.out.println(a);
		} 
	}
}
