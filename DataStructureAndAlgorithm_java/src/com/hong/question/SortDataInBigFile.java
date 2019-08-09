package com.hong.question;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hong.dataTools.ToolsForTestCase;

/**
 * 排序：5亿整数的大文件<br/>
 * 思路：<br/>
 * 维护一个长度合适的有序链表，读取大文件中的数据，存入链表，链表存满时，将其中的数据写入一个新文件<br/>
 * 清空链表，继续读取，重复这个操作；最终将大文本拆分成多个小的临时文本<br/>
 * 依次读取每个临时文本的第一行，获取到最小的数必定为所有数据的最小数，存入到结果文本<br/>
 * 删除最小数所在的临时文本的第一行，重复上面的操作，获取到第二、三……小的数，当某个临时文本为空时，删除该文本 最终获得的结果文本，是有序的，排序完成。<br/>
 * IO占用极高，导致效率极低<br/>
 * TODO 优化方案：<br/>
 * 1.数据量非常大，oriData将会非常大，可能造成内存溢出，建议使用以下方法：在读取时，每读取一定的原数据，就向临时文件中写入<br/>
 * 2.临时文件不向磁盘中写入，而是存放在List中，减少IO消耗<br/>
 * 
 * https://mp.weixin.qq.com/s/DucaXYQo0rqeNvGAvhta-w
 * 
 * @author mc
 *
 */
