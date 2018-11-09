package com.offer.pass.preg.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.offer.pass.preg.R;
import com.offer.pass.preg.customview.MCircleSeekBar;

/**
 * Created by Administrator on 2017/11/9.
 * description:
 */

public class RoundSeekbarDemo extends Activity {
    private TextView tvPerencetValue;
    private MCircleSeekBar mCircleSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_seekbar);
        tvPerencetValue = (TextView) findViewById(R.id.tv_perencet_set_perencet);
        mCircleSeekBar = (MCircleSeekBar) findViewById(R.id.m_circleSeekBar_set_perencet);

        mCircleSeekBar.setSeekBarChangeListener(new MCircleSeekBar.OnSeekChangeListener() {

            public void onProgressChange(MCircleSeekBar view, int newProgress) {
                tvPerencetValue.setText(Integer.toString(view.getProgress()));
            }
        });
    }
}
