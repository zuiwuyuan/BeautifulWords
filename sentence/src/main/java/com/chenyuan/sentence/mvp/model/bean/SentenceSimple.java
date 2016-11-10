package com.chenyuan.sentence.mvp.model.bean;

/**
 * 句子列表
 */
public class SentenceSimple{

    private String title;
    private String content;
    private String imgUrl;
    private String detailUrl;
    private String source_num;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSource_num() {
        return source_num;
    }

    public void setSource_num(String source_num) {
        this.source_num = source_num;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

}
