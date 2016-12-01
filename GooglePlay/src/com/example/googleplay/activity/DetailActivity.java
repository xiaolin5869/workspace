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
      * ���ݳɹ��󴴽��Ľ��沢����
      * @return
      */
	   protected View createSuccessView() 
	   {
		View view=View.inflate(Utils.getContext(),R.layout.activity_detail, null);
		
		//�ײ�����
		bottom_layout = (FrameLayout) view.findViewById(R.id.bottom_layout);
		BottonHolder bottonholder=new BottonHolder();
		bottonholder.setData(data);
		bottom_layout.addView(bottonholder.getContentView());
		
		
		//app����ҳ
		detail_info=(FrameLayout) view.findViewById(R.id.detail_info);
		DetailInfoHolder holder=new DetailInfoHolder();
		//��������
		holder.setData(data);
		//����holder��ʼ���Ľ���
		detail_info.addView(holder.getContentView());
		
		//�м�ļ��ض���
	    detail_safe=(FrameLayout) view.findViewById(R.id.detail_safe);
	    DetailSafeHolder safe=new DetailSafeHolder();
	    safe.setData(data);
	    detail_safe.addView(safe.getContentView());
	    
	    //������������
	    detail_des=(FrameLayout) view.findViewById(R.id.detail_des);
	    DetailDesHolder desholder=new DetailDesHolder();
	    desholder.setData(data);
	    detail_des.addView(desholder.getContentView());
		
	    //�м������ͼƬ����
	    detail_screen=(HorizontalScrollView) view.findViewById(R.id.detail_screen);
	    DetailScreenHolder screenholder=new DetailScreenHolder();
	    screenholder.setData(data);
	    detail_screen.addView(screenholder.getContentView());
	    
	    
	    return view;
	  }

	/**
	 * ��������������ݵķ���
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
