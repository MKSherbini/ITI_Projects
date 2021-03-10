package listeners;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lab6.models.CountManager;

@WebListener
public class ApplicationLifeCycleListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ApplicationLifeCycleListener.contextInitialized()");
		ServletContextListener.super.contextInitialized(sce);
		deserializeCounter();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ApplicationLifeCycleListener.contextDestroyed()");
		ServletContextListener.super.contextDestroyed(sce);
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

	private void deserializeCounter() {
		try {
			JAXBContext jabeontext = JAXBContext.newInstance(CountManager.class);
			Unmarshaller unmarshaller = jabeontext.createUnmarshaller();
			CountManager fromXml = (CountManager) unmarshaller.unmarshal(new FileReader("D:\\counter.xml"));
			fromXml.copytoSingleton();
			System.out.println(fromXml);
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}