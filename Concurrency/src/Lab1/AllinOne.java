package Lab1;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class AllinOne extends JFrame implements Runnable {
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

        AllinOne app = new AllinOne(null);

        app.setBounds(50, 50, 800, 600);
        app.setVisible(true);
    }

    Thread m_thread;
    JLabel m_label;
    static String m_pbPath = "./src/Lab1/pokeball.png";

    JLabel m_textLabel;

    JLabel m_dateLabel;
    Date date;

    void DynamicSetBounds(JLabel l) {
        var f = m_textLabel.getFont();
        var metrics = this.getFontMetrics(f);
        int h = metrics.getHeight();
        int w = metrics.stringWidth(l.getText()) + 2;
        l.setBounds(0, 0, w, h);
    }

    public AllinOne(ImageIcon imageIcon) {
        this.setTitle("AllinOne");

        this.setLayout(null);

        m_label = new JLabel();
//        m_label = new JLabel(imageIcon);
//        m_label.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        m_label.setBounds(0, 0, 55, 55);

        this.add(m_label);

        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
        m_label.setBorder(border);

        m_textLabel = new JLabel();
        m_textLabel.setText("Java World");
        DynamicSetBounds(m_textLabel);
        this.add(m_textLabel);

        m_dateLabel = new JLabel();
//        m_dateLabel.setBorder(border);
        this.add(m_dateLabel);

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
            int textCurrX = 0;
            int textCurrY = 0;
            while (true) {
                int m_windowWidth = this.getContentPane().getWidth();
                int m_windowHeight = this.getContentPane().getHeight();
                int m_charWidth = m_label.getBounds().width;
                int m_charHeight = m_label.getBounds().height;
                if (m_windowWidth < m_charWidth && m_windowHeight < m_charHeight) continue;
                int startX = m_windowWidth / 2 - m_charWidth / 2;
                int startY = m_windowHeight / 2 - m_charHeight / 2;
                if (start) {
                    start = false;
                    currX = startX;
                    currY = startY;
                    textCurrX = startX;
                    textCurrY = startY;
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

                m_textLabel.setLocation(textCurrX, textCurrY);
                textCurrX += Math.abs(stepX);
                if (textCurrX + m_charWidth >= m_windowWidth)
                    textCurrX = 0;

                m_dateLabel.setText(new Date().toString());
                DynamicSetBounds(m_dateLabel);
                m_dateLabel.setLocation(m_windowWidth / 2 - m_dateLabel.getBounds().width / 2, m_windowHeight / 2 - m_dateLabel.getBounds().height / 2);

                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
