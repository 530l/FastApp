package com.fastapp.ui.activity

import com.fastapp.base.BaseBindingActivity
import com.fastapp.databinding.GuideActivityBinding
import com.fastapp.config.GlogConfig
import com.therouter.router.Route

@Route(path = "http://fastapp/guide", action = "abc")
class GuideActivity : BaseBindingActivity<GuideActivityBinding>() {

    override fun initView() {
        binding.writeGladTv.setOnClickListener {
            GlogConfig.write("hello")
        }
        binding.readGladTv.setOnClickListener {
            GlogConfig.read()
        }
    }

    override fun initData() {

    }


}