package com.offer.pass.preg;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.offer.pass.preg.activity.RoundSeekbarDemo;
import com.offer.pass.preg.activity.VerticalViewPagerDemo;
import com.offer.pass.preg.activity.ViewFilpperDemo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int[] btnId = {R.id.btn01, R.id.btn02, R.id.btn03};
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        findView();
    }

    private void initData() {
        mContext = this;
    }

    private void findView() {
        for (int i = 0; i < btnId.length; i++) {
            findViewById(btnId[i]).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn01:
                startNewActivity(RoundSeekbarDemo.class);
                break;
            case R.id.btn02:
                startNewActivity(VerticalViewPagerDemo.class);
                break;
            case R.id.btn03:
                startNewActivity(ViewFilpperDemo.class);
                break;
        }
    }

    private void startNewActivity(Class<?> activityClass) {
        Intent intent = new Intent(mContext, activityClass);
        startActivity(intent);
    }

}
