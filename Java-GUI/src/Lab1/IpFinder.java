package Lab1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class IpFinder {
    public static void main(String[] args) {
        JFrame frame = new JFrame("IpFinder");
        frame.setContentPane(new IpFinder().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel panel1;
    private JButton findIPButton;
    private JTextField textField1;

    public IpFinder() {
        findIPButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = textField1.getText();
                if (!url.startsWith("http")) {
                    url = "https://" + url;
                }
                String ip = null;
                try {
                    ip = InetAddress.getByName(new URL(url).getHost()).getHostAddress();
                } catch (UnknownHostException | MalformedURLException unknownHostException) {
                    ip = "UnknownHost";
                }
                //https://www.google.com/

                String body = String.format("URL: %s \nIP: %s", url, ip);

                JOptionPane.showMessageDialog(null, body, "IP", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
