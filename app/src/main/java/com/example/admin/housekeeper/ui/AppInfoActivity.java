package com.example.admin.housekeeper.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.housekeeper.R;
import com.example.admin.housekeeper.dao.AppInfoDao;
import com.example.admin.housekeeper.adapter.MyadapterAppInfo;

import java.util.ArrayList;
//手机APP详情页
public class AppInfoActivity extends AppCompatActivity {
    private ListView mLvShowAppInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        //初始化控件
        initView();
    }
    private void initView() {
        mLvShowAppInfo = (ListView) findViewById(R.id.lv_show_app_info);
        mLvShowAppInfo.setAdapter(new MyadapterAppInfo(this));
        AppInfoDao appInfoDao = new AppInfoDao(this);
        appInfoDao.getAppInfo();
        final ArrayList<TextView> list = appInfoDao.mListPakageName;
        mLvShowAppInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:"+list.get(position).getText()));
                startActivity(intent);
            }
        });
    }
}
