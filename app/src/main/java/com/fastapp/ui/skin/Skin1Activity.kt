package com.fastapp.ui.skin

import android.text.TextUtils
import androidx.annotation.ColorRes
import com.fastapp.R
import com.fastapp.base.BaseBindingActivity
import com.fastapp.databinding.Sk1ActivityBinding
import com.fastapp.ui.skin.loader.CustomSDCardLoader
import com.therouter.router.Route
import skin.support.SkinCompatManager
import skin.support.content.res.ColorState.ColorBuilder
import skin.support.content.res.SkinCompatUserThemeManager


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
            //插件加载。默认读取ASSETS目录下
            /*  SkinCompatManager.getInstance().loadSkin(
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
              )*/
            //自定义路径加载 初始化需要 add CustomSDCardLoader
            //为了测试，  targetSdkVersion 28  /sdcar/skin/orange.skin
            SkinCompatManager.getInstance().loadSkin(
                "orange.skin",
                null, CustomSDCardLoader.SKIN_LOADER_STRATEGY_SDCARD
            );
        }
    }

    override fun initData() {
//        java.lang.NullPointerException
//        at skin.support.content.res.SkinCompatUserThemeManager.startLoadFromSharedPreferences(SkinCompatUserThemeManager.java:64)
//        at skin.support.content.res.SkinCompatUserThemeManager.<init>(SkinCompatUserThemeManager.java:53)
//        at skin.support.content.res.SkinCompatUserThemeManager.<clinit>(SkinCompatUserThemeManager.java:39)
//        at skin.support.content.res.SkinCompatResources.getSkinColor(SkinCompatResources.java:128)
//        at skin.support.content.res.SkinCompatResources.getColor(SkinCompatResources.java:237)
//        at com.fastapp.view.skin.MyShapeLinearLayout.applySKinShapeLinearLayout(MyShapeLinearLayout.java:54)
//        at com.fastapp.view.skin.MyShapeLinearLayout.<init>(MyShapeLinearLayout.java:37)
//        at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
//        at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
//        at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
//        at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:490)
//        at org.jetbrains.android.uipreview.ViewLoader.createNewInstance(ViewLoader.java:351)
//        at org.jetbrains.android.uipreview.ViewLoader.loadClass(ViewLoader.java:200)
//        at org.jetbrains.android.uipreview.ViewLoader.loadView(ViewLoader.java:161)
//        at com.android.tools.idea.rendering.LayoutlibCallbackImpl.loadView(LayoutlibCallbackImpl.java:294)
//        at android.view.BridgeInflater.loadCustomView(BridgeInflater.java:417)
//        at android.view.BridgeInflater.loadCustomView(BridgeInflater.java:428)
//        at android.view.BridgeInflater.createViewFromTag(BridgeInflater.java:332)
//        at android.view.LayoutInflater.createViewFromTag(LayoutInflater.java:965)
//        at android.view.LayoutInflater.rInflate_Original(LayoutInflater.java:1127)
//        at android.view.LayoutInflater_Delegate.rInflate(LayoutInflater_Delegate.java:72)
//        at android.view.LayoutInflater.rInflate(LayoutInflater.java:1101)
//        at android.view.LayoutInflater.rInflateChildren(LayoutInflater.java:1088)
//        at android.view.LayoutInflater.inflate(LayoutInflater.java:686)
//        at android.view.LayoutInflater.inflate(LayoutInflater.java:505)
//        at com.android.layoutlib.bridge.impl.RenderSessionImpl.inflate(RenderSessionImpl.java:359)
//        at com.android.layoutlib.bridge.Bridge.createSession(Bridge.java:436)
//        at com.android.tools.idea.layoutlib.LayoutLibrary.createSession(LayoutLibrary.java:121)
//        at com.android.tools.idea.rendering.RenderTask.createRenderSession(RenderTask.java:717)
//        at com.android.tools.idea.rendering.RenderTask.lambda$inflate$9(RenderTask.java:873)
//        at com.android.tools.idea.rendering.RenderExecutor$runAsyncActionWithTimeout$3.run(RenderExecutor.kt:192)
//        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
//        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
//        at java.base/java.lang.Thread.run(Thread.java:829)

    }


}