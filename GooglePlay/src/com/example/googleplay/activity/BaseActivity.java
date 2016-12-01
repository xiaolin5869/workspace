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
    //�������е�activity
	static List<BaseActivity> mactivities=new LinkedList<BaseActivity>();
	private List<BaseActivity>    copy;
	private KillAllReceiver receiver;
	
	//����㲥������
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
		
		//���ù�����
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
	
	//ɱ�����н���
	public void killAll()
	{
		//����һ�ݵ��µ��б�
		synchronized (mactivities) 
		{
		   copy = new LinkedList<BaseActivity>(mactivities);
		}
		for(BaseActivity activity:copy)
		{
			   activity.finish();
		}
		
		//ɱ������
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
