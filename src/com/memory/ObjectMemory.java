package com.memory;

import com.memory.entity.Student;

import objectexplorer.MemoryMeasurer;


public class ObjectMemory {
	public static void main(String[] args) {
		Student stu = new Student();
		stu.setName("fdf");
		System.out.println(MemoryMeasurer.measureBytes(stu));
	}
}
