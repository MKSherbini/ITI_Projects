package packt.addressbook;

import SortUtilPak.*;
import ContactUtilPak.*;

public class test {
    public static void main(String[] args) {
       
	   var list = ContactUtilClass.getContacts();
	   for(var a: list) System.out.println(a);
	   SortUtilClass.sortList(list);
	   for(var a: list) System.out.println(a);
    }
}