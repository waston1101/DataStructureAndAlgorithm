package com.hong.dataTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 工具类，用于生成各种数据
 * 
 * @author mc
 *
 */
public class ToolsForTestCase {

	/**
	 * 生成数组
	 * 
	 * @param max    数组中的最大数，默认值999
	 * @param min    数组中的最小数，允许为负则默认-999，否则默认为0
	 * @param length 数组的长度，默认16
	 * @param minus  是否允许为负数，默认false
	 * @param sort   排序方式，0表示乱序，1正序，默认为0
	 * @return
	 */
	public static int[] generateOrderedArray(Integer max, Integer min, Integer length, Boolean minus, Integer sort) {
		return generateOrderedArrayMethod(max, min, length, minus, sort);
	}

	/**
	 * 获取任意长度字符串
	 * 
	 * @param length         字符串长度 默认128
	 * @param containLetterO 是否包含字母o和O 默认false
	 * @param containNum     是否包含数字 默认true
	 * @param containSymbol  是否包含特殊字符“~!@#$%^&*_-=+”，默认false
	 * @param caseCase       大小写：-1小写，0默认，1大写
	 * @return
	 */
	public static String generateRandomString(Long length, Boolean containLetterO, Boolean containNum,
			Boolean containSymbol, Integer caseCase) {
		char[] table1 = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
				'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		char[] table2 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		char[] table3 = { '~', '!', '@', '#', '$', '%', '^', '&', '*', '_', '-', '=', '+' };
		List<Character> charList = new ArrayList<>();
		for (char c : table1) {
			// System.out.println(c);
			charList.add(c);
		}

		if (length == null || length < 1) {
			length = 128L;
		}
		if (containLetterO == null) {
			containLetterO = false;
		}
		if (containNum == null) {
			containNum = true;
		}
		if (containSymbol == null) {
			containSymbol = false;
		}

		if (containNum) {
			for (char c : table2) {
				System.out.println(c);
				charList.add(c);
			}
		}
		if (containSymbol) {
			for (char c : table3) {
				System.out.println(c);
				charList.add(c);
			}
		}
		int index = 0;
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < length; i++) {
			index = ((int) (Math.random() * 1024)) % (charList.size());
			// System.out.println("index = " + index);
			sb.append(charList.get(index));
			index++;
			// System.out.println("sb = " + sb);
		}
		String s = sb.toString();
		switch (caseCase) {
		case -1:
			s = s.toLowerCase();
			break;
		case 0:
			break;
		case 1:
			s = s.toUpperCase();
			break;

		}
		return s;
	}

	private static int[] generateOrderedArrayMethod(Integer max, Integer min, Integer length, Boolean minus,
			Integer sort) {
		if (max == null) {
			max = 999;
		}
		if (length == null || length < 1) {
			length = 16;
		}
		if (minus == null) {
			minus = false;
		}
		if (min == null) {
			if (minus) {
				min = 0;
			} else {
				min = -999;
			}
		}
		if (sort == null) {
			sort = 0;
		}
		int i = 0;
		int[] intArray = new int[length];
		while (i < length) {
			int a = (int) (Math.random() * max);
			int b = (int) (Math.random() * min);
			intArray[i] = Math.random() > 0.5 ? a : b;
			// System.out.println(intArray[i]);
			i++;
		}
		if (sort > 0) {
			Arrays.sort(intArray);
		}
//		i = 0;
//		while(i < length ) {
//			System.out.println(intArray[i]);
//			i++;
//		}
		return intArray;
	}

}
