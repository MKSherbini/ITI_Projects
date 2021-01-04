package ContactUtilPak;
import java.util.*;

public class Contact implements Comparable<Contact>{
	public String id;
	public String firtName;
	public String lastName;
	public String homePhone;
	public String workPhone;
	public String email;
	
	public Contact(String i, String fname){
		id = i;
		firtName = fname;
	}
	public String toString(){
		 return new String("id= "+id+", Name= "+firtName);
	}
	public int compareTo(Contact o){
		return id.compareTo(o.id);
	}
}