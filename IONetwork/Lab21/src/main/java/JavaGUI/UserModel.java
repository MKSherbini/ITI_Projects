package JavaGUI;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class UserModel implements Serializable {
    transient public Image img; //todo curse this
    public String name;

    public UserModel(String name) {
        this.name = name;
    }

    public UserModel(String name, Image img) {
        this.img = img;
        this.name = name;
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        img = SwingFXUtils.toFXImage(ImageIO.read(s), null);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", s);
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
