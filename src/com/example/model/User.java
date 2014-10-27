package com.example.model;

import android.graphics.drawable.Drawable;

public class User {
	private String card;
	private String name;
	private String sex;
	private int registerStatus;
	private String registerPwd;
	private String lastTimeAction;
	private String userAvatarUrl;

	public User(String card, String name, String sex, int registerStatus,
			String registerPwd, String lastTimeAction, String userAvatarUrl) {
		this.card = card;
		this.name = name;
		this.sex = sex;
		this.registerStatus = registerStatus;
		this.registerPwd = registerPwd;
		this.lastTimeAction = lastTimeAction;
		this.userAvatarUrl = userAvatarUrl;
	}

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

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public int getRegisterStatus() {
		return registerStatus;
	}

	public void setRegisterStatus(int registerStatus) {
		this.registerStatus = registerStatus;
	}

	public String getLastTimeAction() {
		return lastTimeAction;
	}

	public void setLastTimeAction(String lastTimeAction) {
		this.lastTimeAction = lastTimeAction;
	}

	public String getUserAvatarUrl() {
		return userAvatarUrl;
	}

	public void setUserAvatarUrl(String userAvatarUrl) {
		this.userAvatarUrl = userAvatarUrl;
	}

	public String getRegisterPwd() {
		return registerPwd;
	}

	public void setRegisterPwd(String registerPwd) {
		this.registerPwd = registerPwd;
	}

}
