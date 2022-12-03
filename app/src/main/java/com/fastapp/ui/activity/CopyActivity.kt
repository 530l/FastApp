package com.fastapp.ui.activity


import androidx.lifecycle.ViewModelProvider
import com.fastapp.R
import com.fastapp.base.BaseBindingActivity
import com.fastapp.databinding.CopyActivityBinding
import com.fastapp.ui.fragment.CopyFragment
import com.fastapp.vm.TaskViewModel
import com.hjq.bar.TitleBar
import com.therouter.router.Route

@Route(path = "http://fastapp/copy", action = "abc")
class CopyActivity : BaseBindingActivity<CopyActivityBinding>() {


    override fun initView() {
        val copyTitleFragment = CopyFragment.newInstance()
        val mTransaction = supportFragmentManager.beginTransaction()
        mTransaction.add(R.id.mContainerView, copyTitleFragment)
        mTransaction.commit()
    }

    override fun onLeftClick(titleBar: TitleBar?) {
        finish()
    }


    override fun initData() {
        ViewModelProvider(this)[TaskViewModel::class.java]
    }
}