package com.offer.pass.preg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int[] btnId = {R.id.btn01, R.id.btn02};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
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

                break;
            case R.id.btn02:

                break;
        }
    }
}
