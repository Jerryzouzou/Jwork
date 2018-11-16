package com.example.game2048.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.game2048.R;

/**
 * Created by Administrator on 2018/2/5.
 * description: 背景音乐服务
 */

public class BackGroundMusicService extends Service implements MediaPlayer.OnCompletionListener{

    MediaPlayer player;
    private final  IBinder binder = new AudioBinder();

    @Override
    public void onCreate() {
        super.onCreate();

        player = MediaPlayer.create(this, R.raw.login_back);
        player.setOnCompletionListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!player.isPlaying()){
            player.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (player.isPlaying()) {
            player.stop();
        }
        player.release();

        stopSelf();     //停掉service
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.start();
    }

    public class AudioBinder extends Binder{
        public BackGroundMusicService getService(){
            return BackGroundMusicService.this;
        }
    }
}
