import java.util.Iterator;

public class MyArrayListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyArrayList<String> strList=new MyArrayList<>();
		
		strList.add("你是我心中最美的云彩");
		strList.add("让我用爱把你留下来~~");
		
		Iterator<String> iter=strList.iterator();
		
		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}
		
	}

}
