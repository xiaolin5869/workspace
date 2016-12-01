package com.example.googleplay.fragment;

import java.util.List;

import com.example.googleplay.R;
import com.example.googleplay.bean.AppInfo;
import com.example.googleplay.tools.BitmapHelper;
import com.example.googleplay.tools.ViewUtils;
import com.example.googleplay.view.LoadingPage;
import com.example.googleplay.view.LoadingPage.LoadResult;
import com.lidroid.xutils.BitmapUtils;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;

public abstract class BaseFragment extends Fragment
{
   
   private LoadingPage loadpage;
   public BitmapUtils utils;
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState)
   {
	 
	   
	   if(loadpage==null)
	   {
		    loadpage=new LoadingPage(getActivity()) {
			
			@Override
			public LoadResult load() {
				// TODO Auto-generated method stub
				return BaseFragment.this.load();
			}
			
			@Override
			public View createSuccessView() {
				// TODO Auto-generated method stub
				return BaseFragment.this.createSuccessView();
			}
		};
	   }else
	   {
		   ViewUtils.removeParent(loadpage);
	   }
	    
	    return loadpage;
	    
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
   
  
	public void show()
	{
		loadpage.show();
	}
	
	public  LoadResult checkData(List load) 
	{
		if(load==null)
		{
			return LoadResult.error;
		}else if(load.size()==0)
		{
			return LoadResult.empty;
		}else
		{
			return LoadResult.success;
		}
		
	}

}
