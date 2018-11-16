package com.common.library;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 2018/2/25.
 * description:排行榜列表
 */

public class SwipeMenuListView extends ListView {

    private static final int TOUCH_STATE_NONE = 0;
    private static final int TOUCH_STATE_X = 1;
    private static final int TOUCH_STATE_Y = 2;
    private int MAX_Y = 5;
    private int MAX_X = 3;
    private float mDownX;
    private float mDownY;
    private int mTouchState;
    private int mTouchPosition;

    private SwipeMenuLayout mTouchView;
    private OnSwipeListener mOnSwipeListener;
    private SwipeMenuCreator mMenuCreator;
    private OnMenuItemClickListener mOnMenuItemClickListener;
    private Interpolator mCloseInterpolator;
    private Interpolator mOpenInterpolator;

    public SwipeMenuListView(Context context) {
        super(context);
        init();
    }



    public SwipeMenuListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwipeMenuListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        MAX_X = dp2px(MAX_X);
        MAX_Y = dp2px(MAX_Y);
        mTouchState = TOUCH_STATE_NONE;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getContext().getResources().getDisplayMetrics());
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(new SwipeMenuAdapter(getContext(), adapter){
            @Override
            public void createMenu(SwipeMenu menu) {
                if (mMenuCreator != null) {
                    mMenuCreator.create(menu);
                }
            }

            @Override
            public void onItemClick(SwipeMenuView view, SwipeMenu menu, int index) {
                boolean flag = false;
                if (mOnMenuItemClickListener != null) {
                    flag = mOnMenuItemClickListener.onMenuItemClick(
                            view.getPosition(), menu, index);
                }
                if (mTouchView != null && !flag) {
                    mTouchView.smoothCloseMenu();
                }
            }
        });

    }

    public Interpolator getCloseInterpolator() {
        return mCloseInterpolator;
    }

    public void setCloseInterpolator(Interpolator mCloseInterpolator) {
        this.mCloseInterpolator = mCloseInterpolator;
    }

    public Interpolator getOpenInterpolator() {
        return mOpenInterpolator;
    }

    public void setOpenInterpolator(Interpolator mOpenInterpolator) {
        this.mOpenInterpolator = mOpenInterpolator;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(ev.getAction() != MotionEvent.ACTION_DOWN && mTouchView == null){
            return super.onTouchEvent(ev);
        }
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                int oldPos = mTouchPosition;
                mDownX = ev.getX();
                mDownY = ev.getY();
                mTouchState = TOUCH_STATE_NONE;
                mTouchPosition = pointToPosition((int) ev.getX(), (int) ev.getY());
                if(mTouchPosition == oldPos && mTouchView != null
                        && mTouchView.isOpen()){
                    mTouchState = TOUCH_STATE_X;
                    mTouchView.onSwipe(ev);
                    return true;
                }

                View view = getChildAt(mTouchPosition - getFirstVisiblePosition());
                if(mTouchView != null && mTouchView.isOpen()){
                    mTouchView.smoothCloseMenu();
                    mTouchView = null;
                    MotionEvent cancelEvent = MotionEvent.obtain(ev);
                    cancelEvent.setAction(MotionEvent.ACTION_CANCEL);
                    onTouchEvent(cancelEvent);
                    return true;
                }
                if(view instanceof SwipeMenuLayout){
                    mTouchView = (SwipeMenuLayout) view;
                }
                if(mTouchView != null){
                    mTouchView.onSwipe(ev);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = Math.abs(ev.getY() - mDownY);
                float dx = Math.abs(ev.getX() - mDownX);
                if(mTouchState == TOUCH_STATE_X){
                    if(mTouchView != null){
                        mTouchView.onSwipe(ev);
                    }
                    getSelector().setState(new int[]{0});
                    ev.setAction(MotionEvent.ACTION_CANCEL);
                    super.onTouchEvent(ev);
                    return true;
                }else if(mTouchState == TOUCH_STATE_NONE){
                    if(Math.abs(dy) > MAX_Y){
                        mTouchState = TOUCH_STATE_Y;
                    }else if(dx > MAX_X){
                        mTouchState = TOUCH_STATE_X;
                        if(mOnSwipeListener != null){
                            mOnSwipeListener.onSwipeStart(mTouchPosition);
                        }
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                if(mTouchState == TOUCH_STATE_X){
                    if(mTouchView != null){
                        mTouchView.onSwipe(ev);
                        if(!mTouchView.isOpen()){
                            mTouchPosition = -1;
                            mTouchView = null;
                        }
                    }
                    if(mOnSwipeListener != null){
                        mOnSwipeListener.onSwipeEnd(mTouchPosition);
                    }
                    ev.setAction(MotionEvent.ACTION_CANCEL);
                    super.onTouchEvent(ev);
                    return true;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void smoothOpenMenu(int position) {
        if (position >= getFirstVisiblePosition()
                && position <= getLastVisiblePosition()) {
            View view = getChildAt(position - getFirstVisiblePosition());
            if (view instanceof SwipeMenuLayout) {
                mTouchPosition = position;
                if (mTouchView != null && mTouchView.isOpen()) {
                    mTouchView.smoothCloseMenu();
                }
                mTouchView = (SwipeMenuLayout) view;
                mTouchView.smoothOpenMenu();
            }
        }
    }

    public void setmOnSwipeListener(OnSwipeListener mOnSwipeListener) {
        this.mOnSwipeListener = mOnSwipeListener;
    }

    public void setmMenuCreator(SwipeMenuCreator mMenuCreator) {
        this.mMenuCreator = mMenuCreator;
    }

    public void setmOnMenuItemClickListener(OnMenuItemClickListener mOnMenuItemClickListener) {
        this.mOnMenuItemClickListener = mOnMenuItemClickListener;
    }

    public static interface OnMenuItemClickListener {
        boolean onMenuItemClick(int position, SwipeMenu menu, int index);
    }

    public static interface OnSwipeListener {
        void onSwipeStart(int position);
        void onSwipeEnd(int position);
    }
}
