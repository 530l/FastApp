package com.fastapp.ui.fragment

import com.fast.base.BaseActivity
import com.fastapp.app.BaseBindingFragment
import com.fastapp.databinding.CopyFragmentBinding

class CopyFragment : BaseBindingFragment<BaseActivity, CopyFragmentBinding>() {

    companion object {
        fun newInstance(): CopyFragment {
            return CopyFragment()
        }
    }


    override fun initView() {

    }

    override fun initData() {
        TODO("Not yet implemented")
    }
}