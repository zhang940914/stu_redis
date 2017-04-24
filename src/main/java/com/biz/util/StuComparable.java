package com.biz.util;

import java.util.Comparator;

import com.biz.entity.Student;

public class StuComparable implements Comparator<Student> {
	public static boolean sortASC = true;

	@Override
	public int compare(Student o1, Student o2) {
		if(sortASC)
		return o1.getAvgscore().compareTo(o2.getAvgscore());
		return -o1.getAvgscore().compareTo(o2.getAvgscore());
	}

}
