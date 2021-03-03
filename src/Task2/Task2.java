package Task2;

import java.util.Arrays;

public class Task2 {
    private static final int[] myElements = {4371, 1323, 6173, 4199, 4344, 9679, 1989};
    private static final int n = 10;
    static int[] emptyArray = new int[n];

    public static void main(String[] args) {

//              A
        System.out.println("             A          Separate chaining\n");
        hA();


//              B
        System.out.println("\n            B         liner probing\n");
        for (int myElement : myElements) {
            emptyArray[hB(myElement, emptyArray)] = myElement;
        }
        for (int i = 0; i < emptyArray.length; i++) {

            System.out.println(i+ " . List :" + emptyArray[i]);
        }
        emptyArray = new int[n];


//              C
        System.out.println("\n            C         Quadratic probing\n");
        // Quadratic probing
        Quadratic(myElements, emptyArray.length, emptyArray, myElements.length);

        for (int i = 0; i < emptyArray.length; i++) {

            System.out.println(i+ " . List :" + emptyArray[i]);
        }

        emptyArray = new int[n];



//              E
        System.out.println("\n            E         second hash function \n");
        for (int myElement : myElements) {
            emptyArray[hD(myElement, emptyArray)] = myElement;
        }
        for (int i = 0; i < emptyArray.length; i++) {

            System.out.println(i+ " . List :" + emptyArray[i]);
        }
        emptyArray = new int[n];

//
        System.out.println("\n            F         Rehash  \n");
        int[] empty2 =new int[n *2];
        for (int value : myElements) {
            empty2[hD(value, empty2)] = value;
        }
        for (int i = 0; i < empty2.length; i++) {

            System.out.println(i+ " . List :" + empty2[i]);
        }
    }

    private static void hA() {

        HashChain hashTable = new HashChain(n);
        for (int myElement : myElements) {
            Link link = new Link(myElement);
            hashTable.insertSeparateChaining(link);

        }
        hashTable.displayTable();

    }

    private static int hB(int i, int[] emptyArray) {
        int pos = i % emptyArray.length;
        if (isActive(emptyArray, pos)) {
            return pos;
        }
        while (!isActive(emptyArray, pos)) {
//          liner
            pos = (pos + 1) % emptyArray.length;
        }
        return pos;
    }


    private static int hD(int i, int[] emptyArray) {
        int pos = i % emptyArray.length;
        if (!isActive(emptyArray, pos)) {
            pos = 7 - (i % 7);
            if (!isActive(emptyArray, pos)) {
                pos =  7 - (i % 7);
            }
        }

        return pos;
    }


    private static boolean isActive(int[] emptyArray, int currentPos) {
        if (emptyArray[currentPos] == 0) {
            return true;
        } else return false;
    }



    static void Quadratic(int[] myElements, int emptySize,
                          int[] emptyArray, int N) {

        // Iterating through the array
        for (int i = 0; i < N; i++) {

            // Computing the hash value
            int hv = myElements[i] % emptySize;

            // Insert in the table if there
            // is no collision
            if (emptyArray[hv] == 0)
                emptyArray[hv] = myElements[i];
            else {

                // If there is a collision
                // iterating through all
                // possible quadratic values
                for (int j = 0; j < emptySize; j++) {

                    // Computing the new hash value
                    int t = (hv + j * j) % emptySize;
                    if (emptyArray[t] == 0) {

                        // Break the loop after
                        // inserting the value
                        // in the table
                        emptyArray[t] = myElements[i];
                        break;
                    }
                }
            }

        }
    }


}
