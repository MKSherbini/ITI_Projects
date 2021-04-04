package eg.gov.iti.jets.handlers;

import java.util.Collections;
import java.util.Set;

import javax.xml.namespace.QName;

import jakarta.xml.ws.handler.LogicalHandler;
import jakarta.xml.ws.handler.LogicalMessageContext;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;

public class MyLogicalHandler implements LogicalHandler<LogicalMessageContext> {
    public Set<QName> getHeaders() {
        System.out.println("MyLogicalHandler.getHeaders()");
        return Collections.emptySet();
    }

    public boolean handleMessage(LogicalMessageContext messageContext) {
        Boolean outboundProperty = (Boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outboundProperty.booleanValue()) {
            System.out.println("\nOutbound message:");
        } else {
            System.out.println("\nInbound message:");
        }

        System.out.println("** Response: " + messageContext.getMessage().toString());
        return true;
    }

    public boolean handleFault(LogicalMessageContext messageContext) {
        System.out.println("MyLogicalHandler.handleFault()");
        return true;
    }

    public void close(MessageContext messageContext) {
        System.out.println("MyLogicalHandler.enclosing_method()");
    }
}
