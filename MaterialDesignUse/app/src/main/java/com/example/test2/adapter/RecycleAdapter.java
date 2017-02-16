package com.example.test2.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.test2.R;
import com.example.test2.Utils.Utils;
import com.example.test2.entity.RecyclerBean;
import com.example.test2.listener.RecyclerItemClickListener;
import com.example.test2.ui.activity.RecyclerDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaolin on 2017/2/14.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>{

    private List<RecyclerBean> list = new ArrayList<>();
    private static final int ANIMATED_ITEMS_COUNT = 4;
    private Activity context;
    private boolean animateItems = false;
    private int lastAnimatedPosition = -1;

    public RecycleAdapter(Activity context, List<RecyclerBean> mlist){
        TypedValue mTypedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        this.context=context;
        this.list=updateItems(mlist,true);
    }
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecycleAdapter.ViewHolder holder, int position) {
        runEnterAnimation(holder.itemView, position);
        RecyclerBean bean = list.get(position);
        holder.mImageView.setImageResource(bean.getImg());
    }

    private void runEnterAnimation(View view, int position) {
        if(!animateItems || position>=ANIMATED_ITEMS_COUNT-1){
            return;
        }

        if(position>lastAnimatedPosition){
            lastAnimatedPosition=position;
            view.setTranslationY(Utils.getScreenHeight(context));
            view.animate()
                    .translationY(0)
                    .setStartDelay(100*position)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(700)
                    .start();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  List<RecyclerBean> updateItems(List<RecyclerBean> books,boolean animated){
        List<RecyclerBean> list=new ArrayList<>();
        animateItems=animated;
        lastAnimatedPosition=-1;
        list.addAll(books);
        notifyDataSetChanged();
        return list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public int position;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView= (ImageView) itemView.findViewById(R.id.ivMain);
        }
    }

    public RecyclerItemClickListener.OnItemClickListener onItemClickListener=new RecyclerItemClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            RecyclerBean main = getMain(position);
            Intent intent = new Intent(context, RecyclerDetailActivity.class);
            intent.putExtra("main", main);

            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(context,
                            view.findViewById(R.id.ivMain),context.getString(R.string.transition_book_img));

            ActivityCompat.startActivity(context, intent, options.toBundle());
        }
    };

    private RecyclerBean getMain(int pos) {
        return list.get(pos);
    }
}
