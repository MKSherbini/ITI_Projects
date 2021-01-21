package JavaGUI;

import javafx.scene.image.Image;

public class User {
    public Image img;
    public String name;

    public User(String name) {
        this.name = name;
    }

    public User(String name, Image img) {
        this.img = img;
        this.name = name;
    }
}
//
//    public static User instance = new User();
//
//    Image img;
//    String name;
//
//    public Image getImg() {
//        return img;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setImg(Image img) {
//        this.img = img;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
