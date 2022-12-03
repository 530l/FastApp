
package com.fastapp.config

import com.drake.net.convert.JSONConvert
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hjq.gson.factory.GsonFactory
import org.json.JSONObject
import java.lang.reflect.Type

class GsonConverter : JSONConvert(code = "errorCode", message = "errorMsg") {
    companion object {
        // 获取单例的 Gson 对象（已处理容错）
         val gson: Gson = GsonFactory.getSingletonGson()
    }

    override fun <R> String.parseBody(succeed: Type): R? {
        return gson.fromJson<R>(JSONObject(this).getString("data"), succeed)
    }
}