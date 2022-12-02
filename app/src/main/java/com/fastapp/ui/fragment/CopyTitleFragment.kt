package com.fastapp.ui.fragment


import com.fastapp.base.BaseBindingTitleBarFragment
import com.fastapp.databinding.CopyTitleFragmentBinding
import com.fastapp.ui.activity.CopyTitleActivity
import com.hjq.bar.TitleBar
import com.hjq.toast.ToastUtils

class CopyTitleFragment :
    BaseBindingTitleBarFragment<CopyTitleActivity, CopyTitleFragmentBinding>() {

    companion object {
        fun newInstance(): CopyTitleFragment {
            return CopyTitleFragment()
        }
    }


    override fun initView() {

    }

    override fun initData() {

    }

    override fun onTitleClick(titleBar: TitleBar?) {
        ToastUtils.show("onTitleClick")
    }

    override fun onLeftClick(titleBar: TitleBar?) {
        ToastUtils.show("onLeftClick")
    }

    override fun onRightClick(titleBar: TitleBar?) {
        ToastUtils.show("onRightClick")
    }

    override fun isStatusBarEnabled(): Boolean {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled()
    }
}