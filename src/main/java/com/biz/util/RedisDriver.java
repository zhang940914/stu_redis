package com.biz.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.biz.entity.Student;

import redis.clients.jedis.Jedis;

public class RedisDriver {
	private volatile static RedisDriver redisDriver;
	private static Jedis jedis;
	private RedisDriver(){}
	public static RedisDriver getRedisDriver(){
		if(redisDriver==null){
			synchronized (RedisDriver.class) {
				if(redisDriver==null){
					jedis=new Jedis("localhost", 6379);
					jedis.auth("zhangping940914");
					jedis.setnx("stuKey", "0");
					redisDriver=new RedisDriver();
				}
			}
		}
		return redisDriver;
	}
	public static boolean addStudent(Student student){
		int stuKey = Integer.parseInt(jedis.get("stuKey"))+1;
			jedis.hset("stu"+stuKey, "name", student.getName());
			jedis.hset("stu"+stuKey, "birthday", student.getBirthday());
			jedis.hset("stu"+stuKey, "description", student.getDescription());
			jedis.hset("stu"+stuKey, "avgscore", student.getAvgscore());
			jedis.set("stuKey", Integer.toString(stuKey));
		return true;
	}
	public static List<Student> listStudent() throws ParseException{
		List<Student> list=new ArrayList<Student>();
		int stuKey=Integer.parseInt(jedis.get("stuKey"));
		for(int i=1;i<= stuKey;i++){
			if(jedis.exists("stu"+i)){
				Student stu=new Student();
				stu.setId(Integer.toString(i));
				stu.setName(jedis.hget("stu"+i, "name"));
				stu.setBirthday(jedis.hget("stu"+i, "birthday"));
				stu.setDescription(jedis.hget("stu"+i, "description"));
				stu.setAvgscore(jedis.hget("stu"+i, "avgscore"));
				list.add(stu);
				stu=null;
			}
		}
		return list;
	}
	public static List<Student> listStudentByDesc() throws ParseException{
		List<Student> list=listStudent();
		StuComparable stu=new StuComparable();
		Collections.sort(list, stu);
		return list;
	}
	public static int getStudentNum(){
		return jedis.keys("stu*").size()-1;
	}
	public static List<Student> listStudentByPagingAndDesc(int pageSize,int pageNum) throws ParseException{
		List<Student> list=listStudentByDesc();
		if(pageNum*pageSize>list.size())
			return list.subList((pageNum-1)*pageSize, list.size());
		return list.subList((pageNum-1)*pageSize, pageNum*pageSize);
	}
	public static boolean updateStudent(Student stu){
		try{
		jedis.hset("stu"+stu.getId(), "name", stu.getName());
		jedis.hset("stu"+stu.getId(), "birthday", stu.getBirthday());
		jedis.hset("stu"+stu.getId(), "description", stu.getDescription());
		jedis.hset("stu"+stu.getId(), "avgscore", stu.getAvgscore());
		}catch(Exception e){
			return false;
		}
		return true;
	}
	public static boolean delStudent(int id){
		try{
			jedis.del("stu"+id);
		}catch(Exception e){
			return false;
		}
		return true;
	}
}
