 import java.util.Arrays;
 import java.util.Random;
public class TimeCost {
    static long time;
    static long mark(){  
        long last=time;
        time=System.currentTimeMillis();
        return (time - last);
    }
    static int bSearch(int [] arr, int key){
    int l=0, r=arr.length-1;
    while(l<=r){
        int mid=l+(r-l)/2;
        // System.out.println("x"+mid+"x"+l+"x"+r);
        
        if(arr[mid]==key)
            return mid;
        if (arr[mid]<key)
            l=mid+1;
        else
            r=mid-1;
    }
    return -1;
    }
    
    static int itSearch(int [] arr, int key){
    int i=0;
    for(;i<arr.length;i++){
        if(arr[i]==key)
            return i;
    }
    return -1;
    }
    static void bubbleSort(int[] arr){
        int n = arr.length;
        for (int i = 0;i<n-1;i++) 
            for (int j=0;j<n-i-1;j++) 
                if (arr[j] > arr[j+1]) 
                { 
                    int temp = arr[j]; 
                    arr[j] = arr[j+1]; 
                    arr[j+1] = temp; 
                } 
    }
    static int stoi(String s) {
        int ret = 0;
        for (int i = s.length() - 1, mul = 1; i >= 0; i--, mul *= 10) {
			if((s.charAt(i) - '0') <=9 && (s.charAt(i) - '0') >= 0){
				ret += (s.charAt(i) - '0') * mul;
			}
		}
        return ret;
    }
    static boolean isSorted(int[] array) {
        for (int i =0;i<array.length-1;i++) {
            if (array[i] > array[i + 1])
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
        if(args.length!=1) return;
        int key = stoi(args[0]);
        int[] arr = new int[1000];
        Random r = new Random();
        for(int i=0;i<arr.length;i++){
           arr[i]=r.nextInt();
        }
        bubbleSort(arr);
        // System.out.println(isSorted(arr));
        mark();
        int sr = bSearch(arr,key);
        if(sr==-1){
            System.out.println("Not found");
        }else{
            System.out.println("Found at "+sr); 
        }
        System.out.println("BS Time = " + mark());
        
        mark();
        sr = itSearch(arr,key);
        if(sr==-1){
            System.out.println("Not found");
        }else{
            System.out.println("Found at "+sr); 
        }
        System.out.println("It Time = " + mark()); 
        
        
    }
}