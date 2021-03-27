package listeners;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationRequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("ApplicationSessionListener.requestDestroyed()");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("ApplicationSessionListener.requestInitialized()");
	}


}