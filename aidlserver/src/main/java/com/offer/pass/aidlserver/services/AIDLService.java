package com.offer.pass.aidlserver.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.offer.pass.aidlclient.Info;
import com.offer.pass.aidlclient.aidl.MessageCenter;

import java.util.ArrayList;
import java.util.List;

public class AIDLService extends Service {

    private static final String TAG = "server service";

    private List<Info> messages = new ArrayList<>();

    private final MessageCenter.Stub messageCenter = new MessageCenter.Stub() {
        @Override
        public List<Info> getInfo() throws RemoteException {
            synchronized (this){
                Log.e(TAG, "getInfo invoking getInfo() method , now the list is : " + messages.toString());
                if (messages != null) {
                    return messages;
                }
                return new ArrayList<>();
            }
        }

        @Override
        public void addInfo(Info info) throws RemoteException {
            synchronized (this){
                if(messages == null){
                    messages = new ArrayList<>();
                }
                if(info == null){
                    Log.e(TAG, "message is null in In");
                    info = new Info();
                }
                //尝试修改book的参数，主要是为了观察其到客户端的反馈
//                message.setContent("dididi");
                if (!messages.contains(info)) {
                    messages.add(info);
                }
                //打印mBooks列表，观察客户端传过来的值
                Log.e(TAG, "客户传来了数据" + messages.toString());
            }
        }
    };

    @Override
    public void onCreate() {
        Info message = new Info();
        message.setStrContent("service 消息");
        messages.add(message);
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, String.format("on bind,intent = %s", intent.toString()));
        return messageCenter;
    }
}
