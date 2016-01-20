package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;

public class ListSelect {
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		final String s ="test";
		List list = new ArrayList();
		list.add("test12");
		list.add("test22");
		list.add("test1234");
		list.add("abc2");
		
		List list1 = (List) CollectionUtils.select(list, new Predicate(){

			@Override
			public boolean evaluate(Object arg0) {
				String ss = (String)arg0;
				boolean b = StringUtils.containsIgnoreCase(ss, s);
				System.out.println(b);
				return b;
			}
			
		});
		System.out.println(list1);
	}

}
