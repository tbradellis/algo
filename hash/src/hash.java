// hash.java
// updating this from linear probing to quadratic probing
import java.io.*;


//the data, or in essence the key that will be hashed and stored.

class DataItem {

   private int iData;

   public DataItem(int ii){
      iData = ii;
   }


   public int getKey() {
      return iData;
   }
}



class HashTable {
   private DataItem[] hashArray;
   private int arraySize;
   private DataItem nonItem;        // for deleted items

   public HashTable(int size) {
      arraySize = size;
      hashArray = new DataItem[arraySize];
      nonItem = new DataItem(-1);   // deleted item key is -1
   }


   public void displayTable() {
      System.out.print("Table: ");
      for(int j=0; j<arraySize; j++) {
         if(hashArray[j] != null)
            System.out.print(hashArray[j].getKey() + " ");
         else
            System.out.print("** ");
      }
      System.out.println();
   }


   public int hashFunc(int key) {
      return key % arraySize;       // hash function
   }
   // changed from linear probe to quadratic probe.  added second var (probeVal) so that hashVal can maintain the original has.
   public void insert(DataItem item){
      int key = item.getKey();
      int hashVal = hashFunc(key);
      int probeVal = hashVal;
      int step = 1;

      while(hashArray[probeVal] != null && hashArray[probeVal].getKey() != -1) {

         probeVal = hashVal + (int) Math.pow(step, 2);
         probeVal %= arraySize;      // wrap around if necessary (hint if hash val size of index, the hashVal mod arraysize = 0
         ++step;
      }

      hashArray[probeVal] = item;
   }



   public DataItem delete(int key) {
      int hashVal = hashFunc(key);

      while(hashArray[hashVal] != null) {
         if(hashArray[hashVal].getKey() == key) {
            DataItem temp = hashArray[hashVal]; // save item
            hashArray[hashVal] = nonItem;       // delete item
            return temp;                        // return item
            }
         ++hashVal;
         hashVal %= arraySize;
         }
      return null;
      }


   public DataItem find(int key){
      int hashVal = hashFunc(key);  // hash the key

      while(hashArray[hashVal] != null){
         if(hashArray[hashVal].getKey() == key)
            return hashArray[hashVal];   // yes, return item
         ++hashVal;                 // go to next cell
         hashVal %= arraySize;      // wraparound if necessary
      }

      return null;                  // can't find item
   }
}
class HashTableApp {
   public static void main(String[] args) throws IOException {
      DataItem aDataItem;
      int aKey, size, n, keysPerCell;
                                    // get sizes
      System.out.print("Enter size of hash table: ");
      size = getInt();
      System.out.print("Enter initial number of items: ");
      n = getInt();
      keysPerCell = 10;
                                    // make table
      HashTable theHashTable = new HashTable(size);

      for(int j=0; j<n; j++){
         aKey = (int)(java.lang.Math.random() *
                                         keysPerCell * size);
         aDataItem = new DataItem(aKey);
         theHashTable.insert(aDataItem);
      }

      while(true){
         System.out.print("Enter first letter of ");
         System.out.print("show, insert, delete, or find: ");
         char choice = getChar();
         switch(choice) {
            case 's':
               theHashTable.displayTable();
               break;
            case 'i':
            System.out.print("Enter key value to insert: ");
               aKey = getInt();
               aDataItem = new DataItem(aKey);
               theHashTable.insert(aDataItem);
               break;
            case 'd':
               System.out.print("Enter key value to delete: ");
               aKey = getInt();
               theHashTable.delete(aKey);
               break;
            case 'f':
               System.out.print("Enter key value to find: ");
               aKey = getInt();
               aDataItem = theHashTable.find(aKey);
               if(aDataItem != null) {
                  System.out.println("Found " + aKey);
               }
               else
                  System.out.println("Could not find " + aKey);
               break;
            default:
               System.out.print("Invalid entry\n");
         }  // end switch
      }  // end while
   }  // end main()
   public static String getString() throws IOException {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      String s = br.readLine();
      return s;
   }


   public static char getChar() throws IOException {
      String s = getString();
      return s.charAt(0);
   }


   public static int getInt() throws IOException {
      String s = getString();
      return Integer.parseInt(s);
   }
}
