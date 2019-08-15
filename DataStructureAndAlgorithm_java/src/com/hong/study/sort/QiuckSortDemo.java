package com.hong.study.sort;

/**
 * 快速排序：递归查找基准数的正确索引
 * 
 * @author mc
 *
 */
public class QiuckSortDemo {

	public static void main(String[] args) {
//		int[] array = { 1, 9, 5, 3, 7, 5, 32, 7, 43, 8, 3, 9, 4, 465, 67 };
		// int[] array = new int[]{1,9,5,3,7,5,32,7,43,8,3,9,4,465,67};
		int[] array = { 5, 9, 5, 3, 7, 5};
		for (int c = 0; c < array.length; c++) {
			System.out.print("   " + array[c]);
		}
		System.out.println();
		QiuckSort qst = new QiuckSort();
		qst.quickSortMethod(array);
//		System.out.println(qst.getArray());
	}

}
