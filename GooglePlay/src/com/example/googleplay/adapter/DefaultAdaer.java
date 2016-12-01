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
	
		//�������position
		position=position-listview.getHeaderViewsCount();
		onInnerItemClick(position);
//		Toast.makeText(Utils.getContext(), "position"+position, 0).show();
	}

	public  void onInnerItemClick(int position) {
		// TODO Auto-generated method stub
		
	}



	public int getCount() {
		// TODO Auto-generated method stub
		return data.size()+1;//���һ����Ŀ���ظ������
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
			return MORE_ITEM;//�����һ����Ŀʱ���ؼ��ظ���Ĳ���
		}
		return getInterItemViewType(position);
	}

	private int getInterItemViewType(int position) 
	{
		return DEFAULT_ITEM;
	}

	/**
	 * �������͵���Ŀ
	 */
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return super.getViewTypeCount()+1;//����������
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
	 * ��������
	 */
	public void loadMore() 
	{
		ThreadManager.getInstance().createLongPool().execute(new Runnable() {
			
			@Override
			public void run() 
			{
				//����õ�������
				final List<Data> newData = onload();
				
				//���߳���ˢ�½���
				Utils.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						
						if(data==null)
						{
							//��������ʧ��
							mholder.setData(MoreHolder.LOAD_ERROR);
							
						}else if(data.size()==0)
						{
							//����Ϊ��,����ûӴ������
							mholder.setData(MoreHolder.HAS_NO_MORE);
						}else
						{
							//�������ݳɹ�
							mholder.setData(MoreHolder.HAS_MORE);
							
							//�����ݼ������
							data.addAll(newData);	
							
						}
						
					   notifyDataSetChanged();
						
					}
				});	
			}

			
		});
		
	}
	
	/**
	 *�ӷ��������ض�
	 */
      public abstract List<Data> onload() ;


}
