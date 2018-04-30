/**
 * @author Jennifer Storozum
 * COSI 21a PA3
 * 8/8/17
 * This class is a generic class creating a HashTable using open addressing and double hashing.
 * I used k mod m and 2027 - (k % 2027) as my hash functions because k mod m is kind of like a
 * baseline, and 2027 is the next smallest prime number less than the initial table size 2029
 * @param <K>
 * @param <V>
 */
public class HashTable<K, V> {

	private static final double LOAD_FACTOR_THRESHOLD = 0.5;
	private int capacity;
	private int size;
	private HashRecord<K, V>[] table;

	/**
	 * Constructor creates a HashTable object of size capacity
	 * Initializes every slot to null
	 * @param capacity
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int capacity){
		this.capacity = capacity;
		this.size = 0;
		table = new HashRecord[capacity];
		for(int i = 0; i < capacity; i++){
			table[i] = null;
		}
	}
	
	/**
	 * First hash function is k mod m
	 * @param k
	 * @return
	 */
	public int hashFunction1(K k){
		int hash1 = k.hashCode();
		if(hash1 < 0){
			hash1 *= -1;
		}
		return hash1 % capacity;
	}
	
	/**
	 * Second hash function is 2027 - (hash % 2027)
	 * @param k
	 * @return
	 */
	public int hashFunction2(K k){
		int hash = k.hashCode();
		if(hash < 0){
			hash *= -1;
		}
		int hash2 = 2027 - (hash % 2027);
		return hash2 % capacity;
	}
	
	/**
	 * Calculates the hash address for double hashing by combining the two 
	 * hash functions. If the slot is full then increase the index. returns -1
	 * if k is unhashable
	 * @param k
	 * @return hash address
	 */
	public int calcHash(K k){
		int i = 0;
		int hash = -1;
		while(i < capacity){
			int hashF = (hashFunction1(k) + (i * hashFunction2(k))) % capacity;
			//if slot occupied, increment
			if(table[hashF]!=null && !table[hashF].getKey().equals(k)){
				i++;
			} 
			else return hashF;
		}
		//if unhashable
		return hash;
	}

	/**
	 * Inserts a give key value pair into the map
	 * 	//The ability to add a record containing key and value at the position indicated by
	//the result of the hash function.
	 * Resizes the map if the load factor is exceeded.
	 * @param key
	 * @param value
	 */
	public void add(K key, V value){
		if((key == null) || (value == null)){
			throw new IllegalArgumentException();
		}
		int hashV = calcHash(key);
		table[hashV] = new HashRecord<K, V>(key, value);
		size++;
		//resize the map and rehash if load factor is met or exceeded
		if(this.size/this.capacity >= this.LOAD_FACTOR_THRESHOLD){
			this.resize();
		}
	}
	
	/**
	 * get a value from the map given a key. Otherwise throw not found exception
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public V get(K key) throws Exception{
		if(key == null){ throw new IllegalArgumentException();}
		int hash = calcHash(key);
		if(table[hash].getKey().equals(key)){
			return table[hash].getValue();
		} else {
			throw new Exception("Key not in map");
		}
	}
	
	/**
	 * Delete a key from the map by setting the key and value to null
	 * @param key
	 */
	public void delete(K key){
		if(key == null){ throw new IllegalArgumentException();}
		if(!this.contains(key)){return;}
		int hash = calcHash(key);
		table[hash].setKey(null);
		table[hash].setValue(null);
	}

	/**
	 * Check if the map already contains a key
	 * @param key
	 * @return
	 */
	public boolean contains(K key){
		int hash = calcHash(key);
		if(table[hash] != null){
			return true;
		} else return false;
	}
	
	/**
	 * resize the hash table and rehash when load factor is too high
	 * by making a table with 2x capacity and copying all the records onto the
	 * new table
	 */
	public void resize(){
		HashRecord<K, V>[] oldTable = table;
		HashTable<K, V> newHT = new HashTable<>(capacity * 2);
		this.table = newHT.table;
		this.capacity = capacity * 2;
		this.size = 0;
		for(HashRecord<K, V> record : oldTable){
			if(record != null){
				this.add(record.getKey(), record.getValue());
			}
		}
	}
}
