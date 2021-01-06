package Lab2;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlledBoundedBall extends JFrame implements Runnable, ActionListener {
    public static void main(String[] args) {

        ImageIcon imageIcon = new ImageIcon(m_pbPath);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(50, 50, Image.SCALE_FAST);
        imageIcon = new ImageIcon(newimg);

        ControlledBoundedBall app = new ControlledBoundedBall(imageIcon);

        app.setBounds(50, 50, 800, 600);
        app.setVisible(true);
    }

    Thread m_thread;
    JLabel m_label;
    static String m_pbPath = "./img/pokeball.png";
    JButton m_btnStart;
    JButton m_btnPause;
    Boolean m_running = true;
    final Boolean m_runningLock = true;

    void DynamicSetBounds(JButton l) {
        l.setMargin(new Insets(0, 0, 0, 0));
        var f = l.getFont();
        var metrics = this.getFontMetrics(f);
        int h = metrics.getHeight();
        int w = metrics.stringWidth(l.getText()) + 10;
        l.setBounds(0, 0, w, h);
    }

    public ControlledBoundedBall(ImageIcon imageIcon) {
        this.setTitle("ControlledBoundedBall");

        this.setLayout(null);
        m_label = new JLabel(imageIcon);
        m_label.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());

        this.add(m_label);

//        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
//        m_label.setBorder(border);

        m_btnPause = new JButton("Pause");
        m_btnPause.addActionListener(this);
        DynamicSetBounds(m_btnPause);
        this.add(m_btnPause);
        m_btnStart = new JButton("Start");
        m_btnStart.addActionListener(this);
        this.add(m_btnStart);
        DynamicSetBounds(m_btnStart);
        m_btnStart.setLocation(m_btnPause.getWidth() * 2, 0);

        m_thread = new Thread(this);
        m_thread.start();
    }

    public void actionPerformed(ActionEvent e) {
//        synchronized (m_runningLock) {

            if (e.getSource() == m_btnPause) {
                System.out.println("m_btnPause " + m_running);
                m_running = false;
            }
            if (e.getSource() == m_btnStart) {
                System.out.println("m_btnStart " + m_running);
                m_running = true;
            }
//            m_runningLock.notifyAll();
//        }
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
//                synchronized (m_runningLock) {
                    while (!m_running)
                        Thread.sleep(1);
//                        try {
//                            m_runningLock.wait();
//                        } catch (InterruptedException e) {
//                        }
//                }
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
