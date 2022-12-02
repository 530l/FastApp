package com.fastapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.drake.logcat.LogCat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launchWhenResumed {
            LogCat.setDebug(BuildConfig.DEBUG) // 全局开关
            LogCat.i(packageName)
        }
    }
}