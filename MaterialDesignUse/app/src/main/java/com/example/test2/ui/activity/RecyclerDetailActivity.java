package com.example.test2.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.ImageView;

import com.example.test2.R;
import com.example.test2.adapter.MyPagerAdapter;
import com.example.test2.entity.RecyclerBean;
import com.example.test2.ui.fragment.DetailFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiaolin on 2017/2/14.
 */

public class RecyclerDetailActivity extends AppCompatActivity {
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @Bind(R.id.ivImage)
    ImageView mIvImage;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.sliding_tab)
    TabLayout mSlidingTab;

    private RecyclerBean recyclerBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_recycle_detail);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        assert mToolbar !=null;

        recyclerBean= (RecyclerBean) getIntent().getSerializableExtra("main");

        mCollapsingToolbar.setTitle(recyclerBean.getTitle());
        mIvImage.setBackgroundResource(recyclerBean.getImglarge());
        //设置viewpager的适配器
        setupViewPager(mViewpager);

        mSlidingTab.addTab(mSlidingTab.newTab().setText("测试1"));
        mSlidingTab.addTab(mSlidingTab.newTab().setText("测试2"));
        mSlidingTab.addTab(mSlidingTab.newTab().setText("测试3"));

        mSlidingTab.setupWithViewPager(mViewpager);
    }

    /**
     * s设置viewpager
     * @param mviewpager
     */
    private void setupViewPager(ViewPager mviewpager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(DetailFragment.newInstance(recyclerBean.getTitle()),"测试1");
        adapter.addFragment(DetailFragment.newInstance(recyclerBean.getTitle()),"测试2");
        adapter.addFragment(DetailFragment.newInstance(recyclerBean.getTitle()),"测试3");
        mviewpager.setAdapter(adapter);
    }
}
