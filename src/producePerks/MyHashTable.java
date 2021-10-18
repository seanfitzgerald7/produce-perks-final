/**
 * This is where you will implement your hash table.
 */
package producePerks;

/**
 * @author Katie Timmerman
 * @author Sean Fitzgerald
 * 
 * Course: Data Structures and Algorithms
 * Semester: Fall 2021
 */

public class MyHashTable {

    private final int HASH_TABLE_SIZE; //Capacity of the table
    private Record[] hashTable; // the array of records
    private int currentSize; //Number of values in the table
   
    public MyHashTable(int size) {
        this.HASH_TABLE_SIZE = size;
        hashTable = new Record[HASH_TABLE_SIZE];
        currentSize = 0;
        for (int i = 0; i < HASH_TABLE_SIZE; i++) {
            Record r = new Record();
            hashTable[i] = r;
        }
    }

    /**
     * This is a private method only to be used internally.
     * It returns the index where the record with the key is stored in the table.
     * It returns -1 if the key is not found in the table.
     * 
     * @param key
     * @return 
     */
    private int indexOf(int key) {
    	int hash = key % HASH_TABLE_SIZE;
    	if (hashTable[hash].getKey() == -1) return -1;
    	else {
    		if (hashTable[hash].getKey() == key) return hash;
    		else {
    			int count = 0;
				do {
    				hash = probeFunc(hash);
    				if (hashTable[hash].getKey() == key) return hash;
    				count++;
    			} while (count != currentSize);
    			return -1;
    		}
    	}
    }
    
    private int probeFunc(int current) {
    	
    	return ((current * current) + 1) % HASH_TABLE_SIZE;
    	
    }

    /*Finds an element with a certain key and returns the associated Customer*/
    public Customer find(int key) {
    	int index = key % HASH_TABLE_SIZE;
    	int curr = indexOf(index);
        if (curr != -1) {
        	if (hashTable[curr].isTombstone() || hashTable[curr].isEmpty()) return null;
        	else if (hashTable[curr].getKey() == key) return hashTable[curr].getValue();
        } else {
        	return null;
        }
        return null;
    }

    /*Inserts the key/value into the hashtable*/
    public boolean insert(int key, Customer value) {
    	if (currentSize == HASH_TABLE_SIZE) return false;
    	int index = key % HASH_TABLE_SIZE;
    	if (hashTable[index].isEmpty()) {
    		hashTable[index] = new Record(key, value);
    		currentSize++;
    		return true;
    	} else if (!hashTable[index].isEmpty()){
    		int count = 0;
    		do {
    			index = probeFunc(index);
    			if (hashTable[index].isEmpty()) {
    				hashTable[index] = new Record(key, value);
    				currentSize++;
    				return true;
    			} 
    			count++;
    		} while (count != HASH_TABLE_SIZE);
    	}
        return false;
    }

    /*Kills a table key and returns the associated value*/
    public Customer remove(int key) {
    	Customer temp;
    	if (indexOf(key) == -1) return null;
    	temp = hashTable[indexOf(key)].getValue();
    	hashTable[indexOf(key)].deleteRecord();
        return temp;
    }
    
    
    public void getTotals() {
    }

    /**
     * returns a string representation of the hash table
     * @return 
     */
    public String toString() {
        String table = "";
        for (int i = 0; i < this.HASH_TABLE_SIZE; i++) {
            table += i + ". " + hashTable[i].toString() + "\n";
        }
        return table;
    }

}
