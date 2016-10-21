package com.otb.designerassist.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.otb.designerassist.R;
import com.otb.designerassist.common.JuziApi;
import com.otb.designerassist.util.JuziUtil;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 名人名句-电影台词
     * 名人名句-小说摘抄
     * 名人名句-散文美句
     * 名人名句-散文美句
     * 名人名句-连续剧台词
     * <p/>
     * 这些个部分虽然请求的url不尽相同，但是数据解析的方式是一样的。
     *
     * @param view
     */
    public void onClick1(View view) {

        int page = 0;
        String url = JuziApi.ALLARTICLE_JINGDIANTAICI + page;

        JuziUtil juziUtil = new JuziUtil();
        juziUtil.getMemorableQuotes(this, url);
    }

    /**
     * 句子迷-名人名句-详情(句子合集-列表)
     * 原创句子-最新原创的句子
     * 原创句子-本周热门原创的句子
     * 原创句子-推荐原创的句子
     *
     * @param view
     */
    public void onClick2(View view) {

        int page = 0;
        String url = JuziApi.ORIGINAL_JU + page;

        JuziUtil juziUtil = new JuziUtil();
        juziUtil.getAllarticleCollectList(this, url);
    }

    /**
     * 精选句集
     *
     * @param view
     */
    public void onClick3(View view) {

        int page = 0;
        final String url = JuziApi.ALBUMS + page;

        JuziUtil juziUtil = new JuziUtil();
        juziUtil.getJuziCollection(this, url);
    }

    /**
     * 最新句集
     *
     * @param view
     */
    public void onClick4(View view) {

        int page = 0;
        final String url = JuziApi.NEWALBUMS + page;

        JuziUtil juziUtil = new JuziUtil();
        juziUtil.getJuziCollection(this, url);
    }

    /**
     * 美图美句
     *
     * @param view
     */
    public void onClick5(View view) {

        int page = 1;
        final String url = JuziApi.MEITUMEIJU + page;

        JuziUtil juziUtil = new JuziUtil();
        juziUtil.getSentenceImgText(this, url);

    }

}
