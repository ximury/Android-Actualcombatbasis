package mrkj.healthylife.activity;

import android.content.res.AssetManager;
import android.widget.TextView;

import org.apache.http.util.EncodingUtils;

import java.io.IOException;
import java.io.InputStream;

import mrkj.healthylife.R;
import mrkj.healthylife.base.BaseActivity;

public class AboutActivity extends BaseActivity {
    // 声明标题栏控件
    private TextView showTxt;
    private String aboutTxt;
    @Override
    protected void setActivityTitle() {
        initTitle();
        setTitle("关于我们", this);
        setMyBackGround(R.color.watm_background_gray);
        setTitleTextColor(R.color.theme_blue_two);
        setTitleLeftImage(R.mipmap.mrkj_back_blue);
    }

    @Override
    protected void getLayoutToView() {
        setContentView(R.layout.activity_about);
    }

    @Override
    protected void initValues() {
        // 获取字符串
        AssetManager assetManager = getAssets();
        InputStream is = null;
        try {
            is = assetManager.open("about/about.txt");
            int length = is.available();
            // 创建byte数组
            byte[] buffer = new byte[length];
            // 将文件中的数据读到byte数组中
            is.read(buffer);
            aboutTxt = EncodingUtils.getString(buffer, "UTF-8");
            System.out.println("读取：" + aboutTxt);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void initViews() {
        showTxt = (TextView) findViewById(R.id.about_txt);
    }

    @Override
    protected void setViewsListener() {

    }

    @Override
    protected void setViewsFunction() {
        showTxt.setText(aboutTxt);
    }
}
