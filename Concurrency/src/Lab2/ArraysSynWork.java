package Lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class ArraysSynWork {
    public static void main(String[] args) {
        Random r = new Random();
        int sz = 30;
        int arr[] = new int[sz];
        for (int i = 0; i < sz; i++) {
            arr[i] = r.nextInt(100);
        }
        var a1 = new Slave(arr, 0, 10);
        var a2 = new Slave(arr, 10, 10);
        var a3 = new Slave(arr, 20, 10);
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
        Slave.printOut();

    }

    static class Slave extends Thread {
        int[] arr;
        static List<Integer> res = new ArrayList<>();
        static final String arrLock = new String();
        int start;
        int size;

        public Slave(int[] arr, int start, int size) {
            this.arr = arr;
            this.start = start;
            this.size = size;
        }


        @Override
        public void run() {
            synchronized (arrLock) {
                for (int i = start; i < start + size; i++) {
                    res.add(arr[i]);
                    System.out.print(arr[i] + " ");
                }
                System.out.println();
            }
//            synchronized (arrLock) {
//                for (int i = start; i < start + size; i++) {
//                    System.out.print(arr[i] + " ");
//                }
//                System.out.println();
//            }
        }

        public static void printOut() {
            for (var a : res) {
                System.out.print(a + " ");
            }
            System.out.println();
        }

    }
}
