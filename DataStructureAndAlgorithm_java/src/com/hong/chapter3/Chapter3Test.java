package com.hong.chapter3;

import java.util.Iterator;
import java.util.List;

import com.hong.dataTools.ToolsForTestCase;

/**
 * 
 * @author mc
 *
 */
public class Chapter3Test {

	public static void main(String[] args) {
		testIterator();

	}

	/**
	 * 集合的迭代器取值
	 */
	private static void testIterator() {
		List<String> strList = ToolsForTestCase.generateStringList(16L);
		Iterator<String> it = strList.iterator();
		while(it.hasNext()) {
			String item = it.next();
			System.out.println(item);
		}
	}

}
