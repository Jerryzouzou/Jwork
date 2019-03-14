package com.offer.pass.preg.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.offer.pass.preg.R;

import java.util.ArrayList;

public class ListViewViewGoneItemDemo extends Activity {

    private ListView mList;
    private Context mContext;
    private MyBaseAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mContext = this;
        findView();
    }

    private void findView() {
        mList = findViewById(R.id.lv_001);
        adapter = new MyBaseAdapter();
        mList.setAdapter(adapter);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(mContext, "click the position is ==="+(i+1), Toast.LENGTH_SHORT).show();
            }
        });
    }


    class MyBaseAdapter extends BaseAdapter{

        private ArrayList<String> data = new ArrayList<String>(){{add("item001");add("item002");add("item003");add("item004");add("item005");}};

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view == null){
                TextView tvShow = new TextView(mContext);
                tvShow.setText(data.get(i));
                tvShow.setTextSize(35);

                if(i == 3){
                    tvShow.setVisibility(View.GONE);
                    AbsListView.LayoutParams params = new AbsListView.LayoutParams(0, 1);
                    tvShow.setLayoutParams(params);
                }

                view = tvShow;
            }

            return view;
        }
    }
}


