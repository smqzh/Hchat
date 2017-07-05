package com.qzh.hchat.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.qzh.hchat.R;
import com.qzh.hchat.base.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        getToolBar().setTitle("Hchat")
                .setDisplayHomeAsUpEnabled(false)
                .setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_add:
                                new MaterialDialog.Builder(mContext)
                                        .title("退出")
                                        .content("您确定要退出吗？")
                                        .positiveText("确定")
                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                               // TemplateApplication.getInstance().getActivityManager().finishAllActivity();
                                            }
                                        })
                                        .negativeText("取消")
                                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .cancelable(false)
                                        .show();
                                break;
                        }
                        return false;
                    }
                });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initInject() {

    }
    /** 创建菜单 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

}
