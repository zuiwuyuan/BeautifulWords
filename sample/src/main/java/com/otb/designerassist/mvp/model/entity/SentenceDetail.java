package com.otb.designerassist.mvp.model.entity;

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
