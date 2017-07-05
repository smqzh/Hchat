package com.qzh.hchat.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.qzh.hchat.R;
import com.qzh.hchat.app.ConstantsImageUrl;
import com.qzh.hchat.databinding.ActivitySplashBinding;
import com.qzh.hchat.utils.CommonUtils;
import com.qzh.hchat.utils.PerfectClickListener;
import java.util.Random;

/**
 * Created by lcx on 2017/6/5.
 */

public class SplashActivity extends AppCompatActivity {


	private ActivitySplashBinding mBinding;
	private boolean animationEnd;
	private boolean isIn;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
		initViews();
	}
	protected void initViews() {
		mBinding.ivDefultPic.setImageDrawable(CommonUtils.getDrawable(R.mipmap.img_transition_default));
		int i = new Random().nextInt(ConstantsImageUrl.TRANSITION_URLS.length);
		Glide.with(this)
				.load(ConstantsImageUrl.TRANSITION_URLS[i])
				.placeholder(R.mipmap.img_transition_default)
				.error(R.mipmap.img_transition_default)
				.into(mBinding.ivPic);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mBinding.ivDefultPic.setVisibility(View.GONE);
			}
		}, 1500);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				toMainActivity();
			}
		}, 3500);

		mBinding.tvJump.setOnClickListener(new PerfectClickListener() {
			@Override
			protected void onNoDoubleClick(View v) {
				animationEnd();
			}
		});
	}

	private void animationEnd() {
		synchronized (SplashActivity.this) {
			if (!animationEnd) {
				animationEnd = true;
				mBinding.ivPic.clearAnimation();
				toMainActivity();
			}
		}
	}

	private void toMainActivity() {
		if (isIn) {
			return;
		}
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
		finish();
		isIn = true;
	}
}