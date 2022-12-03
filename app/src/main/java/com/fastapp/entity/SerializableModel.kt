

package com.fastapp.entity

import android.os.Parcelable
import java.io.Serializable

/** 使用Java自带的[Serializable]进行序列化传递 */
data class SerializableModel(var name: String = "ModelSerializable") : Serializable {
    companion object {
        /**
         * 保证新增字段依然可以读取到对象, 该Id不能重复否则读取异常
         * 注意新增字段的默认值是无效的,比如新增 firstName = "default" 实际上读取到的默认值是null
         * 想保证完整的默认值效果请实现[Parcelable], 具体请查看[ParcelableModel]
         */
        private const val serialVersionUID = -7L
    }
}