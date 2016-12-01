package com.example.googleplay.fragment;

import java.util.HashMap;
import java.util.Map;

import android.support.v4.app.Fragment;

public class FragmentFactory 
{
    
	public static Map<Integer, BaseFragment> mFragments=new HashMap<Integer, BaseFragment>();
	
	
	//创建fragment
   public static BaseFragment createFragment(int position)
   {
	  
	   BaseFragment fragment=null;
	    //先查看是否有缓存
	   fragment= mFragments.get(position);
	   
	   if(fragment==null)
	   {
		    if(position==0)
			{
		    	fragment=new HomeFragment();
			}else if(position==1)
			{
				fragment= new AppFragment();
			}else if(position==2)
			{
				fragment= new GameFragment();
			}else if(position==3)
			{
				fragment= new SubjectFragment();
			}else if(position==4)
			{
				fragment= new CategoryFragment();
			}else if(position==5)
			{
				fragment=new TopFragment();
			}
		    
		    //保存下来，作缓存
		    if(fragment!=null)
		    {
		    	mFragments.put(position, fragment);
		    }
	   }
	    
	    return fragment;
	    
   }
}
