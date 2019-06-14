package com.hong.dataTools;

import java.util.Arrays;

/**
 * 工具类，用于生成各种数据
 * @author mc
 *
 */
public class ToolsForTestCase {

	/**
	 * 
	 * @param max 数组中的最大数，默认值999
	 * @param min 数组中的最小数，允许为负则默认-999，否则默认为0
	 * @param length 数组的长度，默认16
	 * @param minus 是否允许为负数，默认false
	 * @param sort 排序方式，0表示乱序，1正序，默认为0
	 * @return
	 */
	public static int[] generateOrderedArray(Integer max, Integer min, Integer length, Boolean minus, Integer sort) {
		return generateOrderedArrayMethod(max, min, length, minus, sort);
	}

	
	
	
	
	private static int[] generateOrderedArrayMethod(Integer max, Integer min, Integer length, Boolean minus,
			Integer sort) {
		if(max == null) {
			max = 999;
		}
		if(length == null || length < 1) {
			length = 16;
		}
		if(minus == null) {
			minus = false;
		}
		if(min == null) {
			if(minus) {
				min = 0;
			}else {
				min = -999;
			}
		}
		if(sort == null) {
			sort = 0;
		}
		int i = 0;
		int[] intArray = new int[length];
		while(i < length ) {
			int a = (int)(Math.random()*max);
			int b = (int)(Math.random()*min);
			intArray[i] = Math.random()>0.5?a:b;
			//System.out.println(intArray[i]);
			i++;
		}
		if(sort > 0) {
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
