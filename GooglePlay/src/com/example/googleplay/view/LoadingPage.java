package com.example.googleplay.view;

import com.example.googleplay.R;
import com.example.googleplay.appication.BaseApplication;
import com.example.googleplay.tools.ThreadManager;
import com.example.googleplay.tools.Utils;


import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;

public abstract class LoadingPage extends FrameLayout
{

	    private View loadingView;
		private View errorView;
		private View emptyView;

		private final int STATE_UNKNOW=1;
		private final int STATE_LOADING=2;
		private final int STATE_ERROR=3;
		private final int STATE_EMPTY=4;
		private final int STATE_SUCCESS=5;
		
	    public int state=STATE_UNKNOW;
	    
		private View successView;
		
	public LoadingPage(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public LoadingPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	

	public LoadingPage(Context context) {
		super(context);
		init();
	}

	private void init() {
		loadingView = createLoadingView();
		
		if(loadingView!=null)
		{
			this.addView(loadingView,
					new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		}
		
		errorView = createErrorView();
		if(errorView!=null)
		{
			this.addView(errorView,
					new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		
		
		}
		
		emptyView = createEmptyView();
		if(emptyView!=null)
		{
			this.addView(emptyView,
					new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		}

		showPage();
		
	}
	
	/**
	 * 创建为空时的布局
	 * @return
	 */
	private View createEmptyView() {
		View view=View.inflate(Utils.getContext(), R.layout.loadpage_empty, null);
		return view;
	}

    /**
     * 创建错误时的布局
     * @return
     */
	private View createErrorView() 
	{
		View view=View.inflate(Utils.getContext(), R.layout.loadpage_error, null);
		
		Button button=(Button) view.findViewById(R.id.page_bt);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				show();
			}
		});
		
		return view;
	}

	/**
	 * 创建加载中的布局
	 * @return
	 */
	private View createLoadingView() 
	{
		View view=View.inflate(Utils.getContext(), R.layout.loadpage_loading, null);
		return view;
	}
	
	public enum LoadResult
    {
    	error(3),empty(4),success(5);
    	
    	int value;
    	LoadResult(int value)
    	{
    		this.value=value;
    	}
    	 public int getvalue()
    	 {
    		 return value;
    	 }
    }
	
	 //根据服务器返回码显示界面
		public void show() 
		{
		   if(state==STATE_ERROR || state==STATE_EMPTY)
		   {
			   //重置状态
			   state=STATE_UNKNOW;
		   }
		   
			ThreadManager.getInstance().createLongPool().execute(new Runnable() {
				
				@Override
				public void run() 
				{
					 SystemClock.sleep(2000);
					  
					  final LoadResult result=load();
					
					  Utils.runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							 if(result!=null)
								  state=result.getvalue();
							 
							 showPage();
						}
					});
					
				}
			});
			
			showPage();
		}
		

		//根据不同的状态选择不同的界面
		private void showPage() 
		{
			if(loadingView!=null)
			{
				loadingView.setVisibility(state==STATE_UNKNOW || state==STATE_LOADING ? View.VISIBLE:View.INVISIBLE);			
			}
			
			if(errorView!=null)
			{		
		       errorView.setVisibility(state==STATE_ERROR?View.VISIBLE:View.INVISIBLE);
			}
			if(emptyView!=null)
			{
				
				emptyView.setVisibility(state==STATE_EMPTY?View.VISIBLE:View.INVISIBLE);
			}
			
			if (state == STATE_SUCCESS) {
				if (successView == null) {
					successView = createSuccessView();
					this.addView(successView, new FrameLayout.LayoutParams(
							LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				}
				successView.setVisibility(View.VISIBLE);
			} else {
				if (successView != null) {
					successView.setVisibility(View.INVISIBLE);
				}
			}
		}
		
		/**
		    * 向服务器请求数据
		    * @return
		    */
		   public abstract LoadResult load();
		   
		   /**
		    * 创建数据请求成功时的界面
		    * @return
		    */
		   public abstract View createSuccessView();

}
