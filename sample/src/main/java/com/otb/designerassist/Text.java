//package com.otb.designerassist.mvp.ui.activity;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//
//import com.otb.designerassist.R;
//import com.otb.designerassist.mvp.presenter.impl.AlbumsPresenter;
//import com.otb.designerassist.mvp.presenter.impl.AllarticlePresenter;
//import com.otb.designerassist.mvp.presenter.impl.ImgTextPresenter;
//import com.otb.designerassist.mvp.presenter.impl.OrignalPresenter;
//import com.otb.designerassist.mvp.ui.view.IAlbumsView;
//import com.otb.designerassist.mvp.ui.view.IAllarticleView;
//import com.otb.designerassist.mvp.ui.view.IMeituMeijuView;
//import com.otb.designerassist.mvp.ui.view.IOrignalView;
//
//import butterknife.ButterKnife;
//
//
//public class MainActivity extends AppCompatActivity implements IAllarticleView, IOrignalView, IAlbumsView, IMeituMeijuView {
//
//
//    private AllarticlePresenter allarticlePresenter;
//
//    private OrignalPresenter orignalPresenter;
//
//    private AlbumsPresenter albumsPresenter;
//
//    private ImgTextPresenter imgTextPresenter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        ButterKnife.bind(this);
//
//        allarticlePresenter = new AllarticlePresenter(this);
//
//        orignalPresenter = new OrignalPresenter(this);
//
//        albumsPresenter = new AlbumsPresenter(this);
//
//        imgTextPresenter = new ImgTextPresenter(this);
//    }
//
//    /**
//     * 名人名句-电影台词
//     * 名人名句-小说摘抄
//     * 名人名句-散文美句
//     * 名人名句-散文美句
//     * 名人名句-连续剧台词
//     * <p/>
//     * 这些个部分虽然请求的url不尽相同，但是数据解析的方式是一样的。
//     *
//     * @param view
//     */
//    public void onClick1(View view) {
//
//        String page = "0";
//
//        // 名人名句-电影台词    @GET("jingdiantaici")
//
//        // 名人名句-小说摘抄    @GET("zhaichao")
//
//        // 名人名句-散文美句    @GET("sanwen")
//
//        // 名人名句-散文美句    @GET("dongmantaici")
//
//        // 名人名句-连续剧台词    @GET("guwen")
//
//        allarticlePresenter.loadAllarticle(this, "jingdiantaici", page);
//    }
//
//    /**
//     * 句子迷-名人名句-详情(句子合集-列表)
//     * 原创句子-最新原创的句子
//     * 原创句子-本周热门原创的句子
//     * 原创句子-推荐原创的句子
//     *
//     * @param view
//     */
//    public void onClick2(View view) {
//
//        String page = null;
//
//        // 原创句子-最新原创的句子 ju
//
//        // 原创句子-本周热门原创的句子  week
//
//        // 原创句子-推荐原创的句子 recommend
//
//        orignalPresenter.loadOriginal(this, "week", page);
//    }
//
//    /**
//     * 精选句集
//     *
//     * @param view
//     */
//    public void onClick3(View view) {
//
//        String page = null;
//
//        // 精选句集   "http://www.juzimi.com/albums?page=";
//        // 最新句集  http://www.juzimi.com/newalbums?page=";
//
//        albumsPresenter.loadAlbums(this, "albums", page);
//    }
//
//    /**
//     * 最新句集
//     *
//     * @param view
//     */
//    public void onClick4(View view) {
//
////        int page = 0;
////        final String url = JuziApi.NEWALBUMS + page;
////
////        JuziUtil juziUtil = new JuziUtil();
////        juziUtil.getJuziCollection(this, url);
//
//        String page = null;
//
//        albumsPresenter.loadAlbums(this, "newalbums", page);
//    }
//
//    /**
//     * 美图美句
//     *
//     * @param view
//     */
//    public void onClick5(View view) {
//
//        // 美图美句   "http://www.juzimi.com/meitumeiju?page=";
//
//        String page = null;
//        imgTextPresenter.loadImgText(this, page);
//    }
//
//    /**
//     * 美图美句
//     *
//     * @param view
//     */
//    public void onClick6(View view) {
//
//        // 手写美句   "http://www.juzimi.com/meitumeiju/shouxiemeiju?page=";
//        // 经典对白   "http://www.juzimi.com/meitumeiju/jingdianduibai?page=";
//
//        String page = null;
//        String type = "shouxiemeiju";
//        imgTextPresenter.loadImgText(this, type, page);
//    }
//
//}
