package org.dream.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dream.common.util.HashMapUtils;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		Map<String, List<Integer>> map = HashMapUtils.newInstance();
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		map.put("key", list);
		System.out.println(map.get("key"));
	}
}
