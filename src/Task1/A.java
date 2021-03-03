package Task1;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class A {
    static NumberFormat formatter;
    private static int N = 10;
    private static long execution;

    public static void main(String[] args) {
        testing();
//        for (int i = 0; i < 7; i++) {
//            testing();
//            N = N * 10;
//            System.out.println("\n");
//        }

    }


    static void heapify(int[] arr, int i) {
        int Smallest = i; // Initialize Smallest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is Smallest than root
        if (l < arr.length && arr[l] < arr[Smallest])
            Smallest = l;

        // If right child is Smallest than largest so far
        if (r < arr.length && arr[r] < arr[Smallest])
            Smallest = r;

        // If Smallest is not root
        if (Smallest != i) {
            int swap = arr[i];
            arr[i] = arr[Smallest];
            arr[Smallest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, Smallest);
        }
    }

    // Function to build a Max-Heap from the Array
    static void buildHeap(int[] arr) {
        isMinHeap(arr);
        // Index of last non-leaf node
        int startIdx = (arr.length / 2) - 1;

        // Perform reverse level order traversal
        // from last non-leaf node and heapify
        // each node
        for (int i = startIdx; i >= 0; i--) {
            heapify(arr, i);
        }
    }

    // A utility function to print the array
    // representation of Heap
    static int[] printHeap(int[] arr) {

        for (int i = 0; i < arr.length; ++i) {
//            System.out.print(arr[i] + " ");

        }
//        System.out.println();
        for (int i = 1; i <= arr.length / 2; i++) {
//            System.out.print("   PARENT : " + arr[i - 1]
//                    + "    LEFT CHILD : " + arr[2 * i - 1]
//                    + "    RIGHT CHILD :" + arr[2 * i + 1 / arr.length]);
//            System.out.println();
        }
        return arr;

    }

    private static ArrayList<Integer> getArrayListFromFile() {
        ArrayList<Integer> myNumbers = new ArrayList<Integer>();
        File file = new File("src\\Task1\\Seminar 1 - File with random numbers.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null && myNumbers.size() < N) {
                myNumbers.add(Integer.parseInt(text));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
        return myNumbers;
    }

    public static int[] getArrayFromFile() {
        ArrayList<Integer> arrayList = getArrayListFromFile();
        int[] ret = new int[arrayList.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = arrayList.get(i);
        }

        return ret;
    }

    public static void testing() {
//        int[] arr = getArrayFromFile();
        int[] arr = {10, 12, 1, 14, 6, 5, 8, 15, 3, 9, 7, 4, 11, 13, 2};
        N = arr.length ;


        // Task A


//        System.out.println("Task a");
        MinHeapA minHeapA = new MinHeapA(arr.length);
        for (int value : arr) {
            minHeapA.insert(value);
        }
        minHeapA.minHeap();
        int[] A = minHeapA.print();
        minHeapA.remove();

        // Task B
//        System.out.println("\n Task B \n \n");

        buildHeap(arr);
        int[] B = printHeap(arr);


        //remove the from second algo
        System.out.println("\n  A:   " + Arrays.toString(A) + "  " + "\n  B:   " + Arrays.toString(B) + "  \n");


        // Task C

        System.out.println("\n Task C ");
        Tree t1 = new Tree();
        Tree t2 = new Tree();
        t1.root = t1.insertLevelOrder(A, t2.root, 0);
        t2.root = t2.insertLevelOrder(B, t2.root, 0);

        //        print the second Algorithm
        System.out.println("Left tree");
        counter(t1);

        //      print the second Algorithm
        System.out.println("Right tree");
        counter(t2);
    }

    static boolean isMinHeap(int[] level) {
        int n = level.length - 1;

        // First non leaf node is at index (n/2-1).
        // Check whether each parent is greater than child
        for (int i = (n / 2 - 1); i >= 0; i--) {
            // Left child will be at index 2*i+1
            // Right child will be at index 2*i+2
            if (level[i] > level[2 * i + 1])
                return false;

            if (2 * i + 2 < n) {
                // If parent is greater than right child
                if (level[i] > level[2 * i + 2])
                    return false;
            }
        }
        return true;
    }

    public static int remove(int[] Heap) {
        int FRONT = 1;
        int popped = Heap[FRONT];
        Heap[FRONT] = Heap[Heap.length - 1];
        buildHeap(Heap);
        return popped;
    }

    public static void counter(Tree tree) {
        long startTime = System.nanoTime();
        tree.inOrder(tree.root);
        long endTime = System.nanoTime();
        execution = execution + (endTime - startTime);
        formatter = new DecimalFormat("#0.00000");
        System.out.println( "\n"+  N +"---" + " Execution time is " + formatter.format(execution / 1000000d) + " milliseconds   " + " inOrder algorithm ");
        System.out.println();


        startTime = System.nanoTime();
        tree.preOrder(tree.root);
        endTime = System.nanoTime();
        execution = execution + (endTime - startTime);
        formatter = new DecimalFormat("#0.00000");
        System.out.println( "\n"+  N +"---" + " Execution time is " + formatter.format(execution / 1000000d) + " milliseconds   " + " preOrder algorithm ");
        System.out.println();


        startTime = System.nanoTime();
        tree.PostOrder(tree.root);
        endTime = System.nanoTime();
        execution = execution + (endTime - startTime);
        formatter = new DecimalFormat("#0.00000");
        System.out.println("\n"+  N +"---" + " Execution time is " + formatter.format(execution / 1000000d) + " milliseconds   " + " PostOrder algorithm ");

        startTime = System.nanoTime();
        tree.printLevelOrder();
        endTime = System.nanoTime();
        execution = execution + (endTime - startTime);
        formatter = new DecimalFormat("#0.00000");
        System.out.println("\n"+  N +"---" + " Execution time is " + formatter.format(execution / 1000000d) + " milliseconds   " + " LevelOrder algorithm ");


        System.out.println("\n\n");
    }
}