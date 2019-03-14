package com.offer.pass.preg.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.offer.pass.preg.R;

import java.util.HashMap;

public class SharepreBianliDemo extends Activity implements View.OnClickListener{

    private int size;
    private Button btnOld, btnNew;
    private TextView tvShow;
    private SharedPreferences mSp = null;
    private Context context = null;
    private HashMap map = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloomfiter);
        findView();

        initData();
    }

    private void initData() {
        context = this;
        mSp = context.getSharedPreferences("key_study", Context.MODE_PRIVATE);
        map = new HashMap();
        map.put("key001", "mute");
        map.put("key002", "voladd");
        map.put("key003", "voldec");
        map.put("key004", "power");
        map.put("key005", "back");
        map.put("key006", "home");
        size = 6;
    }

    private void findView() {
        btnNew = findViewById(R.id.btn_add002);
        btnOld = findViewById(R.id.btn_add001);
        tvShow = findViewById(R.id.tv_show_info);
        btnOld.setText("旧map");
        btnNew.setText("新map");
        btnNew.setOnClickListener(this);
        btnOld.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add001:
                tvShow.setText(storageAndShowOld());
                break;
            case R.id.btn_add002:
                tvShow.setText(getAndShowNew());
                break;
        }
    }

    private String storageAndShowOld() {
        StringBuilder sb = new StringBuilder();
        if(mSp == null || map==null) return null;
        SharedPreferences.Editor editor = mSp.edit();
        HashMap<String, String> newMap = (HashMap<String, String>) mSp.getAll();
        editor.clear();
        editor.putInt("size", size);
        sb.append("size:"+size+"\n");
        for(int i=0; i<size; i++){
            editor.putString(""+i, "key00"+(i+1));
            editor.putString("key00"+(i+1), (String) map.get("key00"+(i+1)));
            sb.append(i+": "+"key00"+(i+1)+"\n");
            sb.append("key00"+(i+1)+": "+map.get("key00"+(i+1))+"\n");
        }
        editor.commit();
        return sb.toString();
    }

    public String getAndShowNew() {
        if(mSp == null) return null;
        StringBuilder sb = new StringBuilder();

        int msize = mSp.getInt("size", 0);
        sb.append("get size:"+msize+"\n");
        for(int i=0; i<msize; i++){
            String strKey = mSp.getString(""+i, null);
            String strVal = mSp.getString(strKey, null);
            sb.append("strKey:"+strKey+"\n");
            sb.append("strVal:"+strVal+"\n");
        }

        return sb.toString();
    }
}
