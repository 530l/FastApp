package com.fastapp.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

//ViewModel（通过使用 @HiltViewModel）
@HiltViewModel
class MyHiltViewModel :ViewModel() {

}