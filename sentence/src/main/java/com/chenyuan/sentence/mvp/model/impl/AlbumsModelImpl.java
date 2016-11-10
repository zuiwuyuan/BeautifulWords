package com.chenyuan.sentence.mvp.model.impl;

import android.content.Context;

import com.chenyuan.sentence.http.Api;
import com.chenyuan.sentence.http.ServiceFactory;
import com.chenyuan.sentence.http.service.SentenceService;
import com.chenyuan.sentence.mvp.model.IAlbumsModel;
import com.chenyuan.sentence.mvp.model.bean.SentenceCollection;
import com.chenyuan.sentence.mvp.presenter.callback.OnAlbumsListener;
import com.chenyuan.sentence.util.DocParseUtil;
import com.chenyuan.sentence.util.StringUtil;

import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsModelImpl implements IAlbumsModel {

    private SentenceService mSentenceService;

    private OnAlbumsListener mListener;

    private Context mContext;

    @Override
    public void loadAlbums(Context context, String type, String page, OnAlbumsListener listener) {

        this.mContext = context;
        this.mListener = listener;

        this.mSentenceService = ServiceFactory.getInstance().createService(SentenceService.class, Api.BASE_URL_ALBUMS);

        loadArticle(type, page);
    }

    private void loadArticle(String type, String page) {

        Call<ResponseBody> call = mSentenceService.loadAlbums(type, page);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                InputStream inputStream = response.body().byteStream();

                String result = StringUtil.inToString(inputStream);

//                System.out.println(result);

                List<SentenceCollection> sentenceImageTexts = DocParseUtil.parseAlbums(result);

                mListener.onSuccess(sentenceImageTexts);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mListener.onError(t);
            }
        });

    }


}
