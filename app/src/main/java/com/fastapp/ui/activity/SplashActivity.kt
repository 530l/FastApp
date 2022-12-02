package com.fastapp.ui.activity

import android.animation.*
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import com.drake.net.time.Interval
import com.fastapp.app.BaseBindingActivity
import com.fastapp.databinding.SplashActivityBinding
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.hjq.toast.ToastUtils
import com.therouter.TheRouter
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * 闪屏界面
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseBindingActivity<SplashActivityBinding>() {


    override fun initView() {

    }

    override fun initData() {
        Timber.i("%s", "hhahah")
        Timber.e("The activity has been destroyed and permission requests cannot be made")
        Interval(1, 1, TimeUnit.SECONDS, 2, 0).finish {
            TheRouter.build("http://fastapp/guide")
                .withInt("key1", 12345678)
                .withString("key2", "参数")
                .withBoolean("key3", false)
                .navigation(this@SplashActivity)
            finish()
        }.life(this).start()
    }

    override fun createStatusBarConfig(): ImmersionBar {
        return super.createStatusBarConfig()
            // 隐藏状态栏和导航栏
            .hideBar(BarHide.FLAG_HIDE_BAR)
    }

}