package com.fastapp.base

import android.app.Activity
import android.app.Application
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.drake.logcat.LogCat
import com.drake.net.NetConfig
import com.drake.net.cookie.PersistentCookieJar
import com.drake.net.interceptor.LogRecordInterceptor
import com.drake.net.okhttp.setConverter
import com.drake.net.okhttp.setDebug
import com.drake.net.okhttp.setDialogFactory
import com.drake.net.okhttp.setRequestInterceptor
import com.drake.statelayout.StateConfig
import com.fastapp.R
import com.fastapp.config.AppConfig
import com.fastapp.config.GlogConfig
import com.fastapp.config.TitleBarConfig
import com.fastapp.config.glide.GlideApp
import com.fastapp.config.http.GsonConvert
import com.fastapp.config.http.MyRequestInterceptor
import com.fastapp.config.http.RefreshTokenInterceptor
import com.fastapp.ui.skin.loader.CustomSDCardLoader
import com.fastapp.ui.skin.loader.ZipSDCardLoader
import com.fastapp.utils.ActivityManager
import com.fastapp.view.BubbleDialog
import com.hjq.bar.TitleBar
import com.hjq.gson.factory.GsonFactory
import com.hjq.toast.ToastUtils
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tencent.mmkv.MMKV
import com.tencent.mmkv.MMKVLogLevel
import dagger.hilt.android.HiltAndroidApp
import okhttp3.Cache
import skin.support.SkinCompatManager
import skin.support.app.SkinAppCompatViewInflater
import skin.support.app.SkinCardViewInflater
import skin.support.constraint.app.SkinConstraintViewInflater
import skin.support.design.app.SkinMaterialViewInflater
import skin.support.utils.SkinPreference
import java.util.concurrent.TimeUnit


//Application（通过使用 @HiltAndroidApp）
@HiltAndroidApp
class FastApplication : Application() {

    companion object {
        lateinit var install: FastApplication
    }

    override fun onCreate() {
        super.onCreate()
        install = this
        initSdk(this)
        SkinCompatManager.withoutActivity(this)
            .addStrategy(CustomSDCardLoader())// 自定义加载策略，指定SDCard路径
            .addStrategy(ZipSDCardLoader())// 自定义加载策略，获取zip包中的资源
            .addInflater(SkinAppCompatViewInflater()) // 基础控件换肤初始化
            .addInflater(SkinMaterialViewInflater()) // material design 控件换肤初始化[可选]
            .addInflater(SkinConstraintViewInflater()) // ConstraintLayout 控件换肤初始化[可选]
            .addInflater(SkinCardViewInflater()) // CardView v7 控件换肤初始化[可选]
            .setSkinStatusBarColorEnable(true) // 关闭状态栏换肤，默认打开[可选]
            .setSkinWindowBackgroundEnable(true) // 关闭windowBackground换肤，默认打开[可选]
            .loadSkin()
        SkinPreference.getInstance().skinName

    }

    private fun initSdk(application: Application) {
        initSmartRefreshLayout()//缺省，刷新控件
        TitleBar.setDefaultStyle(TitleBarConfig())// 设置标题栏初始化器
        ToastUtils.init(application)  // 初始化吐司
        ActivityManager.getInstance().init(application) // Activity 栈管理初始化
        LogCat.setDebug(AppConfig.isLogEnable()) // LogCat是否输出异常日志
        GlogConfig.init()//Glog
        LogCat.i(filesDir.absolutePath + "/glog", "glogpath")
        initNet(application)//网络
        initConnectivityManager(application)//手机网络监听
        initPreferenceHolder()
    }

    private fun initSmartRefreshLayout() {
        // 全局缺省页配置 [https://github.com/liangjingkanji/StateLayout]
        StateConfig.apply {
            emptyLayout = R.layout.layout_empty
            loadingLayout = R.layout.layout_loading
            errorLayout = R.layout.layout_error
            setRetryIds(R.id.iv)
        }

        // 初始化SmartRefreshLayout, 这是自动下拉刷新和上拉加载采用的第三方库
        // [https://github.com/scwang90/SmartRefreshLayout/tree/master] V2版本
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
            MaterialHeader(context)
        }

        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
            ClassicsFooter(context)
        }
    }

    private fun initNet(application: Application) {
        NetConfig.initialize(AppConfig.getHostUrl(), application) {
            // 超时设置
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)

            // 本框架支持Http缓存协议和强制缓存模式
            cache(Cache(application.cacheDir, 1024 * 1024 * 128))
            // 缓存设置, 当超过maxSize最大值会根据最近最少使用算法清除缓存来限制缓存大小

            setDebug(AppConfig.isLogEnable())

            // AndroidStudio OkHttp Profiler 插件输出网络日志
            addInterceptor(LogRecordInterceptor(AppConfig.isLogEnable()))

            // 添加持久化Cookie管理
            cookieJar(PersistentCookieJar(application))

            // 通知栏监听网络日志
            if (AppConfig.isLogEnable()) {
                addInterceptor(
                    ChuckerInterceptor.Builder(application)
                        .collector(ChuckerCollector(application))
                        .maxContentLength(250000L)
                        .redactHeaders(emptySet())
                        .alwaysReadResponseBody(false)
                        .build()
                )
            }

            // 添加请求拦截器, 可配置全局/动态参数
            setRequestInterceptor(MyRequestInterceptor())
            addInterceptor(RefreshTokenInterceptor())

            // 数据转换器
            setConverter(GsonConvert())

            // 自定义全局加载对话框
            setDialogFactory {
                BubbleDialog(it, "加载中....")
            }
        }
    }

    private fun initConnectivityManager(application: Application) {
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

    private fun initPreferenceHolder() {
        // 设置 Json 解析容错监听
        GsonFactory.setJsonCallback { typeToken, fieldName, jsonToken ->
            LogCat.e("GsonFactory", "类型解析异常：$typeToken#$fieldName，后台返回的类型为：$jsonToken")
        }
        MMKV.initialize(this, cacheDir.absolutePath, MMKVLogLevel.LevelInfo)
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

}