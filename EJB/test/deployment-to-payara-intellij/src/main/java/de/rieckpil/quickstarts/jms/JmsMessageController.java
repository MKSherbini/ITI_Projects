package de.rieckpil.quickstarts.jms;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;

@Startup
@Singleton
public class JmsMessageController {
//    @Inject
//    private JmsMessageModel jmsMessageModel;

    @Inject
//    @JMSConnectionFactory("jms/__defaultConnectionFactory")
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext jmsContext;

    @Resource(mappedName = "jms/notifierQueue")
    private Queue myQueue;

//    @Schedule(hour = "*", minute = "*", second = "*/4")
    @PostConstruct
    public void postConstruct() {
        System.out.println("JmsMessageController.postConstruct");
        sendMessage();
    }

    public void sendMessage() {
        System.out.println("JmsMessageController.sendMessage");
        jmsContext.createProducer().send(myQueue, "Test Message");
    }

}
