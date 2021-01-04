package SortUtilPak;
import java.util.*;

public class SortUtilClass {
    public static void main(String[] args) {
        // for (int i = 0; i < args.length; i++) {
            // System.out.println(args[i]);
        // }
    }
	
	public static <T extends Comparable> void sortList(ArrayList<T> l){
		Collections.sort(l); 
	}
}