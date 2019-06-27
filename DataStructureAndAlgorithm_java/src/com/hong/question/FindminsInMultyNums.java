package com.hong.question;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 在10亿个数中，找到最小的10个数
 * @author mc
 *
 */
public class FindminsInMultyNums {

	public static void main(String[] args) {
		try {
			goAndFind();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 思路：</br>
	 * 读取/生成一个数，放入队列尾部；<br/>
	 * 获取队列首元素，判断大小，如果大于最小小于最大则插入到链表中，然后删除链表的最后一个数；<br/>
	 * @throws IOException 
	 */
	private static void goAndFind() throws Exception {
		// 生成10亿个正整数，保存到本地文件
		generateNumsIntoFile();
		// 存放生成的数
		Queue<Long> queue = new LinkedList<>();
		// 存放临时数据，也是最终的结果
		Queue<Long> result = new LinkedList<>();
	}

	/**
	 * 生成10亿个正整数，保存到本地文件
	 * @throws IOException 
	 */
	private static void generateNumsIntoFile() throws Exception {
		Long count = 1000000000L;
		Long index = 0L;
		// 用File.createNewFile生成文件
//		File xmlFile = new File("billlionNums.txt");
//		xmlFile.createNewFile();//创建文本文件
//		FileWriter fileWriter = new FileWriter(xmlFile);
		
		// 用Filewriter来生成文件
		FileWriter fileWriter = new FileWriter("billlionNums.txt");//创建文本文件
		// 系统换行符
		String separator = System.getProperty("line.separator");
		while(index < count) {
			int a = (int) (Math.random() * 1000000);
			fileWriter.write(String.valueOf(a));
			fileWriter.write(separator);
			if(index % 1000 == 0) {
				System.out.println("index = "+index+", a = "+a);
				Thread.sleep(1);
			}
			index++;
		}
		
		fileWriter.flush();
		fileWriter.close();
	}

}
