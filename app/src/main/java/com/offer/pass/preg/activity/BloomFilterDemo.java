package com.offer.pass.preg.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.offer.pass.preg.R;
import com.offer.pass.preg.tool.BloomFilter02;

public class BloomFilterDemo extends Activity implements View.OnClickListener{

    private BloomFilter02 bf02;
    private SharedPreferences preferences;

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloomfiter);

        initData();
        findView();
    }

    private void findView() {
        findViewById(R.id.btn_add001).setOnClickListener(this);
        findViewById(R.id.btn_add002).setOnClickListener(this);
        findViewById(R.id.btn_add003).setOnClickListener(this);
        findViewById(R.id.btn_add004).setOnClickListener(this);

    }

    private void initData() {
        mContext = this;
        bf02 = new BloomFilter02();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add001:
                bf02.addValue("2e 21 03 15 01");
                break;
            case R.id.btn_add002:
                bf02.addValue("5a a5 11 02 16 01");
                break;
            case R.id.btn_add003:
                if(bf02.contains("2e 21 03 15 01")){
                    showToash("have btn001 str");
                }else {
                    showToash("do not have btn001 str");
                }
                break;
            case R.id.btn_add004:
                if(bf02.contains("aa 55 23 15")){
                    showToash("have aa 55 23 15 str");
                }else {
                    showToash("do not have aa 55 23 15 str");
                }
                break;
        }
    }

    private void showToash(String str){
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }
}
