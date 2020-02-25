package mrkj.flowersdemo.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mrkj.flowersdemo.R;
import mrkj.flowersdemo.application.MyDataHelper;
import mrkj.flowersdemo.ui.bean.Flower;
import mrkj.flowersdemo.ui.utils.L;

/**
 * Created by Administrator on 2016/8/3.
 */
public class SelectorShareFlowerAdapter extends BaseAdapter{
    private ArrayList<Flower> flowers;//数据源
    private ArrayList<Flower> selects;//选择的数
    private LayoutInflater inflater;//布局填充器
    private Bitmap[] bitmaps;//图片
    private boolean isRight;//数值有效

    public interface SelectShareFlowerCallBack{
        void closeKeyBoard();//关闭软件盘
        void selectValuesIsRightful(boolean rightful);//选择的值有效
    }
    private SelectShareFlowerCallBack listener;
    public void setCloseKyeBoardListener(SelectShareFlowerCallBack listener){
        this.listener = listener;
    }
    public SelectorShareFlowerAdapter(Context context, ArrayList<Flower> flowers) {
        this.flowers = flowers;
        this.bitmaps = MyDataHelper.getInstance().getBitmapArray(context);
        this.inflater = LayoutInflater.from(context);
        selects = new ArrayList<>();
        for (int i = 0;i<flowers.size();i++){
            selects.add(new Flower(0,i));
        }
    }

    @Override
    public int getCount() {
        return flowers.size();
    }

    @Override
    public Object getItem(int position) {
        return flowers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SelectorShareFlowerAdapter.ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.select_share_flower_item_layout,null);
            holder.flower_image = (ImageView) convertView.findViewById(R.id.flower_image);
            holder.flower_name = (TextView) convertView.findViewById(R.id.flower_name);
            holder.all_count = (TextView) convertView.findViewById(R.id.all_counts);
            holder.input_count = (EditText) convertView.findViewById(R.id.input_count);
            holder.add_count = (Button) convertView.findViewById(R.id.add_count);
            holder.delete_count = (Button) convertView.findViewById(R.id.delete_count);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.flower_image.setTag(flowers.get(position));
        if (flowers.get(position) == holder.flower_image.getTag()){
            //花朵的图片
            holder.flower_image.setImageBitmap(bitmaps[flowers.get(position).getIndex()]);
            //花朵的名称
            holder.flower_name.setText(flowers.get(position).getName());
            //该类种的总数
            holder.all_count.setText(Html.fromHtml("已拥有<b><font color='#ff0000'>"+ flowers.get(position).getCount()+"</b></font>朵"));
            //输入文本
            holder.input_count.addTextChangedListener(new InputTextChangeListener(position));
            holder.input_count.setOnFocusChangeListener(new InputFocusChangeListener());
            //增加数量（设置分享的数量）
            holder.add_count.setOnClickListener(new AddShareFlowerCount(position,holder.input_count));
            //减少数量（设置分享的数量）
            holder.delete_count.setOnClickListener(new DeleteShareFlowerCount(position,holder.input_count));
        }
        return convertView;
    }

    /**
     * 增加分享花的数量
     */
    class AddShareFlowerCount implements View.OnClickListener{
        private int position;//索引值
        private EditText input_count;
        public AddShareFlowerCount(int position,EditText view) {
            this.position = position;
            this.input_count = view;
        }

        @Override
        public void onClick(View v) {
            L.e("点击了第"+ position + "的Add","AddShareFlowerCount");
            setShow(input_count);
            //增加数量
            //获取当前的数量
            if ("".equals(input_count.getText().toString())){
                //此时输入框内没有值
                input_count.setText(1 + "");
                return;
            }
            int count = Integer.parseInt(input_count.getText().toString());
            if (count == 999){//最大输入的值
                return;
            }
            input_count.setText(++count + "");
        }
    }
    /**
     * 减少分享花的数量
     */
    class DeleteShareFlowerCount implements View.OnClickListener{
        private int position;//索引值
        private EditText input_count;//文本输入框
        public DeleteShareFlowerCount(int position,EditText view) {
            this.position = position;
            this.input_count = view;
        }

        @Override
        public void onClick(View v) {
            L.e("点击了第"+ position + "的Delete","DeleteShareFlowerCount");
            setShow(input_count);
            //减少数量
            if ("".equals(input_count.getText().toString())){
                return;
            }
            if ("1".equals(input_count.getText().toString())){
                //此时输入框内没有值
                input_count.setText("");
                return;
            }
            int count = Integer.parseInt(input_count.getText().toString());
            input_count.setText(--count + "");
        }
    }

    /**
     * 显示的效果
     * @param input_count
     */
    private void setShow (EditText input_count){
        input_count.setFocusable(true);
        input_count.setFocusableInTouchMode(true);
        input_count.requestFocus();
        if (listener != null){
            listener.closeKeyBoard();
        }
    }
    /**
     * 输入框焦点改变
     */
    class InputFocusChangeListener implements View.OnFocusChangeListener{

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            L.e("焦点",hasFocus + "");
        }
    }
    /**
     * 输入框文本改变内容监听
     */
    class InputTextChangeListener implements TextWatcher{
        private int position;//索引值

        public InputTextChangeListener(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            L.e("内容改变前的内容为" + position, "【" + s.toString() + "】");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            L.e("内容改变后的内容为"+ position, "【" + s.toString() + "】");
            if ("".equals(s.toString())){
                //存入数值有效
                selects.get(position).setCount(0).setValid(true);
            }else {
                int count = Integer.parseInt(s.toString());
                if (count > flowers.get(position).getCount()){
                    selects.get(position).setCount(count).setValid(false);//数值无效
                    L.e("提示","所选数不能大于总数！");
                }else {
                    //存入数值有效
                    selects.get(position).setCount(count).setValid(true);
                }
            }
            //遍历集合用于获取提示信息
            traverseListToHint();
            //回调函数
            if (listener != null){
                listener.selectValuesIsRightful(isRight);
            }
        }
    }

    /**
     * 提示方法
     */
    private void traverseListToHint() {
        //遍历数据
        for (Flower flower:selects){
            int count = flower.getCount();
            if (count == 0){
                continue;
            }
            if (!flower.isValid()){
                isRight = false;
                return;
            }
        }
        isRight = true;
    }
    /**
     * 获取设置的数据
     */
    public ArrayList<Flower> getSelectValues(){
        ArrayList<Flower> selectValues = new ArrayList<>();
        for(Flower flower:selects){
            if (flower.getCount() == 0){
                continue;
            }
            selectValues.add(flower);//添加选择值有效的数据
        }
        return selectValues.size() == 0 ? null:selectValues;
    }
    /**
     * 复用
     */
    class ViewHolder{
        ImageView flower_image;
        Button add_count,delete_count;
        EditText input_count;
        TextView all_count,flower_name;
    }
}
