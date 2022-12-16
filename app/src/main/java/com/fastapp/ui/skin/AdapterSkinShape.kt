package com.fastapp.ui.skin

import android.graphics.Color
import com.drake.logcat.LogCat
import com.hjq.shape.layout.ShapeLinearLayout
import skin.support.utils.SkinPreference

//最简单的适配就是动态处理了
//当选择某个主题的时候，清掉所有activity，调转到MainActivity,动态的就这样适配吧
fun adapterSkinShapeLinearLayout(vararg views: ShapeLinearLayout) {
    val skinColorStr: String = when (SkinPreference.getInstance().skinName) {
        "black" -> {
            "#333333"
        }
        "green" -> {
            "#4CAF50"
        }
        "orange.skin" -> {
            "#FF9800"
        }
        else -> {
            "#333333"
        }
    }
    LogCat.i(skinColorStr,"skinColorStr")
    views.forEach {
        it.shapeDrawableBuilder
            .setSolidColor(Color.parseColor(skinColorStr))
            .intoBackground();
    }
}