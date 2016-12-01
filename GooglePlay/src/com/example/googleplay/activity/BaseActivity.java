package com.example.googleplay.activity;

import java.util.LinkedList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class BaseActivity extends ActionBarActivity 
{
    //管理所有的activity
	static List<BaseActivity> mactivities=new LinkedList<BaseActivity>();
	private List<BaseActivity>    copy;
	private KillAllReceiver receiver;
	
	//定义广播接受者
	private class KillAllReceiver extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context context, Intent intent) {
			finish();
			
		}
		
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		receiver = new KillAllReceiver();
		
		//设置过滤器
		IntentFilter filter=new IntentFilter("com.example.killall");
		
		registerReceiver(receiver, filter);
		
		
		synchronized (mactivities) 
		{
			mactivities.add(this);
		}
		
		init();
		initView();
		initActionBar();
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		synchronized (mactivities) 
		{
		mactivities.remove(this);
		}
		
		if(receiver!=null)
		{
			unregisterReceiver(receiver);
		}
		
	}
	
	//杀死所有进程
	public void killAll()
	{
		//复制一份到新的列表
		synchronized (mactivities) 
		{
		   copy = new LinkedList<BaseActivity>(mactivities);
		}
		for(BaseActivity activity:copy)
		{
			   activity.finish();
		}
		
		//杀死进程
		android.os.Process.killProcess(android.os.Process.myPid());
	
	}
	
	protected void initActionBar() {
		// TODO Auto-generated method stub
		
	}

	protected void initView() {
		// TODO Auto-generated method stub
		
	}

	protected void init() {
		// TODO Auto-generated method stub
		
	}
}
