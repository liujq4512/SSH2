package test;

import java.util.ArrayList;
import java.util.List;

public class TestListContains {
	@SuppressWarnings("unchecked")
	public static void main(String args[]){
		String s = new String("test12");
		List list = new ArrayList();
		list.add("test12");
		list.add("test22");
		list.add("test1234");
		list.add(new String("abc2"));
		System.out.println(list.contains(s));
	}
}
