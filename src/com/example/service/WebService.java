package com.example.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.model.User;
import com.example.model.UserRanking;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class WebService {

	/**
	 * 上传当前动作人学号和当前动作 author:莫庸
	 * 
	 * @param handler
	 * @param cardNo
	 * @param passWord
	 */
	public static void postAction(final Handler handler, String card, int action) {
		JSONObject localJSONObject = new JSONObject();
		try {
			localJSONObject.put("card", card);
			localJSONObject.put("action", action);
		} catch (JSONException localJSONException) {
			localJSONException.getStackTrace();
		}
		LogUtil.i("json: " + localJSONObject.toString());

		WebService.postJson("/post_register", localJSONObject,
				new AsyncHttpResponseHandler() {
					public void onSuccess(String response) {
						Message msg = new Message();
						// String j = String.valueOf(i);
						// msg.obj = j;
						msg.what = AppService.POST_ACTION_SUCCESS;
						handler.sendMessage(msg);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						// Message msg = new Message();
						// msg.what = AppService.POST_NETWORK_TIMEOUT;
						// handler.sendMessage(msg);
						LogUtil.i("Failure Status Code "
								+ String.valueOf(statusCode));
						error.printStackTrace(System.out);
					}

					@Override
					public void onFinish() {
						// Completed the request (either success or failure)
						LogUtil.i("Finish");
					}
				});
	}

	/**
	 * 上传设置密码 author:莫庸
	 * 
	 * @param handler
	 * @param card
	 * @param password
	 */
	public static void postPassword(final Handler handler, String card,
			String password) {
		JSONObject localJSONObject = new JSONObject();
		try {
			localJSONObject.put("card", card);
			localJSONObject.put("pwd", password);
		} catch (JSONException localJSONException) {
			localJSONException.getStackTrace();
		}
		LogUtil.i("json: " + localJSONObject.toString());

		WebService.postJson("/post_user_pwd", localJSONObject,
				new AsyncHttpResponseHandler() {
					public void onSuccess(String response) {
						Message msg = new Message();
						msg.what = AppService.POST_PASSWORD_SUCCESS;
						handler.sendMessage(msg);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						// Message msg = new Message();
						// msg.what = AppService.POST_NETWORK_TIMEOUT;
						// handler.sendMessage(msg);
						LogUtil.i("Failure Status Code "
								+ String.valueOf(statusCode));
						error.printStackTrace(System.out);
					}

					@Override
					public void onFinish() {
						// Completed the request (either success or failure)
						LogUtil.i("Finish");
					}
				});
	}

	/**
	 * 获取所有已注册成员 author:莫庸
	 * 
	 * @param handler
	 */
	public static void getAllUsers(final Handler handler) {
		String getAllUsersUrl = "/get_all_users";
		get(getAllUsersUrl, null, new JsonHttpResponseHandler() {
			public void onSuccess(JSONObject jobject) {
				ArrayList<User> userRecords = new ArrayList<User>();
				try {
					int status = jobject.getInt("status");
					if (status == 1) {
						JSONArray records = jobject.getJSONArray("records");
						for (int i = 0; i < records.length(); i++) {
							JSONObject record = records.getJSONObject(i);
							String card = record.getString("card");
							String name = record.getString("name");
							String sex = record.getString("sex");
							String lastTime = record.getString("last_time");
							String userAvatarUrl = AppService.getIp() + "/"
									+ record.getString("user_avatar_url");
							String registerPwd = "null";
							int register_status = record
									.getInt("register_status");
							User u = new User(card, name, sex, register_status,
									registerPwd, lastTime, userAvatarUrl);
							userRecords.add(u);

						}
						Message msg = new Message();
						msg.obj = userRecords;
						msg.what = AppService.GET_ALL_USER;
						handler.sendMessage(msg);
					} else {
						Message msg = new Message();
						msg.what = AppService.GET_ALL_USER_FAILED;
						handler.sendMessage(msg);
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				System.out.println(statusCode);
			}
		});
	}

	/**
	 * 通过用户学号获取用户信息 author:莫庸
	 * 
	 * @param handler
	 * @param card
	 */
	public static void getUserInfo(final Handler handler, String card) {
		String getAllUsersUrl = "/get_one_user/" + card;
		get(getAllUsersUrl, null, new JsonHttpResponseHandler() {
			public void onSuccess(JSONObject jobject) {
				try {
					String card = jobject.getString("card");
					String name = jobject.getString("name");
					String sex = jobject.getString("sex");
					String lastTime = jobject.getString("last_time");
					String userAvatarUrl = AppService.getIp() + "/"
							+ jobject.getString("user_avatar_url");
					String registerPwd = jobject.getString("register_pwd");
					;
					int register_status = jobject.getInt("register_status");
					User u = new User(card, name, sex, register_status,
							registerPwd, lastTime, userAvatarUrl);
					Message msg = new Message();
					msg.obj = u;
					msg.what = AppService.GET_USER_INFO_SUCCESS;
					handler.sendMessage(msg);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				System.out.println(statusCode);
			}
		});
	}

	public static void getRankingByWeek(final Handler handler) {
		String getAllUsersUrl = "/count_all_user_time/2014/10/week";
		get(getAllUsersUrl, null, new JsonHttpResponseHandler() {
			public void onSuccess(JSONObject jobject) {
				ArrayList<UserRanking> userRankings = new ArrayList<UserRanking>();
				try {
					JSONArray records = jobject.getJSONArray("records");
					JSONArray times = jobject.getJSONArray("time");
					for (int i = 0; i < records.length(); i++) {
						JSONObject record = records.getJSONObject(i);
						Double time = (Double) times.get(i);
						String name = record.getString("name");
						UserRanking rank = new UserRanking(time, name);
						userRankings.add(rank);
					}
					Message msg = new Message();
					msg.obj = userRankings;
					msg.what = AppService.GET_RANKING_SUCCESS;
					handler.sendMessage(msg);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				System.out.println(statusCode);
			}
		});
	}

	static String BASE_URL = AppService.getIp() + "/api";
	// private static final String BASE_URL = "http://chss.imedialab.info/api";
	private static AsyncHttpClient client = new AsyncHttpClient();

	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void postJson(String url, JSONObject params,
			AsyncHttpResponseHandler responseHandler) {
		StringEntity entity;
		try {
			entity = new StringEntity(params.toString());
		} catch (Exception e) {
			LogUtil.d("StringEntity: IllegalArgumentException");
			return;
		}
		client.post(null, getAbsoluteUrl(url), entity, "application/json",
				responseHandler);
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}

}
