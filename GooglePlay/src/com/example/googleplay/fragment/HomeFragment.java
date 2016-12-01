package com.example.googleplay.fragment;

import java.util.List;

import com.example.googleplay.R;
import com.example.googleplay.activity.DetailActivity;
import com.example.googleplay.adapter.DefaultAdaer;
import com.example.googleplay.adapter.ListBaseAdapter;
import com.example.googleplay.bean.AppInfo;
import com.example.googleplay.holder.BaseHolder;
import com.example.googleplay.holder.BaseListHolder;
import com.example.googleplay.holder.HomePictureHolder;
import com.example.googleplay.protocol.HomeProtocol;
import com.example.googleplay.tools.BitmapHelper;
import com.example.googleplay.tools.Consts;
import com.example.googleplay.tools.Utils;
import com.example.googleplay.tools.ViewUtils;
import com.example.googleplay.view.BaseListView;
import com.example.googleplay.view.LoadingPage.LoadResult;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.GetChars;
import android.text.format.Formatter;
import android.view.Choreographer.FrameCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class HomeFragment extends BaseFragment
{
	/**
	 * 服务器返回的结果
	 * @return
	 */
	private List<AppInfo> appData;
	private BaseListView listview;
	private ListBaseAdapter adapter;
	private BitmapUtils utils;
	private List<String> pictures;
	
	// 当Fragment挂载的activity创建的时候调用
			
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		show();
	}
			
	public LoadResult load() {
		
		HomeProtocol http=new HomeProtocol();
	    appData = http.load(0);
	    
	    pictures = http.getPictures();
	    
		return checkData(appData);
	}
	public View createSuccessView() {		
		listview = new BaseListView(Utils.getContext());		
		utils= BitmapHelper.getBitmapUtils();
		
		HomePictureHolder holder=new HomePictureHolder();
		holder.setData(pictures);
		View contentView=holder.getContentView();
		
		listview.addHeaderView(contentView);
		
		adapter=new ListBaseAdapter(appData,listview)
		{

			@Override
			public List<AppInfo> onload() {
				HomeProtocol http=new HomeProtocol();
				List<AppInfo> data= http.load(appData.size());
//				appData.addAll(data);
				return data;
			}
			
			@Override
			public void onInnerItemClick(int pos) {
				// TODO Auto-generated method stub
				super.onInnerItemClick(pos);
				
				AppInfo info = appData.get(pos);
				
				Intent intent=new Intent(Utils.getContext(),DetailActivity.class);
			    intent.putExtra("packageName", info.getPackageName());
			    startActivity(intent);
			
			}
			
		};
		listview.setAdapter(adapter);	
		listview.setOnScrollListener(new PauseOnScrollListener(utils, false, true));
		utils.configDefaultLoadFailedImage(R.drawable.ic_launcher);
		utils.configDefaultLoadingImage(R.drawable.ic_launcher);
		return listview;
	}
	
	
}
