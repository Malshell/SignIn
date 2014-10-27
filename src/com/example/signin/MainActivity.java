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

	// ����һ��button�������������һ��imageview��ʾ��������������Ƭ;
	Button but, upload_image;
	ImageView img;
	// ��ȡsd����Ŀ¼��ַ,������ͼƬ��Ŀ¼�ļ�������ļ��Ķ���;
	String file_str = Environment.getExternalStorageDirectory().getPath();
	File mars_file = new File(file_str + "/ǩ����Ƭ");
	File file_go;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		but = (Button) findViewById(R.id.my_camare_button);
		// upload_image = (Button) findViewById(R.id.upload_image);
		// img = (ImageView) findViewById(R.id.my_img_view);
		// ����
		but.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// ��֤sd���Ƿ���ȷ��װ��
				if (Environment.MEDIA_MOUNTED.equals(Environment
						.getExternalStorageState())) {
					// �ȴ�����Ŀ¼������´���һ���ļ���ʱ�򣬸�Ŀ¼û�д��ڣ���ô�����ȴ�����Ŀ¼�����½��ļ���
					if (!mars_file.exists()) {
						mars_file.mkdirs();
					}

					/*
					 * //��������£���������� ������Ŀ¼���������ﲻ��ϵͳ������Ϻ�����������ͼƬ·���Զ�ȥʵ��;
					 * if(!file_go.exists()) { try { file_go.createNewFile(); }
					 * catch (IOException e) { }}
					 */
					// ������ת��ϵͳ���յ�activityΪ��MediaStore.ACTION_IMAGE_CAPTURE ;
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					// ���������յĴ��ڷ�ʽΪ�ⲿ�洢�ʹ洢��·����
					SimpleDateFormat nowdateformat = new SimpleDateFormat(
							"MM-dd HH:mm");
					Date nowDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
					String nowTime = nowdateformat.format(nowDate);
					file_go = new File(file_str + "/ǩ����Ƭ/" + nowTime
							+ " ����.jpg");
					intent.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(file_go));

					// ��ת�����ս���;
					startActivityForResult(intent, 0x1);

				} else {
					Toast.makeText(MainActivity.this, "���Ȱ�װ��sd��",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		// // �ϴ�
		// upload_image.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// if (file_go.exists()) {
		// // ��֤ͼƬ���ں��ʵ�֣��ϴ����ܣ��õ���������������...
		// // ʲôURLconnection ��HttpURLconnectio�ȶ�����.......
		// // Toast.makeText(MainActivity.this, "�ϴ���....",
		// // Toast.LENGTH_LONG).show();
		// } else {
		// Toast.makeText(MainActivity.this, "��������....",
		// Toast.LENGTH_LONG).show();
		// }
		// }
		// });

	}

	// ���ս�������ʾͼƬ;
	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// // �ж�������ͽ�����Ƿ���ȷ�������ȷ�Ļ�����activity����ʾ�ո������յ�ͼƬ;
	// if (requestCode == 0x1 && resultCode == this.RESULT_OK) {
	// /*
	// * ʹ��BitmapFactory.Options���ֹOOM(Out Of Memory)�����⣻
	// * ����һ��BitmapFactory.Options����������bitmap��
	// */
	// BitmapFactory.Options myoptions = new BitmapFactory.Options();
	// /*
	// * ����Options����inJustDecodeBounds������Ϊtrue��������BitmapFactory��
	// * decodeFile(String path, Options opt)���ȡͼƬ�ĸߺͿ�
	// * ������������������ֵΪtrue��ʹ��BitmapFactory��decodeFile()�����޷�����һ��
	// * ͼƬ��bitmap���󣬽����ǰ�ͼƬ�ĸߺͿ���Ϣ��Options����
	// */
	// myoptions.inJustDecodeBounds = true;
	// BitmapFactory.decodeFile(file_go.getAbsolutePath(), myoptions);
	// // ������ͼƬ�Ŀ�͸ߣ��õ�ͼƬ�ڲ����ε����ָ����С�µ�����ͼ,���ÿ�Ϊ222��
	// int height = myoptions.outHeight * 222 / myoptions.outWidth;
	// myoptions.outWidth = 222;
	// myoptions.outHeight = height;
	// // ������������ͼƬ��ʾ�ĸߺͿ���סҪ�޸ģ�Options����inJustDecodeBounds������Ϊfalse;
	// // ��Ȼ�޷���ʾͼƬ;
	// myoptions.inJustDecodeBounds = false;
	// // ��û������Ÿտ�ʼ,Ҫ��Լ�ڴ滹��Ҫ�������ԣ���������ؼ���һ����
	// myoptions.inSampleSize = myoptions.outWidth / 222;
	// // ����������������������������С�ڴ棻
	// myoptions.inPurgeable = true;
	// myoptions.inInputShareable = true;
	// myoptions.inPreferredConfig = Bitmap.Config.ARGB_4444;//
	// Ĭ����Bitmap.Config.ARGB_8888
	// // �ɹ��ˣ��������ʾͼƬ����
	// Bitmap bitmat = BitmapFactory.decodeFile(file_go.getAbsolutePath(),
	// myoptions);
	// img.setImageBitmap(bitmat);
	//
	// } else {
	// System.out.println("����ʾͼƬ");
	// }
	// super.onActivityResult(requestCode, resultCode, data);
	// }
}