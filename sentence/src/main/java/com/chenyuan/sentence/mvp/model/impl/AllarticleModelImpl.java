package com.chenyuan.sentence.mvp.model.impl;

import android.content.Context;

import com.chenyuan.sentence.http.Api;
import com.chenyuan.sentence.http.ServiceFactory;
import com.chenyuan.sentence.http.service.SentenceService;
import com.chenyuan.sentence.mvp.model.IAllarticleModel;
import com.chenyuan.sentence.mvp.model.bean.SentenceSimple;
import com.chenyuan.sentence.mvp.presenter.callback.OnAllarticleListener;
import com.chenyuan.sentence.util.DocParseUtil;
import com.chenyuan.sentence.util.StringUtil;

import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllarticleModelImpl implements IAllarticleModel {

    private SentenceService mSentenceService;

    private OnAllarticleListener mListener;

    private Context mContext;

    @Override
    public void loadArticle(Context context, String type, String page, OnAllarticleListener listener) {

        this.mContext = context;
        this.mListener = listener;

        this.mSentenceService = ServiceFactory.getInstance().createService(SentenceService.class, Api.BASE_URL_ALLARTICLE);

        loadArticle(type, page);
    }

    private void loadArticle(String type, String page) {

        Call<ResponseBody> call = mSentenceService.loadAllarticle(type, page);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                InputStream inputStream = response.body().byteStream();

                String result = StringUtil.inToString(inputStream);

//                System.out.println(result);

                List<SentenceSimple> sentenceSimples = DocParseUtil.parseAllarticle(result);

                mListener.onSuccess(sentenceSimples);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mListener.onError(t);
            }
        });

    }

}
