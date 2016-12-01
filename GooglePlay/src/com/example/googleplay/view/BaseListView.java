package com.example.googleplay.view;



import com.example.googleplay.R;
import com.example.googleplay.tools.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class BaseListView extends ListView {
	
	public BaseListView(Context context) {
		super(context);
		init();
	}

	public BaseListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public BaseListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
//		setSelector  �����ʾ����ɫ
//		setCacheColorHint  ��ק����ɫ
//		setDivider  ÿ����Ŀ�ļ��	�ķָ���	
		this.setSelector(R.drawable.nothing);  // ʲô��û��
		this.setCacheColorHint(R.drawable.nothing);
		this.setDivider(Utils.getDrawalbe(R.drawable.nothing));
	}

}
