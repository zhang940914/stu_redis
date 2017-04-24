package com.biz.test;

import java.io.Console;
import java.text.ParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.biz.entity.Student;
import com.biz.util.RedisDriver;
import com.biz.util.StuComparable;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			RedisDriver.getRedisDriver().addStudent(new Student("","name","2013-01-01 11:22:22","fdsfsdf",90));
			RedisDriver.getRedisDriver().addStudent(new Student("","name","2013-01-01 11:22:22","fdsfsdf",87));
			RedisDriver.getRedisDriver().addStudent(new Student("","name","2013-01-01 11:22:22","fdsfsdf",95));
			RedisDriver.getRedisDriver().addStudent(new Student("","name","2013-01-01 11:22:22","fdsfsdf",54));
			RedisDriver.getRedisDriver().addStudent(new Student("","name","2013-01-01 11:22:22","fdsfsdf",99));
			RedisDriver.getRedisDriver().addStudent(new Student("","name","2013-01-01 11:22:22","fdsfsdf",90));
			RedisDriver.getRedisDriver().addStudent(new Student("","name","2013-01-01 11:22:22","fdsfsdf",87));
			RedisDriver.getRedisDriver().addStudent(new Student("","name","2013-01-01 11:22:22","fdsfsdf",95));
			RedisDriver.getRedisDriver().addStudent(new Student("","name","2013-01-01 11:22:22","fdsfsdf",54));
			RedisDriver.getRedisDriver().addStudent(new Student("","name","2013-01-01 11:22:22","fdsfsdf",99));
			RedisDriver.getRedisDriver().addStudent(new Student("","name","2013-01-01 11:22:22","fdsfsdf",90));
			RedisDriver.getRedisDriver().addStudent(new Student("","name","2013-01-01 11:22:22","fdsfsdf",87));
			RedisDriver.getRedisDriver().addStudent(new Student("","name","2013-01-01 11:22:22","fdsfsdf",95));
			RedisDriver.getRedisDriver().addStudent(new Student("","name","2013-01-01 11:22:22","fdsfsdf",54));
			RedisDriver.getRedisDriver().addStudent(new Student("","name","2013-01-01 11:22:22","fdsfsdf",99));
			List<Student> list=RedisDriver.getRedisDriver().listStudent();
			StuComparable stu=new StuComparable();
			Collections.sort(list, stu);
			for (Student student : list) {
				System.out.println(student.getAvgscore());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
