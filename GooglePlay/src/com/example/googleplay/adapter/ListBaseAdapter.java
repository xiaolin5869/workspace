package com.example.googleplay.adapter;

import java.util.List;

import android.widget.ListView;

import com.example.googleplay.bean.AppInfo;
import com.example.googleplay.holder.BaseHolder;
import com.example.googleplay.holder.BaseListHolder;

public abstract class ListBaseAdapter extends DefaultAdaer<AppInfo>
{

	public ListBaseAdapter(List<AppInfo> data,ListView listview) {
		super(data, listview);
		
	}

	@Override
	public BaseHolder<AppInfo> getHolder() {
		// TODO Auto-generated method stub
		return new BaseListHolder();
	}

	@Override
	public abstract List<AppInfo> onload();
}
