package IONetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BufferedReaderDemo {
    public static void main(String[] args) throws IOException {
        String[] strings = new String[100];
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 100; i++) {
            strings[i] = bufferedReader.readLine();
            if (strings[i].equals("exit")) {
                break;
            }
        }
        Arrays.stream(strings).forEach(System.out::println);
    }
}
