package com.fastapp.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hjq.toast.ToastUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailedTaskViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    //...
    init {
        ToastUtils.show("11231231312")
    }
}