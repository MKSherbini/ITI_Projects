package IONetwork;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {
    public static void main(String[] args) {
        String originalPath = "D:\\Utils\\..\\Tracks";
        Path path = Paths.get("D:\\test.txt");
        Path path1 = Paths.get(originalPath);
        System.out.println("path = " + path);
        System.out.println("path1 = " + path1);
        Path path2 = path1.normalize();
        System.out.println("path2 = " + path2);
    }
}