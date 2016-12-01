package com.example.googleplay.holder;

import java.util.LinkedList;
import java.util.List;

import com.example.googleplay.R;
import com.example.googleplay.tools.Consts;
import com.example.googleplay.tools.Utils;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Choreographer.FrameCallback;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class HomePictureHolder extends BaseHolder<List<String>>
{

	private ViewPager pager;
	private List<String> picture;
	/**
	 * 创建holder时会调用
	 */
	public View initView() 
	{		
		pager = new ViewPager(Utils.getContext());
		pager.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,Utils.getDimenHeight(R.dimen.home_picture_height)));
		
		return pager;
	}

	/**
	 * setdata时会调用
	 */
	public void refreshView(List<String> data) {
		
		this.picture=data;
		pager.setAdapter(new HomeAdapter());
		pager.setCurrentItem(1000*picture.size());
	
		
		task = new AutoRunTask();
		task.start();
		
		pager.setOnTouchListener(new OnTouchListener() 
		{
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
			 switch (event.getAction()) {
			 case MotionEvent.ACTION_DOWN:
				 task.stop();
				
				break;
			 case MotionEvent.ACTION_UP:
				 task.start();
				 break;
			 case MotionEvent.ACTION_CANCEL:
				 task.start();
				 break;
			
			}
				return false;
			}
		});
	}
	
	/**
	 * 定时任务类
	 * @author xiaolin
	 *
	 */
	boolean flag;
	private AutoRunTask task;
	class AutoRunTask implements Runnable
	{

		
		public void run() {
			if(flag)
			{
				Utils.cancel(this);
				int currentItem = pager.getCurrentItem();
				currentItem++;
				pager.setCurrentItem(currentItem);
				Utils.postDelayTask(this,2000);
			}
			
		}
		
		public void start()
		{
			if(!flag)
			{
				Utils.cancel(this);
				flag=true;
				Utils.postDelayTask(this,2000);
			}
		}
		
		public void stop()
		{
			if(flag)
			{
				flag=false;
				Utils.cancel(this);
			}
		}
		
	}
	
	class HomeAdapter extends PagerAdapter
	{

		LinkedList<ImageView> contentView=new LinkedList<ImageView>();
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			ImageView view=(ImageView) object;
			contentView.add(view);
			container.removeView(view);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView image;
			if(contentView.size()>0)
			{
				image=contentView.remove(0);
			}else
			{
				image=new ImageView(Utils.getContext());
			}
			int index=position%picture.size();
			utils.display(image, Consts.URL+"image?name="+picture.get(index));
			container.addView(image);
			return image;
		}
		
		
	}

}
