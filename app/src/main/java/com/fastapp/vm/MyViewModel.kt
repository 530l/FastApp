

package com.fastapp.vm

import androidx.lifecycle.ViewModel

/** 普通的ViewModel, 支持配置变更时自动恢复数据(意外销毁不会恢复) */
class MyViewModel : ViewModel() {
    var name = "MainViewModel"
}