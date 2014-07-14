package com.arjinmc.model;

/**
 * @usage A model call person with id,name,and phone
 * @author Eminem Lu
 * @email arjinmc@hotmail.com
 * @date 2014年7月14日
 */

public class Person {

	private int id;
	private String name;
	//content for update
	private String phone;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
