package com.otb.designerassist.util;

import android.content.Context;
import android.content.Intent;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.otb.designerassist.mvp.model.entity.SentenceCollection;
import com.otb.designerassist.mvp.model.entity.SentenceDetail;
import com.otb.designerassist.mvp.model.entity.SentenceImageText;
import com.otb.designerassist.mvp.model.entity.SentenceSimple;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 句子工具类
 */
public class JuziUtil {

    /**
     * 名人名句
     *
     * @param url
     */
    public void getMemorableQuotes(final Context mContext, final String url) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println(url);

                try {

                    Document doc = Jsoup.connect(url).timeout(10000).get();

                    Elements rowElements = doc.getElementsByClass("views-row");

                    if (rowElements != null) {

                        List<SentenceSimple> sentenceSimples = new ArrayList<>();

                        for (int i = 0; i < rowElements.size(); i++) {

                            SentenceSimple sentenceSimple = new SentenceSimple();

                            Element rowElement = rowElements.get(i);

                            // 图片
                            Elements views_field_tids = rowElement.getElementsByClass("views-field-tid");

                            if (views_field_tids != null && views_field_tids.size() > 0) {
                                Element views_field_tid = views_field_tids.get(0);
                                if (views_field_tid != null && views_field_tid.select("img") != null) {
                                    String imgUrl = views_field_tid.select("img").get(0).attr("src");
                                    sentenceSimple.setImgUrl(imgUrl);
                                }

                                if (views_field_tid != null && views_field_tid.select("a") != null) {
                                    String detailUrl = "http://www.juzimi.com" + views_field_tid.select("a").get(0).attr("href");
                                    sentenceSimple.setDetailUrl(detailUrl);
                                }
                            }

                            Elements views_field_phpcodes = rowElement.getElementsByClass("views-field-phpcode");
                            Element views_field_phpcode = views_field_phpcodes.get(0);

                            // 标题
                            Elements xqallarticletilelinkspans = views_field_phpcode.getElementsByClass("xqallarticletilelinkspan");
                            if (xqallarticletilelinkspans != null && xqallarticletilelinkspans.size() > 0) {
                                Element xqallarticletilelinkspan = xqallarticletilelinkspans.get(0);

                                if (xqallarticletilelinkspan != null) {
                                    String title = xqallarticletilelinkspan.text();
                                    sentenceSimple.setTitle(title);
                                }
                            }

                            // 内容
                            Elements xqagepawirdescs = views_field_phpcode.getElementsByClass("xqagepawirdesc");
                            if (xqagepawirdescs != null && xqagepawirdescs.size() > 0) {
                                Element xqagepawirdesc = xqagepawirdescs.get(0);
                                if (xqagepawirdesc != null) {
//                                    String content = xqagepawirdesc.text().replaceAll(" ", "\n");
                                    String content = xqagepawirdesc.text();
                                    sentenceSimple.setContent(content);
                                }
                            }

                            Elements xqagepawirdesclinks = views_field_phpcode.getElementsByClass("xqagepawirdesclink");
                            if (xqagepawirdesclinks != null && xqagepawirdesclinks.size() > 0) {
                                Element xqagepawirdesclink = xqagepawirdesclinks.get(0);
                                if (xqagepawirdesclink != null) {
                                    // 来源、个数
                                    String source_num = xqagepawirdesclink.text();
                                    sentenceSimple.setSource_num(source_num);
                                }
                            }

                            sentenceSimples.add(sentenceSimple);
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 句子合集-列表
     * 原创句子
     *
     * @param url
     */
    public void getAllarticleCollectList(final Context mContext, final String url) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println(url);

                try {

                    Document doc = Jsoup.connect(url).timeout(10000).get();
//                    System.out.println(doc);

                    Elements field_contents = doc.getElementsByClass("field-content");

                    List<SentenceDetail> sentenceDetails = new ArrayList<>();

                    for (int i = 0; i < field_contents.size(); i++) {

                        SentenceDetail sentenceDetail = new SentenceDetail();

                        Element field_content = field_contents.get(i);
                        if (field_content != null) {
                            Elements xlistjus = field_content.getElementsByClass("xlistju");
                            if (xlistjus != null && xlistjus.size() > 0) {

                                String conent = xlistjus.get(0).text();
//                                String conent = xlistjus.get(0).text().replaceAll(" ", "\n");

                                sentenceDetail.setContent(conent);

                                sentenceDetails.add(sentenceDetail);

                                LogUtils.e(sentenceDetail);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 精选句集-列表
     * 最新句集-列表
     *
     * @param url
     */
    public void getJuziCollection(final Context mContext, final String url) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println(url);

                try {

                    Document doc = Jsoup.connect(url).timeout(10000).get();
//                    System.out.println(doc);

                    List<SentenceCollection> sentenceCollections = new ArrayList<>();

                    Elements field_contents = doc.getElementsByClass("field-content");

                    for (int i = 0; i < field_contents.size(); i++) {
                        Element field_content = field_contents.get(i);
                        if (field_content != null) {

                            SentenceCollection sentenceCollection = new SentenceCollection();

                            // 图片、链接
                            Elements views_field_picture_bares = field_content.getElementsByClass("views-field-picture-bare");
                            if (views_field_picture_bares != null && views_field_picture_bares.size() > 0) {
                                Element views_field_picture_bare = views_field_picture_bares.get(0);
                                if (views_field_picture_bare != null) {
                                    Elements aEles = views_field_picture_bare.select("a");

                                    if (aEles != null && aEles.size() > 0 && aEles.get(0) != null) {
                                        String detailUrl = "http://www.juzimi.com/" + aEles.get(0).attr("href");

                                        sentenceCollection.setDetailUrl(detailUrl);
                                    }

                                    Elements imgEles = views_field_picture_bare.select("img");
                                    if (imgEles != null && imgEles.size() > 0 && imgEles.get(0) != null) {
                                        String imgUrl = imgEles.get(0).attr("src");

                                        sentenceCollection.setImgUrl(imgUrl);
                                    }
                                }
                            }

                            // title
                            Elements views_field_titles = field_content.getElementsByClass("views-field-title");
                            if (views_field_titles != null && views_field_titles.size() > 0) {
                                Element views_field_title = views_field_titles.get(0);
                                if (views_field_title != null && views_field_title.select("a") != null) {
                                    String title = views_field_title.select("a").text();

                                    sentenceCollection.setTitle(title);
                                }
                            }

                            Elements views_field_bodys = field_content.getElementsByClass("views-field-body");
                            if (views_field_bodys != null && views_field_bodys.size() > 0) {
                                Element views_field_body = views_field_bodys.get(0);
                                if (views_field_body != null) {
                                    String desc = views_field_body.text();
                                    sentenceCollection.setDesc(desc);
                                }
                            }

                            // 句集中的句子数
                            Elements views_field_phpcodes = field_content.getElementsByClass("views-field-phpcode-2");
                            if (views_field_phpcodes != null && views_field_phpcodes.size() > 0) {
                                Element views_field_phpcode = views_field_phpcodes.get(0);
                                if (views_field_phpcode != null && views_field_phpcode.select("span") != null) {
                                    String countStr = views_field_phpcode.select("span").text();

                                    // 通过正则，取出其中的数字
                                    String count = countStr.replaceAll("\\(收录", "").replaceAll("个句子\\)", "");

                                    sentenceCollection.setCount(count);
                                }
                            }


                            // 上传者
                            Elements views_field_names = field_content.getElementsByClass("views-field-name");
                            if (views_field_names != null && views_field_names.size() > 0) {
                                Element views_field_name = views_field_names.get(0);
                                if (views_field_name != null && views_field_name.select("a") != null) {
                                    String username = views_field_name.select("a").text();

                                    sentenceCollection.setUsername(username);
                                }
                            }

                            LogUtils.e(sentenceCollection);

                            sentenceCollections.add(sentenceCollection);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * 美图美句、手写美句、经典对白
     *
     * @param url
     */
    public void getSentenceImgText(final Context mContext, final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println(url);

                try {

                    Document doc = Jsoup.connect(url).timeout(10000).get();

//                    System.out.println(doc);

                    List<SentenceImageText> sentenceImageTexts = new ArrayList<>();

                    Gson gson = new Gson();
                    Elements views_field_sns_values = doc.getElementsByClass("alljmojusharecon");
                    for (int i = 0; i < views_field_sns_values.size(); i++) {
                        Element views_field_sns_value = views_field_sns_values.get(i);
                        if (views_field_sns_value != null) {
                            Element bdshare = views_field_sns_value.getElementById("bdshare");
                            if (bdshare != null) {

                                String data = bdshare.attr("data");

                                if (data != null) {
                                    SentenceImageText sentenceImageText = gson.fromJson(data, SentenceImageText.class);

                                    if (sentenceImageText != null) {
                                        sentenceImageTexts.add(sentenceImageText);
                                    }
                                    LogUtils.e(sentenceImageText);
                                }
                            }
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
