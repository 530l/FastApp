package com.fastapp.ui.paintedskin

import com.fastapp.base.BaseBindingActivity
import com.fastapp.databinding.PaintedSkActivityBinding
import com.noober.background.common.ResourceUtils
import com.therouter.router.Route
import org.alee.component.skin.service.ThemeSkinService


@Route(path = "http://fastapp/paintedskin")
class PaintedSkinActivity : BaseBindingActivity<PaintedSkActivityBinding>() {

    override fun initView() {
        binding.sk1btn.setOnClickListener {
            //换肤
            ThemeSkinService.getInstance().switchThemeSkin(1);
        }
    }

    override fun initData() {

    }


}