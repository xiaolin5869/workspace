package com.example.googleplay.activity;

import com.example.googleplay.R;
import com.example.googleplay.bean.AppInfo;
import com.example.googleplay.holder.BottonHolder;
import com.example.googleplay.holder.DetailDesHolder;
import com.example.googleplay.holder.DetailInfoHolder;
import com.example.googleplay.holder.DetailSafeHolder;
import com.example.googleplay.holder.DetailScreenHolder;
import com.example.googleplay.protocol.DetailProtocol;
import com.example.googleplay.tools.Utils;
import com.example.googleplay.view.LoadingPage;
import com.example.googleplay.view.LoadingPage.LoadResult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;

public class DetailActivity  extends BaseActivity
{
	
	public String packageName;
	private AppInfo data;
	private FrameLayout bottom_layout,detail_info,detail_safe,detail_des;	
	private HorizontalScrollView detail_screen;
	
	protected void initView() {
		LoadingPage loadpage=new LoadingPage(Utils.getContext()) {
			
			@Override
			public LoadResult load() {
				// TODO Auto-generated method stub
				return DetailActivity.this.Load();
			}
			
			@Override
			public View createSuccessView() {
				// TODO Auto-generated method stub
				return DetailActivity.this.createSuccessView();
			}
		};
		
		loadpage.show();
		setContentView(loadpage);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		Intent intent = getIntent();
		
		packageName=intent.getStringExtra("packageName");
		
		super.onCreate(savedInstanceState);
		
		
	}
	
     /**
      * 数据成功后创建的界面并返回
      * @return
      */
	   protected View createSuccessView() 
	   {
		View view=View.inflate(Utils.getContext(),R.layout.activity_detail, null);
		
		//底部布局
		bottom_layout = (FrameLayout) view.findViewById(R.id.bottom_layout);
		BottonHolder bottonholder=new BottonHolder();
		bottonholder.setData(data);
		bottom_layout.addView(bottonholder.getContentView());
		
		
		//app详情页
		detail_info=(FrameLayout) view.findViewById(R.id.detail_info);
		DetailInfoHolder holder=new DetailInfoHolder();
		//设置数据
		holder.setData(data);
		//加载holder初始化的界面
		detail_info.addView(holder.getContentView());
		
		//中间的加载动画
	    detail_safe=(FrameLayout) view.findViewById(R.id.detail_safe);
	    DetailSafeHolder safe=new DetailSafeHolder();
	    safe.setData(data);
	    detail_safe.addView(safe.getContentView());
	    
	    //最后的描述界面
	    detail_des=(FrameLayout) view.findViewById(R.id.detail_des);
	    DetailDesHolder desholder=new DetailDesHolder();
	    desholder.setData(data);
	    detail_des.addView(desholder.getContentView());
		
	    //中间的五张图片布局
	    detail_screen=(HorizontalScrollView) view.findViewById(R.id.detail_screen);
	    DetailScreenHolder screenholder=new DetailScreenHolder();
	    screenholder.setData(data);
	    detail_screen.addView(screenholder.getContentView());
	    
	    
	    return view;
	  }

	/**
	 * 向服务器请求数据的方法
	 * @return
	 */
	protected LoadResult Load() 
	{
		DetailProtocol protcol=new DetailProtocol(packageName);
		data = protcol.load(0);
		if(data==null)
		{
			return LoadResult.error;
		}else
		{
			return LoadResult.success;
		}
	
	}


	@Override
	protected void initActionBar() 
    {
		// TODO Auto-generated method stub
		super.initActionBar();
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
	}

}
