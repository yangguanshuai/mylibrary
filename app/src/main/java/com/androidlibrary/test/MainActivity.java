package com.androidlibrary.test;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.androidlibrary.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mylibrary.base.BaseActivity;
import com.mylibrary.base.HttpResult;
import com.mylibrary.http.HttpManager;
import com.mylibrary.listener.HttpOnNextListener;

public class MainActivity extends BaseActivity {


    private TextView tvMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMsg = findViewById(R.id.tv);
        verifyStoragePermissions(this);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testApi testapi = new testApi(MainActivity.this,simpleOnNextListener);
                HttpManager manager = HttpManager.getInstance();
                manager.httpRequest(testapi);
            }
        });
    }


    //   回调一一对应
    HttpOnNextListener simpleOnNextListener = new HttpOnNextListener<ResultPictoken>() {
        @Override
        public void onNext(ResultPictoken subjects) {
            tvMsg.setText("网络返回：\n" + subjects.toString());
        }

        @Override
        public void onCacheNext(String cache) {
            /*缓存回调*/
            Gson gson = new Gson();
            java.lang.reflect.Type type = new TypeToken<HttpResult<ResultPictoken>>() {
            }.getType();
            HttpResult resultEntity = gson.fromJson(cache, type);
            tvMsg.setText("缓存返回：\n" + resultEntity.getResult().toString());
        }

        /*用户主动调用，默认是不需要覆写该方法*/
        @Override
        public void onError(Throwable e) {
            super.onError(e);
            Log.d("cookie",e.getMessage());
            tvMsg.setText("失败：\n" + e.toString());
        }

        /*用户主动调用，默认是不需要覆写该方法*/
        @Override
        public void onCancel() {
            super.onCancel();
            tvMsg.setText("取消請求");
        }
    };


    private final int REQUEST_EXTERNAL_STORAGE = 1;
    private String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    /**
     * 申请权限
     *
     * @param activity
     */
    public void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
