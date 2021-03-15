package listeners;

import java.io.FileWriter;
import java.io.IOException;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lab6.models.CountManager;

@WebListener
public class ApplicationSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("ApplicationRequestListener.sessionCreated()");
		CountManager.getInstance().incrementSessions();
		HttpSessionListener.super.sessionCreated(se);
		serializeCounter();
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("ApplicationRequestListener.sessionDestroyed()");
		CountManager.getInstance().decrementSessions();
		HttpSessionListener.super.sessionDestroyed(se);
		serializeCounter();
	}

	private void serializeCounter() {
		try {
			JAXBContext jabeontext = JAXBContext.newInstance(CountManager.class);
			Marshaller marshaller = jabeontext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(CountManager.getInstance(), new FileWriter("D:\\counter.xml"));
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
	}
	
}