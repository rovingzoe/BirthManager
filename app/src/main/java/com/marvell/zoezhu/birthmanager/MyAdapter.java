package com.marvell.zoezhu.birthmanager;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * Created by zoezhu on 2015/11/4.
 */
public class MyAdapter extends ArrayAdapter {

    int resourceId;
    private View.OnClickListener mOnEditClickListener;
    private View.OnClickListener mOnRemoveClickListener;

    public void setOnEditButtonClickListener(View.OnClickListener listener) {
        mOnEditClickListener = listener;
    }
    public void setOnRemoveButtonClickListener(View.OnClickListener listener) {
        mOnRemoveClickListener = listener;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = (Item)getItem(position); // 获取当前项的Fruit实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.item_name);
            viewHolder.birthday = (TextView) view.findViewById(R.id.item_birthday);
            viewHolder.btn_edit = (Button) view.findViewById(R.id.btn_edit);
            viewHolder.btn_remove = (Button) view.findViewById(R.id.btn_remove);
            view.setTag(viewHolder);

//            view.setOnClickListener(mOnItemClickListener);
//            btn_edit.setOnClickListener(mOnEditClickListener);
//            btn_remove.setOnClickListener(mOnRemoveClickListener);

        }else
        {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(item.getName());
        viewHolder.birthday.setText(item.getBirth());
        viewHolder.btn_edit.setOnClickListener(mOnEditClickListener);
        viewHolder.btn_remove.setOnClickListener(mOnRemoveClickListener);
        viewHolder.btn_edit.setTag(item);
        viewHolder.btn_remove.setTag(item);
        return view;
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    public MyAdapter(Context context, int resource, List<Item> items) {
        super(context, resource, items);
        resourceId = resource;
    }

}
