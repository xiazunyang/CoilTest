package cn.numeron.coiltest

import cn.numeron.okhttp.file.ProgressInterceptor
import cn.numeron.okhttp.log.LogLevel
import cn.numeron.okhttp.log.TextLogInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object Http {

    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .readTimeout(16, TimeUnit.SECONDS)
            .writeTimeout(16, TimeUnit.SECONDS)
            .connectTimeout(8, TimeUnit.SECONDS)
            .addInterceptor(ProgressInterceptor())
            .addInterceptor(TextLogInterceptor().setLevel(LogLevel.BODY))
            .build()
    }

}