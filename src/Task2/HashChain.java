package Task2;

public class HashChain {
    private SortedList[] hashArray;

    private int arraySize;

    public HashChain(int size) {
        arraySize = size;
        hashArray = new SortedList[arraySize];
        for (int i = 0; i < arraySize; i++)
            hashArray[i] = new SortedList();
    }

    public void displayTable() {
        for (int j = 0; j < arraySize; j++) {
//            System.out.print(j + ". ");
            hashArray[j].displayList();
        }
    }

    public int hashFunc(int key) {
        return key % arraySize;
    }
    public int hashFunc2(int key, int[] emptyArray) {
        int pos = key % emptyArray.length;
        if (isActive(emptyArray, pos)) {
            return pos;
        }
        while (!isActive(emptyArray, pos)) {
//          liner
            pos = (pos + 1) % emptyArray.length;
        }
        return pos;

    }

    public void insertSeparateChaining(Link theLink) {
        int key = theLink.getKey();
        int hashVal = hashFunc(key);
        hashArray[hashVal].insert(theLink);
    }

    public void insert_Hash_table_using_linear_probing(Link theLink,int[] emptyArray) {
        int key = theLink.getKey();
        int hashVal = hashFunc2(key,emptyArray);
        hashArray[hashVal].insert(theLink);
    }
    private static boolean isActive(int[] emptyArray, int currentPos) {
        if (emptyArray[currentPos] == 0) {
            return true;
        } else return false;
    }
}
