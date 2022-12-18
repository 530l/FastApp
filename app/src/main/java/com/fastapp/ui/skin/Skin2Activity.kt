package com.fastapp.ui.skin

import com.fastapp.base.BaseBindingActivity
import com.fastapp.databinding.Sk2ActivityBinding
import com.therouter.router.Route

@Route(path = "http://fastapp/sk2")
class Skin2Activity : BaseBindingActivity<Sk2ActivityBinding>() {

    override fun initView() {
        binding.sk1btn.setOnClickListener {
        }
    }

    override fun initData() {

    }


}