package QuadraticProbingHashTable;

public class MayTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Integer> salaryMap=new Map<String, Integer>();
		
		salaryMap.put("Charles", 800000);
	
		System.out.println(salaryMap.get("Charles"));
		
		salaryMap.put("Charles", 1000000);
		
		System.out.println(salaryMap.get("Charles"));
		System.out.println(salaryMap.get("Pony"));
	}

}
