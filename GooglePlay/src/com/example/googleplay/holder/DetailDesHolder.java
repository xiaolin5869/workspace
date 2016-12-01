package com.example.googleplay.holder;

import com.example.googleplay.R;
import com.example.googleplay.bean.AppInfo;
import com.example.googleplay.tools.Utils;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailDesHolder extends BaseHolder<AppInfo> implements android.view.View.OnClickListener 
{

	private TextView des_title,des_content,des_author;
	private RelativeLayout des_layout;
	private ImageView des_arrow;
	public View initView() {
		View view=View.inflate(Utils.getContext(),R.layout.detail_des, null);
		
		des_title=(TextView) view.findViewById(R.id.des_titile);
		des_content=(TextView) view.findViewById(R.id.des_content);
		des_author=(TextView) view.findViewById(R.id.des_author);
		
		des_arrow=(ImageView) view.findViewById(R.id.des_arrow);
		
		des_layout=(RelativeLayout) view.findViewById(R.id.des_layout);
		return view;
	}

	@Override
	public void refreshView(AppInfo data) {
		des_content.setText(data.getDes());
		des_author.setText("����:"+data.getAuthor());
		
		des_layout.setOnClickListener(this);
		
		//��ʼ��ʾ7�еĴ�С
		LayoutParams params = des_content.getLayoutParams();
		params.height=getShortMenuHeight();
		des_content.setLayoutParams(params);//���ý���Ĵ�С		
	}
	
	//�õ�ScrollView
	public ScrollView getScrollView(View view){
		ViewParent parent = view.getParent();
		if(parent instanceof ViewGroup){
			ViewGroup group=(ViewGroup) parent;
			if(group instanceof ScrollView){
				return (ScrollView)group;
			}else{
				return getScrollView(group);
			}
		}else{
			return null;
		}
		
	}
	
	public int getLongMenuHeight()
	{
		int width=des_content.getMeasuredWidth();//�õ��ؼ���ʾ�Ŀ�ȣ�������ʱ��仯����һ����ʵ�ʵĿ�ȣ�
		
		int widthMeasureSpec=MeasureSpec.makeMeasureSpec(MeasureSpec.EXACTLY,
				width);//���ò������򣬰��������Ĵ�С��ģʽ
		int heightMeasureSpec=MeasureSpec.makeMeasureSpec(MeasureSpec.AT_MOST,
				1000);//���ֵģʽ��ȡʵ�ʸ߶Ⱥ�1000��Сֵ����
		des_content.measure(widthMeasureSpec, heightMeasureSpec);
		
		//����֮�󷵻������ļ������õ�ֵ
		return des_content.getMeasuredHeight();
	}
	
	public int getShortMenuHeight()
	{
		TextView tv=new TextView(Utils.getContext());
		tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
		tv.setMaxLines(7);
		tv.setLines(7);//ǿ�����ó�7��
		
		int width=des_content.getMeasuredWidth();//�õ��ؼ���ʾ�Ŀ�ȣ�������ʱ��仯����һ����ʵ�ʵĿ�ȣ�
		
		int widthMeasureSpec=MeasureSpec.makeMeasureSpec(MeasureSpec.EXACTLY,
				width);//���ò������򣬰��������Ĵ�С��ģʽ
		int heightMeasureSpec=MeasureSpec.makeMeasureSpec(MeasureSpec.AT_MOST,1000);
		
		
		tv.measure(widthMeasureSpec, heightMeasureSpec);
		
		return tv.getMeasuredHeight();
	}

	@Override
	public void onClick(View v) {
		//�����������½�
		expand();
		
	}

	private boolean flag=false;
	private ScrollView scrollview;
	//�����������½�
	private void expand()
	{
	    scrollview=getScrollView(des_layout);
		int startHeight;
		int targetHeight;
		if(!flag)
		{
			flag=true;
			startHeight=getShortMenuHeight();
			targetHeight=getLongMenuHeight();
		}else
		{
			flag=false;
			startHeight=getLongMenuHeight();
			targetHeight=getShortMenuHeight();
		}
		
		final LayoutParams params = des_content.getLayoutParams();
		
		ValueAnimator animator=ValueAnimator.ofInt(startHeight,targetHeight);
		
		animator.addUpdateListener(new AnimatorUpdateListener()  {
			public void onAnimationUpdate(ValueAnimator animator) {
				params.height=(Integer) animator.getAnimatedValue();
				des_content.setLayoutParams(params);
				scrollview.scrollTo(0, scrollview.getMeasuredHeight());
			}
		});
		
		animator.addListener(new AnimatorListener() 
		{
			
			/**
			 * ������ʼ��ʱ��
			 */
			public void onAnimationStart(Animator arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animator arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animator arg0) 
			{
				if(flag)
				{
					des_arrow.setImageResource(R.drawable.arrow_down);
				}else
				{
					des_arrow.setImageResource(R.drawable.arrow_up);
				}
				
			}
			
			@Override
			public void onAnimationCancel(Animator arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		animator.setDuration(1000);
		animator.start();
}


}
