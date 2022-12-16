package com.fastapp.config

import com.drake.serialize.serialize.serial
import com.drake.serialize.serialize.serialLazy
import com.fastapp.entity.SerializableModel


object UserConfig {
    const val isLogin = false
    var token = ""

    /** 懒读取, 每次写入都会更新内存/磁盘, 但是读取仅第一次会读取磁盘, 后续一直使用内存中, 有效减少ANR */
    var userId: String by serialLazy()

    var skinModel: String =""/** 每次都读写磁盘 */



}