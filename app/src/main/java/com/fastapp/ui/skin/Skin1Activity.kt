package com.fastapp.ui.skin


import android.graphics.Color
import android.view.ViewGroup
import com.drake.logcat.LogCat
import com.fastapp.base.BaseBindingActivity
import com.fastapp.config.UserConfig
import com.fastapp.databinding.Sk1ActivityBinding
import com.hjq.shape.layout.ShapeLinearLayout
import com.therouter.router.Route
import skin.support.SkinCompatManager


@Route(path = "http://fastapp/sk1")
class Skin1Activity : BaseBindingActivity<Sk1ActivityBinding>() {


    private val skinCompatManager = object : SkinCompatManager.SkinLoaderListener {
        override fun onStart() {
            LogCat.i("SkinCompatManager onStart")
        }

        override fun onSuccess() {
            LogCat.i("SkinCompatManager onSuccess")

        }

        override fun onFailed(errMsg: String?) {
            LogCat.i("SkinCompatManager ${errMsg}")
        }

    }

    override fun initView() {

        binding.sk1btn.setOnClickListener {
            //应用内换肤:
            loadSkin("black", SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN)
        }
        binding.sk2btn.setOnClickListener {
            //应用内换肤:
            loadSkin("green", SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN)
        }
        //todo 插件的形式不能加后缀
        //todo 颜色打包存放在 resource.arsc 文件
        binding.sk3btn.setOnClickListener {
            //插件加载。默认读取ASSETS目录下
            loadSkin("orange.skin", SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS)
        }
        binding.sk4btn.setOnClickListener {
            //自定义路径加载 初始化需要 add CustomSDCardLoader
            //为了测试，  targetSdkVersion 28  /sdcar/skin/orange.skin
//            loadSkin("orange.skin", CustomSDCardLoader.SKIN_LOADER_STRATEGY_SDCARD)
        }

    }

    private fun loadSkin(skinName: String, strategy: Int) {
        SkinCompatManager.getInstance().loadSkin(skinName, skinCompatManager, strategy)
        finish()
    }


    override fun initData() {
        adapterSkinShapeLinearLayout(binding.shape1, binding.shape2, binding.shape3)
    }

    /**
     * 递归获取 ViewGroup
     */
    private fun obtainSkin(group: ViewGroup?) {
        if (group == null) {
            return
        }
        val skinModel = UserConfig.skinModel
        var colorStr = "#333333"
        when (skinModel) {
            "black" -> {
                colorStr = "#333333"
            }
            "green" -> {
                colorStr = "#4CAF50"
            }
            "orange.skin" -> {
                colorStr = "#FF9800"
            }
        }

        for (i in 0..group.childCount) {
            val view = group.getChildAt(i)
            if (view is ShapeLinearLayout) {
                view.shapeDrawableBuilder
                    .setSolidColor(Color.parseColor(colorStr))
                    .intoBackground();
            }
            //子view
            if (view is ViewGroup) {
                obtainSkin(view)
            }
        }
    }

}