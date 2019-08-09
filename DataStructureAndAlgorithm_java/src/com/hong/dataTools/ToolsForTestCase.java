package com.hong.dataTools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
		return generateRandomStringMethod(length, containLetterO, containNum, containSymbol, caseCase);
	}

	/**
	 * 获取指定长度的类型为String的List
	 * 
	 * @param length 默认值为48
	 * @return
	 */
	public static List<String> generateStringList(Long length) {
		return generateStringListMethod(length);
	}

	/**
	 * 在指定目录生成指定个数的int类型的文本文件，默认10亿，位于项目根目录
	 * 
	 * @param size
	 * @param fileName
	 * @throws Exception
	 */
	public static void generateNumsIntoFile(Long size, String fileName) throws Exception {
		generateNumsIntoFileMethod(size, fileName);
	}
	
	/**
	 * 读取文本文件第一行数据并返回
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
//	public static String readFirstLineOfFileData( String fileName) throws Exception {
//		return readFirstLineOfFileDataMethod(fileName);
//	}

	// ========================实现方法
	private static List<String> generateStringListMethod(Long length) {
		List<String> strList = new ArrayList<>();
		if (length == null) {
			length = 48L;
		} else if (length <= 0) {
			return null;
		}
		int i = 0;
		while (i < length) {
			strList.add(generateRandomString(48L, false, true, false, 0));
			i++;
		}
		return strList;
	}

	private static String generateRandomStringMethod(Long length, Boolean containLetterO, Boolean containNum,
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
				// System.out.println(c);
				charList.add(c);
			}
		}
		if (containSymbol) {
			for (char c : table3) {
				// System.out.println(c);
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

	/**
	 * 生成指定个正整数，保存到本地文件
	 * 
	 * @throws IOException
	 */
	private static void generateNumsIntoFileMethod(Long size, String fileName) throws Exception {
		if (size == null || size == 0) {
			size = 100000000L;
		}
		// Long count = 100000000L;
		Long count = size;
		Long index = 0L;
		// 用File.createNewFile生成文件
//		File xmlFile = new File("billlionNums.txt");
//		xmlFile.createNewFile();//创建文本文件
//		FileWriter fileWriter = new FileWriter(xmlFile);

		// 用Filewriter来生成文件
		// FileWriter fileWriter = new FileWriter("billlionNums.txt");//创建文本文件
		if (fileName == null || "".equals(fileName.trim())) {
			fileName = "billlionNums.txt";
		}
		FileWriter fileWriter = new FileWriter(fileName);

		// 系统换行符
		String separator = System.getProperty("line.separator");
		while (index < count) {
			int a = (int) (Math.random() * 1000000);
			fileWriter.write(String.valueOf(a));
			fileWriter.write(separator);
			if (index % 10000 == 0) {
				System.out.println("index = " + index + ", a = " + a);
				// Thread.sleep(1);
			}
			index++;
		}

		fileWriter.flush();
		fileWriter.close();
	}

	/**
	 * 读取第一行数据并返回<br/>
	 * 读取的方式有3种，参见https://blog.csdn.net/milletguo/article/details/80144290
	 * 读取指定行参见：https://blog.csdn.net/luo_da/article/details/77866835
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	private static String readFirstLineOfFileDataMethod(String fileName) throws Exception {
		FileReader fr = new FileReader(fileName);
		BufferedReader bf = new BufferedReader(fr);
		String str;
		// 按行读取字符串
		// 逐行读取并保存到list中
//		ArrayList<String> arrayList = new ArrayList<>();
//		while ((str = bf.readLine()) != null) {
//			arrayList.add(str);
//		}
		str = bf.readLine();
		bf.close();
		fr.close();
		return str;
	}

}
