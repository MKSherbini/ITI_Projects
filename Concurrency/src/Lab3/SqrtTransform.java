package Lab3;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class SqrtTransform extends RecursiveAction {

    static long time;

    static long mark() {
        long last = time;
        time = System.currentTimeMillis();
        return (time - last);
    }

    public static void main(String[] args) {
        System.out.println("par " + new ForkJoinPool().getParallelism());
        mark();
        UsingFJP();
        System.out.println("Time " + mark());
        mark();
        UsingDirect();
        System.out.println("Time " + mark());
    }

    public static void UsingFJP() {
//        ForkJoinPool fjp = ForkJoinPool.commonPool(); //TODO WHYYYYYYYYYYYYYYYYYYY?
        ForkJoinPool fjp = new ForkJoinPool(11);
        double[] nums = new double[1_000_000_00];
        for (int i = 0; i < nums.length; i++) nums[i] = (double) i;
        System.out.println("A portion of the original sequence");
        for (int i = 0; i < 10; i++)
            System.out.print(nums[i] + " ");
        System.out.println("\n");
        SqrtTransform task = new SqrtTransform(nums, 0, nums.length);
        fjp.invoke(task);
        System.out.println("A portion of the transformed sequence" +
                " (to four decimal places): ");
        for (int i = 0; i < 10; i++)
            System.out.format("%.4f ", nums[i]);
        System.out.println();
        System.out.println("parallelism " + fjp.getParallelism());
    }

    public static void UsingDirect() {
        double[] nums = new double[1_000_000_00];
        for (int i = 0; i < nums.length; i++) nums[i] = (double) i;
        System.out.println("A portion of the original sequence");
        for (int i = 0; i < 10; i++)
            System.out.print(nums[i] + " ");
        System.out.println("\n");
        SqrtTransform task = new SqrtTransform(nums, 0, nums.length);
        task.invoke();
        System.out.println("A portion of the transformed sequence" +
                " (to four decimal places): ");
        for (int i = 0; i < 10; i++)
            System.out.format("%.4f ", nums[i]);
        System.out.println();
        System.out.println("parallelism " + ForkJoinPool.commonPool().getParallelism());
    }

    final int seqThreshold = 100_000_000;
    double data[];
    int start, end;

    SqrtTransform(double[] vals, int s, int e) {
        data = vals;
        start = s;
        end = e;
    }

    protected void compute() {
        if ((end - start) < seqThreshold) {
            for (int i = start; i < end; i++) {
                data[i] = Math.sqrt(data[i]);
            }
        } else {
            int middle = (end + start) / 2;
            System.out.println(Thread.currentThread().getName());
            for (int i = 0; i < 30; i++) {
                invokeAll(new SqrtTransform(data, start, middle),
                        new SqrtTransform(data, middle, end));
            }

        }
    }
}
