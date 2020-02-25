package mrkj.flowersdemo.ui.activity;

import android.graphics.Color;
import android.widget.ListView;

import mrkj.flowersdemo.R;
import mrkj.flowersdemo.ui.adapter.MyAdapter;
import mrkj.flowersdemo.ui.base.BaseActivity;

/**
 * 名言警句
 */
public class ShowQuotesActivity extends BaseActivity {

    private ListView show_data;
    @Override
    protected int setChildContentView() {
        return R.layout.activity_show_quotes;
    }

    @Override
    protected void onCreateChild() {
        setStyle();
        show_data = (ListView) findViewById(R.id.show_quotes);
        MyAdapter adapter = new MyAdapter(this);
        show_data.setAdapter(adapter);
    }
    /**
     * 初始化界面的显示
     */
    private void setStyle() {
        //设置界面背景
        setActivityBackGround(R.mipmap.mrkj_grow_background);
        //设置标题栏的背景
        setTitleBackgroundColor(Color.TRANSPARENT);
        //设置标题栏
        setTitle("名人名言",getResources().getColor(R.color.left_text_color),R.mipmap.mrkj_com_back,true);
    }
    @Override
    public boolean isAddTitle() {
        return true;
    }

    @Override
    public boolean setActivityFullScreen() {
        return true;
    }
}
