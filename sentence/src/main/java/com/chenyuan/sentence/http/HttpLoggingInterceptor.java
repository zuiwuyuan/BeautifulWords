package com.chenyuan.sentence.http;

import com.apkfuns.logutils.LogUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * retrofit拦截
 */
public class HttpLoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        Request request = chain.request();

        String requestStartMessage = request.method() + ' ' + request.url();

        LogUtils.e(requestStartMessage);

        long startNs = System.nanoTime();
        Response response = chain.proceed(request);
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        LogUtils.e(response.code() + ' ' + response.message() + " (" + tookMs + "ms" + ')');

        return response;
    }
}
