package com.otb.designerassist.mvp.model.impl;

import android.content.Context;
import android.text.TextUtils;

import com.apkfuns.logutils.LogUtils;
import com.otb.designerassist.http.Api;
import com.otb.designerassist.http.ServiceFactory;
import com.otb.designerassist.http.service.AllarticleService;
import com.otb.designerassist.mvp.model.IImgTextModel;
import com.otb.designerassist.mvp.model.entity.SentenceImageText;
import com.otb.designerassist.mvp.presenter.callback.OnImgTextListener;
import com.otb.designerassist.util.DocParseUtil;
import com.otb.designerassist.util.StringUtil;

import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImgTextModelImpl implements IImgTextModel {

    private AllarticleService allarticleService;

    private OnImgTextListener mListener;

    private Context mContext;

    @Override
    public void loadMeiju(Context context, String type, String page, OnImgTextListener listener) {

        this.mContext = context;
        this.mListener = listener;

        this.allarticleService = ServiceFactory.getInstance().createService(AllarticleService.class, Api.BASE_URL_MEITUMEIJU);

        loadMeiju(type, page);

    }

    @Override
    public void loadMeiju(Context context, String page, OnImgTextListener listener) {

        this.mContext = context;
        this.mListener = listener;

        this.allarticleService = ServiceFactory.getInstance().createService(AllarticleService.class, Api.BASE_URL_MEITUMEIJU);

        loadMeiju(null, page);
    }

    private void loadMeiju(String type, String page) {

        Call<ResponseBody> call = null;

        if (TextUtils.isEmpty(type)) {

            String url = Api.BASE_URL_MEITUMEIJU;

            if (!TextUtils.isEmpty(page)) {
                url = url + "?page=" + page;
            }

            call = allarticleService.loadMeiju(url);
        } else {

            call = allarticleService.loadMeiju(type, page);
        }


        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                InputStream inputStream = response.body().byteStream();

                String result = StringUtil.inToString(inputStream);

//                System.out.println(result);

                List<SentenceImageText> sentenceImageTexts = DocParseUtil.parseMeiju(result);

                mListener.onSuccess(sentenceImageTexts);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                LogUtils.e(t);
                mListener.onError(t);
            }
        });

    }


}
