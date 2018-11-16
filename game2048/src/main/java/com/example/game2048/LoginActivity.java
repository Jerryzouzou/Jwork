package com.example.game2048;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.game2048.Service.BackGroundMusicService;
import com.example.game2048.Utils.Titanic;
import com.example.game2048.Utils.TitanicTextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/2/5.
 * description:
 */

public class LoginActivity extends Activity implements View.OnClickListener {

    private Intent i;   //绑定监听service
    private BackGroundMusicService musicService;
    private ProgressBar pro;

    private long firstTime;     //监听两次返回键
    private int counter;    //加载进度条计数
    private Timer timer;    //加载进度条定时
    private TitanicTextView mTvStartGame, mTvStarCharts;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicService = ((BackGroundMusicService.AudioBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicService = null;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        i = new Intent(this, BackGroundMusicService.class);
        startService(i);
        bindService(i, connection, Context.BIND_AUTO_CREATE);

         mTvStartGame = (TitanicTextView) findViewById(R.id.startGame);
          mTvStarCharts = (TitanicTextView) findViewById(R.id.startCharts);
        mTvStartGame.setOnClickListener(this);
        mTvStarCharts.setOnClickListener(this);
        mTvStartGame.setVisibility(View.INVISIBLE);
        mTvStarCharts.setVisibility(View.INVISIBLE);

        //首先创建一个显示器,然后传进一个  按钮对象  给显示器  开启动画
        Titanic startGameAnim = new Titanic();
        startGameAnim.start(mTvStartGame);
        Titanic startChartsAnim = new Titanic();
        startChartsAnim.start(mTvStarCharts);

        /*final NumberProgressBar bnp = (NumberProgressBar) findViewById(R.id.number_progress_bar);

        //开始加载
        counter = 0;
        timer = new Timer();

        //进度条线程
        timer.schedule(new TimerTask() {
            //重写线程的run方法
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //一次加载几个百分比
                        bnp.incrementProgressBy(1);
                        counter++;
                        //当进度条到达100时候进度条结束加载
                        if (counter == 100) {
                            //设置progress样式
                            bnp.setProgress(0);
                            counter = 0;
                            //隐藏进度条   显示两个按钮
                            bnp.setVisibility(View.INVISIBLE);
                            mTvStarCharts.setVisibility(View.VISIBLE);
                            mTvStartGame.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        }, 500, 50);*/
        pro = (ProgressBar) findViewById(R.id.progress_bar);
        handler.postDelayed(runnable, 1000);
    }

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            pro.setVisibility(View.INVISIBLE);
            mTvStarCharts.setVisibility(View.VISIBLE);
            mTvStartGame.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(connection);
        stopService(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindService(i, connection, Context.BIND_AUTO_CREATE);
        startService(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(i);
    }

    @Override
    public void onClick(View v) {
        Intent next;
        switch (v.getId()){
            case R.id.startGame:
                next = new Intent(this, MainActivity.class);
                startActivity(next);
                finish();
                break;
            case R.id.startCharts:
                next = new Intent(this, ChartsActivity.class);
                startActivity(next);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - firstTime < 3000){
                finish();
                return true;
            }else {
                firstTime = System.currentTimeMillis();
                Toast.makeText(this, "再点一次退出", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return false;
    }
}
