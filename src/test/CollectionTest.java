package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionTest {
	public static void main(String[] args) {
		List lists = new ArrayList();
		List list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(2);
		lists.add(list);
		Set set = new HashSet();
		set.addAll(list);
		list.clear();
		list.addAll(set);

		System.out.println(list);
		System.out.println(lists);
	}

}
