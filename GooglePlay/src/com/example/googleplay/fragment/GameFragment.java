package com.example.googleplay.fragment;


import java.util.List;

import com.example.googleplay.adapter.ListBaseAdapter;
import com.example.googleplay.bean.AppInfo;
import com.example.googleplay.protocol.AppProtocol;
import com.example.googleplay.protocol.GameProtocol;
import com.example.googleplay.tools.Utils;
import com.example.googleplay.view.BaseListView;
import com.example.googleplay.view.LoadingPage.LoadResult;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GameFragment extends BaseFragment
{

	private List<AppInfo> datas;

	@Override
	public LoadResult load() {
		GameProtocol protocol=new GameProtocol();
		datas = protocol.load(0);
		return checkData(datas);
	}

	@Override
	public View createSuccessView() {
	    BaseListView listview=new BaseListView(Utils.getContext());
	    listview.setAdapter(new ListBaseAdapter(datas,listview)
	    {

			@Override
			public List<AppInfo> onload() 
			{
				GameProtocol protocol=new GameProtocol();
				List<AppInfo> data = protocol.load(datas.size());
				return data;
			}
	    	
	    }); 
		return listview;
	}
   
}
