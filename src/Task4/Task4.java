package Task4;

import Task1.MinHeapA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Task4 {
    private static int N = 1;
    static NumberFormat formatter;
    private static long execution;

    public static void main(String[] args) {
//        int[] arr = {10, 12, 1, 14, 6, 5, 8, 15, 3, 9, 7, 4, 11, 13, 2};

        for (int i = 0; i < 8; i++) {
            Test();
           N= N*10;
        }


    }
private static void Test(){
    int[] arr = getArrayFromFile();


    BinarySearchTree binarySearchTree = new BinarySearchTree();
    long startTime = System.nanoTime();

    for (int element : arr) {
        binarySearchTree.insert(element);
    }
    System.out.println("binarySearchTree:");
    long endTime = System.nanoTime();
    execution = execution + (endTime - startTime);
    formatter = new DecimalFormat("#0.00000");
    System.out.print(N + "---" + " Execution time is " + formatter.format(execution / 1000000d) + " milliseconds    ");
    System.out.println();


    AVLTree avlTree = new AVLTree();
    startTime = System.nanoTime();

    for (int item : arr) {
        avlTree.root = avlTree.insert(avlTree.root, item);
    }
    System.out.println("avlTree : ");
    endTime = System.nanoTime();
    execution = execution + (endTime - startTime);
    formatter = new DecimalFormat("#0.00000");
    System.out.print( N + "---" + " Execution time is " + formatter.format(execution / 1000000d) + " milliseconds   ");
    System.out.println();


    MinHeapA minHeapA = new MinHeapA(arr.length);
    startTime = System.nanoTime();

    for (int value : arr) {
        minHeapA.insert(value);

    }
    System.out.println("Heap : ");
    endTime = System.nanoTime();
    execution = execution + (endTime - startTime);
    formatter = new DecimalFormat("#0.00000");
    System.out.print( N + "---" + " Execution time is " + formatter.format(execution / 1000000d) + " milliseconds   ");
    System.out.println();


    RedBlackTree<Integer> redBlackTree = new RedBlackTree<Integer>();
    startTime = System.nanoTime();

    for (int value : arr) {
        redBlackTree.insert(value);
    }
    System.out.println("RedBlackTree : ");
    endTime = System.nanoTime();
    execution = execution + (endTime - startTime);
    formatter = new DecimalFormat("#0.00000");
    System.out.print(N + "---" + " Execution time is " + formatter.format(execution / 1000000d) + " milliseconds   ");
    System.out.println("\n\n");
}
    private static ArrayList<Integer> getArrayListFromFile() {
        ArrayList<Integer> myNumbers = new ArrayList<>();
        File file = new File("src\\Task1\\Seminar 1 - File with random numbers.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String text;

            while ((text = reader.readLine()) != null && myNumbers.size() < N) {
                myNumbers.add(Integer.parseInt(text));
            }
        } catch (IOException e) {
            e.printStackTrace();
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
}
