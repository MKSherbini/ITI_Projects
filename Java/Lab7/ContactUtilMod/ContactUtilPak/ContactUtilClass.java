package ContactUtilPak;
import java.util.*;

public class ContactUtilClass {
	// static ArrayList<Contact> contacts;
	
    public static void main(String[] args) {
		// Contact[] contacts = new Contact[]{
			// new Contact("a","a"),
			// new Contact("b","b")
		// };
		
		for(var a:getContacts()) System.out.println(a);
        // for (int i = 0; i < contacts.length; i++) {
            // System.out.println(contacts.getAt[i]);
        // }
    }
	
	public static ArrayList<Contact> getContacts(){
		var contacts = new ArrayList<Contact>();
		contacts.add(new Contact("b","b"));
		contacts.add(new Contact("c","c"));
		contacts.add(new Contact("a","a"));
		return contacts;
	}
	
	
	
}
