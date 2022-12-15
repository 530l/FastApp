package com.fastapp.ui.skin

import com.drake.logcat.LogCat
import com.fastapp.base.BaseBindingActivity
import com.fastapp.databinding.Sk1ActivityBinding
import com.fastapp.ui.skin.loader.CustomSDCardLoader
import com.therouter.router.Route
import skin.support.SkinCompatManager
import skin.support.SkinCompatManager.SkinLoaderListener

@Route(path = "http://fastapp/sk1")
class Skin1Activity : BaseBindingActivity<Sk1ActivityBinding>() {

    override fun initView() {
        binding.sk1btn.setOnClickListener {
            //应用内换肤:
            SkinCompatManager.getInstance().loadSkin(
                "black",
                object : SkinCompatManager.SkinLoaderListener {
                    override fun onStart() {

                    }

                    override fun onSuccess() {

                    }

                    override fun onFailed(errMsg: String?) {

                    }

                },
                SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN
            )
        }
        binding.sk2btn.setOnClickListener {
            //应用内换肤:
            SkinCompatManager.getInstance().loadSkin(
                "green", null,
                SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN
            )
        }

        //todo 插件的形式不能加后缀
        //todo 颜色打包存放在 resource.arsc 文件
        binding.sk3btn.setOnClickListener {
            SkinCompatManager.getInstance().loadSkin(
                "orange.skin",
                object : SkinLoaderListener {
                    override fun onStart() {
                        LogCat.i("SkinCompatManager onStart")
                    }

                    override fun onSuccess() {
                        LogCat.i("SkinCompatManager onSuccess")
                    }

                    override fun onFailed(errMsg: String?) {
                        LogCat.i("SkinCompatManager ${errMsg}")
                    }

                }, SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS
            )

        }
    }

    override fun initData() {

    }


}