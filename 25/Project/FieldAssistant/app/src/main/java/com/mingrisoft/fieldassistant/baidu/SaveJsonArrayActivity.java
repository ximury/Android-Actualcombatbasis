package com.mingrisoft.fieldassistant.baidu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.tool.ACache;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * 
 */
public class SaveJsonArrayActivity extends Activity {

	private JSONArray jsonArray;

	private ACache mCache;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_layout);
		// 初始化控件
		initView();

		mCache = ACache.get(this);
		jsonArray = new JSONArray();
		JSONObject yosonJsonObject = new JSONObject();

		try {
			yosonJsonObject.put("name", "Yoson");
			yosonJsonObject.put("age", 18);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		JSONObject michaelJsonObject = new JSONObject();
		try {
			michaelJsonObject.put("name", "Michael");
			michaelJsonObject.put("age", 25);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		jsonArray.put(yosonJsonObject);
		jsonArray.put(michaelJsonObject);


	}

	/**
	 * 初始化控�?
	 */
	private void initView() {

	}

	/**
	 * 点击save事件
	 * 
	 * @param v
	 */
	public void save(View v) {
		mCache.put("locationlist", jsonArray);
	}

	/**
	 * 点击read事件
	 * 
	 * @param v
	 */
	public void read(View v) {
		JSONArray testJsonArray = mCache.getAsJSONArray("locationlist");
		if (testJsonArray == null) {
			Toast.makeText(this, "暂时还没有数据.....",
					Toast.LENGTH_SHORT).show();

			return;
		}

	}

	/**
	 * 点击clear事件
	 * 
	 * @param v
	 */
	public void clear(View v) {
		mCache.remove("locationlist");
	}
}
