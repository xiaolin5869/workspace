package com.example.googleplay.holder;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.googleplay.R;
import com.example.googleplay.bean.AppInfo;
import com.example.googleplay.tools.Utils;

public class BottonHolder extends BaseHolder<AppInfo> implements OnClickListener
{

	private Button bottom_favorites,bottom_share,progress_btn;
	public View initView() {
		View view=View.inflate(Utils.getContext(), R.layout.detail_bottom, null);
		bottom_favorites=(Button) view.findViewById(R.id.bottom_favorites);
		bottom_share=(Button) view.findViewById(R.id.bottom_share);
		progress_btn=(Button) view.findViewById(R.id.progress_btn);
		
		
		
		return view;
	}

	@Override
	public void refreshView(AppInfo data) {
		//Ôö¼Ó¼àÌý
		bottom_favorites.setOnClickListener(this);
	    bottom_share.setOnClickListener(this);
		progress_btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		
	}

}
