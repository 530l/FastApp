package com.fastapp.base

import android.os.Bundle
import android.view.*
import androidx.viewbinding.ViewBinding
import com.dylanc.viewbinding.base.FragmentBinding
import com.dylanc.viewbinding.base.FragmentBindingDelegate
import com.fast.base.BaseFragment
import com.fastapp.action.TitleBarAction
import com.fastapp.action.ToastAction
import com.gyf.immersionbar.ImmersionBar
import com.hjq.bar.TitleBar


/**
 *  带标题栏的 Fragment 业务基类
 */
abstract class BaseBindingTitleBarFragment<A : BaseBindingActivity<*>, VB : ViewBinding>
    : BaseFragment<A>(), FragmentBinding<VB> by FragmentBindingDelegate(), ToastAction,
    TitleBarAction {

    /** 标题栏对象 */
    private var titleBar: TitleBar? = null

    /** 状态栏沉浸 */
    private var immersionBar: ImmersionBar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = createViewWithBinding(inflater, container)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleBar = getTitleBar()
        // 设置标题栏点击监听
        titleBar?.setOnTitleBarListener(this)

        if (isStatusBarEnabled()) {
            // 初始化沉浸式状态栏
            getStatusBarConfig().init()
            if (titleBar != null) {
                // 设置标题栏沉浸
                ImmersionBar.setTitleBar(this, titleBar)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (isStatusBarEnabled()) {
            // 重新初始化状态栏
            getStatusBarConfig().init()
        }
    }

    /**
     * 是否在 Fragment 使用沉浸式
     */
    open fun isStatusBarEnabled(): Boolean {
        return false
    }

    /**
     * 获取状态栏沉浸的配置对象
     */
    private fun getStatusBarConfig(): ImmersionBar {
        if (immersionBar == null) {
            immersionBar = createStatusBarConfig()
        }
        return immersionBar!!
    }

    /**
     * 初始化沉浸式
     */
    private fun createStatusBarConfig(): ImmersionBar {
        return ImmersionBar.with(this)
            // 默认状态栏字体颜色为黑色
            .statusBarDarkFont(isStatusBarDarkFont())
            // 指定导航栏背景颜色
            .navigationBarColor(com.fast.base.R.color.white)
            // 状态栏字体和导航栏内容自动变色，必须指定状态栏颜色和导航栏颜色才可以自动变色
            .autoDarkModeEnable(true, 0.2f)
    }

    /**
     * 获取状态栏字体颜色
     */
    protected open fun isStatusBarDarkFont(): Boolean {
        // 返回真表示黑色字体
        return getAttachActivity()!!.isStatusBarDarkFont()
    }

    override fun getTitleBar(): TitleBar? {
        if (titleBar == null || !isLoading()) {
            titleBar = obtainTitleBar(view as ViewGroup)
        }
        return titleBar
    }

}