package com.domain;

public class Employee {
	private Long id;
	private String position;
	private float wage;
	private Company company;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public float getWage() {
		return wage;
	}
	public void setWage(float wage) {
		this.wage = wage;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	
}
