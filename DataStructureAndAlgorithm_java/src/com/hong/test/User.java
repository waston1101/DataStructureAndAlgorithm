package com.hong.test;

public abstract class User<T> implements Comparable<T> {

	private int id;
	private Integer newId;
	private String name;
	private int age;
	private String info;

	@Override
	public int compareTo(T o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
