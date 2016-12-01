package com.example.googleplay.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.googleplay.R;
import com.example.googleplay.bean.AppInfo;
import com.example.googleplay.tools.BitmapHelper;
import com.example.googleplay.tools.Consts;
import com.example.googleplay.tools.Utils;
import com.lidroid.xutils.BitmapUtils;

public class BaseListHolder extends BaseHolder<AppInfo>
{
	public ImageView item_icon;
	public TextView item_title,item_size,item_bottom;	
	public RatingBar item_rating;
	private BitmapUtils utils;

	public View initView() {
		View contentView=View.inflate(Utils.getContext(), R.layout.item_app, null);
		this.item_icon=(ImageView) contentView.findViewById(R.id.item_icon);
		this.item_title=(TextView) contentView.findViewById(R.id.item_title);
		this.item_size=(TextView) contentView.findViewById(R.id.item_size);
		this.item_rating=(RatingBar) contentView.findViewById(R.id.item_rating);
		this.item_bottom=(TextView) contentView.findViewById(R.id.item_bottom);	
		return contentView;
	}

	@Override
	public void refreshView(AppInfo data) 
	{
		utils=BitmapHelper.getBitmapUtils();
        this.item_title.setText(data.getName());		
        this.item_size.setText(Formatter.formatFileSize(Utils.getContext(), data.getSize()));		
        this.item_bottom.setText(data.getDes());		
        this.item_rating.setRating(data.getStars());
        utils.display(this.item_icon, Consts.URL+"image?name="+data.getIconUrl());
		
	}

}
