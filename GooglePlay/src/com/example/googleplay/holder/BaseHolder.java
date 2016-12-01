package com.example.googleplay.holder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.googleplay.R;
import com.example.googleplay.bean.SubjectInfo;
import com.example.googleplay.tools.BitmapHelper;
import com.lidroid.xutils.BitmapUtils;

public abstract class BaseHolder<Data> {

	public Data data;
	private View contentView;
	public  BitmapUtils utils;
	public BaseHolder()
	{
		contentView=initView();
		utils=BitmapHelper.getBitmapUtils();
		
		contentView.setTag(this);
	}
	
	public abstract View initView();
	
	public View getContentView()
	{
		return contentView;
	}
	
	public void setData(Data data)
	{
		this.data=data;
		refreshView(data);
	}
	
	public  abstract void refreshView(Data data);
}
