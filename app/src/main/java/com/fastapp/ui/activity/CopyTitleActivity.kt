package com.fastapp.ui.activity

import com.fast.base.action.ActivityAction
import com.fastapp.R
import com.fastapp.base.BaseBindingActivity
import com.fastapp.databinding.CopyTitleActivityBinding
import com.fastapp.ui.fragment.CopyTitleFragment
import com.therouter.router.Route

@Route(path = "http://fastapp/copytitle", action = "abc")
class CopyTitleActivity : BaseBindingActivity<CopyTitleActivityBinding>() ,ActivityAction{


    override fun initView() {
        val copyTitleFragment = CopyTitleFragment.newInstance()
        val mTransaction = supportFragmentManager.beginTransaction()
        mTransaction.add(R.id.mContainerView, copyTitleFragment)
        mTransaction.commit()

    }

    override fun initData() {}
}