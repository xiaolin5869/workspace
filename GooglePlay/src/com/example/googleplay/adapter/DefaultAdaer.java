package com.example.googleplay.adapter;

import java.util.List;

import com.example.googleplay.bean.AppInfo;
import com.example.googleplay.holder.BaseHolder;
import com.example.googleplay.holder.MoreHolder;
import com.example.googleplay.tools.ThreadManager;
import com.example.googleplay.tools.Utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public abstract class DefaultAdaer<Data> extends BaseAdapter implements OnItemClickListener
{

	public List<Data> data;
	private static final int DEFAULT_ITEM = 0;
	private static final int MORE_ITEM = 1;
	public ListView listview;
	
	public DefaultAdaer(List<Data> data,ListView listview) 
	{
		super();
		this.data=data;
		this.listview=listview;
		listview.setOnItemClickListener(this);
	}
	
	

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
	
		//修正后的position
		position=position-listview.getHeaderViewsCount();
		onInnerItemClick(position);
//		Toast.makeText(Utils.getContext(), "position"+position, 0).show();
	}

	public  void onInnerItemClick(int position) {
		// TODO Auto-generated method stub
		
	}



	public int getCount() {
		// TODO Auto-generated method stub
		return data.size()+1;//最后一个条目加载更多界面
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
  
	
	@Override
	public int getItemViewType(int position) {
		if(data.size()==position)
		{
			return MORE_ITEM;//是最后一个条目时返回加载更多的布局
		}
		return getInterItemViewType(position);
	}

	private int getInterItemViewType(int position) 
	{
		return DEFAULT_ITEM;
	}

	/**
	 * 返回类型的数目
	 */
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return super.getViewTypeCount()+1;//有两种类型
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		BaseHolder<Data> holder=null;			
		switch(getItemViewType(position))
		{
			case DEFAULT_ITEM:
				
					if(convertView==null)
					{
						holder=getHolder();
					}else
					{
						holder=(BaseHolder<Data>) convertView.getTag();
					}
					
					if(position<data.size())
					{
						Data appInfo = data.get(position);
						holder.setData(appInfo);
					}
	
				break;
			case MORE_ITEM:
				if(convertView==null)
				{
					holder=getMoreHolder();
				}else
				{
					holder=(BaseHolder<Data>) convertView.getTag();
				}
				break;
		}
		
		return holder.getContentView();
	}

	public  BaseHolder mholder;
	private BaseHolder getMoreHolder() 
	{
		if(mholder==null)
		{
			mholder=new MoreHolder(this);
			return mholder;
		}else
		{
			return mholder;
		}
	
	}

	public abstract BaseHolder<Data> getHolder();

	/**
	 * 加载数据
	 */
	public void loadMore() 
	{
		ThreadManager.getInstance().createLongPool().execute(new Runnable() {
			
			@Override
			public void run() 
			{
				//请求得到的数据
				final List<Data> newData = onload();
				
				//主线程中刷新界面
				Utils.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						
						if(data==null)
						{
							//请求数据失败
							mholder.setData(MoreHolder.LOAD_ERROR);
							
						}else if(data.size()==0)
						{
							//数据为空,表明没哟数据啦
							mholder.setData(MoreHolder.HAS_NO_MORE);
						}else
						{
							//请求数据成功
							mholder.setData(MoreHolder.HAS_MORE);
							
							//把数据加入进来
							data.addAll(newData);	
							
						}
						
					   notifyDataSetChanged();
						
					}
				});	
			}

			
		});
		
	}
	
	/**
	 *从服务器加载多
	 */
      public abstract List<Data> onload() ;


}
