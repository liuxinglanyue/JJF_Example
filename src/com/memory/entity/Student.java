package com.memory.entity;


public class Student implements Cloneable, Comparable<Student> {
	private String name;
	private String sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public Student clone() {
		Student student = null;
		try {
			student = (Student) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return student;
	}
	@Override
	public int compareTo(Student o) {
		if(this.name.equals(o.getName())) {
			if(this.sex.equals(o.getSex())) {
				return 0;
			}
		}
		return 1;
	}
}