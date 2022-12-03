package com.fastapp.config

import com.therouter.getApplicationContext
import glog.android.Glog
import timber.log.Timber
import java.io.IOException

object GlogConfig {


    private var glog: Glog? = null

    fun init() {
        // 全局初始化 设置内部调试日志等级
        if (AppConfig.isDebug()) {
            Glog.initialize(Glog.InternalLogLevel.InternalLogLevelDebug)
        } else {
            Glog.initialize(Glog.InternalLogLevel.InternalLogLevelInfo)
        }
        setup()
    }


    // 初始化实例
    private fun setup() {
        glog = Glog.Builder(getApplicationContext())
            .protoName("glog_identify") // 实例标识，相同标识的实例只创建一次
//                .encryptMode(Glog.EncryptMode.AES) // 加密方式
//                .key("530key") // ECDH Server public key
            .incrementalArchive(true) // 增量归档，当天日志写入同一文件
            .build()
    }

    // 写入日志
    fun write(data: String) {
        val data: ByteArray = data.toByteArray() // 序列化数据
        glog!!.write(data) // 写入二进制数组
    }

    // 读取日志
    fun read() {
        val logFiles: ArrayList<String> = ArrayList()
        glog!!.getArchiveSnapshot(logFiles, 10, 100 * 1024)
        // 获取日志文件快照，当 cache 中日志条数 >=10 或体积 >= 100 KB 将自动 flush
        val inBuffer = ByteArray(Glog.getSingleLogMaxLength())
        for (logFile in logFiles) {
            try {
                glog!!.openReader(logFile).use { reader ->
                    while (true) {
                        val n = reader.read(inBuffer)
                        if (n < 0) {
                            break
                        } else if (n == 0) { // 触发容错
                            continue
                        }
                        val outBuffer = ByteArray(n)
                        System.arraycopy(inBuffer, 0, outBuffer, 0, n)
                        Timber.e(String(outBuffer))// 反序列化数据
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}