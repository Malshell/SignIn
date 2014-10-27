package com.example.service;

import java.util.ArrayList;

import com.example.model.User;

public class AppService {
	public static final int GET_ALL_USER = 1;
	public static final int GET_ALL_USER_FAILED = 2;
	public static final int POST_ACTION_SUCCESS = 3;
	public static final int POST_AVATAR_SUCCESS = 4;
	public static final int GET_USER_INFO_SUCCESS=5;
	public static final int POST_PASSWORD_SUCCESS=10;
	public static final int GET_RANKING_SUCCESS=20;
	private static User currentUser = null;
	private static String Ip = "http://192.168.1.113:8888";
	private static ArrayList<User> currentUsers = null;

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currentUser) {
		AppService.currentUser = currentUser;
	}

	public static ArrayList<User> getCurrentUsers() {
		return currentUsers;
	}

	public static void setCurrentUsers(ArrayList<User> currentUsers) {
		AppService.currentUsers = currentUsers;
	}

	public static String getIp() {
		return Ip;
	}

	public static void setIp(String ip) {
		Ip = ip;
	}
}
