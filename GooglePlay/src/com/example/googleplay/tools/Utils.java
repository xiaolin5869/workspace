package com.example.googleplay.tools;
/**
 * ������
 */
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.googleplay.appication.BaseApplication;



public class Utils 
{
	/**
	 * �õ���Դ
	 * @return
	 */
   public static Resources getResourse()
   {
	     return BaseApplication.getApplication().getResources();
   }
   
   /**
    * �õ���Դ����
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
	   //�����߳�
	   if(android.os.Process.myTid()==BaseApplication.getMainTid())
	   {
		   runnable.run();
	   }else
	   {
		   //��ȡhandler
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
	 * ȡ������
	 * @param autoRunTask
	 */
	public static void cancel(Runnable autoRunTask) {
		BaseApplication.getHandler().removeCallbacks(autoRunTask);
		
	}

	/**
	 * �ӳ�ִ������
	 * @param autoRunTask ����
	 * @param time ʱ��
	 */
	public static void postDelayTask(Runnable autoRunTask, int time) {
		BaseApplication.getHandler().postDelayed(autoRunTask, time);
		
	}
}
