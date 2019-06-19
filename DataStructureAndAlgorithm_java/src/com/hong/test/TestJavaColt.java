package com.hong.test;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;

public class TestJavaColt {
	
	public static void matTest() {
		DoubleMatrix2D matrix;
        matrix = new DenseDoubleMatrix2D(3,4);
        //matrix = new SparseDoubleMatrix2D(3,4); // 稀疏矩阵
        //matrix = new RCDoubleMatrix2D(3,4); // 稀疏行压缩矩阵
        System.out.println("初始矩阵");
        System.out.println(matrix);
        System.out.println("填充");
        matrix.assign(new double[][]{{1, 2, 3, 4},{5, 6, 7, 8},{9, 10, 11, 12}});
        System.out.println(matrix);
        System.out.println("转置");
        DoubleMatrix2D transpose = Algebra.DEFAULT.transpose(matrix);
        System.out.println(transpose);
        System.out.println("矩阵乘法");
        System.out.println(Algebra.DEFAULT.mult(matrix, transpose));
	}

}
