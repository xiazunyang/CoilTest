package cn.numeron.coiltest

import cn.numeron.okhttp.file.DlProgressCallback
import cn.numeron.okhttp.file.UpProgressCallback
import coil.request.Parameters
import okhttp3.Interceptor
import okhttp3.Response

class CoilProgressInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val parameters = request.tag(Parameters::class.java)
        if (parameters != null) {
            val parametersValue = parameters.values().mapNotNull(Map.Entry<String, Any?>::value)
            val dlProgressCallback = parametersValue.filterIsInstance<DlProgressCallback>().firstOrNull()
            val upProgressCallback = parametersValue.filterIsInstance<UpProgressCallback>().firstOrNull()
            if (dlProgressCallback != null) {
                request = request.newBuilder()
                    .tag(DlProgressCallback::class.java, dlProgressCallback)
                    .build()
            }
            if (upProgressCallback != null) {
                request = request.newBuilder()
                    .tag(UpProgressCallback::class.java, upProgressCallback)
                    .build()
            }
        }
        return chain.proceed(request)
    }

}