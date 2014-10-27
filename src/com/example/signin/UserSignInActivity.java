package com.example.signin;

import com.example.model.User;
import com.example.service.AppService;
import com.example.service.WebService;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UserSignInActivity extends Activity {
	private ImageView userAvatar;
	private ImageButton signIn, changePwd;
	private TextView userName, userCard, lastTimeAction, tips;
	String card;
	String name;
	String sex;
	String userAvatarUrl;
	String lastTime;
	int registerStatus;
	String password;
	int action;
	protected ImageLoader imageLoader = ImageLoader.getInstance();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_sign_in);
		init();
		Intent intent = getIntent();
		card = intent.getStringExtra("extra");
		WebService.getUserInfo(handler, card);
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext()).build();
		imageLoader.init(config);
		changePwd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				View view;
				LayoutInflater inflater = (LayoutInflater) getApplicationContext()
						.getSystemService(LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.login, null);
				final EditText et_pwd_confirm = (EditText) view
						.findViewById(R.id.user_password);
				final EditText et_pwd = (EditText) view
						.findViewById(R.id.user_name);
				et_pwd.setTransformationMethod(PasswordTransformationMethod
						.getInstance());
				et_pwd_confirm
						.setTransformationMethod(PasswordTransformationMethod
								.getInstance());
				final AlertDialog.Builder builder = new AlertDialog.Builder(
						UserSignInActivity.this);
				builder.setTitle("设置签到密码").setView(view)
						.setPositiveButton("确定", null)
						.setNegativeButton("取消", null);
				final AlertDialog dialog = builder.create();
				dialog.show();
				dialog.getButton(Dialog.BUTTON_POSITIVE).setOnClickListener(
						new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								String pwd_confirm = et_pwd_confirm.getText()
										.toString();
								String pwd = et_pwd.getText().toString();
								if (pwd.equals("")) {
									Toast toast = Toast.makeText(
											UserSignInActivity.this,
											"请输入签到密码！！", Toast.LENGTH_LONG);
									toast.show();
								} else {
									if (pwd_confirm.equals("")) {
										Toast toast = Toast.makeText(
												UserSignInActivity.this,
												"请确认签到密码！！", Toast.LENGTH_LONG);
										toast.show();
									} else {
										if (pwd_confirm.equals(pwd)) {
											WebService.postPassword(handler,
													card, pwd_confirm);
											dialog.dismiss();
										} else {
											Toast toast = Toast.makeText(
													UserSignInActivity.this,
													"两次输入不一致 ！！",
													Toast.LENGTH_LONG);
											toast.show();
										}
									}
								}
							};
						});
			}
		});
		signIn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				View view;
				LayoutInflater inflater = (LayoutInflater) getApplicationContext()
						.getSystemService(LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.sign_in_by_pwd, null);
				final EditText et_pwd = (EditText) view
						.findViewById(R.id.user_name);
				et_pwd.setTransformationMethod(PasswordTransformationMethod
						.getInstance());
				final AlertDialog.Builder builder = new AlertDialog.Builder(
						UserSignInActivity.this);
				builder.setTitle("请输入签到密码").setView(view)
						.setPositiveButton("确定", null)
						.setNegativeButton("取消", null);
				final AlertDialog dialog = builder.create();
				dialog.show();
				dialog.getButton(Dialog.BUTTON_POSITIVE).setOnClickListener(
						new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								String pwd = et_pwd.getText().toString();
								if (pwd.equals(password)) {
									WebService.postAction(handler, card, 1);
									dialog.dismiss();
								} else {
									Toast toast = Toast
											.makeText(UserSignInActivity.this,
													"密码输入有误，请重新输入！！",
													Toast.LENGTH_LONG);
									toast.show();
								}
							};
						});

			}

		});
	}

	private void init() {
		userCard = (TextView) findViewById(R.id.card);
		signIn = (ImageButton) findViewById(R.id.sign_in);
		changePwd = (ImageButton) findViewById(R.id.change_pwd);
		userName = (TextView) findViewById(R.id.name);
		tips = (TextView) findViewById(R.id.tips);
		lastTimeAction = (TextView) findViewById(R.id.last_time);
		userAvatar = (ImageView) findViewById(R.id.user_avatar);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case AppService.GET_USER_INFO_SUCCESS: {
				User user = (User) msg.obj;
				name = user.getName();
				sex = user.getSex();
				lastTime = user.getLastTimeAction();
				registerStatus = user.getRegisterStatus();
				userAvatarUrl = user.getUserAvatarUrl();
				password = user.getRegisterPwd();
				imageLoader.displayImage(userAvatarUrl, userAvatar);
				userName.setText("姓名：   " + name);
				userCard.setText("学号：   " + card);
				if (password.equals("null")) {
					lastTimeAction.setText("您尚未设置签到密码，请点击下方按钮设置密码！！");
					changePwd.setVisibility(View.VISIBLE);
				} else {
					if (lastTime.equals("-1")) {
						lastTimeAction.setText("您尚无使用记录！！");
						signIn.setVisibility(View.VISIBLE);
					} else {
						lastTimeAction.setText("上次离岗时间：   " + lastTime);
						signIn.setVisibility(View.VISIBLE);
					}
				}
				break;
			}
			case AppService.POST_PASSWORD_SUCCESS: {
				new AlertDialog.Builder(UserSignInActivity.this)
						.setTitle("提示")
						.setMessage("您已设置签到密码，现在可以签到了！！")
						.setNegativeButton("确定",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface arg0,
											int arg1) {
										UserSignInActivity.this.finish();
									}
								}).show();
				break;
			}
			case AppService.POST_ACTION_SUCCESS: {
				Toast toast = Toast.makeText(UserSignInActivity.this, "签到成功！！",
						Toast.LENGTH_LONG);
				toast.show();
				SignInActivity.instance.finish();
				UserSignInActivity.this.finish();
				break;
			}
			}
		}
	};

}