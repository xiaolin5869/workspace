package com.example.googleplay.fragment;

import java.util.List;

import com.example.googleplay.R;
import com.example.googleplay.adapter.DefaultAdaer;
import com.example.googleplay.adapter.ListBaseAdapter;
import com.example.googleplay.bean.AppInfo;
import com.example.googleplay.holder.BaseHolder;
import com.example.googleplay.protocol.AppProtocol;
import com.example.googleplay.tools.BitmapHelper;
import com.example.googleplay.tools.Consts;
import com.example.googleplay.tools.Utils;
import com.example.googleplay.view.BaseListView;
import com.example.googleplay.view.LoadingPage.LoadResult;
import com.lidroid.xutils.BitmapUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class AppFragment extends BaseFragment
{

	private List<AppInfo> datas;

	@Override
	public LoadResult load() {
		AppProtocol protocol=new AppProtocol();
		datas = protocol.load(0);
		return checkData(datas);
	}

	@Override
	public View createSuccessView() {
	    BaseListView listview=new BaseListView(Utils.getContext());
	    listview.setAdapter(new ListBaseAdapter(datas,listview)
	    {

			@Override
			public List<AppInfo> onload() {
				AppProtocol protocol=new AppProtocol();
				List<AppInfo> data= protocol.load(datas.size());
				return data;
			}
	    	
	    }); 
		return listview;
	}
}
