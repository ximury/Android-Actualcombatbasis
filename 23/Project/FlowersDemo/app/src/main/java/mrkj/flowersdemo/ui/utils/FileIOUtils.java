package mrkj.flowersdemo.ui.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/7/28.
 */
public class FileIOUtils {
    public static File  ExternalStorageDirectory= Environment.getExternalStorageDirectory();//根目录
    /**
     * 判断文件夹是否被创建
     * @param file
     * @param name
     * @param bitmap
     * @return
     * @throws FileNotFoundException
     */
    public static String writeFile(File file, String name, Bitmap bitmap)
            throws FileNotFoundException {
        File save_bitmap = new File(file,name);
        boolean result = bitmap.compress(Bitmap.CompressFormat.JPEG,100,
                new BufferedOutputStream(new FileOutputStream(save_bitmap)));
        if (result){
            return save_bitmap.getPath();
        }
        return null;
    }

    public static Bitmap readFile(String path) throws IOException {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        return bitmap;
    }
}
