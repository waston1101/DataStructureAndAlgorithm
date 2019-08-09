package com.hong.question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hong.dataTools.ToolsForTestCase;

/**
 * 思路同 SortDataInBigFile ，临时数据存放在内存中，不向磁盘中写入，以此减少IO消耗
 * 
 * @author mc
 *
 */
public class SortDataInBigFile2 {

	public static void main(String[] args) {
		try {
			Long numCount = 500000000L;
			Long t0 = System.currentTimeMillis();
			// 生成数据文件
			ToolsForTestCase.generateNumsIntoFile(numCount, t0 + ".txt");
			// 读取原始数据文件--大量数据时这种方法会造成内存溢出，不建议使用这种方法
//			List<Integer> oriData = readFileData(t0 + ".txt");

			Long t1 = System.currentTimeMillis();
			// 排序结果
			List<Integer> result = new ArrayList<>();
			Map<String, List<Integer>> partDataListMap = new HashMap<>();
			// 将读取的数据写入到多个临时List中，每个List的容量是tempListLength
			int tempListLength = (int) Math.sqrt(numCount);
			// 临时List个数
			// int count = (numCount % tempListLength == 0) ? tempListLength :
			// (tempListLength + 1);
			int index = 0;
			List<Integer> tempList = new ArrayList<>();

			FileReader fr = new FileReader(t0 + ".txt");
			BufferedReader bf = new BufferedReader(fr);
			String str;
			// 逐行读取并保存到list中
			while ((str = bf.readLine()) != null) {
				tempList.add(Integer.parseInt(str));
				if (tempList.size() == tempListLength) {
					partDataListMap.put("bigData_part_" + index, tempList);
					tempList = new ArrayList<>();
					index++;
				}
			}
			partDataListMap.put("bigData_part_" + index, tempList);
			str = bf.readLine();
			bf.close();
			fr.close();
			//

			// 维护一个分片数据最小数集合
			Map<String, Integer> minDataMap = new HashMap<>();
			String partDataName = null;
			Integer partDataValue = null;

			// 循环为每个分片数据排序
			// Set<Entry<String, List<Integer>>> keySet = partDataList.entrySet();
			Set<String> partDataList = partDataListMap.keySet();
			for (String partData : partDataList) {
				List<Integer> datas = partDataListMap.get(partData);
				// 为每个分片数据排序，此处可以考虑进行递归
				if (datas != null && datas.size() > 0) {
					Collections.sort(datas);
					minDataMap.put(partData, datas.get(0));
				}
			}
			Set<String> partDataListName = minDataMap.keySet();
			List<String> partDataListNameL = new ArrayList<>();
			partDataListNameL.addAll(partDataListName);

			boolean flag = true;
			while (flag) {
				partDataName = partDataListNameL.get(partDataListNameL.size() - 1);
				partDataValue = partDataListMap.get(partDataName).get(0);
				for (int i = partDataListNameL.size() - 1; i >= 0; i--) {
					// 此处判断
					Integer v = partDataListMap.get(partDataListNameL.get(i)).get(0);
					if (partDataValue > v) {
						partDataName = partDataListNameL.get(i);
						partDataValue = v;
					}
				}
				// 将所有分片数据中的最小数保存到结果集
				result.add(partDataValue);
				// 将最小值从所在的分片中删除
				partDataListMap.get(partDataName).remove(0);
				// 如果该分片数据被消耗完，将分片数据名从维护的分片数据最小数集合中移除
				if (partDataListMap.get(partDataName).size() == 0) {
					partDataListNameL.remove(partDataName);
				} else {
					// 将对应分片中新的最小值放到维护的分片数据最小数集合中
					minDataMap.put(partDataName, partDataListMap.get(partDataName).get(0));
				}
				// System.out.println("partDataListNameL.size() = " + partDataListNameL.size());
				if (partDataListNameL.size() == 0) {
					flag = false;
				}
			}
			Long t2 = System.currentTimeMillis();
			// System.out.println("oriData = " + oriData);
//			System.out.println("result = " + result);

//			Long t3 = System.currentTimeMillis();
//			Collections.sort(oriData);
//			Long t4 = System.currentTimeMillis();
//			System.out.println("resut2 = " + oriData);
			System.out.println("t = " + (t2 - t1));
//			System.out.println("t = " + (t4 - t3));
		} catch (Exception e) {
		}

	}

	private static List<Integer> readFileData(String fileName) throws Exception {
		FileReader fr = new FileReader(fileName);
		BufferedReader bf = new BufferedReader(fr);
		String str;
		// 逐行读取并保存到list中
		List<Integer> arrayList = new ArrayList<>();
		while ((str = bf.readLine()) != null) {
			arrayList.add(Integer.parseInt(str));
		}
		str = bf.readLine();
		bf.close();
		fr.close();
		return arrayList;
	}

}
