package com.hong.study.sort;

/**
 * 归并排序
 * @author mc
 *
 */
public class MergeSort {

	public static void mergeSort(int[] arr) {
	    sort(arr, 0, arr.length - 1);
	    
		for (int c = 0; c < arr.length; c++) {
			System.out.print("   " + arr[c]);
		}
		System.out.println();
	}

	public static void sort(int[] arr, int L, int R) {
	    if(L == R) {
	        return;
	    }
	    int mid = L + ((R - L) >> 1);
	    sort(arr, L, mid);
	    sort(arr, mid + 1, R);
	    merge(arr, L, mid, R);
		for (int c = 0; c < arr.length; c++) {
			System.out.print("   " + arr[c]);
		}
		System.out.println();
	}

	public static void merge(int[] arr, int L, int mid, int R) {
	    int[] temp = new int[R - L + 1];
	    int i = 0;
	    int p1 = L;
	    int p2 = mid + 1;
	    // 比较左右两部分的元素，哪个小，把那个元素填入temp中
	    while(p1 <= mid && p2 <= R) {
	        temp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
	    }
	    // 上面的循环退出后，把剩余的元素依次填入到temp中
	    // 以下两个while只有一个会执行
	    while(p1 <= mid) {
	        temp[i++] = arr[p1++];
	    }
	    while(p2 <= R) {
	        temp[i++] = arr[p2++];
	    }
	    // 把最终的排序的结果复制给原数组
	    for(i = 0; i < temp.length; i++) {
	        arr[L + i] = temp[i];
	    }
	}
}
