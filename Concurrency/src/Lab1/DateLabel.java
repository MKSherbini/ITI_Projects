package Lab1;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Date;

public class DateLabel extends JFrame implements Runnable {
    public static void main(String[] args) {
        DateLabel app = new DateLabel();
        app.setBounds(50, 50, 800, 600);
        app.setVisible(true);
    }

    Thread m_thread;
    JLabel m_dateLabel;
    Date date;

    void DynamicSetBounds(JLabel l) {
        var f = l.getFont();
        var metrics = this.getFontMetrics(f);
        int h = metrics.getHeight();
        int w = metrics.stringWidth(l.getText()) + 2;
        l.setBounds(0, 0, w, h);
    }

    public DateLabel() {
        this.setTitle("DateLabel");
        this.setLayout(null);
        m_dateLabel = new JLabel();
        m_dateLabel.setText("Java World");
        DynamicSetBounds(m_dateLabel);
        this.add(m_dateLabel);

        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
        m_dateLabel.setBorder(border);

        m_thread = new Thread(this);
        m_thread.start();
    }

    @Override
    public void run() {
        try {
            boolean start = true;
            int currX = 0;
            int currY = 0;
            while (true) {
                int m_windowWidth = this.getContentPane().getWidth();
                int m_windowHeight = this.getContentPane().getHeight();
                int m_charWidth = m_dateLabel.getBounds().width;
                int m_charHeight = m_dateLabel.getBounds().height;
                if (m_windowWidth < m_charWidth && m_windowHeight < m_charHeight) continue;
                int stepX = 1;
                int stepY = 1;
                int startX = m_windowWidth / 2 - m_charWidth/ 2;
                int startY = m_windowHeight / 2 - m_charHeight / 2;
                if (start) {
                    start = false;
                    currX = startX;
                    currY = startY;
                }
                m_dateLabel.setText(new Date().toString());
                DynamicSetBounds(m_dateLabel);
                m_dateLabel.setLocation(startX, startY);
//                currX += stepX;
//                if (currX + m_charWidth >= m_windowWidth)
//                    currX = startX;
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
