package com.qzh.hchat.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.qzh.hchat.R;
import com.qzh.hchat.widget.ToolBarSet;

import java.lang.reflect.Field;

import butterknife.ButterKnife;


/**
 * Created by lcx on 2017/5/4.
 */

public abstract class BaseActivity extends AppCompatActivity {

	private Toolbar mToolbar;
	public Context mContext;
	private ToolBarSet mToolBarSet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(attachLayoutRes());
		initState();
		ButterKnife.bind(this);
		init();
	}

	@Override
	public void setContentView(@LayoutRes int layoutResID) {
		View view = getLayoutInflater().inflate(R.layout.base_layout, null);
		super.setContentView(view);
		initDefaultView(layoutResID);
	}

	private void initDefaultView(int layoutResId) {
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		FrameLayout container = (FrameLayout) findViewById(R.id.container);
		View childView = LayoutInflater.from(this).inflate(layoutResId, null);
		container.addView(childView, 0);
	}

	private void init() {
		mContext = this;
		mToolBarSet = new ToolBarSet(mToolbar,this);
		initInject();
		initViews();
		initData();
	}

	/**
	 * 绑定布局文件
	 * @return 布局文件ID
	 */
	@LayoutRes
	protected abstract int attachLayoutRes();

	/**
	 * 初始化视图控件
	 */
	protected abstract void initViews();

	/**
	 * 初始化数据
	 */
	protected abstract void initData();

	/**
	 * 初始化dagger2
	 */
	protected abstract void initInject();

	/**
	 * 获取Toolbar对象
	 * @return
	 */
	public ToolBarSet getToolBar(){
		if(mToolBarSet==null){
			mToolBarSet = new ToolBarSet(mToolbar,this);
		}
		return mToolBarSet;
	}


	/**
	 * 动态的设置状态栏  实现沉浸式状态栏
	 */
	private void initState() {
		//当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			//透明状态栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			//透明导航栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			//
			LinearLayout linear_bar = (LinearLayout) findViewById(R.id.status_bar_content);
			linear_bar.setVisibility(View.VISIBLE);
			//获取到状态栏的高度
			int statusHeight = getStatusBarHeight();
			//动态的设置隐藏布局的高度
			LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linear_bar.getLayoutParams();
			params.height = statusHeight;
			linear_bar.setLayoutParams(params);
		}
	}

	/**
	 * 通过反射的方式获取状态栏高度
	 *
	 * @return
	 */
	private int getStatusBarHeight() {
		try {
			Class<?> c = Class.forName("com.android.internal.R$dimen");
			Object obj = c.newInstance();
			Field field = c.getField("status_bar_height");
			int x = Integer.parseInt(field.get(obj).toString());
			return getResources().getDimensionPixelSize(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
