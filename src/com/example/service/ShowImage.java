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
	private static final int FLING_MIN_DISTANCE = 120;// �ƶ���С����
	private static final int FLING_MIN_VELOCITY = 200;// �ƶ�����ٶ�
	private int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main1);
		image = (ImageView) findViewById(R.id.show);
		// pathsrcs-->���ͼƬ·�������� ImagePositions-->ѡ��ĵڼ���ͼƬ
		String path = GridViewTest.pathsrcs.get(GridViewTest.ImagePositions);
		// ����ͼƬ��ʾ·��
		image.setImageURI(Uri.parse(path));
		// ����Touch����
		image.setOnTouchListener(this);
		// ������
		image.setLongClickable(true);
	}

	// ��������̽����
	GestureDetector mygesture = new GestureDetector(this);

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return mygesture.onTouchEvent(event);
	}

	// ��ʾ��һ��ͼƬ
	public void showNextImage() {
		position = ++GridViewTest.ImagePositions;
		if (position >= GridViewTest.pathsrcs.size()) {
			Toast.makeText(ShowImage.this, "�ѵ����һ��ͼƬ", Toast.LENGTH_SHORT)
					.show();
			GridViewTest.ImagePositions = GridViewTest.pathsrcs.size() - 1;
		} else {
			String path = GridViewTest.pathsrcs.get(position);
			image.setImageURI(Uri.parse(path));
		}
		System.out.println("positoon=" + position);
	}

	// ��ʾ��һ��ͼƬ
	public void showLastImage() {
		position = --GridViewTest.ImagePositions;
		if (position < 0) {
			Toast.makeText(ShowImage.this, "�ѵ���һ��ͼƬ", Toast.LENGTH_SHORT)
					.show();
			GridViewTest.ImagePositions = 0;
		} else {
			String path = GridViewTest.pathsrcs.get(position);
			image.setImageURI(Uri.parse(path));
		}
	}

	/*
	 * �û����´������������ƶ����ɿ�����1��MotionEvent ACTION_DOWN, ���ACTION_MOVE, 1��ACTION_UP����
	 */
	// ��Ҫ����
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// e1����1��ACTION_DOWN MotionEvent
		// e2�����һ��ACTION_MOVE MotionEvent
		// velocityX��X���ϵ��ƶ��ٶȣ�����/�룩
		// velocityY��Y���ϵ��ƶ��ٶȣ�����/�룩

		// X�������λ�ƴ���FLING_MIN_DISTANCE�����ƶ��ٶȴ���FLING_MIN_VELOCITY������/��
		// ���з�ͼƬ
		if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			showNextImage();
		}
		// ����ͼƬ
		if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			showLastImage();
		}
		return false;
	}

	// ////////////////////////////////////////////////////////////���淽��û�ã������������ʵ��
	/* �û��������������ɶ��MotionEvent ACTION_DOWN���� */
	@Override
	public void onLongPress(MotionEvent e) {
	}

	/* �û����´����������϶�����1��MotionEvent ACTION_DOWN, ���ACTION_MOVE���� */
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	/*
	 * �û��ᴥ����������δ�ɿ����϶�����һ��1��MotionEvent ACTION_DOWN����
	 * ע���onDown()������ǿ������û���ɿ������϶���״̬
	 */
	@Override
	public void onShowPress(MotionEvent e) {
	}

	/* �û����ᴥ���������ɿ�����һ��1��MotionEvent ACTION_UP���� */
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

}
