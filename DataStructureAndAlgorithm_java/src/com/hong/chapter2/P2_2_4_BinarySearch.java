package com.hong.chapter2;

import java.util.Arrays;

import com.hong.dataTools.ToolsForTestCase;

/**
 * 二分查找
 * 
 * @author mc
 *
 */
public class P2_2_4_BinarySearch {

	public void demoBinarySearch() {
		int target = 12;
		System.out.println("二分查找，查找的值为：" + target);
		int[] arr = ToolsForTestCase.generateOrderedArray(999, -999, 99999, true, 1);
		System.out.println("二分查找-原始数组为：" + Arrays.toString(arr));

		Long start1 = System.currentTimeMillis();
		int result = binarySearch(arr, 0, arr.length - 1, target);
		Long end1 = System.currentTimeMillis();
		System.out.println("s1 = " + start1);
		System.out.println("e1 = " + end1);
		if (result == -1) {
			System.out.println(target + "不在数组中。");
		} else {
			System.out.println(target + "位于数组的第" + (result + 1) + "位置。");
		}
		
		Long start2 = System.currentTimeMillis();
		searchArray(arr, target);
		Long end2 = System.currentTimeMillis();
		System.out.println("s2 = " + start2);
		System.out.println("e2 = " + end2);
	}

	/**
	 * 二分查找
	 * @param arr
	 * @param left
	 * @param right
	 * @param target
	 * @return
	 */
	private int binarySearch(int[] arr, int left, int right, int target)  {
		try {
			//Thread.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int index = -1;
		int center = left;
		if (target > arr[right] || target < arr[left]) {
			return -1;
		}
		if (left >= right) {
			// return arr[left];
			if (arr[left] == target) {
				index = left;
			}
			System.out.println("index = " + index);
			return index;
		}
		center = (left + right) / 2;
		if (target > arr[center]) {
			index = binarySearch(arr, center + 1, right, target);
		} else {
			index = binarySearch(arr, left, center, target);
		}
		return index;
	}

	/**
	 * 顺序遍历查找
	 * @param arr
	 * @param target
	 */
	private void searchArray(int[] arr, int target)  {
		for (int i = 0; i < arr.length; i++) {
			try {
				//Thread.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (target == arr[i]) {
				System.out.println("searchArray-index = "+i);
				return;
			}
		}

	}
}
