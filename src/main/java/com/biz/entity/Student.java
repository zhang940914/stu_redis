package com.biz.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student implements Serializable {
	private StringBuffer id;
	private StringBuffer name;
	private Date birthday;
	private StringBuffer description;
	private Integer avgscore;
	
	public Student(){
		id=new StringBuffer(40);
		name=new StringBuffer(40);
		birthday=new Date();
		description=new StringBuffer(40);
		avgscore=new Integer(0);
	}

	public Student(String id, String name, String birthday, String description, int avgscore) throws ParseException {
		super();
		this.id = new StringBuffer(id);
		this.name = new StringBuffer(name);
		this.birthday = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(birthday);
		this.description = new StringBuffer(description);
		this.avgscore = Integer.valueOf(avgscore);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id.toString();
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = new StringBuffer(id);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name.toString();
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = new StringBuffer(name);
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return new SimpleDateFormat("yy-MM-dd hh:mm:ss").format(birthday);
	}

	/**
	 * @param birthday the birthday to set
	 * @throws ParseException 
	 */
	public void setBirthday(String birthday) throws ParseException {
		this.birthday = new SimpleDateFormat("yy-MM-dd hh:mm:ss").parse(birthday);
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description.toString();
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = new StringBuffer(description);
	}

	/**
	 * @return the avgscore
	 */
	public String getAvgscore() {
		return avgscore.toString();
	}

	/**
	 * @param avgscore the avgscore to set
	 */
	public void setAvgscore(String avgscore) {
		this.avgscore = Integer.valueOf(avgscore);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", birthday=" + birthday + ", description=" + description
				+ ", avgscore=" + avgscore + "]";
	}
	
	
	
}
