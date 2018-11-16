package com.common.library;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/2/25.
 * description:
 */

public class SwipeMenuView extends LinearLayout implements View.OnClickListener {

    private SwipeMenuListView mListView;
    private SwipeMenuLayout mLayout;
    private SwipeMenu mMenu;
    private OnSwipeItemClickListener onItemClickListener;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public SwipeMenuView(SwipeMenu menu, SwipeMenuListView listView){
        super(menu.getContext());
        mListView = listView;
        mMenu = menu;
        List<SwipeMenuItem> items = menu.getMenuItems();
        int id = 0;
        for(SwipeMenuItem item : items){
            addItem(item, id++);
        }
    }

    private void addItem(SwipeMenuItem item, int id) {
        LayoutParams params = new LayoutParams(item.getWidth(), LayoutParams.MATCH_PARENT);
        LinearLayout parrent = new LinearLayout(getContext());
        parrent.setId(id);
        parrent.setGravity(Gravity.CENTER);
        parrent.setOrientation(LinearLayout.VERTICAL);
        parrent.setLayoutParams(params);
        parrent.setBackground(item.getBackground());
        parrent.setOnClickListener(this);
        addView(parrent);

        if(item.getIcon() != null){
            parrent.addView(createIcon(item));
        }
        if(!TextUtils.isEmpty(item.getTitle())){
            parrent.addView(createTitle(item));
        }
    }

    private TextView createTitle(SwipeMenuItem item) {
        TextView tv = new TextView(getContext());
        tv.setText(item.getTitle());
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(item.getTitleSize());
        tv.setTextColor(item.getTitleColor());
        return tv;
    }

    private ImageView createIcon(SwipeMenuItem item) {
        ImageView iv = new ImageView(getContext());
        iv.setImageDrawable(item.getIcon());
        return iv;
    }

    @Override
    public void onClick(View v) {
        if(onItemClickListener != null && mLayout.isOpen()){
            onItemClickListener.onItemClick(this, mMenu, v.getId());
        }
    }

    public OnSwipeItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnSwipeItemClickListener(OnSwipeItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setLayout(SwipeMenuLayout mLayout) {
        this.mLayout = mLayout;
    }

    public static interface OnSwipeItemClickListener{
        void onItemClick(SwipeMenuView view, SwipeMenu menu, int index);
    }
}
