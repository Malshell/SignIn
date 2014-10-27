package com.example.signin;

import java.util.ArrayList;
import com.example.model.UserRanking;
import com.example.service.AppService;
import com.example.service.WebService;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class RankingActivity extends Activity {
	String[] name=new String[20];
	Double[] time=new Double[20];
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranking);
		WebService.getRankingByWeek(handler);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case AppService.GET_RANKING_SUCCESS: {
				ArrayList<UserRanking> userRankings = (ArrayList<UserRanking>) msg.obj;
				for(int i=0;i<userRankings.size();i++){
					UserRanking userRanking=userRankings.get(i);
					name[i]=userRanking.getName();
					time[i]=userRanking.getTime();
				}
			}
			}
		}
	};

}
