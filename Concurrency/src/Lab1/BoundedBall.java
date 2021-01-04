package Lab1;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BoundedBall extends JFrame implements Runnable {
    public static void main(String[] args) {
//        BufferedImage img = null;
//        try {
//            img = ImageIO.read(new File(m_pbPath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Image dimg = img.getScaledInstance(50, 50,
//                Image.SCALE_DEFAULT);
//        ImageIcon imageIcon = new ImageIcon(dimg);

        ImageIcon imageIcon = new ImageIcon(m_pbPath);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(50, 50, Image.SCALE_FAST);
        imageIcon = new ImageIcon(newimg);

        BoundedBall app = new BoundedBall(imageIcon);

        app.setBounds(50, 50, 800, 600);
        app.setVisible(true);
    }

    Thread m_thread;
    JLabel m_label;
    static String m_pbPath = "./img/pokeball.png";

    public BoundedBall(ImageIcon imageIcon) {
        this.setTitle("BoundedBall");

        this.setLayout(null);
        m_label = new JLabel(imageIcon);
        m_label.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
//        m_label.setBounds(0, 0, 55, 55);

        this.add(m_label);

        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
        m_label.setBorder(border);

        m_thread = new Thread(this);
        m_thread.start();
    }

    @Override
    public void run() {
        try {
            boolean start = true;
            int currX = 0;
            int currY = 0;
            int stepX = 1;
            int stepY = 1;
            while (true) {
                int m_windowWidth = this.getContentPane().getWidth();
                int m_windowHeight = this.getContentPane().getHeight();
               int m_charWidth = m_label.getBounds().width;
                int m_charHeight = m_label.getBounds().height;
                if (m_windowWidth < m_charWidth && m_windowHeight < m_charHeight) {
                    Thread.sleep(1);
                    continue;
                }
                int startX = m_windowWidth / 2 - m_charWidth / 2;
                int startY = m_windowHeight / 2 - m_charHeight / 2;
                if (start) {
                    start = false;
                    currX = startX;
                    currY = startY;
                }
                m_label.setLocation(currX, currY);
                if (currX + stepX > 0 && currX + stepX < m_windowWidth - m_charWidth)
                    currX += stepX;
                else
                    stepX *= -1;

                if (currY + stepY > 0 && currY + stepY < m_windowHeight - m_charHeight)
                    currY += stepY;
                else
                    stepY *= -1;

                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
