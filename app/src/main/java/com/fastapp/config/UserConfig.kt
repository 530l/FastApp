package com.fastapp.config

import com.drake.serialize.serialize.serial
import com.drake.serialize.serialize.serialLazy
import com.fastapp.entity.SerializableModel


object UserConfig {
    const val isLogin = false
    var token = ""

    /** 懒读取, 每次写入都会更新内存/磁盘, 但是读取仅第一次会读取磁盘, 后续一直使用内存中, 有效减少ANR */
    var userId: String by serialLazy()

    /** 每次都读写磁盘 */
    var isFirstLaunch: Boolean by serial()

    /** 保存集合 */
    var host: List<String> by serial()

    /** 只要集合元素属于可序列化对象 Serializable/Parcelable 即可保存到本地 */
    var users: List<SerializableModel> by serial()
}