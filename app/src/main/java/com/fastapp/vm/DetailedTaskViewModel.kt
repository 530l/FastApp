package com.fastapp.vm

import androidx.lifecycle.ViewModel
import com.kunminx.architecture.domain.message.MutableResult

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
