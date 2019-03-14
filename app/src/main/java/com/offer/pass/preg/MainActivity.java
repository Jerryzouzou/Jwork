package com.offer.pass.preg;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.offer.pass.preg.activity.BloomFilterDemo;
import com.offer.pass.preg.activity.DebugUIDemo;
import com.offer.pass.preg.activity.ListViewViewGoneItemDemo;
import com.offer.pass.preg.activity.RoundSeekbarDemo;
import com.offer.pass.preg.activity.SharepreBianliDemo;
import com.offer.pass.preg.activity.VerticalViewPagerDemo;
import com.offer.pass.preg.activity.ViewFilpperDemo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int[] btnId = {R.id.btn01, R.id.btn02, R.id.btn03, R.id.btn04, R.id.btn05, R.id.btn06,
    R.id.btn07};
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
            case R.id.btn04:
                startNewActivity(ListViewViewGoneItemDemo.class);
                break;
            case R.id.btn05:
                startNewActivity(BloomFilterDemo.class);
                break;
            case R.id.btn06:
                startNewActivity(DebugUIDemo.class);
                break;
            case R.id.btn07:
                startNewActivity(SharepreBianliDemo.class);
                break;
        }
    }

    private void startNewActivity(Class<?> activityClass) {
        Intent intent = new Intent(mContext, activityClass);
        startActivity(intent);
    }

}
