package com.example.googleplay.activity;

import com.example.googleplay.R;
import com.example.googleplay.R.drawable;
import com.example.googleplay.R.id;
import com.example.googleplay.R.layout;
import com.example.googleplay.R.menu;
import com.example.googleplay.fragment.AppFragment;
import com.example.googleplay.fragment.BaseFragment;
import com.example.googleplay.fragment.CategoryFragment;
import com.example.googleplay.fragment.FragmentFactory;
import com.example.googleplay.fragment.GameFragment;
import com.example.googleplay.fragment.HomeFragment;
import com.example.googleplay.fragment.SubjectFragment;
import com.example.googleplay.fragment.TopFragment;
import com.example.googleplay.holder.MenuHolder;
import com.example.googleplay.tools.Utils;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    private DrawerLayout layout;
    public ActionBarDrawerToggle toggle;
	private DrawerLayout drawLayout;
    private ViewPager mViewPager;
	private PagerTabStrip pager_tab_strip;
	
	private String[] tab_names;
	
	private FrameLayout fl_menu;//左边菜单的帧布局
    
    protected void init() {
    	//得到标签数组
    	tab_names=Utils.getStringArray(R.array.tab_names);	
    }
    
    
    //初始化界面
    protected void initView() 
    {
    	
    	 setContentView(R.layout.activity_main);
         
         drawLayout = (DrawerLayout) findViewById(R.id.mDrawLayout);
        
         //得到viewpager
         mViewPager=(ViewPager) findViewById(R.id.vp);
         mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
         pager_tab_strip=(PagerTabStrip) findViewById(R.id.pager_tab_strip);
       
         //得到左边的菜单布局
         fl_menu=(FrameLayout) findViewById(R.id.fl_menu);
        
         MenuHolder holder=new  MenuHolder();
         fl_menu.addView(holder.getContentView());

         //设置标签下划线的颜色
         pager_tab_strip.setTabIndicatorColor(getResources().getColor(R.color.indicatorcolor));
    	
    	 mViewPager.setOnPageChangeListener(new SimpleOnPageChangeListener()
    	 {

			@Override
			public void onPageSelected(int position) {
				
				super.onPageSelected(position);
				
				BaseFragment fragment = FragmentFactory.createFragment(position);
			    fragment.show();
			
			}
    		
    	 });
    	
    	
    }
    
    //初始化actionbar
    protected void initActionBar() 
    {
    	 ActionBar actionBar = getSupportActionBar();
         actionBar.setHomeButtonEnabled(true);
         actionBar.setDisplayHomeAsUpEnabled(true);
         
         toggle=new ActionBarDrawerToggle(this, drawLayout, R.drawable.ic_drawer_am, 0, 0);
         
//         actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
         
//         Tab tab1=actionBar.newTab().setText("标签一").setTabListener(new MyTabListener());
//         Tab tab2=actionBar.newTab().setText("标签二").setTabListener(new MyTabListener());
//         
//         Tab tab3=actionBar.newTab().setText("标签三").setTabListener(new MyTabListener());
//         Tab tab4=actionBar.newTab().setText("标签四").setTabListener(new MyTabListener());
//         
//         actionBar.addTab(tab1);
//         actionBar.addTab(tab2);
//         actionBar.addTab(tab3);
//         actionBar.addTab(tab4);
         
         drawLayout.setDrawerListener(toggle);
 		//  让开关和actionbar建立关系 
 		toggle.syncState();
    	
    }
    
    //主要的viewpager适配器
    private class MainAdapter extends FragmentStatePagerAdapter
    {

		public MainAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		//得到每个fragment
		@Override
		public Fragment getItem(int position) 
		{
			return FragmentFactory.createFragment(position);
		}
			

		@Override
		public int getCount() 
		{
			// TODO Auto-generated method stub
			return tab_names.length;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return tab_names[position];
		}
    	
    }
   

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }
    
    /*
     * 处理菜单栏按钮的点击
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    public boolean onOptionsItemSelected(MenuItem item) 
    {
       
    	if(item.getItemId()==R.id.action_search)
        {
        	Toast.makeText(this, "搜索", 0).show();
        }
    	return toggle.onOptionsItemSelected(item)|super.onOptionsItemSelected(item);
    }

    
}
