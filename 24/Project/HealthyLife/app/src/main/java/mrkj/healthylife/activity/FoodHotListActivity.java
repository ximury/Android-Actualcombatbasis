package mrkj.healthylife.activity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mrkj.healthylife.R;
import mrkj.healthylife.base.BaseActivity;
import mrkj.healthylife.entity.FoodMessage;
import mrkj.healthylife.entity.FoodType;
import mrkj.healthylife.utils.DBHelper;

public class FoodHotListActivity extends BaseActivity {
    private int sign= - 1 ; //控制列表的展开
    private String[] food_type_array;//食物类型数组
    private List<FoodType> food_list;//数据集合
    private ExpandableListView data_list;//折叠listview
    private Bitmap[] bitmaps;//图片资源
    private int[] ids;//图片资源ID数组

    /**
     * 设置标题栏
     */
    @Override
    protected void setActivityTitle() {
        initTitle();
        setTitle("食物热量对照表", this);
        setMyBackGround(R.color.watm_background_gray);
        setTitleTextColor(R.color.theme_blue_two);
        setTitleLeftImage(R.mipmap.mrkj_back_blue);
    }

    /**
     * 设置界面布局
     */
    @Override
    protected void getLayoutToView() {
        setContentView(R.layout.activity_food_hot_list);

    }

    /**
     * 初始化数据资源
     */
    @Override
    protected void initValues() {
        ids = new int[]{R.mipmap.mrkj_gu, R.mipmap.mrkj_cai,
        R.mipmap.mrkj_guo, R.mipmap.mrkj_rou, R.mipmap.mrkj_dan,
        R.mipmap.mrkj_yv, R.mipmap.mrkj_nai, R.mipmap.mrkj_he,
        R.mipmap.mrkj_jun, R.mipmap.you};
        bitmaps = new Bitmap[ids.length];
        for (int i = 0;i < ids.length ; i++){
            bitmaps[i] = BitmapFactory.decodeResource(getResources(),ids[i]);
        }
        food_type_array = new String[]{"五谷类",
                "蔬菜类", "水果类", "肉类",
                "蛋类", "水产类", "奶类",
                "饮料类", "菌藻类", "油脂类"};
        food_list = new ArrayList<>();
        //构造数据源
        DBHelper dbHelper = new DBHelper();
        Cursor cursor = dbHelper.selectAllDataOfTable("hot");
//        Log.e("数据数量", cursor.getCount() + "");
//        int j = 0;
        for (int i = 0; i < 10; i++) {
            FoodType foodType = null;
            List<FoodMessage> foods = null;
            int counts = 1;
            while (cursor.moveToNext()) {
//                Log.e("计数", (++j) + "");
//                Log.e("counts", counts + "");
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String hot = cursor.getString(cursor.getColumnIndex("hot"));
                String type_name = cursor.getString(cursor.getColumnIndex("type_name"));
                if (counts == 1) {
                    foodType = new FoodType();
                    foods = new ArrayList<>();
                    foodType.setFood_type(type_name);
//                    Log.e("type_name", type_name + "");

                }
                FoodMessage foodMessage = new FoodMessage();
                foodMessage.setFood_name(name);
                foodMessage.setHot(hot);
                foods.add(foodMessage);
                foodType.setFood_list(foods);
                if (counts == 20) {
                    food_list.add(foodType);
                    break;
                }
                counts++;
            }
        }
        cursor.close();
        Log.e("数据的长度", food_list.size() + "");
//        for (FoodType foodType : food_list) {
//            Log.e("食物类型", foodType.getFood_type() + "");
//            Log.e("食物数量",foodType.getFood_list().size() + "");
//        }
    }

    @Override
    protected void initViews() {
        data_list = (ExpandableListView) findViewById(R.id.food_list);
    }
    /**
     * 绑定适配器
     */
    @Override
    protected void setViewsFunction() {
        MyFoodAdapter adapter = new MyFoodAdapter();
        data_list.setAdapter(adapter);
    }
    /**
     * 设置点击展开一个其余的都收起
     */
    @Override
    protected void setViewsListener() {
        data_list.setOnGroupClickListener( new  ExpandableListView.OnGroupClickListener() {

            @Override
            public   boolean  onGroupClick(ExpandableListView parent, View v,
                                           int  groupPosition,  long  id) {
                // TODO Auto-generated method stub
                if  (sign== - 1 ) {
                    // 展开被选的group
                    data_list.expandGroup(groupPosition);
                    // 设置被选中的group置于顶端
                    data_list.setSelectedGroup(groupPosition);
                    sign= groupPosition;
                }  else   if  (sign== groupPosition) {
                    data_list.collapseGroup(sign);
                    sign= - 1 ;
                }  else  {
                    data_list.collapseGroup(sign);
                    // 展开被选的group
                    data_list.expandGroup(groupPosition);
                    // 设置被选中的group置于顶端
                    data_list.setSelectedGroup(groupPosition);
                    sign= groupPosition;
                }
                return   true ;
            }
        });
    }




    /**
     * 适配器
     */

    class MyFoodAdapter extends BaseExpandableListAdapter{

        //Group的数量
        @Override
        public int getGroupCount() {
            return food_list.size();
        }
        //每个Group中的Child的数量
        @Override
        public int getChildrenCount(int groupPosition) {
            return food_list.get(groupPosition).getFood_list().size();
        }
        //获取对应位置的Group
        @Override
        public Object getGroup(int groupPosition) {
            return food_list.get(groupPosition);
        }
        //获取对应位置中的Child
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return food_list.get(groupPosition).getFood_list().get(childPosition);
        }
        //获取对应位置的Group的ID
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }
        //获取对应位置的Child的ID
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }
        //判断同一个ID是否指向同一个对象
        @Override
        public boolean hasStableIds() {
            return true;
        }
        //获取Group的视图
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupViewHolder holder;
            if (convertView == null){
                holder = new GroupViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.group_item , null);
                holder.image = (ImageView) convertView.findViewById(R.id.group_image);
                holder.title = (TextView) convertView.findViewById(R.id.group_title);
                convertView.setTag(holder);
            }else {
                holder = (GroupViewHolder) convertView.getTag();
            }
            holder.image.setImageBitmap(bitmaps[groupPosition]);
            holder.title.setText(food_type_array[groupPosition]);
            return convertView;
        }
        //获取child的视图
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildViewHolder holder;
            if (convertView == null){
                holder = new ChildViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.child_item,null);
                holder.name = (TextView) convertView.findViewById(R.id.food_name);
                holder.hot = (TextView) convertView.findViewById(R.id.food_hot);
                convertView.setTag(holder);
            }else {
                holder = (ChildViewHolder) convertView.getTag();
            }
            FoodMessage food = food_list.get(groupPosition).getFood_list().get(childPosition);
            holder.name.setText(food.getFood_name());
            holder.hot.setText(food.getHot()+"千卡/克");
            return convertView;
        }
        //判断child是否可以被选择
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    class GroupViewHolder{
        ImageView image;
        TextView title;
    }
    class ChildViewHolder{
        TextView name,hot;
    }
}
