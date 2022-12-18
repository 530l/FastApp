package com.fastapp.base

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate
//import androidx.appcompat.app.SkinAppCompatDelegateImpl
import androidx.viewbinding.ViewBinding
import com.drake.logcat.LogCat
import com.dylanc.viewbinding.base.ActivityBinding
import com.dylanc.viewbinding.base.ActivityBindingDelegate
import com.fast.base.BaseActivity
import com.fastapp.R
import com.fastapp.action.TitleBarAction
import com.fastapp.action.ToastAction
import com.fastapp.config.UserConfig
import com.gyf.immersionbar.ImmersionBar
import com.hjq.bar.TitleBar
import com.hjq.shape.layout.ShapeLinearLayout
import com.noober.background.BackgroundLibrary
//import skin.support.utils.SkinPreference

abstract class BaseBindingActivity<VB : ViewBinding> :
    BaseActivity(), ToastAction, TitleBarAction,
    ActivityBinding<VB> by ActivityBindingDelegate() {

    /** 标题栏对象 */
    private var titleBar: TitleBar? = null

    /** 状态栏沉浸 */
    private var immersionBar: ImmersionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BackgroundLibrary.inject2(this)
        setContentViewWithBinding()
        initActivity()
    }

    override fun initLayout() {
        super.initLayout()
        val titleBar = getTitleBar()
        titleBar?.setOnTitleBarListener(this)
        // 初始化沉浸式状态栏
        if (isStatusBarEnabled()) {
            getStatusBarConfig().init()
            // 设置标题栏沉浸
            if (titleBar != null) {
                ImmersionBar.setTitleBar(this, titleBar)
            }
        }
    }

    /**
     * 是否使用沉浸式状态栏
     */
    protected open fun isStatusBarEnabled(): Boolean {
        return true
    }

    /**
     * 状态栏字体深色模式
     */
    open fun isStatusBarDarkFont(): Boolean {
        return true
    }

    /**
     * 获取状态栏沉浸的配置对象
     */
    open fun getStatusBarConfig(): ImmersionBar {
        if (immersionBar == null) {
            immersionBar = createStatusBarConfig()
        }
        return immersionBar!!
    }

    /**
     * 初始化沉浸式状态栏
     */
    protected open fun createStatusBarConfig(): ImmersionBar {
        return ImmersionBar.with(this) // 默认状态栏字体颜色为黑色
            .statusBarDarkFont(isStatusBarDarkFont()) // 指定导航栏背景颜色
            .navigationBarColor(com.fast.base.R.color.white) // 状态栏字体和导航栏内容自动变色，必须指定状态栏颜色和导航栏颜色才可以自动变色
            .autoDarkModeEnable(true, 0.2f)
    }

    /**
     * 设置标题栏的标题
     */
    override fun setTitle(@StringRes id: Int) {
        title = getString(id)
    }

    /**
     * 设置标题栏的标题
     */
    override fun setTitle(title: CharSequence?) {
        super<BaseActivity>.setTitle(title)
        getTitleBar()?.title = title
    }

    override fun getTitleBar(): TitleBar? {
        if (titleBar == null) {
            titleBar = obtainTitleBar(getContentView())
        }
        return titleBar
    }

    override fun onLeftClick(titleBar: TitleBar?) {
        onBackPressed()
    }

    override fun startActivityForResult(intent: Intent, requestCode: Int, options: Bundle?) {
        super.startActivityForResult(intent, requestCode, options)
        overridePendingTransition(R.anim.right_in_activity, R.anim.right_out_activity)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.left_in_activity, R.anim.left_out_activity)
    }

//    override fun getDelegate(): AppCompatDelegate {
//        return SkinAppCompatDelegateImpl.get(this, this)
//    }


}