package com.example.admin.housekeeper.dao;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public class AppInfoDao {
    /**
     * 创建appdao类，获取应用信息
     */
    private Context           mContext;
    public  List<PackageInfo> mInfoList;
    public ArrayList<ImageView> mListIcon;
    public ArrayList<TextView> mListPakageName;
    public ArrayList<TextView> mListVersionName;
    public ArrayList<TextView> mListAppName;

    public AppInfoDao(Context context) {
        mContext = context;
        mInfoList = new ArrayList<>();
        mListIcon = new ArrayList<>();
        mListAppName = new ArrayList<>();
        mListPakageName = new ArrayList<>();
        mListVersionName = new ArrayList<>();
    }

    /**
     * 获取具体信息
     */
    public void getAppInfo() {
        PackageManager packageManager = mContext.getPackageManager();
        //获取应用信息
        mInfoList = packageManager.getInstalledPackages(0);
        for (int i = 0; i < mInfoList.size(); i++) {
            //将报名赋给packageInfo
            PackageInfo packageInfo = mInfoList.get(i);
            //通过报名packageInfo，获取APP的名字，赋值给appInfo
            ApplicationInfo appInfo = packageInfo.applicationInfo;
            //判断是不是本机自带的应用
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                //通过appInfo，获取与APP对应的图片，赋给icon
                Drawable icon = appInfo.loadIcon(packageManager);
                ImageView imageView = new ImageView(mContext);
                imageView.setImageDrawable(icon);
                //获取icon集合
                mListIcon.add(imageView);
                //获取包名集合
                String name = packageInfo.packageName;
                TextView tvPackage = new TextView(mContext);
                tvPackage.setText(name);
                mListPakageName.add(tvPackage);
                //通过包名packageInfo获取版本号，赋值给versionName
                String versionName = packageInfo.versionName;
                TextView tvVersion = new TextView(mContext);
                tvVersion.setText(versionName);
                //将每一个版本号添加到版本号的集合mListVersionName中
                mListVersionName.add(tvVersion);
                //获取应用名
                String appName = (String) appInfo.loadLabel(packageManager);
                TextView tvAppName = new TextView(mContext);
                tvAppName.setText(appName);
                mListAppName.add(tvAppName);
            }
        }
    }


}
