package com.otb.designerassist.util;

import com.apkfuns.logutils.LogUtils;
import com.otb.designerassist.mvp.model.entity.SentenceDetail;
import com.otb.designerassist.mvp.model.entity.SentenceSimple;

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

            System.out.println(sentenceSimples.size());

            for (int i = 0; i < sentenceSimples.size(); i++) {
                LogUtils.e(sentenceSimples.get(i));
            }

        }

        return sentenceSimples;
    }


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

                    LogUtils.e(sentenceDetail);
                }
            }
        }

        return sentenceDetails;

    }
}
