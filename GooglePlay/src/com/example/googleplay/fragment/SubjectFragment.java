package com.example.googleplay.fragment;


import java.util.List;

import com.example.googleplay.R;
import com.example.googleplay.adapter.DefaultAdaer;
import com.example.googleplay.bean.AppInfo;
import com.example.googleplay.bean.SubjectInfo;
import com.example.googleplay.holder.BaseHolder;
import com.example.googleplay.protocol.SubjectProtocol;
import com.example.googleplay.tools.BitmapHelper;
import com.example.googleplay.tools.Consts;
import com.example.googleplay.tools.Utils;
import com.example.googleplay.view.BaseListView;
import com.example.googleplay.view.LoadingPage.LoadResult;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class SubjectFragment extends BaseFragment
{

	private List<SubjectInfo> datas;
	private BitmapUtils utils;
	private BaseListView lv;

	@Override
	public LoadResult load() {
		
		SubjectProtocol protocol=new SubjectProtocol();
		
		datas = protocol.load(0);
		
		return checkData(datas);
	}

	@Override
	public View createSuccessView() {
		lv = new BaseListView(Utils.getContext());
		
		lv.setAdapter(new SubjectAdapter(datas));
		
		utils= BitmapHelper.getBitmapUtils();
		lv.setOnScrollListener(new PauseOnScrollListener(utils, false, true));
		utils.configDefaultLoadFailedImage(R.drawable.ic_launcher);
		utils.configDefaultLoadingImage(R.drawable.ic_launcher);
		
		return lv;
	}
	
	class SubjectAdapter extends DefaultAdaer<SubjectInfo>
	{

		public SubjectAdapter(List<SubjectInfo> data) {
			super(data,lv);
			// TODO Auto-generated constructor stub
		}

		@Override
		public BaseHolder<SubjectInfo> getHolder() {
			// TODO Auto-generated method stub
			return new SubjectHolder();
		}

		@Override
		public List<SubjectInfo> onload() {
			SubjectProtocol protocol=new SubjectProtocol();
			
			List<SubjectInfo> data = protocol.load(datas.size());

			return data;
		}

	}

	static class SubjectHolder extends BaseHolder<SubjectInfo>
	{
		public ImageView item_icon;
		public TextView item_txt;
		private BitmapUtils bitutils;
		
		@Override
		public View initView() {
			View contentView=View.inflate(Utils.getContext(), R.layout.item_subject, null);
			bitutils= BitmapHelper.getBitmapUtils();
			this.item_icon=(ImageView) contentView.findViewById(R.id.item_icon);
			this.item_txt=(TextView) contentView.findViewById(R.id.item_txt);		
			return contentView;
		}

		@Override
		public void refreshView(SubjectInfo data) 
		{
			this.item_txt.setText(data.des);
		
			bitutils.display(this.item_icon, Consts.URL+"image?name="+data.url);
			
		}
		
	}
		
		
	
   
}
