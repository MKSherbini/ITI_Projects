package Lab1;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MultiBall extends JFrame implements Runnable {
    public static void main(String[] args) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(m_pbPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(50, 50,
                Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(dimg);

//        ImageIcon imageIcon = new ImageIcon(m_pbPath);
//        Image image = imageIcon.getImage();
//        Image newimg = image.getScaledInstance(50, 50, Image.SCALE_FAST);
//        imageIcon = new ImageIcon(newimg);

        MultiBall app = new MultiBall(imageIcon);

        app.setBounds(50, 50, 800, 600);
        app.setVisible(true);
    }

    Thread m_thread;
    static String m_pbPath = "./img/pokeball.png";
    ImageIcon m_ball;

    public MultiBall(ImageIcon imageIcon) {
        this.setTitle("MultiBall");
        setBounds(50, 50, 800, 600);

        this.setLayout(null);
        m_ball = imageIcon;


        m_thread = new Thread(this);
        m_thread.start();
    }

    @Override
    public void run() {
        try {
            boolean start = true;
            Ball[] balls = new Ball[10];
            while (true) {
                int m_windowWidth = this.getContentPane().getWidth();
                int m_windowHeight = this.getContentPane().getHeight();
                if (m_windowWidth <= 0 && m_windowHeight <= 0) {
                    Thread.sleep(1);
                    continue;
                }
                Rectangle[] xBounds = new Rectangle[]{
                        new Rectangle(0, 0, 1, m_windowHeight),
                        new Rectangle(m_windowWidth, 0, 1, m_windowHeight),
                };
                Rectangle[] yBounds = new Rectangle[]{
                        new Rectangle(0, 0, m_windowWidth, 1),
                        new Rectangle(0, m_windowHeight, m_windowWidth, 1),
                };
                int startX = m_windowWidth / 2;
                int startY = m_windowHeight / 2;
                if (start) {
                    start = false;
                    var h = m_ball.getIconHeight();
                    var w = m_ball.getIconWidth();
                    for (int i = 0; i < balls.length; i++) {
                        balls[i] = new Ball(m_ball, this, startX + i / 2 * w * (i % 2 == 0 ? +1 : -1), startY);
                    }

                }
                for (Ball ball : balls) {
                    for (Rectangle b : xBounds) {
                        if (ball.willCollide(b)) {
                            ball.reverseX();
                        }
                    }
                    for (Rectangle b : yBounds) {
                        if (ball.willCollide(b)) {
                            ball.reverseY();
                        }
                    }
                    for (Ball ball2 : balls) {
                        if (ball.willCollide(ball2)) {
                            ball.handleCollide(ball2);
                        }
                    }
                    ball.update();
                }

                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Ball {
        int xSpeed = 1, ySpeed = 1;
        Rectangle transform;
        JLabel sprite;

        public Ball(ImageIcon imageIcon, JFrame parent, int xPos, int yPos) {
            transform = new Rectangle(xPos, yPos, imageIcon.getIconWidth(), imageIcon.getIconHeight());
            sprite = new JLabel(imageIcon);
            sprite.setBounds(transform);
//            Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
//            sprite.setBorder(border);
            parent.add(sprite);
            Random r = new Random();
            xSpeed = r.nextInt(3) + 1;
            ySpeed = r.nextInt(3) + 1;
        }

        public void update() {
            transform.x += xSpeed;
            transform.y += ySpeed;
            sprite.setBounds(transform);
        }

        public void reverseX() {
            xSpeed *= -1;
        }

        public void reverseY() {
            ySpeed *= -1;
        }

        public void reverse() {
            reverseY();
            reverseX();
        }

        public boolean collides(Rectangle bounds) {
            return transform.intersects(bounds);
        }

        public boolean willCollide(Rectangle bounds) {
            var temp = new Rectangle(transform);
            temp.x += xSpeed;
            temp.y += ySpeed;
            return temp.intersects(bounds);
        }

        public boolean willCollide(Ball ball) {
            if (ball == this) return false;
            return willCollide(ball.transform);
        }

        public void handleCollide(Ball ball) {
//            if (ball.xSpeed != xSpeed) {
//                ball.reverseX();
//                reverseX();
//            }
//            if (ball.ySpeed != ySpeed) {
//                ball.reverseY();
//                reverseY();
//            }

            var c1 = new Point(transform.x + transform.width / 2, transform.y + transform.height / 2);
            var c2 = new Point(ball.transform.x + ball.transform.width / 2, ball.transform.y + ball.transform.height / 2);
            var col = new Point(c2.x + (c1.x - c2.x) / 2, c2.y + (c1.y - c2.y) / 2);

            bounce(col);
            ball.bounce(col);
        }

        public void bounce(Point col) {
            var c1 = new Point(transform.x + transform.width / 2, transform.y + transform.height / 2);

            if (c1.x - col.x != xSpeed) xSpeed *= -1;
            if (c1.y - col.y != ySpeed) ySpeed *= -1;
        }

        public String toString() {
            return "Ball: " + transform;
        }
    }
}
