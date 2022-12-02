package com.fastapp.base

import android.app.Activity
import android.app.Application
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.fastapp.R
import com.fastapp.utils.activity.ActivityManager
import com.fastapp.config.AppConfig
import com.fastapp.config.GlideApp
import com.fastapp.utils.glog.GlogUtils
import com.fastapp.utils.log.DebugLoggerTree
import com.fastapp.utils.titlebar.TitleBarStyle
import com.fastapp.utils.toast.ToastStyle
import com.hjq.bar.TitleBar
import com.hjq.toast.ToastLogInterceptor
import com.hjq.toast.ToastUtils
import timber.log.Timber


class FastApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initSdk(this)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        // 清理所有图片内存缓存
        GlideApp.get(this).onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        // 根据手机内存剩余情况清理图片内存缓存
        GlideApp.get(this).onTrimMemory(level)
    }

    companion object {
        /**
         * 初始化一些第三方框架
         */
        fun initSdk(application: Application) {
            // 设置标题栏初始化器
            TitleBar.setDefaultStyle(TitleBarStyle())
            // 初始化吐司
            ToastUtils.init(application, ToastStyle())
            // 设置调试模式
            ToastUtils.setDebugMode(AppConfig.isDebug())
            // 设置 Toast 拦截器
            ToastUtils.setInterceptor(ToastLogInterceptor())
            // 本地异常捕捉
//            CrashHandler.register(application)
            // Activity 栈管理初始化
            ActivityManager.getInstance().init(application)
            // 初始化日志打印
            if (AppConfig.isLogEnable()) {
                Timber.plant(DebugLoggerTree())
            }
            //Glog
            GlogUtils.init()
            // 注册网络状态变化监听
            val connectivityManager: ConnectivityManager? = ContextCompat.getSystemService(
                application,
                ConnectivityManager::class.java
            )
            if (connectivityManager != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                connectivityManager.registerDefaultNetworkCallback(object :
                    ConnectivityManager.NetworkCallback() {
                    override fun onLost(network: Network) {
                        val topActivity: Activity? = ActivityManager.getInstance().getTopActivity()
                        if (topActivity !is LifecycleOwner) {
                            return
                        }
                        val lifecycleOwner: LifecycleOwner = topActivity
                        if (lifecycleOwner.lifecycle.currentState != Lifecycle.State.RESUMED) {
                            return
                        }
                        ToastUtils.show(R.string.common_network_error)
                    }
                })
            }
        }
    }
}