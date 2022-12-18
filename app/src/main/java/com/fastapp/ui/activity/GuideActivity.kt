package com.fastapp.ui.activity

import com.fastapp.base.BaseBindingActivity
import com.fastapp.databinding.GuideActivityBinding
import com.fastapp.config.GlogConfig
import com.therouter.TheRouter
import com.therouter.router.Route

@Route(path = "http://fastapp/guide", action = "abc")
class GuideActivity : BaseBindingActivity<GuideActivityBinding>() {

    override fun initView() {
        binding.writeGladTv.setOnClickListener {
//            TheRouter.build("http://fastapp/sk1").navigation(this)
            TheRouter.build("http://fastapp/paintedskin").navigation(this)

        }

        binding.readGladTv.setOnClickListener {
            TheRouter.build("http://fastapp/sk2").navigation(this)
        }
    }

    override fun initData() {

    }


}