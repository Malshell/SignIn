package com.example.service;

import android.util.Log;

public class LogUtil {
	private final static String TAG="com.gt.ihealth";
	
	/*
	 * send a verbose() log message 
	 */
	public static void v(String msg){
		Log.v(TAG, msg);
	}

	/*
	 * send a debug log message
	 */
	public static void d(String msg){
		Log.v(TAG,msg);
	}
	
	/*
	 * send an info log message
	 */
	public static void i(String msg){
		Log.i(TAG,msg);
	}
	
	/*
	 * send a warning log message 
	 */
	public static void w(String msg){
		Log.w(TAG,msg);
	}
	
	/*
	 * send an error log message
	 */
	public static void e(String msg){
		Log.e(TAG,msg);
	}
}


