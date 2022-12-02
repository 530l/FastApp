package com.fastapp.ui.activity

import com.fastapp.base.BaseBindingActivity
import com.fastapp.databinding.GuideActivityBinding
import com.fastapp.utils.glog.GlogUtils
import com.therouter.router.Route

@Route(path = "http://fastapp/guide", action = "abc")
class GuideActivity : BaseBindingActivity<GuideActivityBinding>() {

    override fun initView() {
        binding.writeGladTv.setOnClickListener {
            GlogUtils.write("hello")
        }
        binding.readGladTv.setOnClickListener {
            GlogUtils.read()
        }
    }

    override fun initData() {

    }


}