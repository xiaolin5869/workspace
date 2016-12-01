package com.example.googleplay.holder;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.googleplay.R;
import com.example.googleplay.bean.UserInfo;
import com.example.googleplay.protocol.UserProtocol;
import com.example.googleplay.tools.Consts;
import com.example.googleplay.tools.ThreadManager;
import com.example.googleplay.tools.Utils;
import com.lidroid.xutils.db.sqlite.CursorUtils.FindCacheSequence;

public class MenuHolder extends BaseHolder<UserInfo> implements OnClickListener
{

	private RelativeLayout photo_layout;
	private ImageView image_photo;
	private TextView user_name;
	private TextView user_email;

	@Override
	public View initView() {
	    View view=View.inflate(Utils.getContext(),R.layout.menu_holder, null);
		
	    photo_layout = (RelativeLayout) view.findViewById(R.id.photo_layout);
	    image_photo = (ImageView) view.findViewById(R.id.image_photo);
	    user_name = (TextView) view.findViewById(R.id.user_name);
	    user_email = (TextView) view.findViewById(R.id.user_email);
	   
	    photo_layout.setOnClickListener(this);
	    
	    return view;
	}

	@Override
	public void refreshView(UserInfo data) {
		user_email.setText(data.email);
		user_name.setText(data.name);
		
		utils.display(image_photo, Consts.URL+"image?name="+data.url);
	}

	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.photo_layout:
			ThreadManager.getInstance().createLongPool().execute(new Runnable() {
				
				@Override
				public void run() 
				{
					UserProtocol protocol=new UserProtocol();
					
					final UserInfo info = protocol.load(0);
				
					
					Utils.runOnUiThread(new Runnable() 
					{
						
						@Override
						public void run()
						{
							setData(info);
							
						}
					});
					
				};
			});
			break;
		}
		
	}

}
