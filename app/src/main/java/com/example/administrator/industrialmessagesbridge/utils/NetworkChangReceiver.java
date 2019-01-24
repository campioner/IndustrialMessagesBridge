package com.example.administrator.industrialmessagesbridge.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.net.ConnectivityManager.TYPE_WIFI;
import static android.provider.ContactsContract.CommonDataKinds.Email.TYPE_MOBILE;

public class NetworkChangReceiver extends BroadcastReceiver {
    private  boolean flag=false;
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {

            switch (networkInfo.getType()) {
               case TYPE_MOBILE:
                   if (flag)
                    Toast.makeText(context, "正在使用移动数据", Toast.LENGTH_SHORT).show();
                    break;
                case TYPE_WIFI:
                    if (flag)
                    Toast.makeText(context, "正在使用wifi上网", Toast.LENGTH_SHORT).show();
                    break;
            }
            flag=true;
        }else{
            flag=true;
            Toast.makeText(context,"当前无网络连接",Toast.LENGTH_SHORT).show();
        }
    }
}
