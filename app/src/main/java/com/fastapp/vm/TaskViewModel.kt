package com.fastapp.vm

import androidx.lifecycle.ViewModel
import com.kunminx.architecture.domain.message.MutableResult

/**
 * 一般使用，先在activity创建一个viewModel的实例，然后fragment引用当前activity的view-model
 */
class TaskViewModel : ViewModel() {

    val mutableLiveData = MutableResult<String?>()

    private var age = 0

    init {
        age = 20
    }

    fun show() {
        mutableLiveData.value = "age=${20}"
    }
}