public class SortDataInBigFile {
	public static void main(String[] args) {
		try {
			// Long numCount = 50000L;
			Long numCount = 2000L;
			// 生成数据文件
			ToolsForTestCase.generateNumsIntoFile(numCount, numCount + ".txt");

			// 2.读取原始数据文件
			List<String> oriData = readFileData(numCount + ".txt");
			Long t1 = System.currentTimeMillis();
			// 对比
			sort2(oriData,numCount + "Sorted2.txt");
			Long t2 = System.currentTimeMillis();
			System.out.println("time1 = "+(t2-t1));
			// 存放临时文件的文件名
			List<String> tempFileNames = new ArrayList<>();
			// 3.将读取的数据写入到多个临时文件中
			int tempNumCount = (int) Math.sqrt(numCount);
			System.out.println("总数据有：" + numCount + ", 每个临时文件存放的数据的个数为：" + tempNumCount);
			generateTempDataFile(oriData, tempFileNames, tempNumCount);
			// TODO 以上2-3，如果数据量非常大，oriData将会非常大，可能造成内存溢出，建议使用以下方法：在读取时，每读取一定的原数据，就向临时文件中写入

			// 生成排序好的文件
			generateFinalFile(numCount + "Sorted.txt", tempFileNames);
			Long t = System.currentTimeMillis();
			System.out.println("time = "+(t-t2));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * 生成排序好的文件<br/>
	 * 逻辑：<br/>
	 * 1.遍历每个临时文件的第一行有效数据（已经标记的不算有效数据），并记录每个数据对应的临时文件名，
	 * 如果某个文件没有有效行，将其从临时文件列表（tempFileNames）中移除。<br/>
	 * 2.记录下该次遍历的所有数据中的最小数，并记录下对应的临时文件的文件名，最小数直接写入到结果文件中，
	 * 将所在临时文件的这一行（第一行有效数据）标记为无效。<br/>
	 * 3.当临时文件列表（tempFileNames）中没有数据时，循环结束，排序结束。<br/>
	 * 
	 * @param string
	 * @param tempFileNames
	 * @throws Exception
	 */
	private static void generateFinalFile(String finalFile, List<String> tempFileNames) throws Exception {
		File finalFileObject = new File(finalFile);
		if(finalFileObject.exists()) {
			finalFileObject.delete();
		}
		FileWriter fileWriter = new FileWriter(finalFile,true);
		String separator = System.getProperty("line.separator");
		while (tempFileNames.size() > 0) {
			// 待删除的临时文件列表
			List<String> delTempFileNames = new ArrayList<>();
			// <tempFileName, minData> ，存放每个临时文件的文件名和最小值
			Map<String, Integer> minDataMap = new HashMap<>();
			for (int i = 0; i < tempFileNames.size(); i++) {
				String delTempFile = tempFileNames.get(i);
				FileReader fr = new FileReader(tempFileNames.get(i));
				BufferedReader bf = new BufferedReader(fr);
				String str = null;
				while ((str = bf.readLine()) != null) {
					if (!"".equals(str) && !str.startsWith("#")) {
						minDataMap.put(tempFileNames.get(i), Integer.parseInt(str));
						// 有有效行，该临时文件不删出
						delTempFile = "";
						break;
					}
				}
				if (!"".equals(delTempFile)) {
					delTempFileNames.add(delTempFile);
				}
				bf.close();
				fr.close();
			}
			// 从临时文件列表中删除
			tempFileNames.removeAll(delTempFileNames);

			String tempFileName = null;
			Integer minNum = null;
			for (Map.Entry<String, Integer> entry : minDataMap.entrySet()) {
				if (minNum == null || minNum > entry.getValue()) {
					tempFileName = entry.getKey();
					minNum = entry.getValue();
				}
			}
			if (minNum != null) {
				// 此时得到了minDataMap中最小的数和对应的临时文件名，将最小数写入结果文件，将对应临时文件的这一行加上无效标记“#”
				// 参数true表示末端追加
				//FileWriter fileWriter = new FileWriter(finalFile,true);
				// 系统换行符
//				String separator = System.getProperty("line.separator");
				fileWriter.write(minNum + "");
				fileWriter.write(separator);
//				fileWriter.flush();
//				fileWriter.close();
				// 读取要重写的临时文件
				//System.out.println("读取要重写的临时文件:" + tempFileName);

				List<String> tempFileOriData = readFileData(tempFileName);
				// 删除临时文件
				//File delFile = new File(tempFileName);
				// 重写临时文件
				FileWriter tempFileWriter = new FileWriter(tempFileName);
				String tag = "#";
				for (int i = 0; i < tempFileOriData.size(); i++) {
					// 标记成无效的数据
					if (tempFileOriData.get(i).startsWith("#")) {
//						tempFileWriter.write(tempFileOriData.get(i)+"");
//						tempFileWriter.write(separator);
					}else {
						tempFileWriter.write(tag + tempFileOriData.get(i));
						tempFileWriter.write(separator);
						tag = "";
					}

				}
				tempFileWriter.flush();
				tempFileWriter.close();
			}
		}
		fileWriter.flush();
		fileWriter.close();
	}

	/**
	 * 将读取的数据写入到多个临时文件中
	 * 
	 * @param oriDate
	 * @throws Exception
	 */
	private static void generateTempDataFile(List<String> oriData, List<String> tempFileNames, int tempLength)
			throws Exception {
		// 生成临时文本的链表，链表的长度应当根据原始数据的长度来综合考量，暂定size/100
		List<Integer> li = new ArrayList<>();
		// 50000/100 = 500
		int length = tempLength;
		// int in = 0;
		// 记录临时文件的个数，用于生成临时文件名
		int fileCount = 0;
		for (int i = 0; i < oriData.size(); i++) {
			// 将元素添加到list中，同时排序(可以选择此时不排序，生成临时文件的时候再排序)
			// addDataToListInOrder(li, oriData.get(i));
			li.add(Integer.parseInt(oriData.get(i)));
			//System.out.println("fileCount = "+fileCount+"   i = " + i);
			
			// 链表达到最大长度，写入到新生成的文件中，链表清空
			if (li.size() == length || i == oriData.size() - 1) {
				writeToTempFile(li, "tempFile-" + fileCount + ".txt");
				tempFileNames.add("tempFile-" + fileCount + ".txt");
				fileCount++;
				li.clear();
			}

			
		}

	}

	/**
	 * 先排序，再写入
	 * 
	 * @param li
	 * @param string
	 * @throws Exception
	 */
	private static void writeToTempFile(List<Integer> li, String fileName) throws Exception {
		Collections.sort(li);
		FileWriter fileWriter = new FileWriter(fileName);
		// 系统换行符
		String separator = System.getProperty("line.separator");
		for (int i = 0; i < li.size(); i++) {
			// System.out.println("--- "+li.get(i));
			fileWriter.write(li.get(i) + "");
			fileWriter.write(separator);
		}

		fileWriter.flush();
		fileWriter.close();

	}

	/**
	 * 读取的方式有3种，参见https://blog.csdn.net/milletguo/article/details/80144290
	 * 读取指定行参见：https://blog.csdn.net/luo_da/article/details/77866835
	 * 
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

	/**
	 * 读取指定行
	 */
//	private static void readLineOfFileDataMethod() throws Exception {
//		long timeStart = System.currentTimeMillis();
//		File file = new File("testData.txt");//文件路径
//		FileReader fileReader = new FileReader(file);
//		LineNumberReader reader = new LineNumberReader(fileReader);
//		int number = 9999999;//设置指定行数
//		String txt = "";
//		int lines = 0;
//		while (txt != null) {
//			lines++;
//			txt = reader.readLine();
//			if (lines == number) {
//				System.out.println("第" + reader.getLineNumber() + "的内容是：" + txt + "\n");
//				long timeEnd = System.currentTimeMillis();
//				System.out.println("总共花费：" + (timeEnd - timeStart) + "ms");
//				System.exit(0);
//			}
//		}
//		reader.close();
//		fileReader.close();
//	}
	private static List<String> readFileData(String fileName) throws Exception {
		FileReader fr = new FileReader(fileName);
		BufferedReader bf = new BufferedReader(fr);
		String str;
		// 逐行读取并保存到list中
		ArrayList<String> arrayList = new ArrayList<>();
		while ((str = bf.readLine()) != null) {
			arrayList.add(str);
		}
		str = bf.readLine();
		bf.close();
		fr.close();
		return arrayList;
	}
	/**
	 * 直接排序，用于对比
	 * @param oriData
	 * @throws Exception 
	 */
	private static void sort2(List<String> oriData,String finalName) throws Exception {
		List<Integer> oriIntData = new ArrayList<>();
		for(String data:oriData) {
			oriIntData.add(Integer.parseInt(data));
		}
		writeToTempFile(oriIntData,finalName);
		
	}
}
