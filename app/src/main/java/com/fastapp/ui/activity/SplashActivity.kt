package com.fastapp.ui.activity


import com.drake.logcat.LogCat
import com.drake.net.time.Interval
import com.fastapp.base.BaseBindingActivity
import com.fastapp.databinding.SplashActivityBinding
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.therouter.TheRouter
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

/**
 * 闪屏界面
 */
@AndroidEntryPoint
class SplashActivity : BaseBindingActivity<SplashActivityBinding>() {


    override fun initView() {
        LogCat.i("%s", "hhahah")
        LogCat.e("The activity has been destroyed and permission requests cannot be made")
        Interval(1, 1, TimeUnit.SECONDS, 2, 0).finish {
            TheRouter.build("http://fastapp/guide").navigation(this@SplashActivity)
//            TheRouter.build("http://fastapp/copy").navigation(this@SplashActivity)
//            TheRouter.build("http://fastapp/copytitle").navigation(this@SplashActivity)
            finish()
        }.life(this).start()
    }

    override fun initData() {
    }

    override fun createStatusBarConfig(): ImmersionBar {
        return super.createStatusBarConfig()
            // 隐藏状态栏和导航栏
            .hideBar(BarHide.FLAG_HIDE_BAR)
    }

}