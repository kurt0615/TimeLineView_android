package com.example.kurt.timelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kurt.timelistview.Vo.BaseVo;
import com.example.kurt.timelistview.Vo.LocationVo;
import com.example.kurt.timelistview.Vo.RoomVo;

import java.util.ArrayList;

/**
 * Created by Kurt on 2014/10/14.
 */
public class SpinnerAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    ArrayList<? extends BaseVo> mDataSource;

    public SpinnerAdapter(Context context, ArrayList<? extends BaseVo> dataSource) {
        layoutInflater = LayoutInflater.from(context);
        mDataSource = dataSource;
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(mDataSource.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.spinner, null);
            holder = new ViewHolder();
            holder.Name = (TextView)view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (mDataSource.get(position) instanceof LocationVo){
            holder.Name.setText(((LocationVo) mDataSource.get(position)).getLocationName());
        }else if (mDataSource.get(position) instanceof RoomVo){
            holder.Name.setText(((RoomVo) mDataSource.get(position)).getRoomName());
        }

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }
}

class ViewHolder{
    TextView Name;
}
