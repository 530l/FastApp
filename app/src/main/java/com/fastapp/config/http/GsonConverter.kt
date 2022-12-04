package com.fastapp.config.http

import com.drake.logcat.LogCat
import com.drake.net.convert.JSONConvert
import com.google.gson.Gson
import com.hjq.gson.factory.GsonFactory
import org.json.JSONObject
import java.lang.reflect.Type


class GsonConvert : JSONConvert(code = "errorCode", message = "errorMsg") {
    companion object {
        // 获取单例的 Gson 对象（已处理容错）
        val gson: Gson = GsonFactory.getSingletonGson()
    }

    override fun <S> String.parseBody(succeed: Type): S? {
        val data = JSONObject(this).getString("data")
        val errorCode = JSONObject(this).getString("errorCode")
        val errorMsg = JSONObject(this).getString("errorMsg")
        LogCat.e(errorCode, "GsonConvert")
        LogCat.e(errorMsg, "GsonConvert")
        return gson.fromJson(data, succeed)
    }
}