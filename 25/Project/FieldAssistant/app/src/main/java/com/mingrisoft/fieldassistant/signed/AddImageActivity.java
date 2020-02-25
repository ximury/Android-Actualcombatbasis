package com.mingrisoft.fieldassistant.signed;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.mingrisoft.fieldassistant.home.BaseActivity;
import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.home.BaseApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/6/22 0022.
 */
public class AddImageActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton imageButton;
    private Button button;
    private ImageView imageView;
    private static final int PHOTO_REQUEST_CAMERA = 1;      // 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;     // 从相册中选择
    private static final String PHOTO_FILE_NAME = "myPhoto.png";    // 图片名称
    File tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME);
    private SharedPreferences sharedPreferences;
    private ProgressDialog pd;
    private boolean progressShow = true;
    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:

                    pd.setCanceledOnTouchOutside(false);
                    pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            progressShow = false;
                        }
                    });
                    pd.setMessage("正在压缩图片，请稍后....");
                    pd.show();

                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_location_image_layout);
        //初始化控件
        init();
    }

    /**
     * 初始化控件
     * */
    private void init() {
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        imageButton = (ImageButton) findViewById(R.id.signed_iv_back);
        button = (Button) findViewById(R.id.add_iv_btn);
        imageView = (ImageView) findViewById(R.id.add_iv);
        imageButton.setOnClickListener(this);
        button.setOnClickListener(this);
        imageView.setOnClickListener(this);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pd = new ProgressDialog(AddImageActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        imageView.setImageURI(Uri.parse(tempFile.getPath())); //给image设置图片
    }

    @Override
    protected void onPause() {
        super.onPause();
        imageView.setImageURI(null);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        imageView.setImageURI(Uri.parse(tempFile.getPath()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signed_iv_back:
                finish();
                break;
            case R.id.add_iv:
                tempFile.delete();
                camera();
                break;
            case R.id.add_iv_btn:
                changeReadPictureType();
                break;
        }
    }

    /**
     * 从相机获取
     */
    public void camera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME)));
        }
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
    }

    /**
     * 判断SD卡是否可用
     *
     * @return
     */
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 通过返回的值来设置图片
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                imageView.setBackground(null);
                // 得到图片的全路径
                Uri uri = data.getData();
                onStart();
            }

        } else if (requestCode == PHOTO_REQUEST_CAMERA) {
            if (hasSdcard()) {
                imageView.setImageURI(Uri.parse(String.valueOf(BaseApplication.getTempFile())));
            } else {
                Toast.makeText(AddImageActivity.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
            }
        }

    }

    //将选取出来的照片以字节的形式保存到本地，以便一键上传
    private void changeReadPictureType() {


        SharedPreferences.Editor editor = sharedPreferences.edit();
//        String fileName = "/sdcard/fishandpaws/" + "picture" + i + ".png";
        try {
            FileInputStream fis = new FileInputStream(tempFile.getPath());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = fis.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
                outputStream.flush();
            }
            byte[] date = outputStream.toByteArray();

            fis.close();
            outputStream.close();
            compress(date);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩图片
     *
     * @return
     */
    private Bitmap compress(byte[] data) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        double radio = Math.min(options.outWidth * 1.0d / (1024f * 3), options.outHeight * 1.0d / (1024f * 3));
        options.inSampleSize = (int) Math.ceil(radio);
        options.inJustDecodeBounds = false;
        final Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                msg.what = 1;
                handle.sendMessage(msg);
                ImageCompressL(bitmap);

            }
        }).start();
        return bitmap;
    }

    /**
     *  计算 bitmap大小，如果超过44kb，则进行压缩
     *
     * @param bitmap
     * @return
     */

    private Bitmap ImageCompressL(Bitmap bitmap) {
        double targetwidth = Math.sqrt(44.00 * 1000);
        if (bitmap.getWidth() > targetwidth || bitmap.getHeight() > targetwidth) {
            // 创建操作图片用的matrix对象
            Matrix matrix = new Matrix();
            // 计算宽高缩放率
            double x = Math.max(targetwidth / bitmap.getWidth(), targetwidth
                    / bitmap.getHeight());
            // 缩放图片动作
            matrix.postScale((float) x, (float) x);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), matrix, true);
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(tempFile.getPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, out);
        finish();
        return bitmap;
    }
}
