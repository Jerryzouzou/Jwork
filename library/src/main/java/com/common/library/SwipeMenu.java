package com.common.library;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/25.
 * description:
 */

public class SwipeMenu {

    private Context mContext;
    private List<SwipeMenuItem> mItems;
    private int mViewType;

    public SwipeMenu(Context mContext) {
        this.mContext = mContext;
        mItems = new ArrayList<SwipeMenuItem>();
    }

    public Context getContext() {
        return mContext;
    }

    public List<SwipeMenuItem> getMenuItems() {
        return mItems;
    }

    public SwipeMenuItem getMenuItem(int index){
        return mItems.get(index);
    }

    public void addMenuItem(SwipeMenuItem item){
        mItems.add(item);
    }

    public void removeMenuItem(SwipeMenuItem item){
        mItems.remove(item);
    }

    public int getViewType() {
        return mViewType;
    }

    public void setViewType(int mViewType) {
        this.mViewType = mViewType;
    }
}
