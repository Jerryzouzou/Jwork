package com.offer.pass.preg.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.offer.pass.preg.R;
import com.offer.pass.preg.customview.VerticalViewPager;

import java.util.ArrayList;

public class VerticalViewPagerDemo extends Activity implements View.OnClickListener{

    private VerticalViewPager vPager;
    private ArrayList<View> aList;
    private MyPagerAdapter mAdapter;
    private View view_one, view_two;

    private int currentPosition = 1;
    private boolean isNeedChanged = false;

    private int img_id[] = {R.id.img_back, R.id.img_home, R.id.img_back, R.id.img_mute, R.id.img_vol_add, R.id.img_vol_sub};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vertical_viewpager);

        vPager = findViewById(R.id.ver_viewpager);

        aList = new ArrayList<>();

        /*TextView tv1 = new TextView(this);
        tv1.setText("Text1");
        tv1.setTextSize(50);

        TextView tv2 = new TextView(this);
        tv1.setText("Text2");
        tv1.setTextSize(50);

        TextView tv3 = new TextView(this);
        tv1.setText("Text3");
        tv1.setTextSize(50);*/

        LayoutInflater li = getLayoutInflater();
        view_one = li.inflate(R.layout.view_one,null,false);
        view_two = li.inflate(R.layout.view_two,null,false);
        aList.add(view_one);
        aList.add(view_two);
        mAdapter = new MyPagerAdapter(aList);

        vPager.setAdapter(mAdapter);
        vPager.setCurrentItem(1 * aList.size());
        vPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position % aList.size();
                vPager.setCurrentItem(position);
                ViewGroup parent = mAdapter.getViewGroup(currentPosition);
                mAdapter.instantiateItem(parent, position+1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                /*if(state==ViewPager.SCROLL_STATE_IDLE ){
                    vPager.setCurrentItem(currentPosition, false);
                }

                if(state == ViewPager.SCROLL_STATE_DRAGGING){
                    ViewGroup parent = mAdapter.getViewGroup(currentPosition);
                    mAdapter.instantiateItem(parent, currentPosition+1);
                }*/
            }
        });
        /*vPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                if(position == aList.size()+1){
                    currentPosition = 1;
                    isNeedChanged = true;
                }else if(position == 0){
                    isNeedChanged = true;
                    currentPosition = aList.size();
                }else {
                    isNeedChanged = false;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state==ViewPager.SCROLL_STATE_IDLE && isNeedChanged){
                    vPager.setCurrentItem(currentPosition, false);
                }
            }
        });*/

        /*vPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == aList.size()+1){
                    currentPosition = 1;
                    isNeedChanged = true;
                }else if(position == 0){
                    isNeedChanged = true;
                    currentPosition = aList.size();
                }else {
                    currentPosition = position;
                    isNeedChanged = false;
                }
                //currentPosition = position%2;
                //mAdapter.destroyItem(currentPosition);
                vPager.setCurrentItem(currentPosition);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state==ViewPager.SCROLL_STATE_IDLE && isNeedChanged){
                    vPager.setCurrentItem(currentPosition, false);
                }
            }
        });
        vPager.setCurrentItem(1);

        //vPager.setOnClickListener(this);

       /* for (int i = 0; i < img_id.length; i++) {
            findViewById(img_id[i]).setOnClickListener(this);
        }*/
        view_one.findViewById(R.id.img_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                Toast.makeText(this, "this is back", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

class MyPagerAdapter extends PagerAdapter{

    private ArrayList<View> viewLists;

    public MyPagerAdapter(ArrayList<View> viewLists) {
        super();
        this.viewLists = viewLists;
    }

    public ViewGroup getViewGroup(int position){
        int pos = position % viewLists.size();
        View view = viewLists.get(pos);
        ViewGroup parent = (ViewGroup) view.getParent();
        return parent;
    }

    @Override
    public int getCount() {
        //return viewLists.size()+2;
        return 10000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //position %= viewLists.size();
        /*container.addView(viewLists.get(position));
        return viewLists.get(position);*/
       /* if(position == viewLists.size()+1){
            position = 0;
        }else if(position == 0){
            position = viewLists.size()-1;
        }else{
            position -= 1;
        }

        if(container.indexOfChild(viewLists.get(position)) != -1){
            container.removeView(viewLists.get(position));
        }
        container.addView(viewLists.get(position));
        return viewLists.get(position);*/
       /*try {
           container.addView(viewLists.get(position % viewLists.size()));
       }catch (Exception e){

       }
       return viewLists.get(position % viewLists.size());*/
       int pos = position % viewLists.size();
       View view = viewLists.get(pos);
       ViewGroup parent = (ViewGroup) view.getParent();
       if(parent != null){
           parent.removeView(view);
       }
       container.addView(view);
       return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        /*if(position == viewLists.size()+1){
            position = 0;
        }else if(position == 0){
            position = viewLists.size()-1;
        }else{
            position -= 1;
        }
        container.removeView(viewLists.get(position));*/
        //container.removeView(viewLists.get(position % viewLists.size()));
    }
}