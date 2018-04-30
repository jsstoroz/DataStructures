/**
 * This class creates records for the hashTable, with keys and values
 * @author Jennifer Storozum
 * COSI 21a  PA3
 * 8/8/17
 * @param <K>
 * @param <V>
 */
public class HashRecord<K, V> {
		private K key;
		private V value;
		
		public HashRecord(K key, V val){
			this.key = key;
			this.value = val;
		}
		
		public K getKey(){
			return this.key;
		}
		
		public V getValue(){
			return this.value;
		}
		
		public void setKey(K key){
			this.key = key;
		}
		
		public void setValue(V val){
			this.value = val;
		}
		
	}