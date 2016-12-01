package com.example.googleplay.tools;
/**
 * 工具类
 */
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.googleplay.appication.BaseApplication;



public class Utils 
{
	/**
	 * 得到资源
	 * @return
	 */
   public static Resources getResourse()
   {
	     return BaseApplication.getApplication().getResources();
   }
   
   /**
    * 得到资源数组
    * @param tab_names
    * @return
    */
   public static String[] getStringArray(int tab_names)
   {
	   return getResourse().getStringArray(tab_names);
   }
   
   public static Context getContext() {
		return BaseApplication.getApplication();
	}
   
   public static void runOnUiThread(Runnable runnable)
   {
	   //在主线程
	   if(android.os.Process.myTid()==BaseApplication.getMainTid())
	   {
		   runnable.run();
	   }else
	   {
		   //获取handler
		   BaseApplication.getHandler().post(runnable);
	   }
   }

	public static Drawable getDrawalbe(int id) {
		
		return getResourse().getDrawable(id);
	}
	

	public static int getDimenHeight(int homePictureHeight) {
		// TODO Auto-generated method stub
		return (int) getResourse().getDimension(homePictureHeight);
	}

	/**
	 * 取消任务
	 * @param autoRunTask
	 */
	public static void cancel(Runnable autoRunTask) {
		BaseApplication.getHandler().removeCallbacks(autoRunTask);
		
	}

	/**
	 * 延迟执行任务
	 * @param autoRunTask 任务
	 * @param time 时间
	 */
	public static void postDelayTask(Runnable autoRunTask, int time) {
		BaseApplication.getHandler().postDelayed(autoRunTask, time);
		
	}
}
