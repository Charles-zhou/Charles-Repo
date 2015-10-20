package QuadraticProbingHashTable;

public class Map<K,V> {
	
	public Map() {
		items=new QuadraticProbingHashTable<Map.Entry<K,V>>();
	}
	
	public void put(K key, V val) {
		Entry<K, V> item=new Entry<K, V>(key, val);
			
		items.insert(item);
	}
	
	public V get(K key) {
		Entry<K, V> item=items.find(new Entry<K, V>(key, null));
		
		//The condition code of judging if item is null or not must be added.
		//Once item is null, and return item.value, there comes the NullPointerException.
		return item!=null?item.value:null;
		
	}
	
	private QuadraticProbingHashTable<Entry<K, V>> items;
	
	private static class Entry<K,V> {
		public K key;
		public V value;
		
		public Entry(K key,V value) {
			this.key=key;
			this.value=value;
		}
		
		@Override
		public int hashCode() {
			return key.hashCode();
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object o)
		{
			return o instanceof Entry && this.key.equals(((Entry<K, V>) o).key);
		}
	}
}
