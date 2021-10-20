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
    	// Basic Hash function implemented
    	int hash = key % HASH_TABLE_SIZE;
    	// Checking if record is normal & key matches given key
    	if (hashTable[hash].isNormal() && hashTable[hash].getKey() == key) return hash;
    	int count = 0;
    	do {
    		// Probe function to loop through hash table, if count reaches the table size, return -1, as we have checked everything
    		hash = probeFunc(hash);
    		if (hashTable[hash].isNormal() && hashTable[hash].getKey() == key) return hash;
    		count++;
    	} while (count != HASH_TABLE_SIZE);
    	return -1;
    }
    
    private int probeFunc(int current) {
    	
    	return ((current * current) + 1) % HASH_TABLE_SIZE;
    	
    }

    /*Finds an element with a certain key and returns the associated Customer*/
    public Customer find(int key) {
    	
    	// uses indexOf() to find key value, returns the customer associated
    	if (indexOf(key) == -1) return null;
    	else {
    		Customer temp = hashTable[indexOf(key)].getValue();
    		return temp;
    	}
    }

    /*Inserts the key/value into the hashtable*/
    public boolean insert(int key, Customer value) {
    	
    	// Implements both hash and probe function to find open slot to insert
    	
    	if (currentSize == HASH_TABLE_SIZE) return false; // base check
    	int index = key % HASH_TABLE_SIZE;
    	if (hashTable[index].isEmpty() || hashTable[index].isTombstone()) { // first check after hash function
    		hashTable[index] = new Record(key, value);
    		currentSize++;
    		return true;
    	} else if (!hashTable[index].isEmpty()){
    		int count = 0;
    		do { // implements probe function to find open slot, checking if empty or tombstone
    			index = probeFunc(index);
    			if (hashTable[index].isEmpty() || hashTable[index].isTombstone()) {
    				hashTable[index] = new Record(key, value);
    				currentSize++;
    				return true;
    			} 
    			count++;
    		} while (count != HASH_TABLE_SIZE - 1);
    	}
        return false;
    }

    /*Kills a table key and returns the associated value*/
    public Customer remove(int key) {
    	// Uses indexOf to find key value in table
    	// Stores it in temp, deletes the record and returns the value of temp
    	Customer temp;
    	if (indexOf(key) == -1) return null;
    	temp = hashTable[indexOf(key)].getValue();
    	hashTable[indexOf(key)].deleteRecord();
    	currentSize--;
        return temp;
    }
    
    public int getSize() {
    	return HASH_TABLE_SIZE;
    }
    
    public int getCurrentSize() {
    	return currentSize;
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
