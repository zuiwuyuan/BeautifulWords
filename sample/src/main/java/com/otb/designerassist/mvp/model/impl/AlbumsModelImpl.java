package com.otb.designerassist.mvp.model.impl;

import android.content.Context;

import com.otb.designerassist.http.Api;
import com.otb.designerassist.http.ServiceFactory;
import com.otb.designerassist.http.service.AllarticleService;
import com.otb.designerassist.mvp.model.IAlbumsModel;
import com.otb.designerassist.mvp.model.entity.SentenceCollection;
import com.otb.designerassist.mvp.presenter.callback.OnAlbumsListener;
import com.otb.designerassist.util.DocParseUtil;
import com.otb.designerassist.util.StringUtil;

import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsModelImpl implements IAlbumsModel {

    private AllarticleService allarticleService;

    private OnAlbumsListener mListener;

    private Context mContext;

    @Override
    public void loadAlbums(Context context, String type, String page, OnAlbumsListener listener) {

        this.mContext = context;
        this.mListener = listener;

        this.allarticleService = ServiceFactory.getInstance().createService(AllarticleService.class, Api.BASE_URL_ALBUMS);

        loadArticle(type, page);
    }

    private void loadArticle(String type, String page) {

        Call<ResponseBody> call = allarticleService.loadAlbums(type, page);

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
