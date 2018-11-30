package com.offer.pass.preg.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ViewFlipper;

import com.offer.pass.preg.R;

import java.util.ArrayList;

public class ViewFilpperDemo extends Activity {

    private ViewFlipper flipper;
    private ArrayList<View> aList;
    private View view_one, view_two;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewfilpper);

        LayoutInflater li = getLayoutInflater();
        view_one = li.inflate(R.layout.view_one,null,false);
        view_two = li.inflate(R.layout.view_two,null,false);

        flipper = findViewById(R.id.filpper);
        flipper.addView(view_one);
        flipper.addView(view_two);
    }
}
