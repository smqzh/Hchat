package com.qzh.hchat.app;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.example.http.HttpUtils;
import com.qzh.hchat.utils.DebugUtil;


/**
 * Created by Administrator on 2017/6/30 0030.
 */

public class HCApplication extends Application{


    private static HCApplication HCApplication;

    public static HCApplication getInstance() {
        return HCApplication;
    }

    @SuppressWarnings("unused")
    @Override
    public void onCreate() {
        super.onCreate();
        HCApplication = this;
        HttpUtils.getInstance().init(this, DebugUtil.DEBUG);
        initTextSize();
    }

    /**
     * 使其系统更改字体大小无效
     */
    private void initTextSize() {
        Resources res = getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}
