package MyLinkedList;
import java.util.*;

public class MyLinkedListTest
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		MyLinkedList<String> aLinkedList=new MyLinkedList<String>();
		
		aLinkedList.add("°Ö°Ö");
		
		aLinkedList.add("È¥ÄÄ¶ù");
		
		System.out.println(aLinkedList.size()+aLinkedList.get(1));
		
		aLinkedList.add(1,"hah");
		
		for (int i = 0; i < aLinkedList.size(); i++)
		{
			System.out.println(aLinkedList.get(i));
		}
		
		aLinkedList.remove(1);
		
		Iterator<String> iter=aLinkedList.iterator();
		
		while(iter.hasNext())
		{
			aLinkedList.remove(2);
			System.out.println(iter.next());
			
		}
		
		
		System.out.println(aLinkedList.size());
	}

}
