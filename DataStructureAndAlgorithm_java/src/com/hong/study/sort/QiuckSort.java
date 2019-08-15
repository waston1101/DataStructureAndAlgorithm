package com.hong.study.sort;

/**
 * 快速排序：递归查找基准数的正确索引
 * TODO 编码有问题，结果不正确，待修改
 * 
 * @author mc
 *
 */
public class QiuckSort {

	private int count = 0;

	public void quickSortMethod(int[] array) {
		quickSortMethod(0, array.length - 1, array);
	}

	public void quickSortMethod(int i, int j, int[] array) {
		count++;
		System.out.println("count = " + count + "    i = " + i + "  j = " + j);
		int s = i;
		int end = j;
		int temp = array[i];
		while (i != j) {
			while (i < j && temp < array[j]) {
				j--;
			}
			while (i < j && temp > array[i]) {
				i++;
			}
			swap(array, i, j);
		}
		if (s != i) {
			swap(array, s, i);

		}
		for (int c = 0; c < array.length; c++) {
			System.out.print("   " + array[c]);
		}
		System.out.println();
		if (count >= 15) {
			return;
		}
		// 递归
		if (s > i) {
			quickSortMethod(s, i, array);
		}
		if (j < end) {
			quickSortMethod(j, end, array);
		}

	}

	private void swap(int[] array, int i, int j) {
		System.out.println("swap:    isw = " + i + "  jsw = " + j);
		int temp = array[i];
		System.out.println("temp = " + temp + "  array[i] = " + array[i] + "  array[j] = " + array[i]);
		array[i] = array[j];
		System.out.println("temp = " + temp + "  array[i] = " + array[i] + "  array[j] = " + array[i]);
		array[j] = temp;
		System.out.println("temp = " + temp + "  array[i] = " + array[i] + "  array[j] = " + array[i]);
		System.out.println();
	}

}
