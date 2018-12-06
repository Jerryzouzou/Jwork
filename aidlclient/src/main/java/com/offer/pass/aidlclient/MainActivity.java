package com.offer.pass.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.offer.pass.aidlclient.aidl.MessageCenter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "AIDL-client-main";

    private EditText edInput;
    private Button btnSend;

    private MessageCenter messageCenter = null;
    private boolean isBound =false;     //标志当前与服务端的连接状态
    private List<Info> mInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!isBound){
            attemptToBindService();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isBound){
            unbindService(mServiceConnection);
            isBound = false;
        }
    }

    private void attemptToBindService() {
        Intent intent = new Intent();
        intent.setAction("com.offer.pass.aidl");
        intent.setPackage("com.offer.pass.aidlserver");
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG, "service connect");
            messageCenter = MessageCenter.Stub.asInterface(iBinder);
            isBound = true;
            if(messageCenter != null){
                try {
                    mInfoList = messageCenter.getInfo();
                    Log.i(TAG, mInfoList.toString());
                }catch (RemoteException e){
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "service disconnect");
            isBound = false;
        }
    };

    private void findView() {
        edInput = findViewById(R.id.ed_input);
        btnSend = findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strSend = edInput.getText().toString();
                addMessage(strSend);
            }
        });
    }

    private void addMessage(String strContent){
        if(!isBound){
            attemptToBindService();
            Toast.makeText(this, "当前与服务端处于未连接状态，正在尝试重连，请稍后再试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (messageCenter == null) return;

        Info info = new Info();
        info.setStrContent(strContent);
        try {
            messageCenter.addInfo(info);
            Log.i(TAG,"客户端："+ info.toString() );
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }
}
