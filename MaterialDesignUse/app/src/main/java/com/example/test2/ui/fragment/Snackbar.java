package com.example.test2.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.test2.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiaolin on 2017/2/16.
 */

public class Snackbar extends Fragment {
    @Bind(R.id.coordinatorlayout)
    CoordinatorLayout mCoordinatorlayout;
    @Bind(R.id.flb_black)
    FloatingActionButton mFlbBlack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_snackbar, container, false);
        ButterKnife.bind(this, view);

        mFlbBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.design.widget.Snackbar.make(mCoordinatorlayout,"snackbar", android.support.design.widget.Snackbar.LENGTH_LONG)
                .setAction("ok",new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity()," snackbar OK clicked",Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
