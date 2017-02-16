package com.example.test2.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.test2.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiaolin on 2017/2/14.
 */

public class RecyclerVpFragment extends Fragment {

    @Bind(R.id.iv_vp)
    ImageView mIvVp;

    public static RecyclerVpFragment newInstance(int param) {
        RecyclerVpFragment fragment = new RecyclerVpFragment();
        Bundle args = new Bundle();
        args.putInt("imgpath", param);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_vp, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle=getArguments();
        if(bundle!=null){
            mIvVp.setBackgroundResource(bundle.getInt("imgpath"));
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
