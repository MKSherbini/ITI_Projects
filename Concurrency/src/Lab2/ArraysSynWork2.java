package Lab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ArraysSynWork2 {
    public static void main(String[] args) {
        Random r = new Random();
        int sz = 30;
        int arr[] = new int[sz];
        for (int i = 0; i < sz; i++) {
            arr[i] = r.nextInt(100);
        }
        System.out.println("Original");
        for (var a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();

        var arr1 = Arrays.copyOfRange(arr, 0, 10);
        var arr2 = Arrays.copyOfRange(arr, 10, 20);
        var arr3 = Arrays.copyOfRange(arr, 20, 30);
        var a1 = new Slave(arr1, arr, 0, 10);
        var a2 = new Slave(arr2, arr, 10, 10);
        var a3 = new Slave(arr3, arr, 20, 10);
        a1.start();
        a2.start();
        a3.start();
        try {
            a1.join();
            a2.join();
            a3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a3.printOut();
        a1.printOut();
    }

    static class Slave extends Thread {
        int[] arr;
        int[] res;
        int start;
        int size;

        public Slave(int[] arr, int[] res, int start, int size) {
            this.arr = arr;
            this.start = start;
            this.size = size;
            this.res = res;
        }


        @Override
        public void run() {
            for (int i = 0; i < size; i++) {
                res[start + i] = (arr[i]);
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }

        public void printOut() {
            for (var a : res) {
                System.out.print(a + " ");
            }
            System.out.println();
        }

    }
}
