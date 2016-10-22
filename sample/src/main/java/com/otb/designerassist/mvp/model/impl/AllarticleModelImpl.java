package com.otb.designerassist.mvp.model.impl;

import android.content.Context;

import com.otb.designerassist.http.Api;
import com.otb.designerassist.http.ServiceFactory;
import com.otb.designerassist.http.service.AllarticleService;
import com.otb.designerassist.mvp.model.IAllarticleModel;
import com.otb.designerassist.mvp.model.entity.SentenceSimple;
import com.otb.designerassist.mvp.presenter.callback.OnAllarticleListener;
import com.otb.designerassist.util.DocParseUtil;
import com.otb.designerassist.util.StringUtil;

import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllarticleModelImpl implements IAllarticleModel {

    private AllarticleService allarticleService;

    private OnAllarticleListener mListener;

    private Context mContext;

    @Override
    public void loadArticle(Context context, String type, String page, OnAllarticleListener listener) {

        this.mContext = context;
        this.mListener = listener;

        this.allarticleService = ServiceFactory.getInstance().createService(AllarticleService.class, Api.BASE_URL_ALLARTICLE);

        loadArticle(type, page);
    }

    private void loadArticle(String type, String page) {

        Call<ResponseBody> call = allarticleService.loadAllarticle(type, page);

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
