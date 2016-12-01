package com.example.googleplay.holder;

import com.example.googleplay.R;
import com.example.googleplay.adapter.DefaultAdaer;
import com.example.googleplay.tools.Utils;

import android.view.View;
import android.widget.RelativeLayout;

public class MoreHolder extends BaseHolder<Integer>
{

	private DefaultAdaer adapter;
	public static final int HAS_NO_MORE=0;  // û�ж���������
	public static final int LOAD_ERROR=1;// ����ʧ��
	public static final int HAS_MORE=2;//  �ж�������
	
	private RelativeLayout rl_more_error,rl_more_loading;
	
	public View initView() 
	{
		View view=View.inflate(Utils.getContext(), R.layout.load_more, null);
		rl_more_error=(RelativeLayout) view.findViewById(R.id.rl_more_error);
		rl_more_loading=(RelativeLayout) view.findViewById(R.id.rl_more_loading);
		return view;
	}
	
	@Override
	public View getContentView() {
        //���ظ���
		loadMore();
		return super.getContentView();
	}
	
	public MoreHolder(DefaultAdaer adapter)
	{
		this.adapter=adapter;
		
	}
	
	private void loadMore() 
	{
	
		adapter.loadMore();	
	}

	
	public void refreshView(Integer data) {
		rl_more_error.setVisibility(data==LOAD_ERROR?View.VISIBLE:View.GONE);
		rl_more_loading.setVisibility(data==HAS_MORE?View.VISIBLE:View.GONE);
	}

}
