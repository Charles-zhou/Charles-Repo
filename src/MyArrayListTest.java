import java.util.Iterator;

public class MyArrayListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyArrayList<String> strList=new MyArrayList<>();
		
		strList.add("�����������������Ʋ�");
		strList.add("�����ð�����������~~");
		
		Iterator<String> iter=strList.iterator();
		
		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}
		
	}

}
