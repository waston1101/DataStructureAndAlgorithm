package com.hong.test;
/**
 * 枚举类型
 * @author mc
 *
 */
public enum EnumDemo {
//	connectionA("DBA"), connectionB(""), connectionC("mysql");
//	private final String name;
//    private EnumDemo(String name)
//    {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
	male(0), female(1);
	private final int sex;

	private EnumDemo(int sex) {
		this.sex = sex;
	}

	public int getSex() {
		return sex;
	}
}
