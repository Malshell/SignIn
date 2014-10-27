package com.example.signin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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

import com.example.model.User;
import com.example.service.AppService;
import com.example.service.WebService;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class UserLeaveActivity extends Activity {
	private ImageView userAvatar;
	private ImageButton leave;
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
		setContentView(R.layout.activity_user_leave);
		init();
		Intent intent = getIntent();
		card = intent.getStringExtra("extra");
		WebService.getUserInfo(handler, card);
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext()).build();
		imageLoader.init(config);
		leave.setOnClickListener(new View.OnClickListener() {
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
						UserLeaveActivity.this);
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
									WebService.postAction(handler, card, 0);
									dialog.dismiss();
								} else {
									Toast toast = Toast
											.makeText(UserLeaveActivity.this,
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
		leave = (ImageButton) findViewById(R.id.leave);
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
				lastTimeAction.setText("上次到岗时间：   " + lastTime);
				break;
			}
			case AppService.POST_ACTION_SUCCESS: {
				Toast toast = Toast.makeText(UserLeaveActivity.this, "离岗成功！！",
						Toast.LENGTH_LONG);
				toast.show();
				LeaveActivity.instance.finish();
				UserLeaveActivity.this.finish();
				break;
			}
			}
		}
	};

}
