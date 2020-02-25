package com.mingrisoft.fieldassistant.tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/7/3.
 */
public class BitmapUtils {
    /**
     * bitmap转为base64
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * base64转为bitmap
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * 对图片进行二次采样，生成缩略图。防止加载过大图片出现内存溢出
     */
    public static Bitmap createThumbnail(byte[] data, int newWidth,
                                         int newHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        int oldWidth = options.outWidth;
        int oldHeight = options.outHeight;

        int ratioWidth = 0;
        int ratioHeight = 0;

        if (newWidth != 0 && newHeight == 0) {
            ratioWidth = oldWidth / newWidth;
            options.inSampleSize = ratioWidth;
        } else if (newWidth == 0 && newHeight != 0) {
            ratioHeight = oldHeight / newHeight;
            options.inSampleSize = ratioHeight;
        }
        options.inPreferredConfig = Bitmap.Config.ALPHA_8;
        options.inJustDecodeBounds = false;
        Bitmap bm = BitmapFactory
                .decodeByteArray(data, 0, data.length, options);
        return bm;
    }
    /**
     * 对图片进行二次采样，生成缩略图。防止加载过大图片出现内存溢出
     */
    public static Bitmap createThumbnail(String pathName, int newWidth,
                                         int newHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        int oldWidth = options.outWidth;
        int oldHeight = options.outHeight;

        int ratioWidth = 0;
        int ratioHeight = 0;

        if (newWidth != 0 && newHeight == 0) {
            ratioWidth = oldWidth / newWidth;
            options.inSampleSize = ratioWidth;
        } else if (newWidth == 0 && newHeight != 0) {
            ratioHeight = oldHeight / newHeight;
            options.inSampleSize = ratioHeight;
        }
        options.inPreferredConfig = Bitmap.Config.ALPHA_8;
        options.inJustDecodeBounds = false;
        Bitmap bm = BitmapFactory.decodeFile(pathName, options);
        return bm;
    }
}
