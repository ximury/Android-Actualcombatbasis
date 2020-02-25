package mrkj.healthylife.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

/**
 * Created by Administrator on 2016/5/27.
 */
public class BaseFragment extends Fragment{

    public LayoutInflater inflater;//布局填充器
    /**
     * 初始化
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = LayoutInflater.from(getContext());
    }
}
