package com.chenyuan.sentence.mvp.model.bean;

/**
 * 句子合集-列表
 */
public class SentenceDetail {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SentenceDetail{" +
                "content='" + content + '\'' +
                '}';
    }
}
