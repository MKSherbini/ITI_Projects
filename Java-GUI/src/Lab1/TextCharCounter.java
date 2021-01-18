package Lab1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextCharCounter {
    public static void main(String[] args) {
        JFrame frame = new JFrame("TextCharCounter");
        frame.setContentPane(new TextCharCounter().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JTextArea TextArea;
    private JPanel panel1;
    private JButton CountWords;
    private JButton CountChars;

    public TextCharCounter() {
        CountWords.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Total Words: " + Pattern.compile("\\w+").matcher(TextArea.getText()).results().count(),
                        "Words Count",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        CountChars.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Total Chars: " +
                                Pattern.compile(".").matcher(TextArea.getText()).results().count(), // or \S ?
                        "Chars Count",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
