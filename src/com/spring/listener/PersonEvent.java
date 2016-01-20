package com.spring.listener;

import org.springframework.context.ApplicationEvent;

public class PersonEvent extends ApplicationEvent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PersonEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	public PersonEvent(Object source,String name){
		super(source);
		this.name = name;
	}
	
	

}
