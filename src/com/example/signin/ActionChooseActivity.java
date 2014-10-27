package com.example.signin;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ActionChooseActivity extends Activity {
	private ImageButton signIn, leave, ranking;
	MediaPlayer mediaPlayer;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_choose);
		signIn = (ImageButton) findViewById(R.id.sign_in);
		leave = (ImageButton) findViewById(R.id.leave);
//		ranking = (ImageButton) findViewById(R.id.ranking);
		mediaPlayer = MediaPlayer
				.create(ActionChooseActivity.this, R.raw.intel);
		// mediaPlayer.start();
		signIn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent();
				intent.setClass(ActionChooseActivity.this, SignInActivity.class);
				startActivity(intent);
			}
		});
		leave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent();
				intent.setClass(ActionChooseActivity.this, LeaveActivity.class);
				startActivity(intent);
			}
		});
//		ranking.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//
//				Intent intent = new Intent();
//				intent.setClass(ActionChooseActivity.this,
//						RankingActivity.class);
//				startActivity(intent);
//			}
//		});
	}

}
