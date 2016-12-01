package com.example.googleplay.holder;

import java.util.List;

import com.example.googleplay.R;
import com.example.googleplay.bean.AppInfo;
import com.example.googleplay.tools.Consts;
import com.example.googleplay.tools.Utils;

import android.view.View;
import android.widget.ImageView;

public class DetailScreenHolder  extends BaseHolder<AppInfo>
{

	private ImageView[] ivs;
	
	public View initView() {
		View view=View.inflate(Utils.getContext(), R.layout.detail_screen, null);
		
		ivs=new ImageView[5];		
		ivs[0]=(ImageView) view.findViewById(R.id.screen_1);
		ivs[1]=(ImageView) view.findViewById(R.id.screen_2);		
		ivs[2]=(ImageView) view.findViewById(R.id.screen_3);	
		ivs[3]=(ImageView) view.findViewById(R.id.screen_4);	
		ivs[4]=(ImageView) view.findViewById(R.id.screen_5);
	
		return view;
	}

	@Override
	public void refreshView(AppInfo data) {
		List<String> screen = data.getScreen();
		
		for(int i=0;i<5;i++)
		{
			if(i<screen.size())
			{
				ivs[i].setVisibility(View.VISIBLE);
				utils.display(ivs[i], Consts.URL+"image?name="+screen.get(i));
			}else
			{
				ivs[i].setVisibility(View.GONE);
			}
		}
		
	}

}
