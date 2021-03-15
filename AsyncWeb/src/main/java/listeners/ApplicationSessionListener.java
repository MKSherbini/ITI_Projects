package listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import utilities.Serializer;

@WebListener
public class ApplicationSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("ApplicationRequestListener.sessionCreated()");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("ApplicationRequestListener.sessionDestroyed()");
    }

}