package com.example.googleplay.appication;

import android.app.Application;
import android.os.Handler;

public class BaseApplication extends Application 
{
	private static  BaseApplication application;
	private static int mainTid;
	private static Handler handler;
	
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	    application=this;
		
	   mainTid=android.os.Process.myTid();
	   handler=new Handler();
	}
	
	/**
	 * µÃµ½application
	 * @return
	 */
	public static BaseApplication getApplication()
	{
		return application;
	}
	
	
	public static int getMainTid() {
		return mainTid;
	}
	public static Handler getHandler() {
		return handler;
	}
}
