package com.example.signin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	// 定义一个button打开照相机，定义一个imageview显示照相机所拍摄的相片;
	Button but, upload_image;
	ImageView img;
	// 获取sd卡根目录地址,并创建图片父目录文件对象和文件的对象;
	String file_str = Environment.getExternalStorageDirectory().getPath();
	File mars_file = new File(file_str + "/签到照片");
	File file_go;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		but = (Button) findViewById(R.id.my_camare_button);
		// upload_image = (Button) findViewById(R.id.upload_image);
		// img = (ImageView) findViewById(R.id.my_img_view);
		// 拍照
		but.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// 验证sd卡是否正确安装：
				if (Environment.MEDIA_MOUNTED.equals(Environment
						.getExternalStorageState())) {
					// 先创建父目录，如果新创建一个文件的时候，父目录没有存在，那么必须先创建父目录，再新建文件。
					if (!mars_file.exists()) {
						mars_file.mkdirs();
					}

					/*
					 * //常规情况下，我们这里会 创建子目录，但在这里不用系统拍照完毕后会根据所给的图片路径自动去实现;
					 * if(!file_go.exists()) { try { file_go.createNewFile(); }
					 * catch (IOException e) { }}
					 */
					// 设置跳转的系统拍照的activity为：MediaStore.ACTION_IMAGE_CAPTURE ;
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					// 并设置拍照的存在方式为外部存储和存储的路径；
					SimpleDateFormat nowdateformat = new SimpleDateFormat(
							"MM-dd HH:mm");
					Date nowDate = new Date(System.currentTimeMillis());// 获取当前时间
					String nowTime = nowdateformat.format(nowDate);
					file_go = new File(file_str + "/签到照片/" + nowTime
							+ " 王杰.jpg");
					intent.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(file_go));

					// 跳转到拍照界面;
					startActivityForResult(intent, 0x1);

				} else {
					Toast.makeText(MainActivity.this, "请先安装好sd卡",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		// // 上传
		// upload_image.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// if (file_go.exists()) {
		// // 验证图片存在后就实现，上传功能，得到与服务器的输出流...
		// // 什么URLconnection ，HttpURLconnectio等都可以.......
		// // Toast.makeText(MainActivity.this, "上传中....",
		// // Toast.LENGTH_LONG).show();
		// } else {
		// Toast.makeText(MainActivity.this, "请先拍照....",
		// Toast.LENGTH_LONG).show();
		// }
		// }
		// });

	}

	// 拍照结束后显示图片;
	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// // 判断请求码和结果码是否正确，如果正确的话就在activity上显示刚刚所拍照的图片;
	// if (requestCode == 0x1 && resultCode == this.RESULT_OK) {
	// /*
	// * 使用BitmapFactory.Options类防止OOM(Out Of Memory)的问题；
	// * 创建一个BitmapFactory.Options类用来处理bitmap；
	// */
	// BitmapFactory.Options myoptions = new BitmapFactory.Options();
	// /*
	// * 设置Options对象inJustDecodeBounds的属性为true，用于在BitmapFactory的
	// * decodeFile(String path, Options opt)后获取图片的高和宽；
	// * 而且设置了他的属性值为true后使用BitmapFactory的decodeFile()方法无法返回一张
	// * 图片的bitmap对象，仅仅是把图片的高和宽信息给Options对象；
	// */
	// myoptions.inJustDecodeBounds = true;
	// BitmapFactory.decodeFile(file_go.getAbsolutePath(), myoptions);
	// // 根据在图片的宽和高，得到图片在不变形的情况指定大小下的缩略图,设置宽为222；
	// int height = myoptions.outHeight * 222 / myoptions.outWidth;
	// myoptions.outWidth = 222;
	// myoptions.outHeight = height;
	// // 在重新设置玩图片显示的高和宽后记住要修改，Options对象inJustDecodeBounds的属性为false;
	// // 不然无法显示图片;
	// myoptions.inJustDecodeBounds = false;
	// // 还没完这里才刚开始,要节约内存还需要几个属性，下面是最关键的一个；
	// myoptions.inSampleSize = myoptions.outWidth / 222;
	// // 还可以设置其他几个属性用于缩小内存；
	// myoptions.inPurgeable = true;
	// myoptions.inInputShareable = true;
	// myoptions.inPreferredConfig = Bitmap.Config.ARGB_4444;//
	// 默认是Bitmap.Config.ARGB_8888
	// // 成功了，下面就显示图片咯；
	// Bitmap bitmat = BitmapFactory.decodeFile(file_go.getAbsolutePath(),
	// myoptions);
	// img.setImageBitmap(bitmat);
	//
	// } else {
	// System.out.println("不显示图片");
	// }
	// super.onActivityResult(requestCode, resultCode, data);
	// }
}