package com.example.model;

public class UserRanking {
	private Double time;
	private int time1;
	private String name;

	public UserRanking(Double time, String name) {
		this.name = name;
		this.time = time;
	}

	public UserRanking(int time1, String name2) {
		this.name = name2;
		this.setTime1(time1);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public int getTime1() {
		return time1;
	}

	public void setTime1(int time1) {
		this.time1 = time1;
	}
}
