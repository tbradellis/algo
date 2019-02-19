// hash.java
// updating this from linear probing to quadratic probing
import java.io.*;


//the data, or in essence the key that will be hashed and stored.

class DataItem {

   private String iData;

   public DataItem(String ss){
      iData = ss;
   }


   public String getKey() {
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
      nonItem = new DataItem("-1");   // deleted item key is -1
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
//Take string
   //

   public int hashFunc(String key) {
      char[] temp = key.toCharArray();
      int bigNumber = 0;
      for(int i = 0; i < key.length()-1; i++){
         bigNumber += (temp[i] - 96) * (int) Math.pow(26, key.length() - i);
      }
      System.out.println(bigNumber);
      return bigNumber % arraySize;       // hash function
   }
//change to linear probe
   public void insert(DataItem item){
      String key = item.getKey();
      int hashVal = hashFunc(key);
      System.out.println(hashVal);

      while(hashArray[hashVal] != null && !hashArray[hashVal].getKey().equals("-1")) {

         hashVal += 1;
         hashVal %= arraySize;      // wrap around if necessary (hint if hash val size of index, the hashVal mod arraysize = 0
         System.out.println(hashVal);

      }

      hashArray[hashVal] = item;
   }



   public DataItem delete(String key) {
      int hashVal = hashFunc(key);

      while(hashArray[hashVal] != null) {
         if(hashArray[hashVal].getKey().equals(key)) {
            DataItem temp = hashArray[hashVal]; // save item
            hashArray[hashVal] = nonItem;       // delete item
            return temp;                        // return item
            }
         ++hashVal;
         hashVal %= arraySize;
         }
      return null;
      }


   public DataItem find(String key){
      int hashVal = hashFunc(key);  // hash the key

      while(hashArray[hashVal] != null){
         if(hashArray[hashVal].getKey().equals(key))
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
      int size;
      String aKey;
                                    // get sizes
      System.out.print("Enter size of hash table: ");
      size = getInt();
                                    // make table
      HashTable theHashTable = new HashTable(size);


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
               aKey = getString();
               aDataItem = new DataItem(aKey);
               theHashTable.insert(aDataItem);
               break;
            case 'd':
               System.out.print("Enter key value to delete: ");
               aKey = getString();
               theHashTable.delete(aKey);
               break;
            case 'f':
               System.out.print("Enter key value to find: ");
               aKey = getString();
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
