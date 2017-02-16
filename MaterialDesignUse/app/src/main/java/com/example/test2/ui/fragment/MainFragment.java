package com.example.test2.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentContainer;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.test2.R;
import com.example.test2.adapter.RecycleAdapter;
import com.example.test2.adapter.RecycleVpAdapter;
import com.example.test2.entity.RecyclerBean;
import com.example.test2.listener.AdListener;
import com.example.test2.listener.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * Created by xiaolin on 2017/2/14.
 */

public class MainFragment extends Fragment {

    @Bind(R.id.recycleview)
    RecyclerView mRecycleview;
    @Bind(R.id.vp_books)
    AutoScrollViewPager viewPager;
    @Bind(R.id.ll_point)
    LinearLayout mLlPoint;
    @Bind(R.id.header)
    RecyclerViewHeader mHeader;
    @Bind(R.id.recycleview_grid)
    RecyclerView mRecycleviewGrid;

    private List<RecyclerBean> mBeanList = new ArrayList<>();
    private String[] title = {"测试文字_01", "测试文字_02", "测试文字_03", "测试文字_04", "测试文字_05", "测试文字_06", "测试文字_07", "测试文字_08", "测试文字_09", "测试文字_10"};

    //recycleview的图片id
    private int[] imgPath = {R.mipmap.ic_recyclerview_01, R.mipmap.ic_recyclerview_02, R.mipmap.ic_recyclerview_03, R.mipmap.ic_recyclerview_04, R.mipmap.ic_recyclerview_05,
            R.mipmap.ic_recyclerview_06, R.mipmap.ic_recyclerview_07, R.mipmap.ic_recyclerview_08, R.mipmap.ic_recyclerview_09, R.mipmap.ic_recyclerview_10};

    //头部的图片id
    private int[] vpImgPath = {R.mipmap.ic_viewpager_01, R.mipmap.ic_viewpager_02, R.mipmap.ic_viewpager_03};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        //下半部分的recycleView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecycleview.setLayoutManager(gridLayoutManager);

        //header的fragment轮播
        List<Fragment> fragmentList=new ArrayList<>();
        for (int i=0;i<vpImgPath.length;i++){
            RecyclerVpFragment recyclerVpFragment = RecyclerVpFragment.newInstance(vpImgPath[i]);
            fragmentList.add(recyclerVpFragment);
        }

        RecycleVpAdapter bAdapter=new RecycleVpAdapter(getChildFragmentManager(),fragmentList);
        viewPager.setAdapter(bAdapter);
        viewPager.addOnPageChangeListener(new AdListener(AdListener.setImageView(getActivity(),mLlPoint,fragmentList)));
        viewPager.setCurrentItem(0);

        //开启Viewpager的自动轮播
        viewPager.startAutoScroll();
        viewPager.setInterval(5000);//5s到下一张
        mHeader.attachTo(mRecycleview,true);

        //设置recycleview的数据
        setData();
        RecycleAdapter adapter=new RecycleAdapter(getActivity(),mBeanList);
        mRecycleview.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),adapter.onItemClickListener));
        mRecycleview.setAdapter(adapter);
        return view;
    }

    private void setData() {
        mBeanList.clear();
        for (int i = 0; i < title.length; i++) {
            RecyclerBean recyclerBean = new RecyclerBean();
            recyclerBean.setImg(imgPath[i]);
            recyclerBean.setInfo(title[i]);
            recyclerBean.setTitle(title[i]);
            recyclerBean.setCatalog(title[i]);
            recyclerBean.setAuthor_intro(title[i]);
            recyclerBean.setSummary(title[i]);
            recyclerBean.setImglarge(imgPath[i]);
            mBeanList.add(recyclerBean);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
