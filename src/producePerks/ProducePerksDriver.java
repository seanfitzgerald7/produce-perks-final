/**
 * This is the driver class. 
 * Note that it should NEVER interact with the Record class.
 * 
 * MyHashTable.java is your creation of a Hash Table.
 * Record.java is a record object that is stored in the hash table.
 * Transaction.java is a storage object that is formed based on one line of the data file.
 * Customer.java is a storage object that represents one person who uses produce perk.
 * 
 * Links between classes
 * The hash table has an array of Records.
 * Each Record holds a (key, value) pair where the key is the SNAP-ID and the value is the Customer.
 * Each Customer has two ArrayLists (dynamic arrays) to hold their transactions: distributed and redeemed.
 */
package producePerks;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Katie Timmerman
 * @author < your name >
 *
 * Course: Data Structures and Algorithms Semester:
 */
public class ProducePerksDriver {

    private static ArrayList<Integer> customer_keys;
    private static MyHashTable table;

    public static void main(String[] args) throws Exception {
        // debuggingFunctions();
        loadData();
        completeAnalysis();

    }

    public static void debuggingFunctions() {
        MyHashTable myTable = new MyHashTable(7);
        ArrayList<Integer> addedKeys = new ArrayList<Integer>();
        System.out.println("TESTING ADDING ELEMENTS");
        for (int i = 0; i < 5; i++) {
            int key_value = (int) (Math.random() * 20);
            if (myTable.insert(key_value, new Customer(key_value))) {
                addedKeys.add(key_value);
            } else {
                i--; //Don't count the insert if key already in table
            }
        }
        System.out.println(myTable);

        System.out.println("\nTESTING FINDING ELEMENTS");
        for (int i = 0; i < addedKeys.size(); i += 2) {
            int key = addedKeys.get(i);
            System.out.println("The value " + key + " found: " + myTable.find(key));
        }
        System.out.println(myTable);

        System.out.println("\nTESTING REMOVING ELEMENTS");
        for (int i = 0; i < addedKeys.size(); i += 2) {
            int key = addedKeys.get(i);
            System.out.println("The value " + key + " removed: " + myTable.remove(key));
        }
        System.out.println(myTable);

        System.out.println("\nTESTING FINDING REMOVED ELEMENTS");
        for (int i = 0; i < addedKeys.size(); i += 2) {
            int key = addedKeys.get(i);
            System.out.println("The value " + key + " found: " + myTable.find(key));
        }
        System.out.println(myTable);

        System.out.println("\nTESTING FINDING VALID ELEMENTS AFTER REMOVAL");
        for (int i = 1; i < addedKeys.size(); i += 2) {
            int key = addedKeys.get(i);
            System.out.println("The value " + key + " found: " + myTable.find(key));
        }
        System.out.println(myTable);

    }

    public static void loadData() throws Exception {
        customer_keys = getCustomerIds();
        table = new MyHashTable(12007);

        //Create and Add Customers
        for (int i = 0; i < customer_keys.size(); i++) {
            int key = customer_keys.get(i);
            table.insert(key, new Customer(key));
        }

        //Read in transactions
        readInTransactions(2019, 1);
        readInTransactions(2020, 1);
        readInTransactions(2019, 2);
        readInTransactions(2020, 2);
        
        System.out.println(table); //FOR DEBUGGING PURPOSES ONLY

    }

    private static ArrayList<Integer> getCustomerIds() {
        ArrayList<Integer> keys = new ArrayList();

        try {
            File file_ids = new File("Customer Ids.csv");
            Scanner in = new Scanner(file_ids);

            while (in.hasNext()) {
                keys.add(in.nextInt());
            }

            in.close();

        } catch (FileNotFoundException ex) {
            System.err.println("File Customer Ids.csv not found");
            System.exit(0);
        }

        return keys;

    }

    /**
     * This method reads in transactions from the file <Type> <year>
     * transactions.csv Each line of the file is a transaction. See the
     * transaction class for details of how that line should be organized. The
     * transaction is added to the corresponding Customer in the class Hash
     * Table table.
     *
     * @param year year of the file to be uploaded.
     * @param type pass 1 for distribution or 2 for redeemed
     */
    private static void readInTransactions(int year, int type) throws Exception {
        String fileName = "Distributed " + year + " transactions.csv";
        if (type == 2) {
            fileName = "Redeemed " + year + " transactions.csv";
        } else if (type != 1) {
            throw new Exception("type must be 1 (distributed) or 2 (redeemed)");
        }

        try {
            File file = new File(fileName);
            Scanner in = new Scanner(file);

            in.nextLine(); //remove headers

            while (in.hasNext()) {
                String line = in.nextLine();
                Transaction t = new Transaction(line);
                int id = t.getId();

                Customer c = table.find(id);
                if (c == null) {
                    System.err.println("Customer " + id + " was not located");
                } else if (type == 1) {
                    c.addDistributed(t);
                } else if (type == 2) {
                    c.addRedeemed(t);
                } // end else if
            } // end while in.hasNext

            in.close();

        } catch (FileNotFoundException ex) {
            System.err.println("File " + fileName + " not found.");
        }
    }

    private static void completeAnalysis() {
        System.out.println("Code to answer your question goes here.");
        // get total spent with snap for each index in hash table
    }

}
