package com.example.testi;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    private final IBinder bindMich = new MyBinder();

    public MyService() {
    }

    public int compute() {
        return 2*2;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return bindMich;
    }

    public class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }
}
