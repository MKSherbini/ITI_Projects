package lab6.models;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "CountManager")
@XmlAccessorType(XmlAccessType.FIELD)
public class CountManager {
	private static CountManager instance = new CountManager();

	public static CountManager getInstance() {
		return instance;
	}

	public void copytoSingleton() {
		instance.requests = requests;
		instance.sessions = sessions;
	}

	private int requests;
	private int sessions;

	public void incrementRequsts() {
		requests++;
	}

	public void incrementSessions() {
		sessions++;
	}

	public void decrementRequsts() {
		requests--;
	}

	public void decrementSessions() {
		sessions--;
	}

}
