package com.example.googleplay.holder;

import org.w3c.dom.Text;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.googleplay.R;
import com.example.googleplay.bean.AppInfo;
import com.example.googleplay.tools.Consts;
import com.example.googleplay.tools.Utils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class DetailInfoHolder extends BaseHolder<AppInfo>
{

	
	@ViewInject(R.id.item_icon)
	private ImageView item_icon;
	@ViewInject(R.id.item_title)
	private TextView item_title;
	@ViewInject(R.id.item_rating)
	private RatingBar item_rating;
	@ViewInject(R.id.item_download)
	private TextView item_download;
	@ViewInject(R.id.item_version)
	private TextView item_version;
	@ViewInject(R.id.item_date)
	private TextView item_date;
	@ViewInject(R.id.item_size)
	private TextView item_size;
	@Override
	public View initView() {
		View view=View.inflate(Utils.getContext(),R.layout.detail_app_info, null);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void refreshView(AppInfo data) {
		utils.display(item_icon, Consts.URL+"image?name="+data.getIconUrl());
		item_title.setText(data.getName());
		item_rating.setRating(data.getStars());
		item_download.setText("下载:"+data.getDownloadNum());
		item_version.setText("版本:"+data.getVersion());
		item_date.setText("时间:"+data.getDate());
		item_size.setText("大小:"+Formatter.formatFileSize(Utils.getContext(), data.getSize()));	
	}

}
