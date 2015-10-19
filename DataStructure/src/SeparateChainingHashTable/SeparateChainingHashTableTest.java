package SeparateChainingHashTable;

public class SeparateChainingHashTableTest
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		SeparateChainingHashTable<String> separateChainingHashTable=new SeparateChainingHashTable<String>();
		
		separateChainingHashTable.insert("hello");
		
		System.out.println(separateChainingHashTable.contains("Hello"));
		System.out.println(separateChainingHashTable.contains("hello"));
	}

}
