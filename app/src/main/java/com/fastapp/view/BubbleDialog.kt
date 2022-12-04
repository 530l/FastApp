package com.fastapp.view

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.RotateDrawable
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StyleRes
import com.fastapp.R
import com.fastapp.utils.ThreadUtils.runMain

/**
 * iOS风格的加载对话框
 * @param title 加载对话框的标题
 */
class BubbleDialog @JvmOverloads constructor(
    context: Context,
    var title: String = context.getString(R.string.bubble_loading_title),
    @StyleRes themeResId: Int = R.style.BubbleDialog,
) : Dialog(context, themeResId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_bubble_dialog)
        findViewById<TextView>(R.id.tv_title).text = title
        val ivLoading = findViewById<ImageView>(R.id.iv_loading)
        val rotateDrawable = ivLoading.background as RotateDrawable
        ObjectAnimator.ofInt(rotateDrawable, "level", 0, 10000).apply {
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            interpolator = LinearInterpolator()
            start()
        }
    }

    override fun show() {
        runMain {
            super.show()
        }
    }

    /**
     * 更新标题文本
     */
    fun updateTitle(text: String) {
        if (isShowing) {
            runMain {
                findViewById<TextView>(R.id.tv_title).text = title
            }
        } else {
            title = text
        }
    }
}