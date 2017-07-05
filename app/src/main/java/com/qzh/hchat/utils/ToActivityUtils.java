package com.qzh.hchat.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public class ToActivityUtils {



    /**
     * 关闭当前页面跳转
     * @param activity 当前Activity
     * @param cls 要跳转的Activity
     */
    public static void finshTogo(Activity activity, Class<?> cls) {
        keepTogo(activity, cls);
        activity.finish();
    }
    /**
     * 关闭当前页面跳转
     * @param activity 当前Activity
     * @param intent intent活动
     */

    public static void finshTogo(Activity activity, Intent intent) {
        keepTogo(activity, intent);
        activity.finish();
    }
    /**
     * 关闭当前页面跳转
     * @param activity 当前Activity
     * @param cls 要跳转的Activity
     * @param bundle Bundle对象
     */

    public static void finshTogo(Activity activity, Class<?> cls, Bundle bundle) {
        keepTogo(activity, cls, bundle);
        activity.finish();
    }
    /**
     * 不关闭当前页面跳转
     * @param activity 当前Activity
     * @param cls 要跳转的Activity
     */

    public static void keepTogo(Activity activity, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        activity.startActivity(intent);
    }
    /**
     * 不关闭当前页面跳转
     * @param activity 当前Activity
     * @param intent intent活动
     */
    public static void keepTogo(Activity activity, Intent intent) {
        activity.startActivity(intent);
    }
    /**
     * 不关闭当前页面跳转
     * @param activity 当前Activity
     * @param cls 要跳转的Activity
     * @param bundle Bundle对象
     */
    public static  void keepTogo(Activity activity, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(activity, cls);
        activity.startActivity(intent);
    }








}
