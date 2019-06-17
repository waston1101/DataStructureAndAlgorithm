package com.hong.chapter2;

import com.hong.dataTools.ToolsForTestCase;

public class P2_2_4_MaxSubString {

	public void demoFindMaxSubString() {
		String str1 = ToolsForTestCase.generateRandomString(1000L, false, false, false, -1);
		String str2 = ToolsForTestCase.generateRandomString(800L, false, false, false, -1);
		findMaxSubString(str1, str2);
		System.out.println(str1);
		System.out.println(str2);
	}

	/**
	 * 查找两字符串中最长的相同子字符串
	 * 
	 * @param sourceStr
	 * @param subStr
	 */
	public String findMaxSubString(String str1, String str2) {
		if (str1 == null || str2 == null) {
			System.out.println("输入参数不允许为null。");
		}
		if (str1.length() == 0 || str2.length() == 0) {
			System.out.println("源字符串长度等于0，请输入参数。");
		}
		String a = str1;
		String b = str2;
		if (str1.length() > str2.length()) {
			a = str2;
			b = str1;
		}
		int subLength = b.length();
		for (int i = 0; i < b.length(); i++) {
			for (int j = 0; j < i; j++) {
				String sub = b.substring(j, b.length() - i + j);
				subLength = sub.length();
				//System.out.println("sub = " + sub);
				if (a.contains(sub)) {
					System.out.println("最长的公共子字符串是：" + sub);
					return sub;
				} else {

				}
			}

		}
		return null;
	}
}
