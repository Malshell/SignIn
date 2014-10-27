package com.example.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.signin.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridViewTest extends Activity {
	private File[] files;
	public static List<String> pathsrcs;
	public static int ImagePositions;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sdcard);
		GridView gridview = (GridView) findViewById(R.id.sdcard);
		// File f = android.os.Environment.getExternalStorageDirectory();
		files = new File("/sdcard/").listFiles();
		pathsrcs = new ArrayList<String>();

		for (File file : files) {
			String path = file.getPath();
			// String path=file.getAbsolutePath();
			if (validate(path)) {
				pathsrcs.add(path);
			}

		}
		gridview.setAdapter(new ImageAdapter(this));
		gridview.setOnItemClickListener(new gridviewItemClick());
	}

	class gridviewItemClick implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			// 点击操作
			Intent intent = new Intent();
			ImagePositions = position;// 图片的位置
			intent.setClass(GridViewTest.this, ShowImage.class);
			startActivity(intent);
		}

	}

	class ImageAdapter extends BaseAdapter {
		private Context mContext;

		public ImageAdapter(Context c) {
			mContext = c;
		}

		public int getCount() {
			return pathsrcs.size();
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
			// return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			ImageView imageView;
			if (convertView == null) { // if it's not recycled, initialize some
										// attributes
				imageView = new ImageView(mContext);
				imageView.setLayoutParams(new GridView.LayoutParams(85, 85));// 控制图片大小
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(8, 8, 8, 8);
			} else {
				imageView = (ImageView) convertView;
			}
			String path = pathsrcs.get(position);
			File file = new File(path);
			if (file.exists()) {
				Bitmap bitmap = BitmapFactory.decodeFile(path);
				imageView.setImageBitmap(bitmap);
			}
			return imageView;
		}
	}

	/**
	 * @判断是否存在该类型的图片
	 * @param fileName
	 * @return boolean
	 */
	private boolean validate(String fileName) {
		int idx = fileName.indexOf(".");
		String subfix = fileName.substring(idx + 1);
		if (fileName.equals("")) {
			return false;
		}
		// subfix.equals()||subfix.equals(".png")||subfix.equals(".jpeg")
		if ("jpg".equals(subfix) || "png".equals(subfix)
				|| "gif".equals(subfix)) {
			return true;
		} else {
			return false;
		}
	}
}
