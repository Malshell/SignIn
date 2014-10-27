package com.example.signin;

import java.util.ArrayList;
import com.example.model.User;
import com.example.service.AppService;
import com.example.service.WebService;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SignInActivity extends Activity implements OnClickListener {

	private RelativeLayout[] userLayout = new RelativeLayout[20];
	private ImageView[] userAvatar = new ImageView[20];
	String[] card = new String[20];
	String[] name = new String[20];
	String[] sex = new String[20];
	String[] userAvatarUrl = new String[20];
	String[] lastTime = new String[20];
	int[] registerStatus = new int[20];
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	Intent intent = new Intent();
	public static SignInActivity instance = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		WebService.getAllUsers(handler);
		instance = this;
		init();
		for (int i = 0; i < 20; i++) {
			userLayout[i].setOnClickListener(this);
		}
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext()).build();
		imageLoader.init(config);

	}

	private void init() {
		for (int i = 0; i < 20; i++) {
			userAvatar[i] = (ImageView) findViewById(2131099711 + 2 * i);
			userLayout[i] = (RelativeLayout) findViewById(2131099710 + 2 * i);
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case AppService.GET_ALL_USER: {
				ArrayList<User> allusers = (ArrayList<User>) msg.obj;
				for (int i = 0; i < allusers.size(); i++) {
					User users = allusers.get(i);
					card[i] = users.getCard();
					name[i] = users.getName();
					sex[i] = users.getSex();
					lastTime[i] = users.getLastTimeAction();
					registerStatus[i] = users.getRegisterStatus();
					userAvatarUrl[i] = users.getUserAvatarUrl();
					if (registerStatus[i] == 0 || registerStatus[i] == -1) {
						userLayout[i].setVisibility(View.VISIBLE);
						imageLoader.displayImage(userAvatarUrl[i],
								userAvatar[i]);
					} else {
						userLayout[i].setVisibility(View.GONE);
					}
				}
				break;
			}
			case AppService.POST_ACTION_SUCCESS: {

			}
			case AppService.POST_AVATAR_SUCCESS: {

			}
			}
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.user1:
			intent.putExtra("extra", card[0]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		case R.id.user2:
			intent.putExtra("extra", card[1]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		case R.id.user3:
			intent.putExtra("extra", card[2]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		case R.id.user4:
			intent.putExtra("extra", card[3]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		case R.id.user5:
			intent.putExtra("extra", card[4]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		case R.id.user6:
			intent.putExtra("extra", card[5]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		case R.id.user7:
			intent.putExtra("extra", card[6]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		case R.id.user8:
			intent.putExtra("extra", card[7]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		case R.id.user9:
			intent.putExtra("extra", card[8]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		case R.id.user10:
			intent.putExtra("extra", card[9]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		case R.id.user11:
			intent.putExtra("extra", card[10]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		case R.id.user12:
			intent.putExtra("extra", card[11]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		case R.id.user13:
			intent.putExtra("extra", card[12]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		case R.id.user14:
			intent.putExtra("extra", card[13]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		case R.id.user15:
			intent.putExtra("extra", card[14]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		case R.id.user16:
			intent.putExtra("extra", card[15]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		case R.id.user17:
			intent.putExtra("extra", card[16]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		case R.id.user18:
			intent.putExtra("extra", card[17]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		case R.id.user19:
			intent.putExtra("extra", card[18]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		case R.id.user20:
			intent.putExtra("extra", card[19]);
			intent.setClass(SignInActivity.this, UserSignInActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}
}
