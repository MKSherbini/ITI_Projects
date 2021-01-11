package Lab3;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TextBus extends JFrame implements Runnable {
    public static void main(String[] args) {
        TextBus app = new TextBus();

        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.submit(app);
    }

    Thread m_thread;
    JLabel m_textLabel;

    void DynamicSetBounds(JLabel l) {
        var f = m_textLabel.getFont();
        var metrics = this.getFontMetrics(f);
        int h = metrics.getHeight();
        int w = metrics.stringWidth(l.getText()) + 2;
        l.setBounds(0, 0, w, h);
    }

    public TextBus() {
        this.setBounds(50, 50, 800, 600);
        this.setVisible(true);
        this.setTitle("TextBus");
        this.setLayout(null);
        m_textLabel = new JLabel();
        m_textLabel.setText("Java World");
        DynamicSetBounds(m_textLabel);
        this.add(m_textLabel);

        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
        m_textLabel.setBorder(border);

        m_thread = new Thread(this);
        m_thread.start();
    }

    @Override
    public void run() {
        try {
            boolean start = true;
            int textCurrX = 0;
            int textCurrY = 0;
            while (true) {
                int m_windowWidth = this.getContentPane().getWidth();
                int m_windowHeight = this.getContentPane().getHeight();
                int m_charWidth = m_textLabel.getBounds().width;
                int m_charHeight = m_textLabel.getBounds().height;
                if (m_windowWidth < m_charWidth && m_windowHeight < m_charHeight) continue;
                int stepX = 1;
                int stepY = 1;
                int startX = -m_charWidth;
                int startY = m_windowHeight / 2 - m_charHeight / 2;
                if (start) {
                    start = false;
                    textCurrX = startX;
                    textCurrY = startY;
                }

                m_textLabel.setLocation(textCurrX, textCurrY);
                textCurrX += stepX;
                if (textCurrX >= m_windowWidth)
                    textCurrX = startX;
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
