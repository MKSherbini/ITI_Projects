package de.rieckpil.quickstarts.jms;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "jms/notifierQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue
                = "javax.jms.Queue")
})
public class MessageReceiver implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("MessageReceiver.onMessage");
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("received message: " + textMessage.getText());
        } catch (JMSException ex) {
            Logger.getLogger(MessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}