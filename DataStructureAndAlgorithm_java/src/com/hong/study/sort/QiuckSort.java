package com.hong.study.sort;

/**
 * 快速排序：递归查找基准数的正确索引 TODO 编码有问题，结果不正确，待修改。
 * 下面的是正确的方法。
 * 
 * @author mc
 *
 */
public class QiuckSort {

//	private int count = 0;
//
//	public void quickSortMethod(int[] array) {
//		quickSortMethod(0, array.length - 1, array);
//	}
//
//	public void quickSortMethod(int i, int j, int[] array) {
//		count++;
//		System.out.println("count = " + count + "    i = " + i + "  j = " + j);
//		int s = i;
//		int end = j;
//		int temp = array[i];
//		while (i != j) {
//			while (i < j && temp < array[j]) {
//				j--;
//			}
//			while (i < j && temp > array[i]) {
//				i++;
//			}
//			swap(array, i, j);
//		}
//		if (s != i) {
//			swap(array, s, i);
//
//		}
//		for (int c = 0; c < array.length; c++) {
//			System.out.print("   " + array[c]);
//		}
//		System.out.println();
//		if (count >= 15) {
//			return;
//		}
//		// 递归
//		if (s > i) {
//			quickSortMethod(s, i, array);
//		}
//		if (j < end) {
//			quickSortMethod(j, end, array);
//		}
//
//	}
//
//	private void swap(int[] array, int i, int j) {
//		System.out.println("swap:    isw = " + i + "  jsw = " + j);
//		int temp = array[i];
//		System.out.println("temp = " + temp + "  array[i] = " + array[i] + "  array[j] = " + array[i]);
//		array[i] = array[j];
//		System.out.println("temp = " + temp + "  array[i] = " + array[i] + "  array[j] = " + array[i]);
//		array[j] = temp;
//		System.out.println("temp = " + temp + "  array[i] = " + array[i] + "  array[j] = " + array[i]);
//		System.out.println();
//	}

	public void quickSortMethod_2(int[] array) {
		quickSortMethod_2(array, 0, array.length - 1);
	}

	public void quickSortMethod_2(int[] R, int low, int high) {
		int i, j, temp;
		i = low;
		j = high;
		if (low < high) {
			System.out.println("low = " + low + "   high = " + high);
			for (int c = 0; c < R.length; c++) {
				System.out.print("   " + R[c]);
			}
			System.out.println();
			System.out.println();
			temp = R[low]; // 设置枢轴
			while (i != j) {
				while (j > i && R[j] >= temp) {
					--j;
				}
				if (i < j) {
					R[i] = R[j];
					++i;
				}

				while (i < j && R[i] < temp) {
					++i;
				}
				if (i < j) {
					R[j] = R[i];
					--j;
				}
			}
			R[i] = temp;
			quickSortMethod_2(R, low, i - 1);
			quickSortMethod_2(R, i + 1, high);
		}

	}

}
