package com.otb.designerassist.mvp.model.impl;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.otb.designerassist.http.Api;
import com.otb.designerassist.http.ServiceFactory;
import com.otb.designerassist.http.service.AllarticleService;
import com.otb.designerassist.mvp.model.IOrignalModel;
import com.otb.designerassist.mvp.model.entity.SentenceDetail;
import com.otb.designerassist.mvp.presenter.callback.OnOrinalListener;
import com.otb.designerassist.util.DocParseUtil;
import com.otb.designerassist.util.StringUtil;

import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrignalModelImpl implements IOrignalModel {

    private AllarticleService allarticleService;

    private OnOrinalListener mListener;

    private Context mContext;

    @Override
    public void loadOriginal(Context context, String type, String page, OnOrinalListener listener) {

        this.mContext = context;
        this.mListener = listener;

        this.allarticleService = ServiceFactory.getInstance().createService(AllarticleService.class, Api.BASE_URL_ORIGINAL);

        loadOriginal(type, page);
    }

    private void loadOriginal(String type, String page) {

        Call<ResponseBody> call = allarticleService.loadOrignal(type, page);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                InputStream inputStream = response.body().byteStream();

                String result = StringUtil.inToString(inputStream);

//                System.out.println(result);

                List<SentenceDetail> sentenceDetails = null;
                try {
                    sentenceDetails = DocParseUtil.parseOrignal(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                LogUtils.e("size： "+sentenceDetails.size());

                mListener.onSuccess(sentenceDetails);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mListener.onError(t);
            }
        });

    }
}
