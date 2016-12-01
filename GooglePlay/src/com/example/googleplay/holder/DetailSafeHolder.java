package com.example.googleplay.holder;

import java.util.List;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.googleplay.R;
import com.example.googleplay.bean.AppInfo;
import com.example.googleplay.tools.Consts;
import com.example.googleplay.tools.Utils;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;

@SuppressLint("NewApi") public class DetailSafeHolder extends BaseHolder<AppInfo> implements OnClickListener
{

	private ImageView[] ivs,iv_des;
	private TextView[] tv_des;
	private LinearLayout[] des_layout;
	private LinearLayout safe_content;
	private RelativeLayout safe_layout;
	private ImageView safe_arrow;
	
	public View initView() {
		View view=View.inflate(Utils.getContext(), R.layout.detail_safe, null);
		
		ivs=new ImageView[4];//初始化标题栏图片
		ivs[0]=(ImageView) view.findViewById(R.id.iv_1);
		ivs[1]=(ImageView) view.findViewById(R.id.iv_2);
		ivs[2]=(ImageView) view.findViewById(R.id.iv_3);
		ivs[3]=(ImageView) view.findViewById(R.id.iv_4);
		
		iv_des=new ImageView[4];//初始化每个描述条目的图片
		iv_des[0]=(ImageView) view.findViewById(R.id.des_iv_1);
		iv_des[1]=(ImageView) view.findViewById(R.id.des_iv_2);
		iv_des[2]=(ImageView) view.findViewById(R.id.des_iv_3);
		iv_des[3]=(ImageView) view.findViewById(R.id.des_iv_4);
		
		tv_des=new TextView[4];////初始化每个描述条目的文字
		tv_des[0]=(TextView) view.findViewById(R.id.des_tv_1);
		tv_des[1]=(TextView) view.findViewById(R.id.des_tv_2);
		tv_des[2]=(TextView) view.findViewById(R.id.des_tv_3);
		tv_des[3]=(TextView) view.findViewById(R.id.des_tv_4);
		
		des_layout=new LinearLayout[4];//初始化条目线性布局
		des_layout[0]=(LinearLayout) view.findViewById(R.id.des_layout_1);
		des_layout[1]=(LinearLayout) view.findViewById(R.id.des_layout_2);
		des_layout[2]=(LinearLayout) view.findViewById(R.id.des_layout_3);
		des_layout[3]=(LinearLayout) view.findViewById(R.id.des_layout_4);
		
		safe_content=(LinearLayout) view.findViewById(R.id.safe_content);
		safe_layout= (RelativeLayout) view.findViewById(R.id.safe_layout);
		
		safe_arrow=(ImageView) view.findViewById(R.id.safe_arrow);
	
		return view;
	}

	@Override
	public void refreshView(AppInfo data) {
		
		safe_layout.setOnClickListener(this);
		
		List<String> safeUrl = data.getSafeUrl();
		List<String> safeDesUrl = data.getSafeDesUrl();
		List<String> safeDes = data.getSafeDes();
		List<Integer> safeDesColor = data.getSafeDesColor(); // 0 1 2 3
		for (int i = 0; i < 4; i++) {
			if (i < safeUrl.size() && i < safeDesUrl.size()
					&& i < safeDes.size() && i < safeDesColor.size()) {
				ivs[i].setVisibility(View.VISIBLE);
				des_layout[i].setVisibility(View.VISIBLE);
				utils.display(ivs[i], Consts.URL + "image?name="
						+ safeUrl.get(i));
				utils.display(iv_des[i], Consts.URL + "image?name="
						+ safeDesUrl.get(i));
				tv_des[i].setText(safeDes.get(i));
				// 根据服务器数据显示不同的颜色
				int color;
				int colorType = safeDesColor.get(i);
				if (colorType >= 1 && colorType <= 3) {
					color = Color.rgb(255, 153, 0); // 00 00 00
				} else if (colorType == 4) {
					color = Color.rgb(0, 177, 62);
				} else {
					color = Color.rgb(122, 122, 122);
				}
				tv_des[i].setTextColor(color);

			} else {
				ivs[i].setVisibility(View.GONE);
				des_layout[i].setVisibility(View.GONE);
			}

		}
		
	}

	private boolean flag=true;
	@SuppressLint("NewApi") public void onClick(View v) {
		switch (v.getId()) {
		case R.id.safe_layout:
			int startHeight=0;
			int targetHeight=0;
			if(!flag)
			{
				flag=true;
//				safe_content.setVisibility(View.VISIBLE);
				startHeight=0;
				targetHeight=getMenuHeight();
			}else
			{
				flag=false;
//				safe_content.setVisibility(View.GONE);
				startHeight=getMenuHeight();
				targetHeight=0;
			}
			//值动画
			ValueAnimator animator=ValueAnimator.ofInt(startHeight,targetHeight);
			
			final RelativeLayout.LayoutParams layoutParams = (android.widget.RelativeLayout.LayoutParams) safe_content.getLayoutParams();
			
			
			animator.addUpdateListener(new AnimatorUpdateListener() 
			{
				
				
				public void onAnimationUpdate(ValueAnimator animator) 
				{
					
					int value=(Integer) animator.getAnimatedValue();
					layoutParams.height=value;
					safe_content.setLayoutParams(layoutParams);
					
				}
			});
			
			animator.addListener(new AnimatorListener() 
			{
				
				/**
				 * 动画开始的时候
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
						safe_arrow.setImageResource(R.drawable.arrow_down);
					}else
					{
						safe_arrow.setImageResource(R.drawable.arrow_up);
					}
					
				}
				
				@Override
				public void onAnimationCancel(Animator arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
			animator.setDuration(1000);
			animator.start();
			break;

		}
		
	}
	/**
	 * 得到控件的高度
	 * @return
	 */
	public int getMenuHeight()
	{
		int width=safe_content.getMeasuredWidth();//得到控件显示的宽度（有缩放时会变化，不一定是实际的宽度）
		
		int widthMeasureSpec=MeasureSpec.makeMeasureSpec(MeasureSpec.EXACTLY,
				width);//设置测量规则，包括测量的大小和模式
		int heightMeasureSpec=MeasureSpec.makeMeasureSpec(MeasureSpec.AT_MOST,
				1000);//最大值模式，取实际高度和1000的小值返回
		safe_content.measure(widthMeasureSpec, heightMeasureSpec);
		
		//测量之后返回配置文件中设置的值
		return safe_content.getMeasuredHeight();
	}

}
