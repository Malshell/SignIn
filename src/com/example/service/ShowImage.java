package com.example.service;

import com.example.signin.R;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class ShowImage extends Activity implements OnTouchListener,
		OnGestureListener {
	private static ImageView image = null;
	private static final int FLING_MIN_DISTANCE = 120;// 移动最小距离
	private static final int FLING_MIN_VELOCITY = 200;// 移动最大速度
	private int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main1);
		image = (ImageView) findViewById(R.id.show);
		// pathsrcs-->存放图片路径的数组 ImagePositions-->选择的第几张图片
		String path = GridViewTest.pathsrcs.get(GridViewTest.ImagePositions);
		// 设置图片显示路径
		image.setImageURI(Uri.parse(path));
		// 设置Touch监听
		image.setOnTouchListener(this);
		// 允许长按
		image.setLongClickable(true);
	}

	// 构建手势探测器
	GestureDetector mygesture = new GestureDetector(this);

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return mygesture.onTouchEvent(event);
	}

	// 显示下一张图片
	public void showNextImage() {
		position = ++GridViewTest.ImagePositions;
		if (position >= GridViewTest.pathsrcs.size()) {
			Toast.makeText(ShowImage.this, "已到最后一张图片", Toast.LENGTH_SHORT)
					.show();
			GridViewTest.ImagePositions = GridViewTest.pathsrcs.size() - 1;
		} else {
			String path = GridViewTest.pathsrcs.get(position);
			image.setImageURI(Uri.parse(path));
		}
		System.out.println("positoon=" + position);
	}

	// 显示上一张图片
	public void showLastImage() {
		position = --GridViewTest.ImagePositions;
		if (position < 0) {
			Toast.makeText(ShowImage.this, "已到第一张图片", Toast.LENGTH_SHORT)
					.show();
			GridViewTest.ImagePositions = 0;
		} else {
			String path = GridViewTest.pathsrcs.get(position);
			image.setImageURI(Uri.parse(path));
		}
	}

	/*
	 * 用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发
	 */
	// 主要方法
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// e1：第1个ACTION_DOWN MotionEvent
		// e2：最后一个ACTION_MOVE MotionEvent
		// velocityX：X轴上的移动速度（像素/秒）
		// velocityY：Y轴上的移动速度（像素/秒）

		// X轴的坐标位移大于FLING_MIN_DISTANCE，且移动速度大于FLING_MIN_VELOCITY个像素/秒
		// 向有翻图片
		if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			showNextImage();
		}
		// 向左翻图片
		if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			showLastImage();
		}
		return false;
	}

	// ////////////////////////////////////////////////////////////下面方法没用，但是这里必须实现
	/* 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发 */
	@Override
	public void onLongPress(MotionEvent e) {
	}

	/* 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发 */
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	/*
	 * 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发
	 * 注意和onDown()的区别，强调的是没有松开或者拖动的状态
	 */
	@Override
	public void onShowPress(MotionEvent e) {
	}

	/* 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发 */
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

}
