package com.chenyuan.sentence.util;

import com.chenyuan.sentence.mvp.model.bean.SceneListDetail;
import com.chenyuan.sentence.mvp.model.bean.SentenceCollection;
import com.chenyuan.sentence.mvp.model.bean.SentenceDetail;
import com.chenyuan.sentence.mvp.model.bean.SentenceImageText;
import com.chenyuan.sentence.mvp.model.bean.SentenceSimple;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 句子解析工具类
 */
public class DocParseUtil {

    /**
     * 解析名人名句
     *
     * @param result
     * @return
     */
    public static List<SentenceSimple> parseAllarticle(String result) {

        Document doc = Jsoup.parse(result);

        Elements rowElements = doc.getElementsByClass("views-row");

        List<SentenceSimple> sentenceSimples = new ArrayList<>();

        if (rowElements != null) {


            for (int i = 0; i < rowElements.size(); i++) {

                SentenceSimple sentenceSimple = new SentenceSimple();

                Element rowElement = rowElements.get(i);

                // 图片
                Elements views_field_tids = rowElement.getElementsByClass("views-field-tid");

                if (views_field_tids != null && views_field_tids.size() > 0) {
                    Element views_field_tid = views_field_tids.get(0);
                    if (views_field_tid != null && views_field_tid.select("img") != null && views_field_tid.select("img").size() > 0) {
                        String imgUrl = views_field_tid.select("img").get(0).attr("src");
                        sentenceSimple.setImgUrl(imgUrl);
                    }

                    if (views_field_tid != null && views_field_tid.select("a") != null && views_field_tid.select("a").size() > 0) {
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

//            System.out.println(sentenceSimples.size());

//            for (int i = 0; i < sentenceSimples.size(); i++) {
//                LogUtils.e(sentenceSimples.get(i));
//            }

        }

        return sentenceSimples;
    }


    /**
     * 原创句子
     *
     * @param result
     * @return
     */
    public static List<SentenceDetail> parseOrignal(String result) {

        Document doc = Jsoup.parse(result);

        Elements field_contents = doc.getElementsByClass("views-field-phpcode");

        List<SentenceDetail> sentenceDetails = new ArrayList<>();

        for (int i = 0; i < field_contents.size(); i++) {

            SentenceDetail sentenceDetail = new SentenceDetail();

            Element field_content = field_contents.get(i);
            if (field_content != null) {
                Elements xlistjus = field_content.getElementsByClass("xlistju");
                if (xlistjus != null && xlistjus.size() > 0) {

//                    String conent = xlistjus.get(0).text();
                    String conent = xlistjus.get(0).text().replaceAll(" ", "\n");

                    sentenceDetail.setContent(conent);

                    sentenceDetails.add(sentenceDetail);

//                    LogUtils.e(sentenceDetail);
                }
            }
        }

        return sentenceDetails;

    }

    /**
     * 句集
     *
     * @param result
     * @return
     */
    public static List<SentenceCollection> parseAlbums(String result) {

        Document doc = Jsoup.parse(result);

        List<SentenceCollection> sentenceCollections = new ArrayList<>();


        Elements views_field_sns_values = doc.getElementsByClass("views-field-phpcode");
        for (int i = 0; i < views_field_sns_values.size(); i++) {
            Element views_field_sns_value = views_field_sns_values.get(i);
            if (views_field_sns_value != null) {
                SentenceCollection sentenceCollection = new SentenceCollection();

                Elements views_field_picture_bares = views_field_sns_value.getElementsByClass("views-field-picture-bare");
                if (views_field_picture_bares != null && views_field_picture_bares.size() > 0) {
                    Element views_field_picture_bare = views_field_picture_bares.first();

                    Elements as = views_field_picture_bare.select("a");
                    if (as != null && as.size() > 0) {
                        Element a = as.first();
                        String url = "http://www.juzimi.com" + a.attr("href");

                        sentenceCollection.setDetailUrl(url);
                    }

                    Elements imgs = views_field_picture_bare.select("img");
                    if (imgs != null && imgs.size() > 0) {
                        Element img = imgs.first();
                        String src = img.attr("src");

                        sentenceCollection.setImgUrl(src);
                    }
                }

                Elements views_field_titles = views_field_sns_value.getElementsByClass("views-field-title");
                if (views_field_titles != null && views_field_titles.size() > 0) {
                    Element views_field_title = views_field_titles.first();

                    Elements as = views_field_title.select("a");
                    if (as != null && as.size() > 0) {
                        Element a = as.first();
                        String title = a.text();

                        sentenceCollection.setTitle(title);
                    }
                }

                Elements views_field_bodys = views_field_sns_value.getElementsByClass("views-field-body");
                if (views_field_bodys != null && views_field_bodys.size() > 0) {
                    Element views_field_title = views_field_bodys.first();
                    if (views_field_title != null) {
                        String desc = views_field_title.text();
                        sentenceCollection.setDesc(desc);
                    }
                }

                // 句集中的句子数
                Elements views_field_phpcodes = views_field_sns_value.getElementsByClass("views-field-phpcode-2");
                if (views_field_phpcodes != null && views_field_phpcodes.size() > 0) {
                    Element views_field_phpcode = views_field_phpcodes.get(0);
                    if (views_field_phpcode != null && views_field_phpcode.select("span") != null) {
                        String countStr = views_field_phpcode.select("span").text();

                        // 通过正则，取出其中的数字
                        String count = countStr.replaceAll("\\(收录", "").replaceAll("个句子\\)", "");

                        sentenceCollection.setCount(count);
                    }
                }

                // 创建时间
                Elements views_field_createds = views_field_sns_value.getElementsByClass("views-field-created");
                if (views_field_createds != null && views_field_createds.size() > 0) {
                    Element views_field_created = views_field_createds.get(0);
                    if (views_field_created != null && views_field_created.select("span") != null) {
                        String countStr = views_field_created.select("span").text();

                        // 通过正则，取出其中的数字
                        String createTime = countStr.replaceAll("于: ", "");

                        sentenceCollection.setCreateTime(createTime);
                    }
                }

                // 上传者
                Elements views_field_names = views_field_sns_value.getElementsByClass("views-field-name");
                if (views_field_names != null && views_field_names.size() > 0) {
                    Element views_field_name = views_field_names.get(0);
                    if (views_field_name != null && views_field_name.select("a") != null) {
                        String username = views_field_name.select("a").text();

                        sentenceCollection.setUsername(username);
                    }
                }

//                LogUtils.e(sentenceCollection);
                sentenceCollections.add(sentenceCollection);
            }
        }

        return sentenceCollections;
    }

    public static SceneListDetail parseMeiju(boolean isFirst, String result) {

        Document doc = Jsoup.parse(result);

        SceneListDetail sceneListDetail = new SceneListDetail();

        // 仅第一次记录页数
        if (isFirst) {
            Elements pageLasts = doc.getElementsByClass("pager-last");
            if (pageLasts != null && pageLasts.size() > 0) {
                String page = pageLasts.first().text();
                sceneListDetail.page = page;
            }
        }

        List<SentenceImageText> sentenceImageTexts = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();

        Elements views_field_phpcodes = doc.getElementsByClass("views-field-phpcode");
        for (int i = 0; i < views_field_phpcodes.size(); i++) {
            Element views_field_phpcode = views_field_phpcodes.get(i);
            if (views_field_phpcode != null) {

                Element bdshare = views_field_phpcode.getElementById("bdshare");
                if (bdshare != null) {

                    String data = bdshare.attr("data");
//                    LogUtils.e(data);

                    try {
                        JSONObject jsonObject = new JSONObject(data);

//                        LogUtils.e(jsonObject.get("text") + "  " + jsonObject.get("desc") + "  " + jsonObject.get("url") + "  " + jsonObject.get("pic"));

                        SentenceImageText sentenceImageText = new SentenceImageText();
                        sentenceImageText.setText("" + jsonObject.get("text"));
                        sentenceImageText.setDesc("" + jsonObject.get("desc"));
                        sentenceImageText.setUrl("" + jsonObject.get("url"));
                        sentenceImageText.setPic("" + jsonObject.get("pic"));

                        sentenceImageTexts.add(sentenceImageText);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        sceneListDetail.mImageTexts = sentenceImageTexts;
        return sceneListDetail;
    }


    /**
     * 句子的详情
     *
     * @param result
     * @return
     */
    public static SceneListDetail parseJuziDetail(boolean isFirst, String result) {

        SceneListDetail sceneListDetail = new SceneListDetail();

        Document doc = Jsoup.parse(result);

        // 仅第一次记录页数
        if (isFirst) {
            Elements pageItems = doc.getElementsByClass("pager-item");
            if (pageItems != null && pageItems.last() != null) {
                String page = pageItems.last().text();
                sceneListDetail.page = page;
            } else {
                sceneListDetail.page = null;
            }
        }

        Elements field_contents = doc.select("div.views-field-field-sns-value");

        List<SentenceImageText> sentenceImageTexts = new ArrayList<>();

        for (int i = 0; i < field_contents.size(); i++) {

            Element field_content = field_contents.get(i);
            if (field_content != null) {
                Elements bdshares = field_content.select("div#bdshare");
                if (bdshares != null && bdshares.size() > 0) {
                    String data = bdshares.first().attr("data");
                    try {
                        JSONObject jsonObject = new JSONObject(data);

                        SentenceImageText sentenceImageText = new SentenceImageText();
                        sentenceImageText.setText("" + jsonObject.get("text"));
                        sentenceImageText.setDesc("" + jsonObject.get("desc"));
                        sentenceImageText.setUrl("" + jsonObject.get("url"));
                        sentenceImageText.setPic("" + jsonObject.get("pic"));

                        sentenceImageTexts.add(sentenceImageText);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        sceneListDetail.mImageTexts = sentenceImageTexts;

        return sceneListDetail;

    }
}
